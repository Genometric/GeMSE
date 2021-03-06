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
package GeMSE.GS.Analysis.Stats;

import GeMSE.GS.History.NodeData;
import GeMSE.GS.TreeInfo;
import GeMSE.GeMSE;
import GeMSE.GlobalVariables;
import GeMSE.IO.OpenWebpage;
import GeMSE.Popups.TreeClickListener;
import GeMSE.StateSpaceTree.A2MConverter;
import GeMSE.StateSpaceTree.StateSpaceTreeRenderer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author Vahid Jalili
 */
public class StatisticalInferenceWindow extends javax.swing.JFrame
{
    public StatisticalInferenceWindow()
    {
        initComponents();
        _sysCV = true;
        _tTestPanelOneSample = new OneSampleTTestPanel();
        _tTestPanelTwoSample = new TwoSampleTTestPanel();
        _covariancePanelOneSample = new OneSampleCovariancePanel();
        _covariancePanelTwoSample = new TwoSampleCovariancePanel();
        _pearsonPanelOneSample = new OneSamplePearsonsCorrelationPanel();
        _pearsonPanelTwoSample = new TwoSamplePearsonsCorrelationPanel();
        _pcaPanelOneSample = new OneSamplePCAPanel();

        _treeA = new JTree();
        _treeA.addMouseListener(new TreeClickListener());
        _treeA.addTreeSelectionListener(this::TreeASelectionChanged);
        CreateStateTransitionTree(_treeA, Sample1SP);
        Sample1CB.setEnabled(false);
        _treeA.setEnabled(false);

        _treeB = new JTree();
        _treeB.addMouseListener(new TreeClickListener());
        _treeB.addTreeSelectionListener(this::TreeBSelectionChanged);
        CreateStateTransitionTree(_treeB, Sample2SP);
        Sample2CB.setEnabled(false);
        _treeB.setEnabled(false);

        AnalysisCB.addItem(_cctT);
        AnalysisCB.addItem(_shtT);
        AnalysisCB.setSelectedIndex(-1);
        TestCB.setEnabled(false);
        _sysCV = false;
    }

    private Boolean _sysCV;
    private final JTree _treeA;
    private final JTree _treeB;

    private final String _shtT = "Statistical hypothesis testing";
    private final String _cctT = "Covariance and correlation";

    private final String _ttestT = "Student's t-test";

    private final String _covT = "Covariance";
    private final String _pcoT = "Pearson's product-moment correlation coefficients";
    private final String _pcaT = "Principal component analysis";


    private final OneSampleTTestPanel _tTestPanelOneSample;
    private final TwoSampleTTestPanel _tTestPanelTwoSample;
    private final OneSampleCovariancePanel _covariancePanelOneSample;
    private final TwoSampleCovariancePanel _covariancePanelTwoSample;
    private final OneSamplePearsonsCorrelationPanel _pearsonPanelOneSample;
    private final TwoSamplePearsonsCorrelationPanel _pearsonPanelTwoSample;
    private final OneSamplePCAPanel _pcaPanelOneSample;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel3 = new javax.swing.JPanel();
        TestDescriptionL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TestDescriptionTP = new javax.swing.JTextPane();
        TestCB = new javax.swing.JComboBox<>();
        ChooseTestL = new javax.swing.JLabel();
        ChooseAnalysisL = new javax.swing.JLabel();
        AnalysisCB = new javax.swing.JComboBox<>();
        ResultsSP = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        Sample1CB = new javax.swing.JCheckBox();
        Sample1SP = new javax.swing.JScrollPane();
        Sample2SP = new javax.swing.JScrollPane();
        Sample2CB = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        ExitMI = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        OnlineSupportMI = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        OnlineDocsMI = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        TestDescriptionL.setText("Description");

        TestDescriptionTP.setBackground(new java.awt.Color(238, 238, 238));
        TestDescriptionTP.setBorder(null);
        TestDescriptionTP.setFont(new java.awt.Font("Lucida Grande", 2, 12)); // NOI18N
        TestDescriptionTP.setCaretColor(new java.awt.Color(238, 238, 238));
        TestDescriptionTP.setDropTarget(null);
        TestDescriptionTP.setFocusTraversalKeysEnabled(false);
        TestDescriptionTP.setFocusable(false);
        jScrollPane1.setViewportView(TestDescriptionTP);

