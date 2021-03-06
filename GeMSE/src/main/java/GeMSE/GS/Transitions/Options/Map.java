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

import GeMSE.GlobalVariables;
import java.io.Serializable;

/**
 *
 * @author Vahid Jalili
 */
public class Map implements Cloneable, Serializable
{
    public Map(double rangeStart, double rangeStop, Function function, double argument)
    {
        _rangeStart = rangeStart;
        _rangeStop = rangeStop;
        _function = function;
        _argument = argument;
    }
    
    public static final long serialVersionUID = GlobalVariables.serialVersionUID;

    private final double _rangeStart;
    private final double _rangeStop;
    private final Function _function;
    // TODO: different functions could have differnt type and number of 
    // arguments, so a better strategy for argument is to replace it with 
    // function-specific class of values using factory pattern.
    private final double _argument;

    public enum Function
    {
        Static, Log
    }

    public double GetRangeStart()
    {
        return _rangeStart;
    }

    public double GetRaneStop()
    {
        return _rangeStop;
    }

    public Function GetFunction()
    {
        return _function;

    }

    public double GetArgument()
    {
        return _argument;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
