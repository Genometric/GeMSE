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

import ExternalLibraries.Cluster;
import GeMSE.GlobalVariables;

/**
 * Converts a given cluster to a Newick tree representation satisfying
 * "Ultrametric Tree" property.
 *
 * @author Vahid Jalili
 */
public class ClusterToNewick
{
    private static double _maxDistance;
    private static double _epsilon;

    public static String Convert()
    {
        _epsilon = GlobalVariables.HeatmapOptions.epsilonDistance;
        _maxDistance = FindMaxDistance(GlobalVariables.HeatmapOptions.heatmapParameters.dendrogram, _epsilon);
        return Cluster2Newick(GlobalVariables.HeatmapOptions.heatmapParameters.dendrogram, 0, _epsilon) + ";";
    }

    public static String Convert(Cluster cluster)
    {
        _epsilon = GlobalVariables.HeatmapOptions.epsilonDistance;
        _maxDistance = FindMaxDistance(cluster, _epsilon);
        return Cluster2Newick(cluster, 0, _epsilon) + ";";
    }

    private static double FindMaxDistance(Cluster root, double distance)
    {
        if (root.isLeaf())
        {
            return distance + _epsilon;
        }
        else
        {
            double maxDis = _epsilon;
            for (Cluster child : root.getChildren())
                maxDis = Math.max(maxDis, FindMaxDistance(child, distance + root.getDistance() + _epsilon));

            return maxDis;
        }
    }

    private static String Cluster2Newick(Cluster root, double distance, double distanceToRoot)
    {
        if (root.isLeaf())
        {
            return root.getName() + ":" + String.valueOf(_maxDistance - distanceToRoot);
        }
        else
        {
            String rtv = "(";
            boolean firstChild = true;
            for (Cluster child : root.getChildren())
            {
                if (!firstChild) rtv += ",";
                firstChild = false;
                rtv += Cluster2Newick(child, root.getDistance(), distanceToRoot + root.getDistance() + _epsilon);
            }
            return rtv + "):" + String.valueOf(root.getDistance() + _epsilon);
        }
    }
}
