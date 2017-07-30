/** GenoMetric Space Explorer (GeMSE) Copyright (C) 2017 Vahid Jalili
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software Foundation,
 *  Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */
package GeMSE.IO;

import GeMSE.GS.SampleData;
import GeMSE.GlobalVariables;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Vahid Jalili
 */
public class Loader
{
    private boolean _samplesAreHeterogeneous = false;

    private final String _tGTFDescription = "General Feature Format (GTF)";
    private final String _tCSVDescription = "General Tab-delimited (CSV)";
    private final String _tBEDDescription = "Browser Extensible Data (BED)";
    private final String _narrowPeakDescription = "ENCODE narrowPeak";
    private final String _broadPeakDescription = "ENCODE broadPeak";
    private JFrame _parentFrame;

    private final ArrayList<FileNameExtensionFilter> _fileNameExtensionFilters;

    public Loader(JFrame parentFrame)
    {
        _parentFrame = parentFrame;
        _samplesAreHeterogeneous = false;
        _fileNameExtensionFilters = new ArrayList<>();
        _fileNameExtensionFilters.add(new FileNameExtensionFilter(_tGTFDescription, "GTF"));
        _fileNameExtensionFilters.add(new FileNameExtensionFilter(_tCSVDescription, "CSV", "TXT"));
        _fileNameExtensionFilters.add(new FileNameExtensionFilter(_tBEDDescription, "BED"));
        _fileNameExtensionFilters.add(new FileNameExtensionFilter(_narrowPeakDescription, "narrowPeak"));
        _fileNameExtensionFilters.add(new FileNameExtensionFilter(_broadPeakDescription, "broadPeak"));
        _fileNameExtensionFilters.sort(new FileExtensionComparer());
    }

    public enum LoadType
    {
        File, Directory
    };

