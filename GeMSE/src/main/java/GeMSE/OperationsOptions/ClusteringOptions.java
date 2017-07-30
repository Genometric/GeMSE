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
package GeMSE.OperationsOptions;

import ExternalLibraries.Cluster;
import java.io.Serializable;

/**
 *
 * @author Vahid Jalili
 */
public class ClusteringOptions implements Cloneable, Serializable
{
    public ClusteringOptions()
    {
        method = ClusteringMethods.AHC;
        linkage = LinkageCriterias.Average;
        domain = ClusteringDomains.Rows;
        metric = Metrics.EuclideanDistance;
        distanceType = DistanceType.Height;
        cutDistance = 0.0;
        dendrogram = null;
    }

    public static final long serialVersionUID = 1;

    public enum ClusteringMethods
    {
        /**
         * Agglomerative Hierarchical Clustering
         */
        AHC
    }

    public enum LinkageCriterias
    {
        Single, Average, Complete
    };

    public enum ClusteringDomains
    {
        Rows, Columns, BiClustering
    };

    public enum Metrics
    {
        EuclideanDistance,
        ManhattanDistance,
        EarthMoversDistance,
        ChebyshevDistance,
        CanberraDistance,
        PearsonCorrelationCoefficient
    };

    public enum DistanceType
    {
        /**
         * Cut dendrogram where the distance (height) of the node from root
         * equals a given distance.
         */
        Height,
        /**
         * Cut dendrogram where the distance between the children of a node is
         * less than or equal to a given distance.
         */
        Distance
    };

    public ClusteringMethods method;
    public LinkageCriterias linkage;
    public ClusteringDomains domain;
    public Metrics metric;
    public DistanceType distanceType;
    public double cutDistance;
    public Cluster dendrogram;

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
