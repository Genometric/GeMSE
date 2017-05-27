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

package GeMSE.PatternSearch;

import java.util.Objects;

/**
 *
 * @author Vahid Jalili
 */
public class AttributeValuePair implements Comparable<AttributeValuePair>
{
    private final String _attribute;
    private final String _value;

    public AttributeValuePair(String attribute, String value)
    {
        _attribute = attribute;
        _value = value;
    }

    public String GetAttribute()
    {
        return _attribute;
    }

    public String GetValue()
    {
        return _value;
    }

    @Override
    public int compareTo(AttributeValuePair o)
    {
        /// try to improve this comparision for less footprints if needed. 
        int aC = _attribute.compareTo(o.GetAttribute());
        return aC == 0 ? _value.compareTo(o.GetValue()) : aC;
    }
    @Override
    public int hashCode()
    {
        /// following are java generated hash function.
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this._attribute);
        hash = 89 * hash + Objects.hashCode(this._value);
        return hash;
    }

    @Override
    public boolean equals(Object o)
    {
        return _attribute.equals(((AttributeValuePair) o).GetAttribute()) && _value.equals(((AttributeValuePair) o).GetValue());
    }
}
