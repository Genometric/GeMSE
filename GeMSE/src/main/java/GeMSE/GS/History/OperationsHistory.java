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

import GeMSE.GS.Operations.Functions;
import GeMSE.GS.Space;
import GeMSE.GlobalVariables;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Vahid Jalili
 */
public final class OperationsHistory implements Serializable
{
    public static final long serialVersionUID = GlobalVariables.serialVersionUID;
    public String rootID = "Root";
    private String _parentIDToSearch = "rewrew";
    private final Node<NodeData> _history;
    private final HashMap<String, int[]> _generatedSpaces;

    public OperationsHistory()
    {
        _history = new Node<>(new NodeData(null, rootID, Functions.Root, "", null));
        _generatedSpaces = new HashMap<>();
        _generatedSpaces.put("Root", null);
    }

    public Space GetSpace(String spaceID)
    {
        return null;//_generated_Spaces.get(ID);
    }

    public void Insert(String parentSpaceID, NodeData node)
    {
        int[] parentPath = null;
        if (!parentSpaceID.equals(rootID))
            parentPath = OperationsHistory.this.FindParentPath(parentSpaceID);

        _generatedSpaces.put(node.GetSpaceID(), parentPath); // maybe this is not needed, maybe I can totaly remove _generated_spaces.

        AddOperation(parentPath, node);
    }

    private void AddOperation(int[] parent, NodeData nodeData)
    {
        Node<NodeData> node = _history;

        if (parent == null)
        {
            node.AddChild(nodeData);
        }
        else
        {
            for (int i = 0 ; i < parent.length ; i++)
                node = GetChild(parent[i], node);

            node.AddChild(nodeData);
        }
    }

    public Node<NodeData> GetTree()
    {
        return _history;
    }

    private Node<NodeData> GetChild(int index, Node<NodeData> parent)
    {
        return parent.GetChildren().get(index);
    }

    public int[] FindParentPath(String parentID)
    {
        _parentIDToSearch = parentID;
        return FindParentPath(_history);
    }

    public int[] FindParentPath(Node<NodeData> parent)
    {
        int[] path = null;

        if (parent.GetData().GetSpaceID().equals(_parentIDToSearch))
        {
            return new int[]
            {
                0
            };
        }
        else if (!parent.IsLeaf())
        {
            List<Node<NodeData>> children = parent.GetChildren();

            for (int i = 0 ; i < children.size() ; i++)
            {
                if (children.get(i).GetData().GetSpaceID().equals(_parentIDToSearch))
                {
                    return new int[]
                    {
                        i
                    };
                }
                else
                {
                    path = FindParentPath(children.get(i));

                    if (path != null)
                    {
                        return Concatenate(new int[]
                        {
                            i
                        }, path);
                    }
                }
            }
        }

        return path;
    }

    private int[] Concatenate(int[] A, int[] B)
    {
        int[] C = new int[A.length + B.length];
        System.arraycopy(A, 0, C, 0, A.length);
        System.arraycopy(B, 0, C, A.length, B.length);
        return C;
    }

    public NodeData[] FindTransformationPath(String spaceID)
    {
        _parentIDToSearch = spaceID;

        return FindTransformationPath(_history);
    }

    private NodeData[] FindTransformationPath(Node<NodeData> parent)
    {
        NodeData[] path = null;

        if (parent.GetData().GetSpaceID().equals(_parentIDToSearch))
        {
            return new NodeData[]
            {
                parent.GetData()
            };
        }
        else if (!parent.IsLeaf())
        {
            List<Node<NodeData>> children = parent.GetChildren();

            for (int i = 0 ; i < children.size() ; i++)
            {
                if (children.get(i).GetData().GetSpaceID().equals(_parentIDToSearch))
                {
                    return new NodeData[]
                    {
                        children.get(i).GetData()
                    };
                }
                else
                {
                    path = FindTransformationPath(children.get(i));

                    if (path != null)
                    {
                        return ConcatenatePath(new NodeData[]
                        {
                            children.get(i).GetData()
                        }, path);
                    }
                }
            }
        }

        return path;
    }

    private NodeData[] ConcatenatePath(NodeData[] A, NodeData[] B)
    {
        NodeData[] C = new NodeData[A.length + B.length];
        System.arraycopy(A, 0, C, 0, A.length);
        System.arraycopy(B, 0, C, A.length, B.length);
        return C;
    }
}
