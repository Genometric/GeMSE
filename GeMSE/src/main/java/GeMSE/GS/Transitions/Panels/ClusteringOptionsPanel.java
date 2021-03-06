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
package GeMSE.GS.Transitions.Panels;


import GeMSE.GlobalVariables;
import GeMSE.GS.Transitions.Options.ClusteringOptions;
import GeMSE.GS.Transitions.Options.ClusteringOptions.ClusteringDomains;
import GeMSE.GS.Transitions.Options.ClusteringOptions.ClusteringMethods;
import GeMSE.GS.Transitions.Options.ClusteringOptions.DistanceType;
import GeMSE.GS.Transitions.Options.ClusteringOptions.LinkageCriterias;
import GeMSE.GS.Transitions.Options.ClusteringOptions.Metrics;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import ExternalLibraries.Cluster;

/**
 *
 * @author Vahid Jalili
 */
public class ClusteringOptionsPanel extends javax.swing.JPanel
{
    /**
     * Creates new form Select_Options
     */
    public ClusteringOptionsPanel()
    {
        initComponents();

        ClusteringDomainButtonGroup.add(DomainRowRB);
        ClusteringDomainButtonGroup.add(DomainColRB);
        ClusteringDomainButtonGroup.add(Domain_BiD_RB);

        ((JLabel) ClusteringOptionCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel) LinkageCriteriaCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel) MetricCB.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
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

        ClusteringDomainButtonGroup = new javax.swing.ButtonGroup();
        Main_Panel = new javax.swing.JPanel();
        Domain_L = new javax.swing.JLabel();
        Clustering_Option_L = new javax.swing.JLabel();
        linkage_criteria_L = new javax.swing.JLabel();
        ClusteringOptionCB = new javax.swing.JComboBox();
        LinkageCriteriaCB = new javax.swing.JComboBox();
        DomainRowRB = new javax.swing.JRadioButton();
        DomainColRB = new javax.swing.JRadioButton();
        Domain_BiD_RB = new javax.swing.JRadioButton();
        Metric_L = new javax.swing.JLabel();
        MetricCB = new javax.swing.JComboBox();
        DisplayElbowOutputCB = new javax.swing.JCheckBox();

        setMaximumSize(new java.awt.Dimension(258, 32767));
        setMinimumSize(new java.awt.Dimension(258, 510));
        setPreferredSize(new java.awt.Dimension(258, 520));
        setLayout(new java.awt.BorderLayout());

        Main_Panel.setMaximumSize(new java.awt.Dimension(32767, 50));
        Main_Panel.setPreferredSize(new java.awt.Dimension(258, 400));

        Domain_L.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Domain_L.setText("Clustering Domain");
        Domain_L.setMaximumSize(new java.awt.Dimension(60, 20));
        Domain_L.setMinimumSize(new java.awt.Dimension(60, 20));
        Domain_L.setPreferredSize(new java.awt.Dimension(60, 20));

        Clustering_Option_L.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Clustering_Option_L.setText("Clustering Option");
        Clustering_Option_L.setMaximumSize(new java.awt.Dimension(60, 20));
        Clustering_Option_L.setMinimumSize(new java.awt.Dimension(60, 20));
        Clustering_Option_L.setPreferredSize(new java.awt.Dimension(60, 20));

        linkage_criteria_L.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        linkage_criteria_L.setText("Linkage Criteria");
        linkage_criteria_L.setMaximumSize(new java.awt.Dimension(60, 20));
        linkage_criteria_L.setMinimumSize(new java.awt.Dimension(60, 20));
        linkage_criteria_L.setPreferredSize(new java.awt.Dimension(60, 20));

        ClusteringOptionCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Agglomerative Hierarchical Clustering" }));

        LinkageCriteriaCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Single linkage", "Average linkage", "Complete linkage" }));

        DomainRowRB.setText("Rows (regions)");

        DomainColRB.setText("Columns (samples)");

        Domain_BiD_RB.setText("Biclustering");
        Domain_BiD_RB.setActionCommand("");

        Metric_L.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Metric_L.setText("Metric");
        Metric_L.setMaximumSize(new java.awt.Dimension(60, 20));
        Metric_L.setMinimumSize(new java.awt.Dimension(60, 20));
        Metric_L.setPreferredSize(new java.awt.Dimension(60, 20));

        MetricCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Euclidean Distance", "Manhattan Distance", "Earth Movers Distance", "Chebyshev Distance", "Canberra Distance", "Pearson Correlation Coefficient" }));

        DisplayElbowOutputCB.setText("Plot Elbow method data");

