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

/**
 *
 * @author Vahid Jalili
 */
public class SortOptions implements Cloneable
{
    public SortOptions(int colCount, int rowCount)
    {
        order = Orders.Ascending;
        domain = Domains.RowsContent;
        _colCount = colCount;
        _rowCount = rowCount;
    }

    public enum Orders
    {
        Ascending, Descending
    };

    public enum Domains
    {
        RowsContent, RowsMetadata, ColumnsContent, ColumnsMetadata
    };

    public Orders order;
    public Domains domain;
    public String[] criteria;

    private final int _colCount;
    public int GetColCount()
    {
        return _colCount;
    }

    private final int _rowCount;
    public int GetRowCount()
    {
        return _rowCount;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
