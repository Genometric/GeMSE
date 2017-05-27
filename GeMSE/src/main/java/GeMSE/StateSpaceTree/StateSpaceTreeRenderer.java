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
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Vahid Jalili
 */
public class StateSpaceTreeRenderer implements TreeCellRenderer
{
    Color backgroundSelectionColor;
    Color backgroundNonSelectionColor;

    JLabel operationL = new JLabel("operation");
    JLabel titleL = new JLabel("title");

    JPanel renderer = new JPanel();

    DefaultTreeCellRenderer default_Renderer = new DefaultTreeCellRenderer();

    public StateSpaceTreeRenderer()
    {
        backgroundSelectionColor = default_Renderer.getBackgroundSelectionColor();
        backgroundNonSelectionColor = default_Renderer.getBackgroundNonSelectionColor();

        operationL.setForeground(Color.BLUE);
        titleL.setForeground(Color.RED);

        titleL.setFont(new Font(titleL.getFont().getFamily(), Font.BOLD, titleL.getFont().getSize()));

        renderer.add(operationL);
        renderer.add(titleL);
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
    {
        Component returnValue = null;

        if ((value != null) && (value instanceof DefaultMutableTreeNode))
        {
            Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
            if (userObject instanceof NodeData)
            {
                NodeData e = (NodeData) userObject;

                operationL.setText(e.GetOperation().toString() + " ");
                titleL.setText(e.GetUserDefinedTitle());

                operationL.setSize(operationL.getWidth(), 10);
                titleL.setSize(titleL.getWidth(), 10);
                renderer.setSize(renderer.getWidth(), 10);

                if (selected)
                {
                    operationL.setForeground(Color.WHITE);
                    titleL.setForeground(Color.CYAN);

                    renderer.setBackground(backgroundSelectionColor);
                }
                else
                {
                    operationL.setForeground(Color.getHSBColor(0.88f, 1.0f, 1.0f));
                    titleL.setForeground(Color.getHSBColor(0.74f, 1.0f, 0.6f));

                    renderer.setOpaque(false);
                }

                renderer.setEnabled(tree.isEnabled());
                returnValue = renderer;
            }
        }
        if (returnValue == null)
        {
            returnValue = default_Renderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        }
        return returnValue;
    }
}
