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
package GeMSE.Heatmap;

import GeMSE.GS.Space;
import GeMSE.OperationsOptions.ClusteringOptions;
import java.awt.Dimension;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import ExternalLibraries.HeatChart;
import GeMSE.IO.ClusterToNewick;
import GeMSE.IO.Exporter;
import GeMSE.GeMSE;
import GeMSE.GlobalVariables;
import java.awt.Color;

/**
 *
 * @author Vahid Jalili
 */
public class HeatMap
{
    private static HeatChart _heatChart;
    private static Space _space;

    public static void InitializeChart(Space space)
    {
        if (GlobalVariables.HeatmapOptions.heatmpaType == false)
        {
            _heatChart = new HeatChart(space.content);
            _heatChart.setXValues(space.colTitle);
            _heatChart.setYValues(space.rowTitle);

            if (!GlobalVariables.HeatmapOptions.heatmapTitle.isEmpty())
                _heatChart.setTitle(GlobalVariables.HeatmapOptions.heatmapTitle);
            _heatChart.setXAxisLabel(GlobalVariables.HeatmapOptions.horizontalAxisTitle);
            _heatChart.setYAxisLabel(GlobalVariables.HeatmapOptions.verticalAxisTitle);

            _heatChart.setHighValueColour(GlobalVariables.hightValueColor);
            _heatChart.setLowValueColour(GlobalVariables.lowValueColor);
        }
        else
        {
            _space = space;
        }
    }

    public static int GetChartMargin()
    {
        if (GlobalVariables.HeatmapOptions.heatmpaType == false)
            return _heatChart.getChartMargin();
        else
            return 0;
    }

    public static Image GetHeatMap(Dimension dimension) throws IOException
    {
        Image image = null;

        // If TRUE  : use R
        // If FALSE : use Integrated Heatmap package.
        if (GlobalVariables.HeatmapOptions.heatmpaType == false)
        {
            float[] bColor = new float[3];
            Color.RGBtoHSB(214, 217, 223, bColor);

            _heatChart.setCellSize(dimension);
            _heatChart.setBackgroundColour(Color.getHSBColor(bColor[0], bColor[1], bColor[2]));
            image = _heatChart.getChartImage(false);
        }
        else
        {
            image = RHeatmap();
        }

        return image;
    }

