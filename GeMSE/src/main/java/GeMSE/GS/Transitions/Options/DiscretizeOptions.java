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
package GeMSE.GS.Transitions.Options;

import java.util.ArrayList;

/**
 *
 * @author Vahid Jalili
 */
public class DiscretizeOptions implements Cloneable
{
    public DiscretizeOptions(double minValue, double maxValue, int columnFrom, int columnTo, int rowFrom, int rowTo)
    {
        range = new SelectOptions(columnFrom, columnTo, rowFrom, rowTo);
        _minValue = minValue;
        _maxValue = maxValue;
    }

    private double _minValue;
    private double _maxValue;
    public SelectOptions range;
    public ArrayList<Map> maps;

    public double GetMinValue()
    {
        return _minValue;
    }

    public double GetMaxValue()
    {
        return _maxValue;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
