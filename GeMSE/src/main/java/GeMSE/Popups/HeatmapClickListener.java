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
package GeMSE.Popups;

import GeMSE.GlobalVariables;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

/**
 *
 * @author Vahid Jalili
 */
public class HeatmapClickListener extends MouseAdapter
{
    @Override
    public void mousePressed(MouseEvent e)
    {
        if (e.isPopupTrigger() && !GlobalVariables.disablePopups)
            Popup(e);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (e.isPopupTrigger() && !GlobalVariables.disablePopups)
            Popup(e);
    }

    private void Popup(MouseEvent e)
    {
        PopupMenu menu = new PopupMenu((JComponent) e.getComponent(), PopupMenuType.Heatmap);
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}