        TestCB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                TestCBActionPerformed(evt);
            }
        });

        ChooseTestL.setText("Choose a test");

        ChooseAnalysisL.setText("Choose an analysis category");

        AnalysisCB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                AnalysisCBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(TestCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(TestDescriptionL)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(ChooseTestL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AnalysisCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ChooseAnalysisL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ChooseAnalysisL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AnalysisCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ChooseTestL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TestCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TestDescriptionL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Sample1CB.setText("Sample 1");
        Sample1CB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                Sample1CBActionPerformed(evt);
            }
        });

        Sample2CB.setText("Sample 2");
        Sample2CB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                Sample2CBActionPerformed(evt);
            }
        });

        jLabel1.setText("Choose datasets (samples) for the test");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Sample1SP, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Sample1CB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Sample2CB)
                            .addComponent(Sample2SP, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Sample1CB)
                    .addComponent(Sample2CB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Sample1SP, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                    .addComponent(Sample2SP))
                .addContainerGap())
        );

        jMenu1.setText(" File ");

        ExitMI.setText("     Exit     ");
        ExitMI.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ExitMIActionPerformed(evt);
            }
        });
        jMenu1.add(ExitMI);

        jMenuBar1.add(jMenu1);

        jMenu2.setText(" Help ");

        OnlineSupportMI.setText("     Online Support     ");
        OnlineSupportMI.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                OnlineSupportMIActionPerformed(evt);
            }
        });
        jMenu2.add(OnlineSupportMI);
        jMenu2.add(jSeparator1);

        OnlineDocsMI.setText("     Online Docs     ");
        OnlineDocsMI.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                OnlineDocsMIActionPerformed(evt);
            }
        });
        jMenu2.add(OnlineDocsMI);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ResultsSP, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ResultsSP))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TestCBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_TestCBActionPerformed
    {//GEN-HEADEREND:event_TestCBActionPerformed
        if (_sysCV) return;
        PerformStatInference();
    }//GEN-LAST:event_TestCBActionPerformed

    private void Sample1CBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_Sample1CBActionPerformed
    {//GEN-HEADEREND:event_Sample1CBActionPerformed
        if (_sysCV) return;
        Sample1SP.setEnabled(Sample1CB.isSelected());
        _treeA.setEnabled(Sample1CB.isSelected());
        PerformStatInference();
    }//GEN-LAST:event_Sample1CBActionPerformed

    private void Sample2CBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_Sample2CBActionPerformed
    {//GEN-HEADEREND:event_Sample2CBActionPerformed
        if (_sysCV) return;
        Sample2SP.setEnabled(Sample2CB.isSelected());
        _treeB.setEnabled(Sample2CB.isSelected());
        PerformStatInference();
    }//GEN-LAST:event_Sample2CBActionPerformed

    private void AnalysisCBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AnalysisCBActionPerformed
    {//GEN-HEADEREND:event_AnalysisCBActionPerformed
        if (AnalysisCB.getSelectedIndex() == -1 || _sysCV) return;
        TestCB.removeAllItems();
        String selectedAnalysis = (String) AnalysisCB.getSelectedItem();
        if (selectedAnalysis.equals(_shtT))
        {
            TestCB.addItem(_ttestT);
        }
        else if (selectedAnalysis.equals(_cctT))
        {
            TestCB.addItem(_covT);
            TestCB.addItem(_pcoT);
            TestCB.addItem(_pcaT);
        }
        TestCB.setSelectedIndex(0);
        TestCB.setEnabled(true);

        Boolean t = _sysCV;
        _sysCV = true;
        Sample1CB.setEnabled(true);
        Sample2CB.setEnabled(true);
        _sysCV = t;
    }//GEN-LAST:event_AnalysisCBActionPerformed

    private void ExitMIActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ExitMIActionPerformed
    {//GEN-HEADEREND:event_ExitMIActionPerformed
        dispose();
    }//GEN-LAST:event_ExitMIActionPerformed

    private void OnlineDocsMIActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_OnlineDocsMIActionPerformed
    {//GEN-HEADEREND:event_OnlineDocsMIActionPerformed
        try
        {
            OpenWebpage.open(new URI("https://github.com/Genometric/GeMSE/wiki/Statistical-Inference"));
        }
        catch (URISyntaxException ex)
        {
            Logger.getLogger(GeMSE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_OnlineDocsMIActionPerformed

    private void OnlineSupportMIActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_OnlineSupportMIActionPerformed
    {//GEN-HEADEREND:event_OnlineSupportMIActionPerformed
        try
        {
            OpenWebpage.open(new URI("https://github.com/Genometric/GeMSE/issues"));
        }
        catch (URISyntaxException ex)
        {
            Logger.getLogger(GeMSE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_OnlineSupportMIActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(StatisticalInferenceWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(StatisticalInferenceWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(StatisticalInferenceWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(StatisticalInferenceWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new StatisticalInferenceWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> AnalysisCB;
    private javax.swing.JLabel ChooseAnalysisL;
    private javax.swing.JLabel ChooseTestL;
    private javax.swing.JMenuItem ExitMI;
    private javax.swing.JMenuItem OnlineDocsMI;
    private javax.swing.JMenuItem OnlineSupportMI;
    private javax.swing.JScrollPane ResultsSP;
    private javax.swing.JCheckBox Sample1CB;
    private javax.swing.JScrollPane Sample1SP;
    private javax.swing.JCheckBox Sample2CB;
    private javax.swing.JScrollPane Sample2SP;
    private javax.swing.JComboBox<String> TestCB;
    private javax.swing.JLabel TestDescriptionL;
    private javax.swing.JTextPane TestDescriptionTP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables

    private void PerformStatInference()
    {
        String selectedTest = (String) TestCB.getSelectedItem();
        if (selectedTest == null)
        {
            ResultsSP.setViewportView(null);
            ResultsSP.repaint();
            return;
        }
        if (selectedTest.equals(_ttestT))
        {
            Sample2CB.setEnabled(true);
            Sample2SP.setEnabled(true);
            _treeB.setEnabled(true);
            if (Sample1CB.isSelected() && Sample2CB.isSelected())
            {
                ResultsSP.setViewportView(_tTestPanelTwoSample);
                _tTestPanelTwoSample.RunAnalysis(
                        TreeInfo.GetSelectedNodeAsVector(_treeA),
                        TreeInfo.GetSelectedNodeAsVector(_treeB));
            }
            else if (Sample1CB.isSelected())
            {
                ResultsSP.setViewportView(_tTestPanelOneSample);
                _tTestPanelOneSample.RunAnalysis(
                        TreeInfo.GetSelectedNodeAsVector(_treeA));
            }
            else if (Sample2CB.isSelected())
            {
                ResultsSP.setViewportView(_tTestPanelOneSample);
                _tTestPanelOneSample.RunAnalysis(
                        TreeInfo.GetSelectedNodeAsVector(_treeB));
            }
            else
            {
                ResultsSP.setViewportView(null);
                ResultsSP.repaint();
            }
        }
        else if (selectedTest.equals(_covT))
        {
            Sample2CB.setEnabled(true);
            Sample2SP.setEnabled(true);
            _treeB.setEnabled(true);
            if (Sample1CB.isSelected() && Sample2CB.isSelected())
            {
                ResultsSP.setViewportView(_covariancePanelTwoSample);
                _covariancePanelTwoSample.RunAnalysis(
                        TreeInfo.GetSelectedNodeAsVector(_treeA),
                        TreeInfo.GetSelectedNodeAsVector(_treeB));
            }
            else if (Sample1CB.isSelected())
            {
                ResultsSP.setViewportView(_covariancePanelOneSample);
                _covariancePanelOneSample.RunAnalysis(
                        TreeInfo.GetSelectedNodeAsMatrix(_treeA));
            }
            else if (Sample2CB.isSelected())
            {
                ResultsSP.setViewportView(_covariancePanelOneSample);
                _covariancePanelOneSample.RunAnalysis(
                        TreeInfo.GetSelectedNodeAsMatrix(_treeB));
            }
            else
            {
                ResultsSP.setViewportView(null);
                ResultsSP.repaint();
            }
        }
        else if (selectedTest.equals(_pcoT))
        {
            Sample2CB.setEnabled(true);
            Sample2SP.setEnabled(true);
            _treeB.setEnabled(true);
            if (Sample1CB.isSelected() && Sample2CB.isSelected())
            {
                ResultsSP.setViewportView(_pearsonPanelTwoSample);
                _pearsonPanelTwoSample.RunAnalysis(
                        TreeInfo.GetSelectedNodeAsVector(_treeA),
                        TreeInfo.GetSelectedNodeAsVector(_treeB));
            }
            else if (Sample1CB.isSelected())
            {
                ResultsSP.setViewportView(_pearsonPanelOneSample);
                _pearsonPanelOneSample.RunAnalysis(
                        TreeInfo.GetSelectedNodeAsMatrix(_treeA));
            }
            else if (Sample2CB.isSelected())
            {
                ResultsSP.setViewportView(_pearsonPanelOneSample);
                _pearsonPanelOneSample.RunAnalysis(
                        TreeInfo.GetSelectedNodeAsMatrix(_treeB));
            }
            else
            {
                ResultsSP.setViewportView(null);
                ResultsSP.repaint();
            }
        }
        else if (selectedTest.equals(_pcaT))
        {
            Sample2CB.setEnabled(false);
            Sample2CB.setSelected(false);
            Sample2SP.setEnabled(false);
            _treeB.setEnabled(false);
            if (Sample1CB.isSelected() && Sample2CB.isSelected())
            {
                ResultsSP.setViewportView(null);
                ResultsSP.repaint();
                /*ResultsSP.setViewportView(_pcaPanelOneSample);
                _pcaPanelOneSample.RunAnalysis(
                        TreeInfo.GetSelectedNodeAsMatrix(_treeA),
                        TreeInfo.GetSelectedNodeLabel(_treeA),
                        TreeInfo.GetSelectedNodeRowLabels(_treeA),
                        TreeInfo.GetSelectedNodeColLabels(_treeA),
                        TreeInfo.GetSelectedNodeAsMatrix(_treeB),
                        TreeInfo.GetSelectedNodeLabel(_treeB),
                        TreeInfo.GetSelectedNodeRowLabels(_treeB),
                        TreeInfo.GetSelectedNodeColLabels(_treeB));*/
            }
            else if (Sample1CB.isSelected())
            {
                ResultsSP.setViewportView(_pcaPanelOneSample);
                _pcaPanelOneSample.RunAnalysis(
                        TreeInfo.GetSelectedNodeAsMatrix(_treeA),
                        TreeInfo.GetSelectedNodeLabel(_treeA),
                        TreeInfo.GetSelectedNodeRowLabels(_treeA),
                        TreeInfo.GetSelectedNodeColLabels(_treeA));
            }
            else if (Sample2CB.isSelected())
            {
                ResultsSP.setViewportView(_pcaPanelOneSample);
                _pcaPanelOneSample.RunAnalysis(
                        TreeInfo.GetSelectedNodeAsMatrix(_treeB),
                        TreeInfo.GetSelectedNodeLabel(_treeB),
                        TreeInfo.GetSelectedNodeRowLabels(_treeB),
                        TreeInfo.GetSelectedNodeColLabels(_treeB));
            }
            else
            {
                ResultsSP.setViewportView(null);
                ResultsSP.repaint();
            }
        }
    }

    private void TreeASelectionChanged(TreeSelectionEvent e)
    {
        PerformStatInference();
    }

    private void TreeBSelectionChanged(TreeSelectionEvent e)
    {
        PerformStatInference();
    }

    private void CreateStateTransitionTree(JTree tree, JScrollPane pane)
    {
        A2MConverter converter = new A2MConverter();

        DefaultMutableTreeNode previouslySelectedNode = null;

        if (tree != null)
            previouslySelectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        DefaultMutableTreeNode newTree = converter.GetNodes(GlobalVariables.space.GetTree());

        tree.setModel(new DefaultTreeModel(newTree));
        tree.setOpaque(false);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setRootVisible(true);
        TreeCellRenderer renderer = new StateSpaceTreeRenderer();
        tree.setCellRenderer(renderer);

        pane.setViewportView(tree);

        if (previouslySelectedNode != null)
        {
            TreePath newPath = converter.GetPath((NodeData) previouslySelectedNode.getUserObject(), newTree);
            tree.expandPath(newPath);
            tree.setSelectionPath(newPath);
            tree.scrollPathToVisible(tree.getSelectionPath());
            tree.setScrollsOnExpand(true);
            tree.setExpandsSelectedPaths(true);
        }
    }
}
