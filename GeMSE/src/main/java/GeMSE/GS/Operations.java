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
package GeMSE.GS;

import GeMSE.GS.Transitions.SortingFunction;
import GeMSE.GS.Transitions.Options.ClusteringOptions;
import GeMSE.GS.Transitions.Options.DiscretizeOptions;
import GeMSE.GS.Transitions.Options.SelectOptions;
import GeMSE.GS.Transitions.Options.SortOptions;
import java.util.logging.Level;
import java.util.logging.Logger;
import ExternalLibraries.Cluster;
import GeMSE.GS.Transitions.Options.Map;
import GeMSE.GlobalVariables;
import GeMSE.IO.InProgress;
import java.io.Serializable;
import javax.swing.SwingWorker;


/**
 *
 * @author Vahid Jalili
 */
public class Operations implements Serializable
{
    public Operations()
    {
        source = null;
        result = null;
        parameters = null;
    }

    public Space source;
    public Space result;
    public Object parameters;
    public static final long serialVersionUID = GlobalVariables.serialVersionUID;

    public enum Functions
    {
        Extract, Rewrite, Discretize, Clustering, UpdateClustering, Sort, Root
    };

    public void Multiplexer(String userDefinedTitle, Functions command, Object parameters)
    {
        switch (command)
        {
            case Extract:
                result = Extract((SelectOptions) parameters);
                this.parameters = parameters;
                break;

            case Rewrite:
            case Discretize:
                result = Discretize((DiscretizeOptions) parameters);
                this.parameters = parameters;
                break;

            case Clustering:
                if (((ClusteringOptions) parameters).dendrogram == null)
                {
                    InProgress inProgress = new InProgress(null, "Clustering data, please wait ...");
                    inProgress.setLocationRelativeTo(null);
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
                    {
                        @Override
                        protected Void doInBackground()
                        {
                            result = Clustering((ClusteringOptions) parameters);
                            inProgress.dispose();
                            return null;
                        }
                    };
                    worker.execute();
                    inProgress.setVisible(true);
                }
                else
                    result = Clustering((ClusteringOptions) parameters);
                break;

            case UpdateClustering:
                result = UpdateClustering((ClusteringOptions) parameters);
                this.parameters = parameters;
                break;

            case Sort:
                result = Sort((SortOptions) parameters);
                this.parameters = parameters;
                break;
        }

        result.UpdateColumnsTitles();
        result.UpdateRowsTitles();
    }

    public Space Extract(SelectOptions options)
    {
        Space rtv = new Space(options.RowTo - options.RowFrom, options.ColumnTo - options.ColumnFrom);

        System.arraycopy(source.rowsID, options.RowFrom, rtv.rowsID, 0, options.RowTo - options.RowFrom);
        System.arraycopy(source.colsID, options.ColumnFrom, rtv.colsID, 0, options.ColumnTo - options.ColumnFrom);

        for (int row = options.RowFrom ; row < options.RowTo ; row++)
            for (int col = options.ColumnFrom ; col < options.ColumnTo ; col++)
                rtv.content[row - options.RowFrom][col - options.ColumnFrom] = source.content[row][col];

        return rtv;
    }

    public Space Discretize(DiscretizeOptions options)
    {
        Space rtv = new Space(source.rowsID.length, source.colsID.length);
        System.arraycopy(source.rowsID, 0, rtv.rowsID, 0, source.rowsID.length);
        System.arraycopy(source.colsID, 0, rtv.colsID, 0, source.colsID.length);

        // These loops initialize the return value space with source space. 
        for (int row = 0 ; row < source.content.length ; row++)
            for (int col = 0 ; col < source.content[0].length ; col++)
                rtv.content[row][col] = source.content[row][col];

        for (Map map : options.maps)
            for (int row = options.range.RowFrom ; row < options.range.RowTo ; row++)
                for (int col = options.range.ColumnFrom ; col < options.range.ColumnTo ; col++)
                    if (source.content[row][col] >= map.GetRangeStart() && source.content[row][col] < map.GetRaneStop())
                    {
                        switch (map.GetFunction())
                        {
                            case Log:
                                rtv.content[row][col]
                                = Math.log(source.content[row][col])
                                  / Math.log(map.GetArgument());
                                // This function implements Log_x(y) as: 
                                // Log_e(x) / Log_e(y)
                                break;

                            case Static:
                                rtv.content[row][col] = map.GetArgument();
                                break;
                        }
                    }

        return rtv;
    }

    public Space Clustering(ClusteringOptions options)
    {
        ClusteringFunctions cF = new ClusteringFunctions(source);
        cF.SetDistanceToRoot(options.cutDistance);
        cF.SetDistanceType(options.distanceType);

        if (options.dendrogram == null)
            options.dendrogram = cF.AggHieClu(options);

        options.cutDistance = cF.GetDistanceToRoot();

        try
        {
            parameters = options.clone();
        }
        catch (CloneNotSupportedException ex)
        {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cF.OrderSpaceBasedOnCluster(options.dendrogram, options.domain);
    }

    public Space UpdateClustering(ClusteringOptions options)
    {
        ClusteringFunctions cF = new ClusteringFunctions(source);
        cF.SetDistanceType(options.distanceType);
        cF.SetDistanceToRoot(options.cutDistance);

        return cF.OrderSpaceBasedOnCluster(options.dendrogram, options.domain);
    }

    public Space Sort(SortOptions options)
    {
        Space rtv = new Space(source.rowsID.length, source.colsID.length);
        rtv.content = source.content;
        rtv.rowsID = source.rowsID;
        rtv.rowTitle = source.rowTitle;
        rtv.colsID = source.colsID;
        rtv.colTitle = source.colTitle;

        return new SortingFunction(rtv).Sort(options);
    }
}
