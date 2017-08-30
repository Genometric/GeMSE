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

import GeMSE.GlobalVariables;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Vahid Jalili
 */
public class Node<T> implements Serializable
{
    public static final long serialVersionUID = GlobalVariables.serialVersionUID;
    private final List<Node<T>> children = new ArrayList<>();
    private Node<T> parent = null;
    private T data = null;

    public Node(T data)
    {
        this.data = data;
    }

    public Node(T data, Node<T> parent)
    {
        this.data = data;
        this.parent = parent;
    }

    public List<Node<T>> GetChildren()
    {
        return children;
    }

    public void SetParent(Node<T> parent)
    {
        this.parent = parent;
    }

    public void AddChild(T data)
    {
        Node<T> child = new Node<>(data);
        child.SetParent(this);
        this.children.add(child);
    }

    public void AddChild(Node<T> child)
    {
        child.SetParent(this);
        this.children.add(child);
    }

    public T GetData()
    {
        return this.data;
    }

    public void SetData(T data)
    {
        this.data = data;
    }

    public boolean IsRoot()
    {
        return (this.parent == null);
    }

    public boolean IsLeaf()
    {
        if (this.children.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void RemoveParent()
    {
        this.parent = null;
    }
}
