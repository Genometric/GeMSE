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
package GeMSE.Views;

import GeMSE.GS.Space;
import GeMSE.GeMSE;
import GeMSE.Popups.HeatmapClickListener;
import GeMSE.Visualization.HeatMap;
import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vahid Jalili
 */
public class HeatmapView extends javax.swing.JPanel
{

    /**
     * Creates new form HeatmapView
     */
    public HeatmapView()
    {
        initComponents();
        plot.addMouseListener(new HeatmapClickListener());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        plot = new javax.swing.JLabel();

        plot.setBackground(new java.awt.Color(255, 0, 102));
        plot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        plot.setText("No Plot");
        plot.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jScrollPane1.setViewportView(plot);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel plot;
    // End of variables declaration//GEN-END:variables

    public Dimension Plot(Space space, Dimension containerSize, Boolean autoDetermineCellDimension, Dimension setDimension)
    {
        plot.setText("");
        Dimension calculatedCellDimension = setDimension;
        
        Dimension dimension = new Dimension(containerSize.width - 75, containerSize.height - 75);
        
        HeatMap.InitializeChart(space);
        
        if (autoDetermineCellDimension)
        {
            dimension.height = (int) Math.round((dimension.height - (HeatMap.GetChartMargin() * 8.00)) / space.content.length);
            dimension.width = (int) Math.round((dimension.width - (HeatMap.GetChartMargin() * 5.0)) / space.content[0].length);
            
            if (dimension.height < 1) dimension.height = 1;
            if (dimension.width < 1) dimension.width = 1;
            
            calculatedCellDimension = new Dimension(dimension.width, dimension.height);
        }
        else
        {
            dimension = setDimension;
        }
        
        try
        {
            plot.setIcon(new javax.swing.ImageIcon(HeatMap.GetHeatMap(dimension)));
        }
        catch (IOException ex)
        {
            Logger.getLogger(GeMSE.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return calculatedCellDimension;
    }
}
