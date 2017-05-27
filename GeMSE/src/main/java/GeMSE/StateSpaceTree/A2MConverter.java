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

import javax.swing.tree.DefaultMutableTreeNode;
import GeMSE.GS.History.Node;
import GeMSE.GS.History.NodeData;
import javax.swing.tree.TreePath;

/**
 * This class provides a function that converts an ArrayList to a vector of
 * DefaultMutableTreeNode. This conversion is essential when the operations
 * history tree is intended to be displayed on a JTree. The operations history
 * tree is of class Node which in principle is an Arraylist of NodeData. The
 * function of this class converts Node into a vector representation of
 * DefaultMutableTreeNode format which is the recommended format of JTree.
 *
 * @author Vahid Jalili
 */
public class A2MConverter
{
    private NodeData _searchNode;

    public DefaultMutableTreeNode GetNodes(Node<NodeData> node)
    {
        if (!node.IsLeaf())
        {
            DefaultMutableTreeNode parent = new DefaultMutableTreeNode(node.GetData());
            for (Node<NodeData> child : node.GetChildren())
                parent.add(GetNodes(child));
            return parent;
        }
        else
        {
            return new DefaultMutableTreeNode(node.GetData());
        }
    }

    public TreePath GetPath(NodeData searchNode, DefaultMutableTreeNode tree)
    {
        _searchNode = searchNode;
        DefaultMutableTreeNode TreeNode = SearchPath(tree);
        return new TreePath(((DefaultMutableTreeNode) TreeNode.getLastChild()).getPath());
    }

    private DefaultMutableTreeNode SearchPath(DefaultMutableTreeNode tree)
    {
        DefaultMutableTreeNode rtv = null;

        if (((NodeData) tree.getUserObject()).equals(_searchNode))
        {
            return (DefaultMutableTreeNode) tree;
        }
        else
        {
            for (int i = 0 ; i < tree.getChildCount() ; i++)
            {
                NodeData child = (NodeData) ((DefaultMutableTreeNode) tree.getChildAt(i)).getUserObject();
                if (child.equals(_searchNode))
                    return (DefaultMutableTreeNode) tree.getChildAt(i);
                else
                    rtv = SearchPath((DefaultMutableTreeNode) tree.getChildAt(i));

                if (rtv != null)
                    return rtv;
            }
        }

        return rtv;
    }
}
