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
package GeMSE.GS;

import GeMSE.GS.History.NodeData;
import GeMSE.GlobalVariables;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Vahid Jalili
 */
public class TreeInfo
{
    public static double[] GetSelectedNodeAsVector(JTree tree)
    {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selectedNode != null)
        {
            NodeData nodeData = (NodeData) selectedNode.getUserObject();
            return GlobalVariables.space.GetSpace(nodeData.GetSpaceID()).GetVector();
        }
        else
        {
            return new double[0];
        }
    }

    public static double[][] GetSelectedNodeAsMatrix(JTree tree)
    {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selectedNode != null)
        {
            NodeData nodeData = (NodeData) selectedNode.getUserObject();
            return GlobalVariables.space.GetSpace(nodeData.GetSpaceID()).GetContent(true);
        }
        else
        {
            return new double[0][0];
        }
    }

    public static String GetSelectedNodeLabel(JTree tree)
    {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selectedNode != null)
            return ((NodeData) selectedNode.getUserObject()).GetUserDefinedTitle();
        else
            return "";
    }

    public static String[] GetSelectedNodeRowLabels(JTree tree)
    {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selectedNode != null)
        {
            NodeData nodeData = (NodeData) selectedNode.getUserObject();
            return GlobalVariables.space.GetSpace(nodeData.GetSpaceID()).rowTitle;
        }
        else
            return new String[0];
    }
    
    public static String[] GetSelectedNodeColLabels(JTree tree)
    {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selectedNode != null)
        {
            NodeData nodeData = (NodeData) selectedNode.getUserObject();
            return GlobalVariables.space.GetSpace(nodeData.GetSpaceID()).colTitle;
        }
        else
            return new String[0];
    }
}
