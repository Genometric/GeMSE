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

import GeMSE.GlobalVariables;
import GeMSE.GlobalVariables.RLS;
import java.io.Serializable;

/**
 *
 * @author Vahid Jalili
 */
public class Space implements Serializable
{
    public static final long serialVersionUID = 1;
    public double[][] content;
    public String[] rowsID;
    public String[] colsID;
    public String[] rowTitle;
    public String[] colTitle;

    public Space(int rowsCount, int columnsCount)
    {
        content = new double[rowsCount][columnsCount];
        rowsID = new String[rowsCount];
        colsID = new String[columnsCount];
    }

    public void UpdateColumnsTitles()
    {
        colTitle = GetColumnTitles(GlobalVariables.selectedColumnLabelOption);
    }

    public String[] GetColumnTitles(String attribute)
    {
        String[] rtv = new String[colsID.length];

        // This loop is useful for PatternSearch only because
        // the operation may change the column size and creating
        // columns with IDs that are not valid. This loop avoids
        // null values for invalid IDs, hence can be plotted. 
        for (int i = 0 ; i < colsID.length ; i++)
            rtv[i] = " ";

        int cfs = GlobalVariables.samples.size();

        for (int i = 0 ; i < colsID.length ; i++)
        {
            for (int j = 0 ; j < cfs ; j++)
            {
                if (GlobalVariables.samples.get(j).sampleID.equals(colsID[i]))
                {
                    if (attribute.equals("Sample Name (file name)"))
                    {
                        rtv[i] = GlobalVariables.samples.get(j).fileName;
                    }
                    else
                    {
                        String[] value = GlobalVariables.samples.get(j).metaData.get(attribute);

                        if (value != null)
                        {
                            if (value.length > 0)
                            {
                                rtv[i] = value[0];

                                for (int k = 1 ; k < value.length ; k++)
                                    rtv[i] += value[k];
                            }
                            else
                            {
                                rtv[i] = "NA";
                            }
                        }
                        else
                        {
                            rtv[i] = "NA";
                        }
                    }

                    break;
                }
            }
        }

        return rtv;
    }

    public void UpdateRowsTitles()
    {
        rowTitle = GetRowTitles(GlobalVariables.rowLabelsSource,
                                GlobalVariables.rowLabelsSourceSelectedSample,
                                GlobalVariables.rowLabelsSourceSelectedAttribute);
    }

    public String[] GetRowTitles(RLS rowLabelSource, String sampleFileName, String attribute)
    {
        String[] rtv = new String[rowsID.length];

        switch (rowLabelSource)
        {
            case ID:
                for (int row = 0 ; row < rowsID.length ; row++)
                {
                    String[] id = rowsID[row].split("\\s*_\\s*");
                    rtv[row] = id[0] + "_" + id[1] + "_" + id[2];
                }
                break;
            case SampleAttributes:
                for (SampleData sample : GlobalVariables.samples)
                    if (sample.fileName.equals(sampleFileName))
                        for (int row = 0 ; row < rowsID.length ; row++)
                        {
                            String[] id = rowsID[row].split("\\s*_\\s*");
                            rtv[row] = sample.GetTXTAttribute(
                                    id[0],
                                    Integer.valueOf(id[1]),
                                    Integer.valueOf(id[2]),
                                    attribute);
                        }
                break;
            case ReferenceAttributes:
                for (int row = 0 ; row < rowsID.length ; row++)
                {
                    String[] id = rowsID[row].split("\\s*_\\s*");
                    rtv[row] = GlobalVariables.annotations.GetTXTAttribute(
                            id[0],
                            Integer.valueOf(id[1]),
                            Integer.valueOf(id[2]),
                            attribute);
                }
                break;
        }

        return rtv;
    }

    public void OrderSpaceByColIDs(String[] colIDs)
    {
        int colLength = this.colsID.length;
        int rowLength = this.rowsID.length;
        Space tmpSpace = new Space(rowLength, colLength);

        for (int newIndex = 0 ; newIndex < colLength ; newIndex++)
        {
            for (int oldIndex = 0 ; oldIndex < colLength ; oldIndex++)
            {
                if (this.colsID[oldIndex].equals(colIDs[newIndex]))
                {
                    for (int row = 0 ; row < rowLength ; row++)
                        tmpSpace.content[row][newIndex] = this.content[row][oldIndex];

                    break; // this breaks the oldIndex loop. 
                }
            }
        }

        this.colsID = colIDs;
        this.content = tmpSpace.content;
    }

    public void OrderSpaceByRowIDs(String[] rowIDs)
    {
        int colLength = this.colsID.length;
        int rowLength = this.rowsID.length;
        Space tmpSpace = new Space(rowLength, colLength);

        for (int newIndex = 0 ; newIndex < rowLength ; newIndex++)
            for (int oldIndex = 0 ; oldIndex < rowLength ; oldIndex++)
                if (this.rowsID[oldIndex].equals(rowIDs[newIndex]))
                {
                    System.arraycopy(this.content[oldIndex], 0, tmpSpace.content[newIndex], 0, colLength);
                    break; // this breaks the oldIndex loop. 
                }

        this.rowsID = rowIDs;
        this.content = tmpSpace.content;
    }

    public double[] GetVector()
    {
        if (content.length == 0) return new double[0];

        double[] rtv = new double[content.length * content[0].length];
        for (int i = 0 ; i < content.length ; i++)
            for (int j = 0 ; j < content[0].length ; j++)
                if (Double.isNaN(content[i][j]))
                    rtv[i + j] = 0;
                else
                    rtv[i + j] = content[i][j];
        return rtv;
    }

    public double[][] GetContent()
    {
        return GetContent(false);
    }
    public double[][] GetContent(Boolean replaceNaN)
    {
        if (!replaceNaN) return content;
        double[][] rtv = new double[content.length][content[0].length];
        for (int i = 0 ; i < content.length ; i++)
            for (int j = 0 ; j < content[0].length ; j++)
                if (Double.isNaN(content[i][j]))
                    rtv[i][j] = 0;
                else
                    rtv[i][j] = content[i][j];
        return rtv;
    }
}
