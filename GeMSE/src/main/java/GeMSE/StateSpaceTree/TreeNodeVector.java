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

package GeMSE.StateSpaceTree;

import GeMSE.GS.History.NodeData;
import java.util.Vector;

/**
 *
 * @author Vahid Jalili
 * @param <E>
 */
public class TreeNodeVector<E> extends Vector<E>
{
    public NodeData component;

    public TreeNodeVector(NodeData component)
    {
        this.component = component;
    }

    public TreeNodeVector(NodeData component, E elements[])
    {
        this.component = component;
        for (int i = 0 ; i < elements.length ; i++)
            add(elements[i]);
    }

    @Override
    public String toString()
    {
        return component.GetUserDefinedTitle();
    }
}
