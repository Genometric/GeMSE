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
package GeMSE;

import GeMSE.GS.SampleData;
import GeMSE.GS.GenometricSpace;
import GeMSE.OperationsOptions.ClusteringOptions;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Vahid Jalili
 */
public class GlobalVariables
{
    public static GenometricSpace space;

    public static ArrayList<SampleData> samples = new ArrayList<>();
    public static SampleData annotations = new SampleData();
    public static HashMap<String, Integer> featuresCount = new HashMap<>();

    public static String[] availableColumnLabelOptions;
    public static String selectedColumnLabelOption;

    public static String[] availableRowLabelOptions;
    public static String selectedRowLabelOption;
    public static int columnIndexToReadRowsInfoFrom;

    public static Color lowValueColor = Color.YELLOW;
    public static Color hightValueColor = Color.BLUE;

    public static Boolean disablePopups;


    private static String _lastBrowsedDirectory;
    public static String GetLastBrowsedDirectory()
    {
        return _lastBrowsedDirectory;
    }
    public static void SetLastBrowsedDirectoryFromFile(String absolutePath)
    {
        _lastBrowsedDirectory = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
    }
    public static void SetLastBrowsedDirectoryFromPath(String path)
    {
        /*
        This function is called when in a file chooser,
        a folder chosen (as opposed to when a file is chosen 
        which is handled by SetLastBrowsedDirectoryFromFile).
        We could define two behaviors for last browsed folder 
        for this scenario: 
        
        a. The chosen folder is the last browsed folder (e.g., 
        in a path A/B/C where folder C is chosen, we could set 
        A/B/C as last browsed folder. 
        
        b. The path in which the selected item (folder or file) 
        is located is the last browsed folder. So, applying same 
        behavior for both choosing a folder and file. 
        
        I found option b. more intutive and practical. A simpler
        implementation would be having only one function
        SetLastBrowsedDirectory. However, to enable both options 
        I created two functions. If you want to use a., then
        simply uncomment options a. and comment out b.
        */
        
        // a. 
        //_lastBrowsedDirectory = path;
        
        // b. 
        _lastBrowsedDirectory = path.substring(0, path.lastIndexOf(File.separator));
    }

    public static ArrayList<String> AllNumAttributes()
    {
        ArrayList<String> allAtt = new ArrayList<>();
        for (int i = 0 ; i < GlobalVariables.samples.size() ; i++)
        {
            ArrayList<String> dA = GlobalVariables.samples.get(i).GetNumAttributesArray();
            for (int j = 0 ; j < dA.size() ; j++)
                if (allAtt.contains(dA.get(j)) == false)
                    allAtt.add(dA.get(j));
        }
        return allAtt;
    }
    public static ArrayList<String> AllTXTAttributes()
    {
        ArrayList<String> allAtt = new ArrayList<>();
        for (int i = 0 ; i < GlobalVariables.samples.size() ; i++)
        {
            ArrayList<String> dA = GlobalVariables.samples.get(i).GetTXTAttributesArray();
            for (int j = 0 ; j < dA.size() ; j++)
                if (allAtt.contains(dA.get(j)) == false)
                    allAtt.add(dA.get(j));
        }
        return allAtt;
    }
    public static ArrayList<String> AllFeatures()
    {
        ArrayList<String> allFea = new ArrayList<>();
        for (int i = 0 ; i < GlobalVariables.samples.size() ; i++)
        {
            ArrayList<String[]> dA = GlobalVariables.samples.get(i).determinedFeatures;
            for (int j = 0 ; j < dA.size() ; j++)
                if (allFea.contains(dA.get(j)[0]) == false)
                    allFea.add(dA.get(j)[0]);
        }

        allFea.add("ALL");
        return allFea;
    }

    public enum RLS
    {
        ID, SampleAttributes, ReferenceAttributes
    }
    public static RLS rowLabelsSource = RLS.ID;

    public static String rowLabelsSourceSelectedSample;
    public static String rowLabelsSourceSelectedAttribute;

    public static Boolean plotElbowMethodOutput;

    public static class HeatmapOptions
    {
        /**
         * If false, use integrated heatmap package. If true, use R to generate
         * heatmap.
         */
        public static boolean heatmpaType = false;

        /**
         * This command will be used to generate heatmaps using R. The
         * dendrogram option is provided by heatmapDendrogramCommand. That
         * command needs to be concatenated with this and a closing parentheses.
         */
        public static String heatmapCommand = "heatmap.2(space";

        /**
         * If set, the cluster shall be exported in Newick format to be used in
         * R for proper dendrogram creation for heatmap.
         */
        public static ClusteringOptions heatmapParameters = null;

        public static String heatmapTitle = "";

        public static String horizontalAxisTitle = "Samples";

        public static String verticalAxisTitle = "Regions";

        public static Dimension size;

        public static String RScriptPath = "C:\\Program Files\\R\\R-3.2.0\\bin\\Rscript.exe";

        public static Boolean autoFitRPlotSize = true;

        /**
         * R heatmap basic/advanced options to be used. If set true, basic
         * options will be used; and if set false, advanced options will be
         * used.
         */
        public static Boolean RBAOptions = true;

        /**
         * Is a very small number to be added to estimated distances between
         * clusters when exporting the clusters in Fenwick format to be used in
         * R for heatmapping. This number will solve the issues of many clusters
         * with 0 distance from each other raised in R. This number will not
         * modify the estimated distances and hence will not effect cutting the
         * tree or any further manipulations.
         */
        public static double epsilonDistance = 0.0001;
    }
}
