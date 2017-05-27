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
package GeMSE.OperationsPanels;

import GeMSE.GS.Operations.Functions;
import GeMSE.OperationsOptions.ClusteringOptions;
import GeMSE.OperationsOptions.DiscretizeOptions;
import GeMSE.OperationsOptions.SelectOptions;
import GeMSE.OperationsOptions.SortOptions;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Vahid Jalili
 */
public class OperationsOptions
{
    JPanel panelReturned = null;

    public JPanel GetPanel(Functions function, Object iniValues)
    {
        switch (function)
        {
            case Extract:
                ExtractOptionsPanel extractPanel = new ExtractOptionsPanel();
                extractPanel.SetValues((SelectOptions) iniValues);
                panelReturned = extractPanel;
                break;

            case Rewrite:
                RewriteOptionsPanel rewritePanel = new RewriteOptionsPanel();
                rewritePanel.SetValues((DiscretizeOptions) iniValues);
                panelReturned = rewritePanel;
                break;

            case Discretize:
                DiscretizeOptionsPanel dscrtzPanel = new DiscretizeOptionsPanel();
                dscrtzPanel.SetValues((DiscretizeOptions) iniValues);
                panelReturned = dscrtzPanel;
                break;

            case Clustering:
                ClusteringOptionsPanel clustgPanel = new ClusteringOptionsPanel();
                clustgPanel.SetValues((ClusteringOptions) iniValues);
                panelReturned = clustgPanel;
                break;

            case Sort:
                SortOptionsPanel sortPanel = new SortOptionsPanel();
                sortPanel.SetValues((SortOptions) iniValues);
                panelReturned = sortPanel;
                break;
        }

        JPanel nee = new JPanel();
        nee.setBackground(Color.red);

        return panelReturned;
    }

    public Object GetValues()
    {
        if (panelReturned instanceof ExtractOptionsPanel)
        {
            return ((ExtractOptionsPanel) panelReturned).GetValues();
        }
        else if (panelReturned instanceof RewriteOptionsPanel)
        {
            return ((RewriteOptionsPanel) panelReturned).GetValues();
        }
        else if (panelReturned instanceof ClusteringOptionsPanel)
        {
            return ((ClusteringOptionsPanel) panelReturned).GetValues();
        }
        else if (panelReturned instanceof SortOptionsPanel)
        {
            return ((SortOptionsPanel) panelReturned).GetValues();
        }
        else if (panelReturned instanceof DiscretizeOptionsPanel)
        {
            return ((DiscretizeOptionsPanel) panelReturned).GetValues();
        }
        else
        {
            return null;
        }
    }
}