    public boolean Load(LoadType loadType)
    {
        HashMap<String, Integer> previouslyLoadedSamples = new HashMap<>();
        for (SampleData sample : GeMSE.GlobalVariables.samples)
            previouslyLoadedSamples.put(sample.sampleID, -1);

        JFileChooser chooser = new JFileChooser(GlobalVariables.GetLastBrowsedDirectory());
        chooser.setAcceptAllFileFilterUsed(false);
        for (FileNameExtensionFilter filter : _fileNameExtensionFilters)
            chooser.addChoosableFileFilter(filter);

        if (loadType == LoadType.Directory)
        {
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setMultiSelectionEnabled((boolean) false);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                GlobalVariables.SetLastBrowsedDirectoryFromPath(chooser.getSelectedFile().getPath());
                if (GetData(FilesInDirectory.Get(
                        new File(chooser.getSelectedFile().getPath()),
                        chooser.getFileFilter()),
                            chooser.getFileFilter().getDescription()))
                    return true;
            }
        }
        else
        {
            chooser.setMultiSelectionEnabled((boolean) true);
            for (FileNameExtensionFilter filter : _fileNameExtensionFilters)
                chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                GlobalVariables.SetLastBrowsedDirectoryFromFile(chooser.getSelectedFile().getPath());
                if (GetData(chooser.getSelectedFiles(), chooser.getFileFilter().getDescription()))
                    return true;
            }
        }

        Iterator<SampleData> iterator = GeMSE.GlobalVariables.samples.iterator();
        while (iterator.hasNext())
        {
            SampleData sample = iterator.next();
            if (previouslyLoadedSamples.containsKey(sample.sampleID) == false)
                iterator.remove();
        }

        return false;
    }

    private boolean GetData(File[] source, String fileFilterDescription)
    {
        if (source.length == 0)
        {
            JOptionPane.showMessageDialog(
                    null,
                    "The specified path does not contain any file of selected extension.\n"
                    + "Please retry having verified the path.",
                    "No file selected!", 2);
            return false;
        }

        switch (fileFilterDescription)
        {
            case _tGTFDescription:
                if (!LoadGTFFiles(source)) return false;
                break;

            case _tCSVDescription:
            case _tBEDDescription:
            case _narrowPeakDescription:
            case _broadPeakDescription:
                if (!LoadCSVFile(source)) return false;
                break;
        }

        if (_samplesAreHeterogeneous)
        {
            int dialogResult
                = JOptionPane.showConfirmDialog(
                            null,
                            "The files you have selected differ with each other (and/or with previously loaded samples) in the number of parsed features.\n"
                            + "This happens when you provide heterogeneous input data.\n"
                            + "GeMSE can help loading such datasets, by mapping the features on a reference sample (e.g., annotations).\n\n"
                            + "Do you want to provide a reference sample to fix the issue?\n"
                            + "You may choose to cancel loading these samples.",
                            "Heterogeneous data!", 2);
            if (dialogResult == JOptionPane.YES_OPTION)
            {
                if (!CreatehomogeneousSamples()) return false;
            }
            else
            {
                return false;
            }
        }

        LoadResultsDialog loadResultsDialog = new LoadResultsDialog(null, true);
        loadResultsDialog.setLocationRelativeTo(null);
        Boolean showResult = loadResultsDialog.ShowDialog(source, fileFilterDescription);
        if (showResult == false)
        {
            GeMSE.GlobalVariables.samples.clear();
            return false;
        }

        UpdateDeterminedFeaturesCount();
        return true;
    }

    private boolean LoadGTFFiles(File[] source)
    {
        for (File file : source)
        {
            GTFParser gtfParser = new GTFParser(
                    file.getAbsolutePath(),
                    true, // should find at least one numberical attribute-value pair
                    (byte) 0, // start offset
                    (byte) 0, // chromosome column
                    (byte) 3, // start column
                    (byte) 4, // stop column
                    (byte) 2, // feature column
                    (byte) 8, // attribute column
                    true);    // add "chr" prefix if missing

            SampleData gtfFile = gtfParser.Parse();
            MetaDataParser metaParser = new MetaDataParser(file.getAbsolutePath() + ".meta");
            gtfFile.metaData = metaParser.Parse();
            GeMSE.GlobalVariables.samples.add(gtfFile);

            if (GeMSE.GlobalVariables.samples.size() > 0)
                if (GlobalVariables.samples.get(0).featuresCount != gtfFile.featuresCount
                    || !HaveSameNumberOfFeatures(GlobalVariables.samples.get(0).determinedFeatures, gtfFile.determinedFeatures))
                    _samplesAreHeterogeneous = true;
        }

        return true;
    }

    private boolean LoadCSVFile(File[] source)
    {
        CSVSetup csvSetup = new CSVSetup(null, true, source[0]);
        csvSetup.setLocationRelativeTo(null);
        CSVOptions genericColumn = csvSetup.ShowDialog();
        if (genericColumn == null) return false;

        InProgress inProgress = new InProgress(_parentFrame, "Loading the selected files, please wait ...");
        inProgress.setLocationRelativeTo(_parentFrame);
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
        {
            @Override
            protected Void doInBackground()
            {
                for (File file : source)
                {
                    CSVParser csvParser = new CSVParser(file.getAbsolutePath(), genericColumn);
                    SampleData csvFile = csvParser.Parse();
                    MetaDataParser metaParser = new MetaDataParser(file.getAbsolutePath() + ".meta");
                    csvFile.metaData = metaParser.Parse();
                    GeMSE.GlobalVariables.samples.add(csvFile);

                    if (GeMSE.GlobalVariables.samples.size() > 0)
                        if (GlobalVariables.samples.get(0).featuresCount != csvFile.featuresCount
                            || !HaveSameNumberOfFeatures(GlobalVariables.samples.get(0).determinedFeatures, csvFile.determinedFeatures))
                            _samplesAreHeterogeneous = true;
                }
                inProgress.dispose();
                return null;
            }
        };
        worker.execute();
        inProgress.setVisible(true);
        return true;
    }

    private Boolean CreatehomogeneousSamples()
    {
        LoadHetroSamplesDialog hetroSLDialog = new LoadHetroSamplesDialog(null, true);
        hetroSLDialog.setLocationRelativeTo(null);
        hetroSLDialog.setVisible(true);
        return hetroSLDialog.GetResult();
    }

    private Boolean HaveSameNumberOfFeatures(ArrayList<String[]> setA, ArrayList<String[]> setB)
    {
        for (String[] itemA : setA)
            for (String[] itemB : setB)
                if (itemA[0].equals(itemB[0]))
                    if (!itemA[1].equals(itemB[1]))
                        return false;
                    else
                        break;

        return true;
    }

    private void UpdateDeterminedFeaturesCount()
    {
        GlobalVariables.featuresCount.clear();
        for (String[] dFeature : GlobalVariables.samples.get(0).determinedFeatures)
            GlobalVariables.featuresCount.put(dFeature[0], Integer.parseInt(dFeature[1]));
    }
}