    private static Image RHeatmap() throws IOException
    {
        Image image = null;
        String workingPath = new File(".").getCanonicalPath() + File.separator;
        String spacePath = (workingPath + "space.csv").replace(File.separator, File.separator + File.separator);
        String clusterFilePath = (workingPath + "cluster.newick").replace(File.separator, File.separator + File.separator);
        String heatmapPath = (workingPath + "heatmap.png").replace(File.separator, File.separator + File.separator);
        String scriptPath = workingPath + "script.R";

        String RscriptPath = GlobalVariables.HeatmapOptions.RScriptPath;
        String heatmapCommand = GlobalVariables.HeatmapOptions.heatmapCommand;

        ExportSpace2TempFile(spacePath);

        if (GlobalVariables.HeatmapOptions.heatmapParameters != null)
        {
            // heatmapDendrogramCommand shall be updated cos it is not addressing for different clustering domains
            if (GlobalVariables.HeatmapOptions.heatmapParameters.domain != ClusteringOptions.ClusteringDomains.BiClustering)
                ExportCluster(clusterFilePath);
            GenerateRScript(spacePath, scriptPath, heatmapPath, clusterFilePath, heatmapCommand, true);
        }
        else
        {
            GenerateRScript(spacePath, scriptPath, heatmapPath, clusterFilePath, heatmapCommand, false);
        }

        try
        {
            if (scriptPath.contains(" "))
            {
                if (System.getProperty("os.name").toLowerCase().contains("windows"))
                    scriptPath = "\"" + scriptPath + "\"";
                else
                    scriptPath = scriptPath.replace(" ", "\\ ");
            }

            Process process = Runtime.getRuntime().exec(RscriptPath + " " + scriptPath);
            process.waitFor();

            image = ImageIO.read(new File(heatmapPath));
        }
        catch (IOException ex)
        {
            Logger.getLogger(GeMSE.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(HeatMap.class.getName()).log(Level.SEVERE, null, ex);
        }

        new File(spacePath).delete();
        new File(scriptPath).delete();
        new File(heatmapPath).delete();
        new File(clusterFilePath).delete();

        return image;
    }

    private static void ExportSpace2TempFile(String spaceFilePath)
    {
        try
        {
            File file = new File(spaceFilePath);

            file.createNewFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
            {
                int colLenght = _space.colsID.length;
                String line = "";
                for (int cH = 0 ; cH < colLenght ; cH++)
                {
                    line += "," + _space.colTitle[cH];
                }
                writer.write(line);

                for (int r = 0 ; r < _space.content.length ; r++)
                {
                    line = _space.rowTitle[r];

                    for (int c = 0 ; c < colLenght ; c++)
                    {
                        line += "," + String.valueOf(_space.content[r][c]);
                    }

                    writer.newLine();
                    writer.write(line);
                }

                writer.close();
            }
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void GenerateRScript(
            String spaceFilePath,
            String scriptFilePath,
            String heatmapFilePath,
            String clusterFilePath,
            String heatmapCommand,
            boolean includeDendrogram)
    {
        String dendrogramCommand = "";
        try
        {
            File file = new File(scriptFilePath);

            file.createNewFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
            {
                // Load library used for plotting.
                writer.write("library(gplots)");

                if (includeDendrogram)
                {
                    writer.newLine();
                    // Load library used for reading and casting clustering.
                    writer.write("library(ape)");
                }

                writer.newLine();
                // read in the CSV file as matrix
                writer.write("space = as.matrix(read.csv(\"" + spaceFilePath + "\", sep = \",\", row.names = 1))");

                if (includeDendrogram)
                {
                    switch (GlobalVariables.HeatmapOptions.heatmapParameters.domain)
                    {
                        case Rows:
                            writer.newLine();
                            writer.write("tree = read.tree(\"" + clusterFilePath + "\")");
                            writer.newLine();
                            writer.write("tClstr = as.hclust(tree)");
                            writer.newLine();
                            writer.write("rowDen = as.dendrogram(tClstr)");
                            dendrogramCommand = ", Rowv = rowDen, colv = FALSE, dendrogram = \"row\"";
                            break;

                        case Columns:
                            writer.newLine();
                            writer.write("tree = read.tree(\"" + clusterFilePath + "\")");
                            writer.newLine();
                            writer.write("tClstr = as.hclust(tree)");
                            writer.newLine();
                            writer.write("colDen = as.dendrogram(tClstr)");
                            dendrogramCommand = ", Colv = colDen, rowv = FALSE, dendrogram = \"col\"";
                            break;

                        case BiClustering:
                            dendrogramCommand = ", dendrogram = \"both\"";
                            break;
                    }
                }
                else
                {
                    dendrogramCommand = ", dendrogram = \"none\", Rowv = FALSE , Colv = FALSE";
                }

                writer.newLine();
                // creating a device in order to directly save the plot to a png file
                writer.write("png(filename = \""
                             + heatmapFilePath + "\""
                             + ", width = " + String.valueOf(GlobalVariables.HeatmapOptions.size.width)
                             + ", height = " + String.valueOf(GlobalVariables.HeatmapOptions.size.height)
                             + ", units = \"px\""
                             + ", bg = \"transparent\"" + ")");

                writer.newLine();
                // generates heatmap
                writer.write(heatmapCommand + dendrogramCommand + ")");

                writer.newLine();
                // Turn-off the device used to directly saving plot to a file.
                // The file will be generated after calling this command.
                writer.write("dev.off()");
            }

        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void ExportCluster(String clusterFilePath)
    {
        try
        {
            File file = new File(clusterFilePath);

            file.createNewFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
            {
                writer.write(ClusterToNewick.Convert());
            }
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
