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
import GeMSE.GS.Transitions.Options.SortOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Vahid Jalili
 */
public class SortingFunction
{
    private Space _source;
    public SortingFunction(Space source)
    {
        _source = source;
    }

    private class SortingPair implements Comparable<SortingPair>
    {
        private final double[] _numericItem;
        private final String[] _stringItems;
        private final String _id;
        private int _order = 1; // 1: Ascending , -1 Descending

        public SortingPair(String[] stringItems, String id, int order)
        {
            _numericItem = null;
            _stringItems = stringItems;
            _id = id;
            _order = order;
        }

        public SortingPair(int itemsLenght, String id, int order, char itemType)
        {
            if (itemType == 'N')
            {
                _numericItem = new double[itemsLenght];
                _stringItems = null;
            }
            else
            {
                _numericItem = null;
                _stringItems = new String[itemsLenght];
            }

            _id = id;
            _order = order;
        }

        public SortingPair(double[] numericItem, String id, int order)
        {
            _numericItem = numericItem;
            _stringItems = null;
            _id = id;
            _order = order;
        }


        public double[] getNumericItem()
        {
            return _numericItem;
        }

        public String[] getStringItems()
        {
            return _stringItems;
        }

        public void setStringItem(int index, String value)
        {
            _stringItems[index] = value;
        }


        public void setNumbericItem(int index, double value)
        {
            _numericItem[index] = value;
        }

        public String getID()
        {
            return _id;
        }
        @Override
        public int compareTo(SortingPair that)
        {
            if (this == null) return -1;
            if (that == null) return 1;
            if (this == that) return 0; // equal

            int rtv = 0;
            int i = -1;

            if (_numericItem != null) // compare numeric item
            {
                while (rtv == 0 && ++i < this._numericItem.length)
                {
                    double tmpThis = this._numericItem[i];
                    double tmpThat = that._numericItem[i];
                    
                    if(Double.isNaN(tmpThis) || Double.isInfinite(tmpThis))
                        tmpThis = 0;
                    if(Double.isNaN(tmpThat) || Double.isInfinite(tmpThat))
                        tmpThat = 0;
                    
                    if (tmpThis < tmpThat)
                        rtv = -1 * _order;
                    else if (tmpThis > tmpThat)
                        rtv = 1 * _order;
                    else
                        rtv = 0;
                }

                return rtv;
            }
            else // compare string item
            {
                while (rtv == 0 && ++i < this._stringItems.length)
                    rtv = this._stringItems[i].compareTo(that._stringItems[i]) * _order;

                return rtv;
            }
        }
    }

    public Space Sort(SortOptions options)
    {
        int order = 1;
        switch (options.order)
        {
            case Ascending:
                order = 1;
                break;
            case Descending:
                order = -1;
                break;
        }

        switch (options.domain)
        {
            case ColumnsMetadata: return SortColumnMetadata(order, options.criteria);
            case ColumnsContent: return SortColumnContent(order, options.criteria);
            case RowsMetadata: return SortRowMetadata(order, options.criteria);
            case RowsContent: return SortRowContent(order, options.criteria);
        }

        return null;
    }

    private Space SortColumnMetadata(int order, String[] attribute)
    {
        List<SortingPair> items = new ArrayList<>();
        for (String columnsID : _source.colsID)
        {
            items.add(new SortingPair(attribute.length, columnsID, order, 'S'));
        }

        String[] columnTitles;
        for (int att = 0 ; att < attribute.length ; att++)
        {
            columnTitles = _source.GetColumnTitles(attribute[att]);
            for (int col = 0 ; col < _source.colsID.length ; col++)
                items.get(col).setStringItem(att, columnTitles[col]);
        }

        Collections.sort(items);

        String[] colIDsOrder = new String[items.size()];
        for (int i = 0 ; i < items.size() ; i++)
            colIDsOrder[i] = items.get(i).getID();

        _source.OrderSpaceByColIDs(colIDsOrder);
        _source.UpdateColumnsTitles();

        return _source;
    }

    private Space SortColumnContent(int order, String[] domain)
    {
        List<SortingPair> items = new ArrayList<>();
        for (String rowID : _source.rowsID)
            items.add(new SortingPair(domain.length, rowID, order, 'N'));

        for (int row = 0 ; row < _source.rowsID.length ; row++)
            for (int d = 0 ; d < domain.length ; d++)
                items.get(row).setNumbericItem(d, _source.content[row][Integer.valueOf(domain[d])]);

        //TODO: provide a custom sort function, and address NaN using that function. 
        Collections.sort(items);

        String[] rowIDsOrder = new String[items.size()];
        for (int i = 0 ; i < items.size() ; i++)
            rowIDsOrder[i] = items.get(i).getID();

        _source.OrderSpaceByRowIDs(rowIDsOrder);
        _source.UpdateRowsTitles();

        return _source;
    }

    private Space SortRowMetadata(int order, String[] attribute)
    {
        List<SortingPair> items = new ArrayList<>();
        for (String rowsID : _source.rowsID)
            items.add(new SortingPair(attribute.length, rowsID, order, 'S'));

        String[] rowsTitles;
        for (int att = 0 ; att < attribute.length ; att++)
        {
            rowsTitles = _source.GetRowTitles(GlobalVariables.rowLabelsSource,
                    GlobalVariables.rowLabelsSourceSelectedSample,
                    attribute[att]);

            for (int row = 0 ; row < _source.rowsID.length ; row++)
                items.get(row).setStringItem(att, rowsTitles[row]);
        }

        Collections.sort(items);

        String[] rowIDsOrder = new String[items.size()];
        for (int i = 0 ; i < items.size() ; i++)
            rowIDsOrder[i] = items.get(i).getID();

        _source.OrderSpaceByRowIDs(rowIDsOrder);
        _source.UpdateRowsTitles();

        return _source;
    }

    private Space SortRowContent(int order, String[] domain)
    {
        List<SortingPair> items = new ArrayList<>();
        for (String colID : _source.colsID)
            items.add(new SortingPair(domain.length, colID, order, 'N'));

        for (int col = 0 ; col < _source.colsID.length ; col++)
            for (int d = 0 ; d < domain.length ; d++)
                items.get(col).setNumbericItem(d, _source.content[Integer.valueOf(domain[d])][col]);

        Collections.sort(items);

        String[] colIDsOrder = new String[items.size()];
        for (int i = 0 ; i < items.size() ; i++)
            colIDsOrder[i] = items.get(i).getID();

        _source.OrderSpaceByColIDs(colIDsOrder);
        _source.UpdateColumnsTitles();

        return _source;
    }
}
