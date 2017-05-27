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

import GeMSE.OperationsOptions.SelectOptions;

/**
 *
 * @author Vahid Jalili
 */
public class ExtractOptionsPanel extends javax.swing.JPanel
{

    /**
     * Creates new form Select_Options
     */
    public ExtractOptionsPanel()
    {
        initComponents();
    }

    private int _colCount;
    private int _rowCount;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        Select_Panel = new javax.swing.JPanel();
        Rows_Range_L = new javax.swing.JLabel();
        RowsFrom_TB = new javax.swing.JTextField();
        RowsTo_TB = new javax.swing.JTextField();
        Columns_Range_L = new javax.swing.JLabel();
        ColumnsFrom_TB = new javax.swing.JTextField();
        ColumnsTo_TB = new javax.swing.JTextField();
        From_L = new javax.swing.JLabel();
        To_L = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(258, 308));
        setMinimumSize(new java.awt.Dimension(258, 308));
        setPreferredSize(new java.awt.Dimension(258, 308));
        setLayout(new java.awt.BorderLayout());

        Select_Panel.setMaximumSize(new java.awt.Dimension(200, 100));
        Select_Panel.setMinimumSize(new java.awt.Dimension(200, 80));
        Select_Panel.setPreferredSize(new java.awt.Dimension(200, 100));

        Rows_Range_L.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Rows_Range_L.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Rows_Range_L.setText("Rows");
        Rows_Range_L.setMaximumSize(new java.awt.Dimension(60, 20));
        Rows_Range_L.setMinimumSize(new java.awt.Dimension(60, 20));
        Rows_Range_L.setPreferredSize(new java.awt.Dimension(60, 20));

        RowsFrom_TB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RowsFrom_TB.setText("0");

        RowsTo_TB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        RowsTo_TB.setText("100");

        Columns_Range_L.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Columns_Range_L.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Columns_Range_L.setText("Columns");
        Columns_Range_L.setMaximumSize(new java.awt.Dimension(60, 20));
        Columns_Range_L.setMinimumSize(new java.awt.Dimension(60, 20));
        Columns_Range_L.setPreferredSize(new java.awt.Dimension(60, 20));

        ColumnsFrom_TB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ColumnsFrom_TB.setText("0");

        ColumnsTo_TB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ColumnsTo_TB.setText("100");

        From_L.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        From_L.setText("From");
        From_L.setMaximumSize(new java.awt.Dimension(60, 20));
        From_L.setMinimumSize(new java.awt.Dimension(60, 20));
        From_L.setPreferredSize(new java.awt.Dimension(60, 20));

        To_L.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        To_L.setText("To");
        To_L.setMaximumSize(new java.awt.Dimension(60, 20));
        To_L.setMinimumSize(new java.awt.Dimension(60, 20));
        To_L.setPreferredSize(new java.awt.Dimension(60, 20));

        javax.swing.GroupLayout Select_PanelLayout = new javax.swing.GroupLayout(Select_Panel);
        Select_Panel.setLayout(Select_PanelLayout);
        Select_PanelLayout.setHorizontalGroup(
            Select_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Select_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Select_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(Select_PanelLayout.createSequentialGroup()
                        .addComponent(Columns_Range_L, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(ColumnsFrom_TB, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ColumnsTo_TB, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Select_PanelLayout.createSequentialGroup()
                        .addComponent(Rows_Range_L, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Select_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(RowsFrom_TB)
                            .addComponent(From_L, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Select_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(To_L, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(RowsTo_TB))))
                .addContainerGap())
        );
        Select_PanelLayout.setVerticalGroup(
            Select_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Select_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Select_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(From_L, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(To_L, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(Select_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(RowsTo_TB)
                    .addComponent(RowsFrom_TB)
                    .addComponent(Rows_Range_L, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(Select_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ColumnsTo_TB)
                    .addComponent(Columns_Range_L, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ColumnsFrom_TB))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        add(Select_Panel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ColumnsFrom_TB;
    private javax.swing.JTextField ColumnsTo_TB;
    private javax.swing.JLabel Columns_Range_L;
    private javax.swing.JLabel From_L;
    private javax.swing.JTextField RowsFrom_TB;
    private javax.swing.JTextField RowsTo_TB;
    private javax.swing.JLabel Rows_Range_L;
    private javax.swing.JPanel Select_Panel;
    private javax.swing.JLabel To_L;
    // End of variables declaration//GEN-END:variables

    public SelectOptions GetValues()
    {
        if (Integer.valueOf(RowsTo_TB.getText()) > _rowCount)
            RowsTo_TB.setText(String.valueOf(_rowCount));

        if (Integer.valueOf(ColumnsTo_TB.getText()) > _colCount)
            ColumnsTo_TB.setText(String.valueOf(_colCount));

        SelectOptions options
                      = new SelectOptions(
                        Integer.valueOf(ColumnsFrom_TB.getText()),
                        Integer.valueOf(ColumnsTo_TB.getText()),
                        Integer.valueOf(RowsFrom_TB.getText()),
                        Integer.valueOf(RowsTo_TB.getText()));
        return options;
    }

    public void SetValues(SelectOptions values)
    {
        if (values != null)
        {
            ColumnsFrom_TB.setText(String.valueOf(values.ColumnFrom));
            ColumnsTo_TB.setText(String.valueOf(values.ColumnTo));
            RowsFrom_TB.setText(String.valueOf(values.RowFrom));
            RowsTo_TB.setText(String.valueOf(values.RowTo));
        }
        else
        {
            ColumnsFrom_TB.setText("0");
            ColumnsTo_TB.setText("0");
            RowsFrom_TB.setText("0");
            RowsTo_TB.setText("0");
        }

        _colCount = Integer.valueOf(ColumnsTo_TB.getText());
        _rowCount = Integer.valueOf(RowsTo_TB.getText());
    }
}