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
import GeMSE.Visualization.Graph.GraphType;
import GeMSE.Visualization.Graph.NodeGrouping;
import GeMSE.GS.Transitions.Options.ClusteringOptions;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import prefuse.util.ColorLib;

/**
 *
 * @author Vahid Jalili
 */
public class GlobalVariables implements Serializable
{
    public static final long serialVersionUID = 2L;
    public static GenometricSpace space;
    public static String selectedNodeID;

    public static ArrayList<SampleData> samples = new ArrayList<>();
    public static SampleData annotations = new SampleData();
    public static HashMap<String, Integer> featuresCount = new HashMap<>();

    public static String[] availableColumnLabelOptions;
    public static String selectedColumnLabelOption;

    public static String[] availableRowLabelOptions;
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
    public static String rowLabelsSourceSelectedSample = "";
    public static String rowLabelsSourceSelectedAttribute = "";

    public static Boolean plotElbowMethodOutput;
    public static Boolean sessionSerializationRequired = false;

    private void writeObject(ObjectOutputStream stream) throws IOException
    {
        stream.writeObject(space);
        stream.writeObject(samples);
        stream.writeObject(annotations);
        stream.writeObject(featuresCount);
        stream.writeObject(availableColumnLabelOptions);
        stream.writeObject(selectedColumnLabelOption);
        stream.writeObject(availableRowLabelOptions);
        stream.writeInt(columnIndexToReadRowsInfoFrom);
        stream.writeObject(lowValueColor);
        stream.writeObject(hightValueColor);
        stream.writeBoolean(disablePopups);
        stream.writeObject(_lastBrowsedDirectory);
        stream.writeObject(rowLabelsSource);
        stream.writeObject(rowLabelsSourceSelectedSample);
        stream.writeObject(rowLabelsSourceSelectedAttribute);
        stream.writeBoolean(plotElbowMethodOutput);
        stream.writeBoolean(HeatmapOptions.heatmpaType);
        stream.writeObject(HeatmapOptions.heatmapCommand);
        stream.writeObject(HeatmapOptions.heatmapParameters);
        stream.writeObject(HeatmapOptions.heatmapTitle);
        stream.writeObject(HeatmapOptions.horizontalAxisTitle);
        stream.writeObject(HeatmapOptions.verticalAxisTitle);
        stream.writeObject(HeatmapOptions.size);
        stream.writeObject(HeatmapOptions.RScriptPath);
        stream.writeBoolean(HeatmapOptions.autoFitRPlotSize);
        stream.writeBoolean(HeatmapOptions.RBAOptions);
        stream.writeDouble(HeatmapOptions.epsilonDistance);
        stream.writeObject(GraphOptions.graphType);
        stream.writeObject(GraphOptions.grouping);
        stream.writeObject(GraphOptions.theme);
        stream.writeObject(GraphOptions.useSolidAggColor);
        stream.writeObject(GraphOptions.beforeCutColor);
        stream.writeObject(GraphOptions.afterCutColor);
        stream.writeObject(GraphOptions.solidColor);
        stream.writeObject(GraphOptions.enforceAngularBound);
        stream.writeInt(GraphOptions.angularBoundDegree);
        stream.writeInt(GraphOptions.angularBoundRadius);
        stream.writeObject(GraphOptions.background);
        stream.writeObject(GraphOptions.foreground);
        stream.writeInt(GraphOptions.edgeColor);
        stream.writeInt(GraphOptions.textColor);
        stream.writeInt(GraphOptions.nodeColor);
        stream.writeInt(GraphOptions.textColorHover);
        stream.writeInt(GraphOptions.nodeColorHover);
        stream.writeInt(GraphOptions.nodeColorSelected);
        stream.writeInt(GraphOptions.nodeColorSearchResult);
        stream.writeInt(GraphOptions.nodeAggHoverStroke);
        stream.writeInt(GraphOptions.nodeAggDefaultStroke);
        stream.writeInt(GraphOptions.aggHoverStroke);
        stream.writeInt(GraphOptions.aggDefaultStroke);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException
    {
        space = (GenometricSpace) stream.readObject();
        samples = (ArrayList<SampleData>) stream.readObject();
        annotations = (SampleData) stream.readObject();
        featuresCount = (HashMap<String, Integer>) stream.readObject();
        availableColumnLabelOptions = (String[]) stream.readObject();
        selectedColumnLabelOption = (String) stream.readObject();
        availableRowLabelOptions = (String[]) stream.readObject();
        columnIndexToReadRowsInfoFrom = stream.readInt();
        lowValueColor = (Color) stream.readObject();
        hightValueColor = (Color) stream.readObject();
        disablePopups = stream.readBoolean();
        _lastBrowsedDirectory = (String) stream.readObject();
        rowLabelsSource = (RLS) stream.readObject();
        rowLabelsSourceSelectedSample = (String) stream.readObject();
        rowLabelsSourceSelectedAttribute = (String) stream.readObject();
        plotElbowMethodOutput = stream.readBoolean();
        HeatmapOptions.heatmpaType = stream.readBoolean();
        HeatmapOptions.heatmapCommand = (String) stream.readObject();
        HeatmapOptions.heatmapParameters = (ClusteringOptions) stream.readObject();
        HeatmapOptions.heatmapTitle = (String) stream.readObject();
        HeatmapOptions.horizontalAxisTitle = (String) stream.readObject();
        HeatmapOptions.verticalAxisTitle = (String) stream.readObject();
        HeatmapOptions.size = (Dimension) stream.readObject();
        HeatmapOptions.RScriptPath = (String) stream.readObject();
        HeatmapOptions.autoFitRPlotSize = stream.readBoolean();
        HeatmapOptions.RBAOptions = stream.readBoolean();
        HeatmapOptions.epsilonDistance = stream.readDouble();
        GraphOptions.graphType = (GraphType) stream.readObject();
        GraphOptions.grouping = (NodeGrouping) stream.readObject();
        GraphOptions.theme = (GraphOptions.Theme) stream.readObject();
        GraphOptions.useSolidAggColor = (Boolean) stream.readObject();
        GraphOptions.beforeCutColor = (Color) stream.readObject();
        GraphOptions.afterCutColor = (Color) stream.readObject();
        GraphOptions.solidColor = (Color) stream.readObject();
        GraphOptions.enforceAngularBound = (Boolean) stream.readObject();
        GraphOptions.angularBoundDegree = (int) stream.readInt();
        GraphOptions.angularBoundRadius = (int) stream.readInt();
        GraphOptions.background = (Color) stream.readObject();
        GraphOptions.foreground = (Color) stream.readObject();
        GraphOptions.edgeColor = (int) stream.readInt();
        GraphOptions.textColor = (int) stream.readInt();
        GraphOptions.nodeColor = (int) stream.readInt();
        GraphOptions.textColorHover = (int) stream.readInt();
        GraphOptions.nodeColorHover = (int) stream.readInt();
        GraphOptions.nodeColorSelected = (int) stream.readInt();
        GraphOptions.nodeColorSearchResult = (int) stream.readInt();
        GraphOptions.nodeAggHoverStroke = (int) stream.readInt();
        GraphOptions.nodeAggDefaultStroke = (int) stream.readInt();
        GraphOptions.aggHoverStroke = (int) stream.readInt();
        GraphOptions.aggDefaultStroke = (int) stream.readInt();
    }


    public static class HeatmapOptions implements Serializable
    {
        public static final long serialVersionUID = GlobalVariables.serialVersionUID;
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

    public static class GraphOptions implements Serializable
    {
        public static final long serialVersionUID = GlobalVariables.serialVersionUID;

        public enum Theme
        {
            Bright, Dark, Custom
        };

        // The followings are the graph visualization parameters.
        public static GraphType graphType = GraphType.Radial;
        public static NodeGrouping grouping = NodeGrouping.None;
        public static Theme theme = Theme.Dark;
        public static Boolean useSolidAggColor = true;
        public static Color beforeCutColor = Color.RED;
        public static Color afterCutColor = Color.YELLOW;
        public static Color solidColor = Color.LIGHT_GRAY;
        public static Boolean enforceAngularBound = false;
        public static int angularBoundDegree = 0;
        public static int angularBoundRadius = 6;
        public static Color background = Color.black;
        public static Color foreground = Color.yellow;
        public static int edgeColor = ColorLib.rgb(40, 117, 254);
        public static int textColor = ColorLib.rgb(200, 255, 255);
        public static int nodeColor = ColorLib.rgb(0, 0, 20);
        public static int textColorHover = ColorLib.rgb(255, 165, 255);
        public static int nodeColorHover = ColorLib.rgb(30, 0, 60);
        public static int nodeColorSelected = ColorLib.rgb(7, 2, 30);
        public static int nodeColorSearchResult = ColorLib.rgb(80, 80, 0);
        public static int nodeAggHoverStroke = ColorLib.rgb(255, 0, 0);
        public static int nodeAggDefaultStroke = ColorLib.rgb(0, 255, 0);
        public static int aggHoverStroke = ColorLib.rgb(255, 244, 0);
        public static int aggDefaultStroke = ColorLib.rgb(60, 70, 150);
    }
}
