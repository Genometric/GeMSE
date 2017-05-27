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
package GeMSE.GS.clustering;


import GeMSE.OperationsOptions.ClusteringOptions.ClusteringDomains;
import GeMSE.OperationsOptions.ClusteringOptions.Metrics;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.apache.commons.math3.ml.distance.ManhattanDistance;
import org.apache.commons.math3.ml.distance.EarthMoversDistance;
import org.apache.commons.math3.ml.distance.ChebyshevDistance;
import org.apache.commons.math3.ml.distance.CanberraDistance;

/**
 *
 * @author Vahid Jalili
 */
public class DistanceMatrix
{
    EuclideanDistance euclideanDistance;
    ManhattanDistance manhattanDistance;
    EarthMoversDistance earthMoversDistance;
    ChebyshevDistance chebyshevDistance;
    CanberraDistance canberraDistance;

    public double[][] GetDistanceMatrix(double[][] space, Metrics metric, ClusteringDomains clusteringDomain)
    {
        euclideanDistance = new EuclideanDistance();
        manhattanDistance = new ManhattanDistance();
        earthMoversDistance = new EarthMoversDistance();
        chebyshevDistance = new ChebyshevDistance();
        canberraDistance = new CanberraDistance();

        double[][] rtv = null;

        int rowCount = space.length;
        int colCount = space[0].length;
        int rtvIndex = 0;

        switch (clusteringDomain)
        {
            case Rows:
                rtv = new double[1][(int) Math.floor((Math.pow(rowCount, 2) - rowCount) / 2f)];

                for (int i = 0 ; i < rowCount - 1 ; i++)
                    for (int j = i + 1 ; j < rowCount ; j++)
                        // TODO: replace the following mess with the factory method. 
                        switch (metric)
                        {
                            case EuclideanDistance:
                                rtv[0][rtvIndex++] = euclideanDistance.compute(space[i], space[j]);
                                break;

                            case ManhattanDistance:
                                rtv[0][rtvIndex++] = manhattanDistance.compute(space[i], space[j]);
                                break;

                            case EarthMoversDistance:
                                rtv[0][rtvIndex++] = earthMoversDistance.compute(space[i], space[j]);
                                break;

                            case ChebyshevDistance:
                                rtv[0][rtvIndex++] = chebyshevDistance.compute(space[i], space[j]);
                                break;

                            case CanberraDistance:
                                rtv[0][rtvIndex++] = canberraDistance.compute(space[i], space[j]);
                                break;

                            case PearsonCorrelationCoefficient:
                                rtv[0][rtvIndex++] = ComputePCC(space[i], space[j]);
                                break;
                        }

                break;

            case Columns:
                rtv = new double[1][(int) Math.floor((Math.pow(colCount, 2) - colCount) / 2f)];

                double[] col_i = new double[rowCount];
                double[] col_j = new double[rowCount];

                for (int i = 0 ; i < colCount - 1 ; i++)
                {
                    for (int j = i + 1 ; j < colCount ; j++)
                    {
                        for (int row = 0 ; row < rowCount ; row++)
                        {
                            col_i[row] = space[row][i];
                            col_j[row] = space[row][j];
                        }

                        // TODO: Replace the following mess with the factory pattern. 
                        switch (metric)
                        {
                            case EuclideanDistance:
                                rtv[0][rtvIndex++] = euclideanDistance.compute(col_i, col_j);
                                break;

                            case ManhattanDistance:
                                rtv[0][rtvIndex++] = manhattanDistance.compute(col_i, col_j);
                                break;

                            case EarthMoversDistance:
                                rtv[0][rtvIndex++] = earthMoversDistance.compute(col_i, col_j);
                                break;

                            case ChebyshevDistance:
                                rtv[0][rtvIndex++] = chebyshevDistance.compute(col_i, col_j);
                                break;

                            case CanberraDistance:
                                rtv[0][rtvIndex++] = canberraDistance.compute(col_i, col_j);
                                break;

                            case PearsonCorrelationCoefficient:
                                rtv[0][rtvIndex++] = ComputePCC(col_i, col_j);
                                break;
                        }
                    }
                }
                break;
        }

        return rtv;
    }

    /**
     * Computes Pearson product-moment correlation coefficient.
     */
    private double ComputePCC(double[] X, double[] Y)
    {
        double sumX = 0;
        for (double x : X)
            sumX += x;

        double sumY = 0;
        for (double y : Y)
            sumY += y;

        double[] XY = new double[X.length];
        for (int i = 0 ; i < X.length ; i++)
            XY[i] = X[i] * Y[i];

        double sumXY = 0;
        for (double xy : XY)
            sumXY += xy;

        double[] XSqrd = new double[X.length];
        for (int i = 0 ; i < X.length ; i++)
            XSqrd[i] = Math.pow(X[i], 2);

        double sumXSqrd = 0;
        for (double xSqrd : XSqrd)
            sumXSqrd += xSqrd;

        double[] YSqrd = new double[Y.length];
        for (int i = 0 ; i < Y.length ; i++)
            YSqrd[i] = Math.pow(Y[i], 2);

        double sumYSqrd = 0;
        for (double ySqrd : YSqrd)
            sumYSqrd += ySqrd;

        double r = ((X.length * sumXY) - (sumX * sumY))
                   / Math.sqrt(
                        ((X.length * sumXSqrd) - Math.pow(sumX, 2))
                        * ((X.length * sumYSqrd) - Math.pow(sumY, 2)));

        // technically speaking, following corrections
        // should not be required; however, it turns-out that
        // above calculation produces numbers that have a little 
        // precision issues. Following is a patch, later on, the 
        // above calculation should be updated to prevent such 
        // precision errors. 
        if (r > 1) r = 1;
        else if (r < -1) r = -1;

        // re-scale r from [-1, 1] to [1, 100]
        return (((r - (-1)) * (100 - 1)) / (1 - (-1))) + 1;
    }
}
