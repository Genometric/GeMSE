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

import GeMSE.GS.ClusteringFunctions;
import GeMSE.GS.Operations.Functions;
import GeMSE.GS.Space;
import ExternalLibraries.Cluster;
import GeMSE.OperationsOptions.ClusteringOptions.DistanceType;
import ExternalLibraries.DendrogramPanel;
import GeMSE.GlobalVariables;
import GeMSE.OperationsOptions.ClusteringOptions;
import GeMSE.Popups.DendrogramClickListener;
import java.util.HashMap;


/**
 *
 * @author Vahid Jalili
 */
public class OVDendrogramPanel extends javax.swing.JPanel
{

    /**
     * Creates new form Select_Options
     *
     * @param spaceID
     * @param command
     * @param parameters
     */
    public OVDendrogramPanel(String spaceID, Functions command, ClusteringOptions parameters)
    {
        initComponents();

        _spaceID = spaceID;
        _command = command;
        _parameters = parameters;
        ScaleValueDecimalsSlider.setValue(scaleValueDecimals);
        ScaleValueDecimalsValueLabel.setText(String.valueOf(scaleValueDecimals));

        _cF = new ClusteringFunctions(new Space(0, 0));

        if (_parameters.distanceType == DistanceType.Height)
            D2R_RB.setSelected(true);
        else
            DbC_RB.setSelected(true);


        double defaultDistance = _cF.GetSuggestedClusterCountBasedonH(_parameters.dendrogram, DistanceType.Height);
        _cF.SetDistanceToRoot(defaultDistance);
        D2R_TB.setText(String.valueOf(defaultDistance));
        NumberOfClustersLabelD2R.setText(String.valueOf(_cF.GetClusterCount(_parameters.dendrogram, DistanceType.Height)));

        defaultDistance = _cF.GetSuggestedClusterCountBasedonH(_parameters.dendrogram, DistanceType.Distance);
        _cF.SetDistanceToRoot(defaultDistance);
        DbC_TB.setText(String.valueOf(defaultDistance));
        NumberOfClustersLabelDBC.setText(String.valueOf(_cF.GetClusterCount(_parameters.dendrogram, DistanceType.Distance)));

        UpdateDendrogram();
    }

    private final String _spaceID;
    private final Functions _command;
    private boolean showDistances = false;
    private int scaleValueDecimals = 2;
    private final ClusteringFunctions _cF;
    private final ClusteringOptions _parameters;
    HashMap<String, String> _ID2Label;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        Options_Panel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        D2R_RB = new javax.swing.JRadioButton();
        D2R_TB = new javax.swing.JTextField();
        DbC_RB = new javax.swing.JRadioButton();
        DbC_TB = new javax.swing.JTextField();
        Clustering_Cut_L = new javax.swing.JLabel();
        NumberOfClustersLabelD2R = new javax.swing.JLabel();
        Clustering_Cut_L1 = new javax.swing.JLabel();
        NumberOfClustersLabelDBC = new javax.swing.JLabel();
        Clustering_Cut_L2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        ScaleValueDecimalsTXTLabel = new javax.swing.JLabel();
        ScaleValueDecimalsSlider = new javax.swing.JSlider();
        ScaleValueDecimalsValueLabel = new javax.swing.JLabel();
        ShowDistancesCB = new javax.swing.JCheckBox();
        Dendrogram_SCP = new javax.swing.JScrollPane();