        javax.swing.GroupLayout Main_PanelLayout = new javax.swing.GroupLayout(Main_Panel);
        Main_Panel.setLayout(Main_PanelLayout);
        Main_PanelLayout.setHorizontalGroup(
            Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Domain_L, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Clustering_Option_L, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(linkage_criteria_L, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ClusteringOptionCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LinkageCriteriaCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, 246, Short.MAX_VALUE)
                    .addComponent(Metric_L, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MetricCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Main_PanelLayout.createSequentialGroup()
                        .addGroup(Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(DomainColRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DomainRowRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Domain_BiD_RB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(DisplayElbowOutputCB))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Main_PanelLayout.setVerticalGroup(
            Main_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Clustering_Option_L, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClusteringOptionCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(linkage_criteria_L, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LinkageCriteriaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(Domain_L, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DomainRowRB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DomainColRB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Domain_BiD_RB)
                .addGap(18, 18, 18)
                .addComponent(Metric_L, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MetricCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DisplayElbowOutputCB)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        add(Main_Panel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup ClusteringDomainButtonGroup;
    private javax.swing.JComboBox ClusteringOptionCB;
    private javax.swing.JLabel Clustering_Option_L;
    private javax.swing.JCheckBox DisplayElbowOutputCB;
    private javax.swing.JRadioButton DomainColRB;
    private javax.swing.JRadioButton DomainRowRB;
    private javax.swing.JRadioButton Domain_BiD_RB;
    private javax.swing.JLabel Domain_L;
    private javax.swing.JComboBox LinkageCriteriaCB;
    private javax.swing.JPanel Main_Panel;
    private javax.swing.JComboBox MetricCB;
    private javax.swing.JLabel Metric_L;
    private javax.swing.JLabel linkage_criteria_L;
    // End of variables declaration//GEN-END:variables

    public ClusteringOptions GetValues()
    {
        ClusteringOptions cOp = new ClusteringOptions();

        if (ClusteringOptionCB.getSelectedItem().equals("Agglomerative Hierarchical Clustering"))
            cOp.method = ClusteringMethods.AHC;

        if (DomainRowRB.isSelected() == true)
            cOp.domain = ClusteringDomains.Rows;
        else if (DomainColRB.isSelected() == true)
            cOp.domain = ClusteringDomains.Columns;
        else
            cOp.domain = ClusteringDomains.BiClustering;

        if (LinkageCriteriaCB.getSelectedItem().equals("Single linkage"))
            cOp.linkage = LinkageCriterias.Single;
        else if (LinkageCriteriaCB.getSelectedItem().equals("Average linkage"))
            cOp.linkage = LinkageCriterias.Average;
        else if (LinkageCriteriaCB.getSelectedItem().equals("Complete linkage"))
            cOp.linkage = LinkageCriterias.Complete;

        if (MetricCB.getSelectedItem().equals("Euclidean Distance"))
            cOp.metric = Metrics.EuclideanDistance;
        else if (MetricCB.getSelectedItem().equals("Manhattan Distance"))
            cOp.metric = Metrics.ManhattanDistance;
        else if (MetricCB.getSelectedItem().equals("Earth Movers Distance"))
            cOp.metric = Metrics.EarthMoversDistance;
        else if (MetricCB.getSelectedItem().equals("Chebyshev Distance"))
            cOp.metric = Metrics.ChebyshevDistance;
        else if (MetricCB.getSelectedItem().equals("Canberra Distance"))
            cOp.metric = Metrics.CanberraDistance;
        else if (MetricCB.getSelectedItem().equals("Pearson correlation coefficient"))
            cOp.metric = Metrics.PearsonCorrelationCoefficient;

        cOp.distanceType = DistanceType.Height;
        cOp.cutDistance = 0.0;
        cOp.dendrogram = null;

        GlobalVariables.plotElbowMethodOutput = DisplayElbowOutputCB.isSelected();

        return cOp;
    }

    public void SetValues(ClusteringOptions values)
    {
        DisplayElbowOutputCB.setSelected(GlobalVariables.plotElbowMethodOutput);

        if (values != null)
        {
            switch (values.method)
            {
                case AHC:
                    ClusteringOptionCB.setSelectedItem("Agglomerative Hierarchical Clustering");
                    break;
            }

            switch (values.domain)
            {
                case Rows: DomainRowRB.setSelected(true);
                    break;
                case Columns: DomainColRB.setSelected(true);
                    break;
                case BiClustering: Domain_BiD_RB.setSelected(true);
                    break;
            }

            switch (values.linkage)
            {
                case Average:
                    LinkageCriteriaCB.setSelectedItem("Average linkage");
                    break;

                case Single:
                    LinkageCriteriaCB.setSelectedItem("Single linkage");
                    break;

                case Complete:
                    LinkageCriteriaCB.setSelectedItem("Complete linkage");
                    break;
            }

            switch (values.metric)
            {
                case EuclideanDistance:
                    MetricCB.setSelectedItem("Euclidean distance");
                    break;

                case ManhattanDistance:
                    MetricCB.setSelectedItem("Manhattan Distance");
                    break;

                case EarthMoversDistance:
                    MetricCB.setSelectedItem("Earth Movers Distance");
                    break;

                case ChebyshevDistance:
                    MetricCB.setSelectedItem("Chebyshev Distance");
                    break;

                case CanberraDistance:
                    MetricCB.setSelectedItem("Canberra Distance");
                    break;

                case PearsonCorrelationCoefficient:
                    MetricCB.setSelectedItem("Pearson correlation coefficient");
                    break;
            }
        }
        else
        {
            ClusteringOptionCB.setSelectedItem("Agglomerative Hierarchical Clustering");
            DomainRowRB.setSelected(true);
            LinkageCriteriaCB.setSelectedItem("Average linkage");
            MetricCB.setSelectedItem("Euclidean distance");
        }
    }
}
