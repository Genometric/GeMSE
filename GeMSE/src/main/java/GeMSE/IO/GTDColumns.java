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

/**
 *
 * @author Vahid Jalili
 */
public class GTDColumns
{
    public GTDColumns(boolean read, int columnIndex, String columnLabel)
    {
        _read = read;
        _columnIndex = columnIndex;
        _colmunLabel = columnLabel;
    }
    private boolean _read;
    private final int _columnIndex;
    private String _colmunLabel;


    public void SetEnabled(boolean value)
    {
        _read = value;
    }
    public boolean GetRead()
    {
        return _read;
    }
    public int GetColumnIndex()
    {
        return _columnIndex;
    }
    public void SetColumnLabel(String label)
    {
        _colmunLabel = label;
    }
    public String GetColumnLabel()
    {
        return _colmunLabel;
    }
}