        setMaximumSize(new java.awt.Dimension(258, 32767));
        setMinimumSize(new java.awt.Dimension(258, 510));
        setPreferredSize(new java.awt.Dimension(258, 520));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(10, 500));

        D2R_RB.setSelected(true);
        D2R_RB.setText("Distance to root (height) is :");
        D2R_RB.setPreferredSize(new java.awt.Dimension(165, 23));
        D2R_RB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                D2R_RBActionPerformed(evt);
            }
        });

        D2R_TB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        D2R_TB.setText("0");
        D2R_TB.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                D2R_TBKeyReleased(evt);
            }
        });

        DbC_RB.setText("Distance between children is :");
        DbC_RB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                DbC_RBActionPerformed(evt);
            }
        });

        DbC_TB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        DbC_TB.setText("0");
        DbC_TB.setEnabled(false);
        DbC_TB.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                DbC_TBKeyReleased(evt);
            }
        });

        Clustering_Cut_L.setText("Cut Clustering where :");

        NumberOfClustersLabelD2R.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NumberOfClustersLabelD2R.setText("0");

        Clustering_Cut_L1.setText("Number of clusters:");

        NumberOfClustersLabelDBC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        NumberOfClustersLabelDBC.setText("0");

        Clustering_Cut_L2.setText("Number of clusters:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(D2R_RB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DbC_RB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(D2R_TB, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(DbC_TB)))
                    .addComponent(Clustering_Cut_L))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Clustering_Cut_L1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NumberOfClustersLabelD2R, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Clustering_Cut_L2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NumberOfClustersLabelDBC, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Clustering_Cut_L)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(D2R_RB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D2R_TB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NumberOfClustersLabelD2R)
                    .addComponent(Clustering_Cut_L1))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DbC_RB)
                    .addComponent(DbC_TB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NumberOfClustersLabelDBC)
                    .addComponent(Clustering_Cut_L2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ScaleValueDecimalsTXTLabel.setText("Scale value decimals");

        ScaleValueDecimalsSlider.setMaximum(4);
        ScaleValueDecimalsSlider.setValue(2);
        ScaleValueDecimalsSlider.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                ScaleValueDecimalsSliderStateChanged(evt);
            }
        });

        ScaleValueDecimalsValueLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ScaleValueDecimalsValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ScaleValueDecimalsValueLabel.setText("0");

        ShowDistancesCB.setText("Show distances");
        ShowDistancesCB.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                ShowDistancesCBStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ScaleValueDecimalsTXTLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ScaleValueDecimalsSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScaleValueDecimalsValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ShowDistancesCB)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ShowDistancesCB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(ScaleValueDecimalsTXTLabel)
                        .addGap(9, 9, 9)
                        .addComponent(ScaleValueDecimalsSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ScaleValueDecimalsValueLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Options_Panel.add(jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Options_Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE)
            .addComponent(Dendrogram_SCP, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Options_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Dendrogram_SCP, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void D2R_RBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_D2R_RBActionPerformed
    {//GEN-HEADEREND:event_D2R_RBActionPerformed
        if (D2R_RB.isSelected() == true)
        {
            DbC_RB.setSelected(false);
            D2R_TB.setEnabled(true);
            DbC_TB.setEnabled(false);

            _cF.SetDistanceType(DistanceType.Height);
            _cF.SetDistanceToRoot(Double.valueOf(D2R_TB.getText()));
            NumberOfClustersLabelD2R.setText(String.valueOf((_cF.GetRequestedCluster(_parameters.dendrogram)).size()));
        }
    }//GEN-LAST:event_D2R_RBActionPerformed

    private void DbC_RBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_DbC_RBActionPerformed
    {//GEN-HEADEREND:event_DbC_RBActionPerformed
        if (DbC_RB.isSelected() == true)
        {
            D2R_RB.setSelected(false);
            D2R_TB.setEnabled(false);
            DbC_TB.setEnabled(true);

            _cF.SetDistanceType(DistanceType.Distance);
            _cF.SetDistanceToRoot(Double.valueOf(DbC_TB.getText()));
            NumberOfClustersLabelDBC.setText(String.valueOf((_cF.GetRequestedCluster(_parameters.dendrogram)).size()));
        }
    }//GEN-LAST:event_DbC_RBActionPerformed

    private void ScaleValueDecimalsSliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_ScaleValueDecimalsSliderStateChanged
    {//GEN-HEADEREND:event_ScaleValueDecimalsSliderStateChanged
        ScaleValueDecimalsValueLabel.setText(String.valueOf(ScaleValueDecimalsSlider.getValue()));
        scaleValueDecimals = ScaleValueDecimalsSlider.getValue();
        UpdateDendrogram();
    }//GEN-LAST:event_ScaleValueDecimalsSliderStateChanged

    private void ShowDistancesCBStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_ShowDistancesCBStateChanged
    {//GEN-HEADEREND:event_ShowDistancesCBStateChanged
        showDistances = ShowDistancesCB.isSelected();
        UpdateDendrogram();
    }//GEN-LAST:event_ShowDistancesCBStateChanged

    private void D2R_TBKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_D2R_TBKeyReleased
    {//GEN-HEADEREND:event_D2R_TBKeyReleased
        if (!D2R_TB.getText().trim().equals(""))
        {
            _cF.SetDistanceType(DistanceType.Height);
            _cF.SetDistanceToRoot(Double.valueOf(D2R_TB.getText()));
            NumberOfClustersLabelD2R.setText(String.valueOf((_cF.GetRequestedCluster(_parameters.dendrogram)).size()));
        }
    }//GEN-LAST:event_D2R_TBKeyReleased

    private void DbC_TBKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_DbC_TBKeyReleased
    {//GEN-HEADEREND:event_DbC_TBKeyReleased
        if (!DbC_TB.getText().trim().equals(""))
        {
            _cF.SetDistanceType(DistanceType.Distance);
            _cF.SetDistanceToRoot(Double.valueOf(DbC_TB.getText()));
            NumberOfClustersLabelDBC.setText(String.valueOf((_cF.GetRequestedCluster(_parameters.dendrogram)).size()));
        }
    }//GEN-LAST:event_DbC_TBKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Clustering_Cut_L;
    private javax.swing.JLabel Clustering_Cut_L1;
    private javax.swing.JLabel Clustering_Cut_L2;
    private javax.swing.JRadioButton D2R_RB;
    private javax.swing.JTextField D2R_TB;
    private javax.swing.JRadioButton DbC_RB;
    private javax.swing.JTextField DbC_TB;
    private javax.swing.JScrollPane Dendrogram_SCP;
    private javax.swing.JLabel NumberOfClustersLabelD2R;
    private javax.swing.JLabel NumberOfClustersLabelDBC;
    private javax.swing.JPanel Options_Panel;
    private javax.swing.JSlider ScaleValueDecimalsSlider;
    private javax.swing.JLabel ScaleValueDecimalsTXTLabel;
    private javax.swing.JLabel ScaleValueDecimalsValueLabel;
    private javax.swing.JCheckBox ShowDistancesCB;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables

    private void UpdateDendrogram()
    {
        DendrogramPanel dPanel = new DendrogramPanel();
        dPanel.setScaleValueDecimals(scaleValueDecimals);
        dPanel.setShowDistances(showDistances);

        dPanel.setModel(ConvertDendrogramNodeIDsToTitles());
        dPanel.setVisible(true);
        dPanel.addMouseListener(new DendrogramClickListener());
        Dendrogram_SCP.setViewportView(dPanel);
    }

    private Cluster ConvertDendrogramNodeIDsToTitles()
    {
        Space tmp = GlobalVariables.space.GetSpace(_spaceID);
        _ID2Label = new HashMap<>();

        if (null != _parameters.domain)
            switch (_parameters.domain)
            {
                case Rows:
                    tmp.UpdateRowsTitles();
                    for (int row = 0 ; row < tmp.rowsID.length ; row++)
                        _ID2Label.put(tmp.rowsID[row], tmp.rowTitle[row]);
                    break;

                case Columns:
                    tmp.UpdateColumnsTitles();
                    for (int col = 0 ; col < tmp.colsID.length ; col++)
                        _ID2Label.put(tmp.colsID[col], tmp.colTitle[col]);
                    break;

                default:
                    break;
            }

        Cluster rtv = _parameters.dendrogram.clone();
        if (rtv.isLeaf()) return rtv;
        RenameDendrogramNodes(rtv);
        return rtv;
    }

    private void RenameDendrogramNodes(Cluster parent)
    {
        if (parent.getChildren().isEmpty())
            parent.setName(_ID2Label.get(parent.getName()));
        else
            for (Cluster child : parent.getChildren())
                RenameDendrogramNodes(child);
    }
}
