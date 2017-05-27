/*
Expression GeMSE is undefined on line 4, column 3 in Templates/Licenses/license-gpl30.txt.Copyright (C) 2017 vahid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package GeMSE.GS.clustering;

/**
 *
 * @author Vahid Jalili
 */
public class ElbowData
{
    public ElbowData(int height, int clusterCount, double distance, double varianceOfPercentage)
    {
        this.height = height;
        this.clusterCount = clusterCount;
        this.distance = distance;
        this.percentageOfVariance = varianceOfPercentage;
    }

    public int height;
    public int clusterCount;
    public double distance;
    public double percentageOfVariance;
}
