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

package GeMSE.GS.History;

import GeMSE.GS.Space;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vahid Jalili
 */
public class SpacesHistory
{

    private final HashMap<String, Space> _history;

    public int maximumNumberOfSpacesToBeCached = 1;

    public SpacesHistory()
    {
        _history = new HashMap();
    }

    public void Insert(String spaceID, Space space)
    {
        // convert to while
        if (_history.size() > maximumNumberOfSpacesToBeCached - 1)
        {
            // Based on iterator, it removes the first occurance of key-value pair. 
            // Another method could be removal of biggest space, or removal of user specified space.
            _history.remove((String) ((Map.Entry) _history.entrySet().iterator().next()).getKey());
        }

        _history.put(spaceID, space);
    }

    public Space GetSpace(String id)
    {
        return _history.get(id);
    }

    public boolean IsSpaceAvailable(String spaceID)
    {
        return _history.containsKey(spaceID);
    }
}
