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
package GeMSE.GS.Analysis.PatternSearch;

import GeMSE.GS.SampleData;
import GeMSE.GS.Transitions.Options.ClusteringOptions.ClusteringDomains;
import GeMSE.GS.Space;
import GeMSE.GlobalVariables;
import GeMSE.GS.Transitions.Options.ClusteringOptions.Metrics;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import ExternalLibraries.HeatChart;
import GeMSE.Visualization.HeatMap;
import GeMSE.GS.Analysis.Clustering.ClusterToGraphML;
import GeMSE.GeMSE;
import GeMSE.Visualization.Graph.GraphVis;
import GeMSE.IO.InProgress;
import GeMSE.IO.OpenWebpage;
import GeMSE.GS.Transitions.Options.ClusteringOptions;
import GeMSE.Popups.DataGridClickListener;
import GeMSE.Popups.HeatmapClickListener;
import java.awt.Color;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

/**
 *
 * @author Vahid Jalili
 */
public class PatternSearchWindow extends javax.swing.JFrame
{
    /**
     * Creates new form PatternSearchProcessesMainWindow
     */
    public PatternSearchWindow()
    {
        initComponents();
        ButtonGroup group = new ButtonGroup();
        group.add(UseRowID_RB);
        group.add(UseSampleRegionTXTAttributes_RB);
        group.add(UseReferenceAnnotation_RB);

        _userChangingSlider = Sliders.None;

        RowPatternPlot.setVisible(false);
        ColPatternPlot.setVisible(false);

        for (String cL : GlobalVariables.availableColumnLabelOptions)
            Columns_CB.addItem(cL);
        Columns_CB.setSelectedItem(GlobalVariables.selectedColumnLabelOption);
        Columns_CB.setSelectedItem(GlobalVariables.selectedColumnLabelOption);

        for (String cL : GlobalVariables.availableColumnLabelOptions)
            Columns_CB1.addItem(cL);
        Columns_CB1.setSelectedItem(GlobalVariables.selectedColumnLabelOption);
        Columns_CB1.setSelectedItem(GlobalVariables.selectedColumnLabelOption);
        EnableDisableMetadataAggregates(false);

        if (GlobalVariables.annotations == null || GlobalVariables.annotations.features.isEmpty())
            UseReferenceAnnotation_RB.setEnabled(false);
        else
            for (String attribute : GlobalVariables.annotations.GetTXTAttributesArray())
                ReferenceSampleAttributes_CB.addItem(attribute);

        PatternSearchDomainBG.add(DomainRowRB);
        PatternSearchDomainBG.add(DomainColRB);
        DomainColRB.setSelected(true);
        EnableDiableColPatternDetailAttributes(false);
        EnableDiableRowPatternDetailAttributes(false);

        DisplayElbowOutputCB.setSelected(GlobalVariables.plotElbowMethodOutput);

        PatternHeatmapPane.addMouseListener(new HeatmapClickListener());
        PatternDetailPlot.addMouseListener(new HeatmapClickListener());

        Color.RGBtoHSB(214, 217, 223, bColor);

        patternDetails_DG1.addMouseListener(new DataGridClickListener());
        metadataCount_DG.addMouseListener(new DataGridClickListener());
        patternDetails_DG2.addMouseListener(new DataGridClickListener());
        PatternDetailDG.addMouseListener(new DataGridClickListener());
        selectedPatternDetails_DG.addMouseListener(new DataGridClickListener());
    }

    public Space source;
    public String spaceID;
    private PatternSearchProcesses _psp;
    private Dimension _dimension;
    private String _logestRowL;
    private String _logestColL;
    private HashMap<AttributeValuePair, int[]> _metadataCount;
    private final String _patternTagPrefix = "P_";
    float[] bColor = new float[3];


    private Sliders _userChangingSlider;

    private enum Sliders
    {
        Count, Height, Distance, None
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        PatternSearchDomainBG = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        DistanceL3 = new javax.swing.JLabel();
        ClusterCountSlider2 = new javax.swing.JSlider();
        ClusterCountSliderValue2 = new javax.swing.JLabel();
        ControlPanel = new javax.swing.JPanel();
        SearchButton = new javax.swing.JButton();
        MetricL = new javax.swing.JLabel();
        MetricCB = new javax.swing.JComboBox();
        DomainL = new javax.swing.JLabel();
        DomainRowRB = new javax.swing.JRadioButton();
        DomainColRB = new javax.swing.JRadioButton();
        DisplayElbowOutputCB = new javax.swing.JCheckBox();
        ClusterCountPanel = new javax.swing.JPanel();
        NoOfClustersL = new javax.swing.JLabel();
        ClusterCountSlider = new javax.swing.JSlider();
        ClusterCountSliderValue = new javax.swing.JLabel();
        ClusterCountSuggestedL = new javax.swing.JLabel();
        ClusterHeightPanel = new javax.swing.JPanel();
        HeightL = new javax.swing.JLabel();
        HeightSlider = new javax.swing.JSlider();
        HeightSliderValue = new javax.swing.JLabel();
        ClusterHeightSuggestedL = new javax.swing.JLabel();
        ClusterDistancePanel = new javax.swing.JPanel();
        DistanceL = new javax.swing.JLabel();
        DistanceSlider = new javax.swing.JSlider();
        DistanceSliderValue = new javax.swing.JLabel();
        ClusterDistanceSuggestedL = new javax.swing.JLabel();
        GraphVisualization = new javax.swing.JButton();
        PatternTabbedPane = new javax.swing.JTabbedPane();
        heatMap_tab = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PatternHeatmapPane = new javax.swing.JLayeredPane();
        RowPatternPlot = new javax.swing.JLabel();
        PatternPlot = new javax.swing.JLabel();
        ColPatternPlot = new javax.swing.JLabel();
        gridView_tab = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        metadataCount_DG = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        patternDetails_DG1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        DeterminedAtts_L3 = new javax.swing.JLabel();
        Columns_CB1 = new javax.swing.JComboBox();
        DeterminedAtts_L4 = new javax.swing.JLabel();
        gridView_tab1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        patternDetails_DG2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        DeterminedAtts_L1 = new javax.swing.JLabel();
        Columns_CB = new javax.swing.JComboBox();
        DeterminedAtts_L = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        DeterminedAtts_L2 = new javax.swing.JLabel();
        UseRowID_RB = new javax.swing.JRadioButton();
        UseSampleRegionTXTAttributes_RB = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        ChooseSampleForTXTAttributes_CB = new javax.swing.JComboBox<>();
        SampleAttribute_CB = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        UseReferenceAnnotation_RB = new javax.swing.JRadioButton();
        ReferenceSampleAttributes_CB = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        selectedPatternDetails_DG = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        PatternDetailPlot = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        PatternDetailDG = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        HOnlineSupport = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        HOnlineDocs = new javax.swing.JMenu();
        HOPatternAnalysis = new javax.swing.JMenuItem();
        HVideoTutorial = new javax.swing.JMenu();
        HVPatternAnalysis = new javax.swing.JMenuItem();
        HVSave = new javax.swing.JMenuItem();

        DistanceL3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DistanceL3.setText("Number of clusters");
        DistanceL3.setToolTipText("");

        ClusterCountSlider2.setMinimum(1);
        ClusterCountSlider2.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                ClusterCountSlider2StateChanged(evt);
            }
        });

        ClusterCountSliderValue2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ClusterCountSliderValue2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ClusterCountSliderValue2.setText("1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ClusterCountSliderValue2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DistanceL3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ClusterCountSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DistanceL3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClusterCountSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClusterCountSliderValue2)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GeMSE: Patten Exploration");

        SearchButton.setText("Search");
        SearchButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                SearchButtonActionPerformed(evt);
            }
        });

        MetricL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MetricL.setText("Metric");
        MetricL.setMaximumSize(new java.awt.Dimension(60, 20));
        MetricL.setMinimumSize(new java.awt.Dimension(60, 20));
        MetricL.setPreferredSize(new java.awt.Dimension(60, 20));

        MetricCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Euclidean Distance", "Manhattan Distance", "Earth Movers Distance", "Chebyshev Distance", "Canberra Distance", "Pearson Correlation Coefficient" }));

        DomainL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DomainL.setText("Domain");
        DomainL.setMaximumSize(new java.awt.Dimension(60, 20));
        DomainL.setMinimumSize(new java.awt.Dimension(60, 20));
        DomainL.setPreferredSize(new java.awt.Dimension(60, 20));

        DomainRowRB.setText("Rows (regions)");

        DomainColRB.setText("Columns (samples)");

        DisplayElbowOutputCB.setText("Plot Elbow method data");
        DisplayElbowOutputCB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                DisplayElbowOutputCBActionPerformed(evt);
            }
        });

        ClusterCountPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ClusterCountPanel.setMaximumSize(new java.awt.Dimension(206, 125));

        NoOfClustersL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NoOfClustersL.setText("Number of clusters (patterns)");
        NoOfClustersL.setToolTipText("");
        NoOfClustersL.setEnabled(false);

        ClusterCountSlider.setMinimum(1);
        ClusterCountSlider.setEnabled(false);
        ClusterCountSlider.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                ClusterCountSliderStateChanged(evt);
            }
        });

        ClusterCountSliderValue.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ClusterCountSliderValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ClusterCountSliderValue.setText("1");
        ClusterCountSliderValue.setEnabled(false);

        ClusterCountSuggestedL.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        ClusterCountSuggestedL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ClusterCountSuggestedL.setText(" ");
        ClusterCountSuggestedL.setEnabled(false);

        javax.swing.GroupLayout ClusterCountPanelLayout = new javax.swing.GroupLayout(ClusterCountPanel);
        ClusterCountPanel.setLayout(ClusterCountPanelLayout);
        ClusterCountPanelLayout.setHorizontalGroup(
            ClusterCountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClusterCountPanelLayout.createSequentialGroup()
                .addGroup(ClusterCountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ClusterCountPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(ClusterCountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ClusterCountSliderValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NoOfClustersL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ClusterCountSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(ClusterCountPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ClusterCountSuggestedL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ClusterCountPanelLayout.setVerticalGroup(
            ClusterCountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClusterCountPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NoOfClustersL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClusterCountSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClusterCountSliderValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(ClusterCountSuggestedL)
                .addContainerGap())
        );

        ClusterHeightPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ClusterHeightPanel.setMaximumSize(new java.awt.Dimension(206, 125));
        ClusterHeightPanel.setPreferredSize(new java.awt.Dimension(206, 125));

        HeightL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HeightL.setText("Height from Root");
        HeightL.setToolTipText("");
        HeightL.setEnabled(false);

        HeightSlider.setEnabled(false);
        HeightSlider.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                HeightSliderStateChanged(evt);
            }
        });

        HeightSliderValue.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        HeightSliderValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HeightSliderValue.setText("1");
        HeightSliderValue.setEnabled(false);

        ClusterHeightSuggestedL.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        ClusterHeightSuggestedL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ClusterHeightSuggestedL.setText(" ");
        ClusterHeightSuggestedL.setEnabled(false);

        javax.swing.GroupLayout ClusterHeightPanelLayout = new javax.swing.GroupLayout(ClusterHeightPanel);
        ClusterHeightPanel.setLayout(ClusterHeightPanelLayout);
        ClusterHeightPanelLayout.setHorizontalGroup(
            ClusterHeightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClusterHeightPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(ClusterHeightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ClusterHeightSuggestedL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HeightSliderValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HeightL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HeightSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        ClusterHeightPanelLayout.setVerticalGroup(
            ClusterHeightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClusterHeightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HeightL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HeightSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HeightSliderValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ClusterHeightSuggestedL)
                .addGap(37, 37, 37))
        );

        ClusterDistancePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        DistanceL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DistanceL.setText("Distance between children");
        DistanceL.setToolTipText("");
        DistanceL.setEnabled(false);

        DistanceSlider.setEnabled(false);
        DistanceSlider.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                DistanceSliderStateChanged(evt);
            }
        });

        DistanceSliderValue.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        DistanceSliderValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DistanceSliderValue.setText("1");
        DistanceSliderValue.setEnabled(false);

        ClusterDistanceSuggestedL.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        ClusterDistanceSuggestedL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ClusterDistanceSuggestedL.setText(" ");
        ClusterDistanceSuggestedL.setEnabled(false);

        javax.swing.GroupLayout ClusterDistancePanelLayout = new javax.swing.GroupLayout(ClusterDistancePanel);
        ClusterDistancePanel.setLayout(ClusterDistancePanelLayout);
        ClusterDistancePanelLayout.setHorizontalGroup(
            ClusterDistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClusterDistancePanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(ClusterDistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ClusterDistancePanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(ClusterDistanceSuggestedL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(DistanceSliderValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DistanceL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DistanceSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        ClusterDistancePanelLayout.setVerticalGroup(
            ClusterDistancePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClusterDistancePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DistanceL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DistanceSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DistanceSliderValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClusterDistanceSuggestedL)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        GraphVisualization.setText("Graph Visualization");
        GraphVisualization.setEnabled(false);
        GraphVisualization.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                GraphVisualizationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ControlPanelLayout = new javax.swing.GroupLayout(ControlPanel);
        ControlPanel.setLayout(ControlPanelLayout);
        ControlPanelLayout.setHorizontalGroup(
            ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SearchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ControlPanelLayout.createSequentialGroup()
                        .addComponent(MetricL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MetricCB, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ControlPanelLayout.createSequentialGroup()
                        .addComponent(DisplayElbowOutputCB, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(GraphVisualization, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ControlPanelLayout.createSequentialGroup()
                        .addComponent(DomainL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ControlPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(DomainRowRB))
                            .addGroup(ControlPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DomainColRB)))
                        .addGap(28, 28, 28))
                    .addComponent(ClusterCountPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ClusterHeightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(ClusterDistancePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        ControlPanelLayout.setVerticalGroup(
            ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SearchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GraphVisualization)
                .addGap(18, 18, 18)
                .addComponent(DisplayElbowOutputCB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MetricCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MetricL, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DomainL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DomainColRB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DomainRowRB)
                .addGap(25, 25, 25)
                .addComponent(ClusterCountPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ClusterHeightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ClusterDistancePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PatternHeatmapPane.setBackground(new java.awt.Color(214, 217, 223));
        PatternHeatmapPane.setOpaque(true);

        RowPatternPlot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RowPatternPlot.setText("RowPattern");

        PatternPlot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PatternPlot.setText("GenometricSpace");

        ColPatternPlot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ColPatternPlot.setText("ColPattern");

        PatternHeatmapPane.setLayer(RowPatternPlot, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PatternHeatmapPane.setLayer(PatternPlot, javax.swing.JLayeredPane.DEFAULT_LAYER);
        PatternHeatmapPane.setLayer(ColPatternPlot, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout PatternHeatmapPaneLayout = new javax.swing.GroupLayout(PatternHeatmapPane);
        PatternHeatmapPane.setLayout(PatternHeatmapPaneLayout);
        PatternHeatmapPaneLayout.setHorizontalGroup(
            PatternHeatmapPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PatternHeatmapPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RowPatternPlot, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PatternHeatmapPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ColPatternPlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PatternPlot, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE))
                .addContainerGap())
        );
        PatternHeatmapPaneLayout.setVerticalGroup(
            PatternHeatmapPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PatternHeatmapPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ColPatternPlot, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PatternHeatmapPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RowPatternPlot, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addComponent(PatternPlot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane2.setViewportView(PatternHeatmapPane);

        javax.swing.GroupLayout heatMap_tabLayout = new javax.swing.GroupLayout(heatMap_tab);
        heatMap_tab.setLayout(heatMap_tabLayout);
        heatMap_tabLayout.setHorizontalGroup(
            heatMap_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        heatMap_tabLayout.setVerticalGroup(
            heatMap_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
        );

        PatternTabbedPane.addTab("   Patterns   ", heatMap_tab);

        metadataCount_DG.setAutoCreateRowSorter(true);
        metadataCount_DG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {},
                {},
                {},
                {}
            },
            new String []
            {

            }
        ));
        metadataCount_DG.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(metadataCount_DG);

        patternDetails_DG1.setAutoCreateRowSorter(true);
        patternDetails_DG1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {},
                {},
                {},
                {}
            },
            new String []
            {

            }
        ));
        jScrollPane5.setViewportView(patternDetails_DG1);

        DeterminedAtts_L3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        DeterminedAtts_L3.setText("Attribute");

        DeterminedAtts_L4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DeterminedAtts_L4.setText("Column Pattern Details");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(DeterminedAtts_L3, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Columns_CB1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(DeterminedAtts_L4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(DeterminedAtts_L4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Columns_CB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeterminedAtts_L3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout gridView_tabLayout = new javax.swing.GroupLayout(gridView_tab);
        gridView_tab.setLayout(gridView_tabLayout);
        gridView_tabLayout.setHorizontalGroup(
            gridView_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gridView_tabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gridView_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addContainerGap())
        );
        gridView_tabLayout.setVerticalGroup(
            gridView_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gridView_tabLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(gridView_tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gridView_tabLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        PatternTabbedPane.addTab("   Metadata Aggregates   ", gridView_tab);

        patternDetails_DG2.setAutoCreateRowSorter(true);
        patternDetails_DG2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {},
                {},
                {},
                {}
            },
            new String []
            {

            }
        ));
        jScrollPane3.setViewportView(patternDetails_DG2);

        DeterminedAtts_L1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        DeterminedAtts_L1.setText("Attribute");

        DeterminedAtts_L.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DeterminedAtts_L.setText("Column Pattern Details");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(DeterminedAtts_L1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Columns_CB, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(DeterminedAtts_L, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(DeterminedAtts_L)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Columns_CB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeterminedAtts_L1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DeterminedAtts_L2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DeterminedAtts_L2.setText("Row Pattern Details");

        UseRowID_RB.setText("Use ID (chr - start - stop)");
        UseRowID_RB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                UseRowID_RBActionPerformed(evt);
            }
        });

        UseSampleRegionTXTAttributes_RB.setText("Use textual attributes of regions");
        UseSampleRegionTXTAttributes_RB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                UseSampleRegionTXTAttributes_RBActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Sample");

        ChooseSampleForTXTAttributes_CB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ChooseSampleForTXTAttributes_CBActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Attribute");

        UseReferenceAnnotation_RB.setText("Use reference sample");
        UseReferenceAnnotation_RB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                UseReferenceAnnotation_RBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DeterminedAtts_L2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UseRowID_RB)
                            .addComponent(UseSampleRegionTXTAttributes_RB)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(UseReferenceAnnotation_RB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(SampleAttribute_CB, javax.swing.GroupLayout.Alignment.LEADING, 0, 194, Short.MAX_VALUE)
                                    .addComponent(ChooseSampleForTXTAttributes_CB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ReferenceSampleAttributes_CB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DeterminedAtts_L2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UseRowID_RB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UseSampleRegionTXTAttributes_RB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChooseSampleForTXTAttributes_CB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(SampleAttribute_CB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UseReferenceAnnotation_RB)
                    .addComponent(ReferenceSampleAttributes_CB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        selectedPatternDetails_DG.setAutoCreateRowSorter(true);
        selectedPatternDetails_DG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {},
                {},
                {},
                {}
            },
            new String []
            {

            }
        ));
        jScrollPane4.setViewportView(selectedPatternDetails_DG);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("   Overview   ", jPanel4);

        PatternDetailPlot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PatternDetailPlot.setText("GeMSE: Selected Pattern Heatmap");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PatternDetailPlot, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PatternDetailPlot, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("   Heatmap   ", jPanel5);

        PatternDetailDG.setAutoCreateRowSorter(true);
        PatternDetailDG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {},
                {},
                {},
                {}
            },
            new String []
            {

            }
        ));
        jScrollPane7.setViewportView(PatternDetailDG);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("   Grid View   ", jPanel7);

        javax.swing.GroupLayout gridView_tab1Layout = new javax.swing.GroupLayout(gridView_tab1);
        gridView_tab1.setLayout(gridView_tab1Layout);
        gridView_tab1Layout.setHorizontalGroup(
            gridView_tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gridView_tab1Layout.createSequentialGroup()
                .addGroup(gridView_tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gridView_tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(gridView_tab1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        gridView_tab1Layout.setVerticalGroup(
            gridView_tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gridView_tab1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gridView_tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(gridView_tab1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        PatternTabbedPane.addTab("   Patterns Details      ", gridView_tab1);

        jMenu1.setText("  File  ");

        Exit.setText("     Exit Pattern Analysis     ");
        Exit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ExitActionPerformed(evt);
            }
        });
        jMenu1.add(Exit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("  Help  ");

        HOnlineSupport.setText("     Online Support     ");
        HOnlineSupport.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                HOnlineSupportActionPerformed(evt);
            }
        });
        jMenu2.add(HOnlineSupport);
        jMenu2.add(jSeparator1);

        HOnlineDocs.setText("     Online Docs     ");

        HOPatternAnalysis.setText("     Pattern Analysis     ");
        HOPatternAnalysis.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                HOPatternAnalysisActionPerformed(evt);
            }
        });
        HOnlineDocs.add(HOPatternAnalysis);

        jMenu2.add(HOnlineDocs);

        HVideoTutorial.setText("     Video Tutorials     ");

        HVPatternAnalysis.setText("     Pattern Analysis     ");
        HVPatternAnalysis.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                HVPatternAnalysisActionPerformed(evt);
            }
        });
        HVideoTutorial.add(HVPatternAnalysis);

        HVSave.setText("     Save Data     ");
        HVSave.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                HVSaveActionPerformed(evt);
            }
        });
        HVideoTutorial.add(HVSave);

        jMenu2.add(HVideoTutorial);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PatternTabbedPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PatternTabbedPane)
                    .addComponent(ControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        PatternTabbedPane.getAccessibleContext().setAccessibleName("   Patterns      ");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SearchButtonActionPerformed
    {//GEN-HEADEREND:event_SearchButtonActionPerformed
        InProgress inProgress = new InProgress(null, "Processing patterns in the data, please wait ...");
        inProgress.setLocationRelativeTo(null);
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
        {
            @Override
            protected Void doInBackground()
            {
                ProcessPatterns();
                inProgress.dispose();
                return null;
            }

            @Override
            protected void done()
            {
                GraphVisualization.setEnabled(true);
            }
        };
        worker.execute();
        inProgress.setVisible(true);
    }//GEN-LAST:event_SearchButtonActionPerformed

    private void DisplayElbowOutputCBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_DisplayElbowOutputCBActionPerformed
    {//GEN-HEADEREND:event_DisplayElbowOutputCBActionPerformed
        GlobalVariables.plotElbowMethodOutput = DisplayElbowOutputCB.isSelected();
    }//GEN-LAST:event_DisplayElbowOutputCBActionPerformed

    private void ClusterCountSliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_ClusterCountSliderStateChanged
    {//GEN-HEADEREND:event_ClusterCountSliderStateChanged
        ClusterCountSliderValue.setText(String.valueOf(ClusterCountSlider.getValue()));
        if (_userChangingSlider == Sliders.Count || _userChangingSlider == Sliders.None)
        {
            _userChangingSlider = Sliders.Count;
            UpdateDeterminedPatterns();
            _userChangingSlider = Sliders.None;
        }
    }//GEN-LAST:event_ClusterCountSliderStateChanged

    private void HeightSliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_HeightSliderStateChanged
    {//GEN-HEADEREND:event_HeightSliderStateChanged
        HeightSliderValue.setText(String.valueOf(HeightSlider.getValue()));
        if (_userChangingSlider == Sliders.Height || _userChangingSlider == Sliders.None)
        {
            _userChangingSlider = Sliders.Height;
            UpdateDeterminedPatterns();
            _userChangingSlider = Sliders.None;
        }
    }//GEN-LAST:event_HeightSliderStateChanged

    private void ClusterCountSlider2StateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_ClusterCountSlider2StateChanged
    {//GEN-HEADEREND:event_ClusterCountSlider2StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ClusterCountSlider2StateChanged

    private void DistanceSliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_DistanceSliderStateChanged
    {//GEN-HEADEREND:event_DistanceSliderStateChanged
        DistanceSliderValue.setText(String.valueOf(DistanceSlider.getValue()));
        if (_userChangingSlider == Sliders.Distance || _userChangingSlider == Sliders.None)
        {
            _userChangingSlider = Sliders.Distance;
            UpdateDeterminedPatterns();
            _userChangingSlider = Sliders.None;
        }
    }//GEN-LAST:event_DistanceSliderStateChanged

    private void GraphVisualizationActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_GraphVisualizationActionPerformed
    {//GEN-HEADEREND:event_GraphVisualizationActionPerformed
        String filename = System.getProperty("user.dir") + "tmpGraphML.xml";
        ClusteringDomains dom = ClusteringDomains.Columns;
        if (DomainRowRB.isSelected())
            dom = ClusteringDomains.Rows;
        ClusterToGraphML.Convert(spaceID, _psp.GetDendrogram(), filename, dom, ClusteringOptions.DistanceType.Height, HeightSlider.getValue());

        GraphVis gVis = new GraphVis(filename);
        gVis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gVis.setLocationRelativeTo(this);
        gVis.setVisible(true);
        gVis.UpdateGraph();
        //new File(filename).delete();
    }//GEN-LAST:event_GraphVisualizationActionPerformed

    private void UseReferenceAnnotation_RBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_UseReferenceAnnotation_RBActionPerformed
    {//GEN-HEADEREND:event_UseReferenceAnnotation_RBActionPerformed
        if (UseReferenceAnnotation_RB.isSelected())
        {
            ChooseSampleForTXTAttributes_CB.setEnabled(false);
            SampleAttribute_CB.setEnabled(false);
            ReferenceSampleAttributes_CB.setEnabled(true);
        }
    }//GEN-LAST:event_UseReferenceAnnotation_RBActionPerformed

    private void ChooseSampleForTXTAttributes_CBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ChooseSampleForTXTAttributes_CBActionPerformed
    {//GEN-HEADEREND:event_ChooseSampleForTXTAttributes_CBActionPerformed
        for (SampleData sample : GlobalVariables.samples)
            if (sample.fileName.equals(ChooseSampleForTXTAttributes_CB.getSelectedItem().toString()))
            {
                ArrayList<String> attributes = sample.GetTXTAttributesArray();
                if (attributes.isEmpty())
                {
                    SampleAttribute_CB.setEnabled(false);
                    JOptionPane.showMessageDialog(
                            null,
                            "The selected sample does not have any textual attribute-value pairs",
                            "No textual attribute-value pair", 2);
                }
                else
                {
                    for (String attribute : sample.GetTXTAttributesArray())
                        SampleAttribute_CB.addItem(attribute);
                }
            }
    }//GEN-LAST:event_ChooseSampleForTXTAttributes_CBActionPerformed

    private void UseSampleRegionTXTAttributes_RBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_UseSampleRegionTXTAttributes_RBActionPerformed
    {//GEN-HEADEREND:event_UseSampleRegionTXTAttributes_RBActionPerformed
        if (UseSampleRegionTXTAttributes_RB.isSelected())
        {
            ChooseSampleForTXTAttributes_CB.setEnabled(true);
            SampleAttribute_CB.setEnabled(true);
            ReferenceSampleAttributes_CB.setEnabled(false);

            for (SampleData sample : GlobalVariables.samples)
                ChooseSampleForTXTAttributes_CB.addItem(sample.fileName);
        }
    }//GEN-LAST:event_UseSampleRegionTXTAttributes_RBActionPerformed

    private void UseRowID_RBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_UseRowID_RBActionPerformed
    {//GEN-HEADEREND:event_UseRowID_RBActionPerformed
        if (UseRowID_RB.isSelected())
        {
            ChooseSampleForTXTAttributes_CB.setEnabled(false);
            SampleAttribute_CB.setEnabled(false);
            ReferenceSampleAttributes_CB.setEnabled(false);
        }
    }//GEN-LAST:event_UseRowID_RBActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ExitActionPerformed
    {//GEN-HEADEREND:event_ExitActionPerformed
        dispose();
    }//GEN-LAST:event_ExitActionPerformed

    private void HOnlineSupportActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_HOnlineSupportActionPerformed
    {//GEN-HEADEREND:event_HOnlineSupportActionPerformed
        try
        {
            OpenWebpage.open(new URI("https://github.com/Genometric/GeMSE/issues"));
        }
        catch (URISyntaxException ex)
        {
            Logger.getLogger(GeMSE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_HOnlineSupportActionPerformed

    private void HOPatternAnalysisActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_HOPatternAnalysisActionPerformed
    {//GEN-HEADEREND:event_HOPatternAnalysisActionPerformed
        try
        {
            OpenWebpage.open(new URI("https://github.com/Genometric/GeMSE/wiki/Pattern-Exploration"));
        }
        catch (URISyntaxException ex)
        {
            Logger.getLogger(GeMSE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_HOPatternAnalysisActionPerformed

    private void HVPatternAnalysisActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_HVPatternAnalysisActionPerformed
    {//GEN-HEADEREND:event_HVPatternAnalysisActionPerformed
        try
        {
            OpenWebpage.open(new URI("https://youtu.be/_57CfWSe_Cg?list=PLfWxoOMC6swJBgu4nIDgsoXO_sLuGnIqF"));
        }
        catch (URISyntaxException ex)
        {
            Logger.getLogger(GeMSE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_HVPatternAnalysisActionPerformed

    private void HVSaveActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_HVSaveActionPerformed
    {//GEN-HEADEREND:event_HVSaveActionPerformed
        try
        {
            OpenWebpage.open(new URI("https://youtu.be/Mv0vw2CZuPE?list=PLfWxoOMC6swJBgu4nIDgsoXO_sLuGnIqF"));
        }
        catch (URISyntaxException ex)
        {
            Logger.getLogger(GeMSE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_HVSaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look DistanceSlider * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
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
            java.util.logging.Logger.getLogger(PatternSearchWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(PatternSearchWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(PatternSearchWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(PatternSearchWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new PatternSearchWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ChooseSampleForTXTAttributes_CB;
    private javax.swing.JPanel ClusterCountPanel;
    private javax.swing.JSlider ClusterCountSlider;
    private javax.swing.JSlider ClusterCountSlider2;
    private javax.swing.JLabel ClusterCountSliderValue;
    private javax.swing.JLabel ClusterCountSliderValue2;
    private javax.swing.JLabel ClusterCountSuggestedL;
    private javax.swing.JPanel ClusterDistancePanel;
    private javax.swing.JLabel ClusterDistanceSuggestedL;
    private javax.swing.JPanel ClusterHeightPanel;
    private javax.swing.JLabel ClusterHeightSuggestedL;
    private javax.swing.JLabel ColPatternPlot;
    private javax.swing.JComboBox Columns_CB;
    private javax.swing.JComboBox Columns_CB1;
    private javax.swing.JPanel ControlPanel;
    private javax.swing.JLabel DeterminedAtts_L;
    private javax.swing.JLabel DeterminedAtts_L1;
    private javax.swing.JLabel DeterminedAtts_L2;
    private javax.swing.JLabel DeterminedAtts_L3;
    private javax.swing.JLabel DeterminedAtts_L4;
    private javax.swing.JCheckBox DisplayElbowOutputCB;
    private javax.swing.JLabel DistanceL;
    private javax.swing.JLabel DistanceL3;
    private javax.swing.JSlider DistanceSlider;
    private javax.swing.JLabel DistanceSliderValue;
    private javax.swing.JRadioButton DomainColRB;
    private javax.swing.JLabel DomainL;
    private javax.swing.JRadioButton DomainRowRB;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JButton GraphVisualization;
    private javax.swing.JMenuItem HOPatternAnalysis;
    private javax.swing.JMenu HOnlineDocs;
    private javax.swing.JMenuItem HOnlineSupport;
    private javax.swing.JMenuItem HVPatternAnalysis;
    private javax.swing.JMenuItem HVSave;
    private javax.swing.JMenu HVideoTutorial;
    private javax.swing.JLabel HeightL;
    private javax.swing.JSlider HeightSlider;
    private javax.swing.JLabel HeightSliderValue;
    private javax.swing.JComboBox MetricCB;
    private javax.swing.JLabel MetricL;
    private javax.swing.JLabel NoOfClustersL;
    private javax.swing.JTable PatternDetailDG;
    private javax.swing.JLabel PatternDetailPlot;
    private javax.swing.JLayeredPane PatternHeatmapPane;
    private javax.swing.JLabel PatternPlot;
    private javax.swing.ButtonGroup PatternSearchDomainBG;
    private javax.swing.JTabbedPane PatternTabbedPane;
    private javax.swing.JComboBox ReferenceSampleAttributes_CB;
    private javax.swing.JLabel RowPatternPlot;
    private javax.swing.JComboBox<String> SampleAttribute_CB;
    private javax.swing.JButton SearchButton;
    private javax.swing.JRadioButton UseReferenceAnnotation_RB;
    private javax.swing.JRadioButton UseRowID_RB;
    private javax.swing.JRadioButton UseSampleRegionTXTAttributes_RB;
    private javax.swing.JPanel gridView_tab;
    private javax.swing.JPanel gridView_tab1;
    private javax.swing.JPanel heatMap_tab;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable metadataCount_DG;
    private javax.swing.JTable patternDetails_DG1;
    private javax.swing.JTable patternDetails_DG2;
    private javax.swing.JTable selectedPatternDetails_DG;
    // End of variables declaration//GEN-END:variables

    private void ProcessPatterns()
    {
        // this prevents all change events on the three sliders.
        _userChangingSlider = null;
        PatternDetailPlot.setIcon(null);
        PatternDetailPlot.setText("GeMSE: Selected Pattern Heatmap");

        selectedPatternDetails_DG.setModel(new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        });
        patternDetails_DG1.setModel(new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        });
        metadataCount_DG.setModel(new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        });

        metadataCount_DG.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ResizeColumnWidth(metadataCount_DG);

        Metrics metric = Metrics.EuclideanDistance;
        switch (MetricCB.getSelectedItem().toString())
        {
            case "Euclidean distance":
                metric = Metrics.EuclideanDistance;
                break;

            case "Manhattan Distance":
                metric = Metrics.ManhattanDistance;
                break;

            case "Earth Movers Distance":
                metric = Metrics.EarthMoversDistance;
                break;

            case "Chebyshev Distance":
                metric = Metrics.ChebyshevDistance;
                break;

            case "Canberra Distance":
                metric = Metrics.CanberraDistance;
                break;

            case "Pearson Correlation Coefficient":
                metric = Metrics.PearsonCorrelationCoefficient;
                break;
        }


        if (DomainRowRB.isSelected())
        {
            EnableDisableMetadataAggregates(true);
            RowPatternPlot.setVisible(true);
            ColPatternPlot.setVisible(false);
            patternDetails_DG1.setEnabled(true);
            _psp = new PatternSearchProcesses(
                    source,
                    ClusteringDomains.Rows,
                    metric);

            EnableDiableColPatternDetailAttributes(false);
            EnableDiableRowPatternDetailAttributes(false);

            UseRowID_RB.setEnabled(true);
            if (GlobalVariables.annotations == null || GlobalVariables.annotations.features.isEmpty())
                UseReferenceAnnotation_RB.setEnabled(false);
            else
                UseReferenceAnnotation_RB.setEnabled(true);

            DeterminedAtts_L2.setEnabled(true);
            SampleAttribute_CB.setEnabled(false);

            if (null != GlobalVariables.rowLabelsSource)
                switch (GlobalVariables.rowLabelsSource)
                {
                    case ID:
                        UseRowID_RB.setSelected(true);
                        break;
                    case SampleAttributes:
                        UseSampleRegionTXTAttributes_RB.setSelected(true);
                        SampleAttribute_CB.setEnabled(true);
                        break;
                    case ReferenceAttributes:
                        UseReferenceAnnotation_RB.setSelected(true);
                        ReferenceSampleAttributes_CB.setEnabled(true);
                        break;
                }
        }
        else
        {
            EnableDisableMetadataAggregates(false);
            RowPatternPlot.setVisible(false);
            ColPatternPlot.setVisible(true);
            patternDetails_DG1.setEnabled(false);
            _psp = new PatternSearchProcesses(
                    source,
                    ClusteringDomains.Columns,
                    metric);

            EnableDiableColPatternDetailAttributes(true);
            EnableDiableRowPatternDetailAttributes(false);
        }


        ClusterCountSlider.setMaximum(_psp.MaxClusterCount());
        ClusterCountSlider.setValue(_psp.DefaultClusterCount());
        HeightSlider.setMaximum(_psp.MaxHeight());
        HeightSlider.setValue(_psp.ExchangeC4H(ClusterCountSlider.getValue()));
        DistanceSlider.setMaximum((int) Math.floor(_psp.MaxDistance()) + 1);
        DistanceSlider.setMinimum((int) Math.floor(_psp.MinDistance()));
        DistanceSlider.setValue((int) Math.round(_psp.ExchangeC4D(ClusterCountSlider.getValue())));

        ClusterCountSuggestedL.setText("Suggested value: " + String.valueOf(ClusterCountSlider.getValue()));
        ClusterHeightSuggestedL.setText("Suggested value: " + String.valueOf(HeightSlider.getValue()));
        ClusterDistanceSuggestedL.setText("Suggested value: " + String.valueOf(DistanceSlider.getValue()));

        EnableDisableClusterSlidersGroups(true);

        _userChangingSlider = Sliders.Count;
        UpdateDeterminedPatterns();
        _userChangingSlider = Sliders.None;
    }

    private void UpdateDeterminedPatterns()
    {
        int clusterCount;
        switch (_userChangingSlider)
        {
            case Count:
                clusterCount = ClusterCountSlider.getValue();
                if (!Double.isNaN(_psp.ExchangeC4D(clusterCount)))
                    DistanceSlider.setValue((int) Math.round(_psp.ExchangeC4D(clusterCount)));
                if (_psp.ExchangeC4H(clusterCount) != -1)
                    HeightSlider.setValue(_psp.ExchangeC4H(clusterCount));
                break;
            case Distance:
                clusterCount = _psp.ExchangeD4C(DistanceSlider.getValue());
                ClusterCountSlider.setValue(clusterCount);
                if (_psp.ExchangeC4H(clusterCount) != -1)
                    HeightSlider.setValue(_psp.ExchangeC4H(clusterCount));
                break;
            case Height:
                clusterCount = _psp.ExchangeH4C(HeightSlider.getValue());
                if (clusterCount == -1)
                    clusterCount = _psp.MaxClusterCount();
                ClusterCountSlider.setValue(clusterCount);
                if (!Double.isNaN(_psp.ExchangeC4D(clusterCount)))
                    DistanceSlider.setValue((int) Math.round(_psp.ExchangeC4D(clusterCount)));
                break;
            case None:
            default:
                return;
        }

        if (clusterCount == -1) return;
        _psp.SearchPattern(Double.NaN, clusterCount);
        PlotData();

        if (DomainRowRB.isSelected())
        {
            PlotRowsPattern();
        }
        else
        {
            PlotColsPattern();
            CountColMetadata();
        }

        DisplayPatternDetails();
    }

    private void PlotData()
    {
        Space determinedPattern = _psp.GetPatternSpace();
        HeatChart heatChart = new HeatChart(determinedPattern.content);
        heatChart.setBackgroundColour(Color.getHSBColor(bColor[0], bColor[1], bColor[2]));
        heatChart.setChartMargin(10);

        heatChart.setXValues(determinedPattern.colTitle);
        heatChart.setYValues(determinedPattern.rowTitle);
        heatChart.setXAxisLabel(GlobalVariables.HeatmapOptions.horizontalAxisTitle);
        heatChart.setYAxisLabel(GlobalVariables.HeatmapOptions.verticalAxisTitle);

        _logestColL = "";
        for (String columnTitle : determinedPattern.colTitle)
            if (_logestColL.length() < columnTitle.length())
                _logestColL = columnTitle;

        _logestRowL = "";
        for (String rowTitle : determinedPattern.rowTitle)
            if (_logestRowL.length() < rowTitle.length())
                _logestRowL = rowTitle;

        heatChart.setHighValueColour(GlobalVariables.hightValueColor);
        heatChart.setLowValueColour(GlobalVariables.lowValueColor);

        _dimension = new Dimension(PatternPlot.getSize().width - 200, PatternPlot.getSize().height - 100);
        _dimension.height = (int) Math.round((_dimension.height - (heatChart.getChartMargin() * 8.00)) / determinedPattern.content.length);
        _dimension.width = (int) Math.round((_dimension.width - (heatChart.getChartMargin() * 5.0)) / determinedPattern.content[0].length);
        if (_dimension.height < 1) _dimension.height = 1;
        if (_dimension.width < 1) _dimension.width = 1;
        heatChart.setCellSize(_dimension);

        PatternPlot.setText("");
        PatternPlot.setIcon(new javax.swing.ImageIcon(heatChart.getChartImage(true)));
    }
    private void PlotRowsPattern()
    {
        double[][] determinedPattern = _psp.GetPatternCount();
        HeatChart heatChart = new HeatChart(determinedPattern);
        heatChart.setBackgroundColour(Color.getHSBColor(bColor[0], bColor[1], bColor[2]));
        heatChart.setChartMargin(10);

        heatChart.setXValues(new String[]
        {
            _logestColL
        });

        String[] yValues = new String[determinedPattern.length];
        for (int i = 0 ; i < yValues.length ; i++)
            yValues[i] = _patternTagPrefix + i + " : " + (int) determinedPattern[i][0] + " ";
        heatChart.setYValues(yValues);

        heatChart.setHighValueColour(GlobalVariables.hightValueColor);
        heatChart.setLowValueColour(GlobalVariables.lowValueColor);

        Dimension dimension = new Dimension(50, _dimension.height);
        heatChart.setCellSize(dimension);

        RowPatternPlot.setText("");
        RowPatternPlot.setIcon(new javax.swing.ImageIcon(heatChart.getChartImage(false)));
    }
    private void PlotColsPattern()
    {
        double[][] determinedPattern = _psp.GetPatternCount();
        HeatChart heatChart = new HeatChart(determinedPattern);
        heatChart.setBackgroundColour(Color.getHSBColor(bColor[0], bColor[1], bColor[2]));
        heatChart.setChartMargin(10);

        heatChart.setYValues(new String[]
        {
            _logestRowL
        });

        String[] xValues = new String[determinedPattern[0].length];
        for (int i = 0 ; i < xValues.length ; i++)
            xValues[i] = _patternTagPrefix + i + " : " + (int) determinedPattern[0][i] + " ";
        heatChart.setXValues(xValues);

        heatChart.setHighValueColour(GlobalVariables.hightValueColor);
        heatChart.setLowValueColour(GlobalVariables.lowValueColor);

        Dimension dimension = new Dimension(_dimension.width, 50);
        heatChart.setCellSize(dimension);

        ColPatternPlot.setText("");
        ColPatternPlot.setIcon(new javax.swing.ImageIcon(heatChart.getChartImage(false)));
    }

    private void CountColMetadata()
    {
        Space spacePattern = _psp.GetPatternSpace();
        _metadataCount = new HashMap<>();

        for (int col = 0 ; col < spacePattern.content[0].length ; col++)
        {
            ArrayList<String> ids = _psp.GetRelativeIDs(spacePattern.colsID[col]);

            for (String id : ids)
                CountMetadata(id, col, spacePattern.content[0].length);
        }

        String[] columnTitle = new String[spacePattern.content[0].length + 2];
        columnTitle[0] = "Attribute";
        columnTitle[1] = "Value";
        for (int i = 2 ; i < spacePattern.content[0].length + 2 ; i++)
            columnTitle[i] = _patternTagPrefix + String.valueOf(i - 2);
        DisplayCountedMetada(columnTitle, spacePattern.content[0].length + 2);
    }

    private void CountMetadata(String sampleID, int col, int colSize)
    {
        int[] tempValue;
        for (SampleData cachedFeature : GlobalVariables.samples)
        {
            if (cachedFeature.sampleID.equals(sampleID))
            {
                for (Map.Entry<String, String[]> md : cachedFeature.metaData.entrySet())
                {
                    for (String value : md.getValue())
                    {
                        AttributeValuePair avPair = new AttributeValuePair(md.getKey(), value);

                        if (_metadataCount.containsKey(avPair))
                            tempValue = _metadataCount.get(avPair);
                        else
                            tempValue = new int[colSize];

                        tempValue[col]++;
                        _metadataCount.put(avPair, tempValue);
                    }
                }
                return;
            }
        }
    }
    private void DisplayCountedMetada(String[] columnTitle, int colCount)
    {
        DefaultTableModel spacePatternTabMod = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        spacePatternTabMod.setColumnIdentifiers(columnTitle);

        for (Map.Entry<AttributeValuePair, int[]> md : _metadataCount.entrySet())
        {
            String[] row = new String[colCount];
            row[0] = md.getKey().GetAttribute();
            row[1] = md.getKey().GetValue();

            for (int i = 0 ; i < md.getValue().length ; i++)
                row[i + 2] = String.valueOf(md.getValue()[i]);

            spacePatternTabMod.addRow(row);
        }

        this.metadataCount_DG.setModel(spacePatternTabMod);
        metadataCount_DG.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ResizeColumnWidth(metadataCount_DG);
    }
    private void DisplayPatternDetails()
    {
        DefaultTableModel spacePatternTabMod = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        String[] columnTitle = new String[2];
        columnTitle[0] = "Pattern Tag";
        columnTitle[1] = "Pattern Count";
        spacePatternTabMod.setColumnIdentifiers(columnTitle);

        double[][] determinedPattern = _psp.GetPatternCount();

        if (DomainRowRB.isSelected())
        {
            for (int r = 0 ; r < determinedPattern.length ; r++)
            {
                String[] row = new String[2];
                row[0] = _patternTagPrefix + r;
                row[1] = String.valueOf((int) Math.round(determinedPattern[r][0]));
                spacePatternTabMod.addRow(row);
            }

            this.patternDetails_DG1.setModel(spacePatternTabMod);
            patternDetails_DG1.getSelectionModel().addListSelectionListener(new DG1_SelectionListener());
        }
        else
        {
            for (int c = 0 ; c < determinedPattern[0].length ; c++)
            {
                String[] row = new String[2];
                row[0] = _patternTagPrefix + c;
                row[1] = String.valueOf((int) Math.round(determinedPattern[0][c]));
                spacePatternTabMod.addRow(row);
            }
        }

        patternDetails_DG2.setModel(spacePatternTabMod);
        patternDetails_DG2.getSelectionModel().addListSelectionListener(new DG2_SelectionListener());
    }


    class DG1_SelectionListener implements ListSelectionListener
    {
        /// Following code, to some extend, is copied and pasted from Space.java. 
        /// It seems that a better implementation of all this mess would be:
        /// create a space for each determined pattern, and let predefined functions do the job.

        @Override
        public void valueChanged(ListSelectionEvent e)
        {
            if (e.getValueIsAdjusting()) return; // mouse button not released yet

            int row = patternDetails_DG1.getSelectedRow();
            if (row < 0) return; // true when clearSelection

            int col = patternDetails_DG1.getSelectedColumn();
            if (col < 0) return; // true when clearSelection

            int selectedPattern = Integer.valueOf(((String) patternDetails_DG1.getValueAt(row, 0)).substring(_patternTagPrefix.length()));

            double minVV = 0;// Double.valueOf(MinValidTF.getText());
            double maxVV = Double.MAX_VALUE;// Double.valueOf(MaxValidTF.getText());
            Space spacePattern = _psp.GetPatternSpace();
            _metadataCount = new HashMap<>();

            ArrayList<String> ids = _psp.GetRelativeIDs(spacePattern.rowsID[selectedPattern]);

            for (String id : ids)
                for (int r = 0 ; r < source.rowsID.length ; r++)
                    if (source.rowsID[r].equals(id))
                    {
                        for (int c = 0 ; c < spacePattern.colsID.length ; c++)
                            if (source.content[r][c] >= minVV && source.content[r][c] <= maxVV)
                                CountMetadata(spacePattern.colsID[c], c, spacePattern.content[0].length);
                        break;
                    }

            String[] columnTitle = new String[spacePattern.colsID.length + 2];
            String attribute = Columns_CB1.getSelectedItem().toString();
            columnTitle[0] = "Attribute";
            columnTitle[1] = "Value";

            for (int c = 0 ; c < spacePattern.colsID.length ; c++)
                //columnTitle[c + 1] = spacePattern.colTitle[c];
                for (int j = 0 ; j < GlobalVariables.samples.size() ; j++)
                    if (GlobalVariables.samples.get(j).sampleID.equals(spacePattern.colsID[c]))
                        if (attribute.equals("Sample Name (file name)"))
                            columnTitle[c + 2] = GlobalVariables.samples.get(j).fileName;
                        else
                        {
                            String[] value = GlobalVariables.samples.get(j).metaData.get(attribute);
                            if (value != null)
                            {
                                if (value.length > 0)
                                {
                                    columnTitle[c + 2] = value[0];
                                    for (int k = 1 ; k < value.length ; k++)
                                        columnTitle[c + 2] += value[k];
                                }
                                else
                                    columnTitle[c + 2] = "NA";
                            }
                            else
                                columnTitle[c + 2] = "NA";
                        }

            DisplayCountedMetada(columnTitle, spacePattern.content[0].length + 2);
        }
    }

    class DG2_SelectionListener implements ListSelectionListener
    {
        /// Following code, to some extend, is copied and pasted from Space.java. 
        /// It seems that a better implementation of all this mess would be:
        /// create a space for each determined pattern, and let predefined functions do the job.

        @Override
        public void valueChanged(ListSelectionEvent e)
        {
            if (e.getValueIsAdjusting()) return; // mouse button not released yet

            int row = patternDetails_DG2.getSelectedRow();
            if (row < 0) return; // true when clearSelection

            int col = patternDetails_DG2.getSelectedColumn();
            if (col < 0) return; // true when clearSelection

            int selectedPattern = Integer.valueOf(((String) patternDetails_DG2.getValueAt(row, 0)).substring(_patternTagPrefix.length()));
            Space spacePattern = _psp.GetPatternSpace();
            DefaultTableModel selectedPatternDetails_DG_TabMod = new DefaultTableModel()
            {
                @Override
                public boolean isCellEditable(int row, int column)
                {
                    return false;
                }
            };

            int tCounter = 0;
            Space tSpace;
            if (DomainRowRB.isSelected())
            {
                ArrayList<String> ids = _psp.GetRelativeIDs(spacePattern.rowsID[selectedPattern]);
                String[] columnTitle;

                if (UseRowID_RB.isSelected())
                {
                    columnTitle = new String[]
                    {
                        "index", "ID"
                    };
                    selectedPatternDetails_DG_TabMod.setColumnIdentifiers(columnTitle);

                    for (String id : ids)
                        selectedPatternDetails_DG_TabMod.addRow(new String[]
                        {
                            String.valueOf(tCounter++), id
                        });
                }
                else if (UseSampleRegionTXTAttributes_RB.isSelected())
                {
                    columnTitle = new String[spacePattern.colTitle.length + 1];
                    columnTitle[0] = "index";
                    for (int c = 0 ; c < spacePattern.colTitle.length ; c++)
                        columnTitle[c + 1] = spacePattern.colTitle[c];
                    selectedPatternDetails_DG_TabMod.setColumnIdentifiers(columnTitle);

                    String attribute = SampleAttribute_CB.getSelectedItem().toString();
                    for (String id : ids)
                    {
                        String[] newRow = new String[source.colsID.length + 1];
                        newRow[0] = id;
                        String[] ID = id.split("\\s*_\\s*");
                        for (int c = 0 ; c < source.colsID.length ; c++)
                            for (SampleData gfd : GlobalVariables.samples)
                                if (gfd.sampleID.equals(source.colsID[c]))
                                {
                                    newRow[c + 1] = gfd.GetTXTAttribute(
                                            ID[0],
                                            Integer.valueOf(ID[1]),
                                            Integer.valueOf(ID[2]),
                                            attribute);

                                    break;
                                }

                        selectedPatternDetails_DG_TabMod.addRow(newRow);
                    }
                }
                else
                {
                    String attribute = ReferenceSampleAttributes_CB.getSelectedItem().toString();
                    columnTitle = new String[]
                    {
                        "index", attribute
                    };
                    selectedPatternDetails_DG_TabMod.setColumnIdentifiers(columnTitle);

                    for (String id : ids)
                    {
                        String[] ID = id.split("\\s*_\\s*");
                        selectedPatternDetails_DG_TabMod.addRow(new String[]
                        {
                            String.valueOf(tCounter++),
                            GlobalVariables.annotations.GetTXTAttribute(
                            ID[0],
                            Integer.valueOf(ID[1]),
                            Integer.valueOf(ID[2]),
                            attribute)
                        });
                    }
                }
                tSpace = CreateSpaceBasedOnRowPattern(ids);
                PlotPattern(tSpace);
            }
            else
            {
                int cfs = GlobalVariables.samples.size();
                String attribute = Columns_CB.getSelectedItem().toString();
                ArrayList<String> ids = _psp.GetRelativeIDs(spacePattern.colsID[selectedPattern]);
                selectedPatternDetails_DG_TabMod.setColumnIdentifiers(new String[]
                {
                    "index", attribute
                });

                for (String id : ids)
                {
                    String[] newRow = new String[2];
                    newRow[0] = String.valueOf(tCounter++);
                    for (int j = 0 ; j < cfs ; j++)
                        if (GlobalVariables.samples.get(j).sampleID.equals(id))
                        {
                            if (attribute.equals("Sample Name (file name)"))
                                newRow[1] = GlobalVariables.samples.get(j).fileName;
                            else
                            {
                                String[] value = GlobalVariables.samples.get(j).metaData.get(attribute);
                                if (value != null)
                                {
                                    if (value.length > 0)
                                    {
                                        newRow[1] = value[0];
                                        for (int k = 1 ; k < value.length ; k++)
                                            newRow[1] += value[k];
                                    }
                                    else
                                        newRow[1] = "NA";
                                }
                                else
                                    newRow[1] = "NA";
                            }
                            break;
                        }

                    selectedPatternDetails_DG_TabMod.addRow(newRow);
                }

                tSpace = CreateSpaceBasedOnColPattern(ids);
                PlotPattern(tSpace);
            }

            ShowPatternOnDG(tSpace);
            selectedPatternDetails_DG.setModel(selectedPatternDetails_DG_TabMod);
        }

        private Space CreateSpaceBasedOnRowPattern(ArrayList<String> ids)
        {
            Space p = new Space(ids.size(), source.colsID.length);
            System.arraycopy(source.colsID, 0, p.colsID, 0, source.colsID.length);
            ids.toArray(p.rowsID);

            int i = 0;
            HashSet<String> idsH = new HashSet<>(ids);

            for (int row = 0 ; row < source.rowsID.length ; row++)
                if (idsH.contains(source.rowsID[row]))
                {
                    for (int col = 0 ; col < source.colsID.length ; col++)
                        p.content[i][col] = source.content[row][col];
                    i++;
                }

            return p;
        }
        private Space CreateSpaceBasedOnColPattern(ArrayList<String> ids)
        {
            Space p = new Space(source.rowsID.length, ids.size());
            System.arraycopy(source.rowsID, 0, p.rowsID, 0, source.rowsID.length);
            ids.toArray(p.colsID);

            int i = 0;
            HashSet<String> idsH = new HashSet<>(ids);

            for (int col = 0 ; col < source.colsID.length ; col++)
                if (idsH.contains(source.colsID[col]))
                {
                    for (int row = 0 ; row < source.rowsID.length ; row++)
                        p.content[row][i] = source.content[row][col];
                    i++;
                }

            return p;
        }

        private void PlotPattern(Space p)
        {
            p.UpdateRowsTitles();
            p.UpdateColumnsTitles();

            HeatChart heatChart = new HeatChart(p.content);
            heatChart.setBackgroundColour(Color.getHSBColor(bColor[0], bColor[1], bColor[2]));
            heatChart.setChartMargin(10);

            heatChart.setXValues(p.colTitle);
            heatChart.setYValues(p.rowTitle);
            heatChart.setXAxisLabel(GlobalVariables.HeatmapOptions.horizontalAxisTitle);
            heatChart.setYAxisLabel(GlobalVariables.HeatmapOptions.verticalAxisTitle);

            heatChart.setHighValueColour(GlobalVariables.hightValueColor);
            heatChart.setLowValueColour(GlobalVariables.lowValueColor);

            _dimension = new Dimension(PatternDetailPlot.getSize().width - 200, PatternDetailPlot.getSize().height - 100);
            _dimension.height = (int) Math.round((_dimension.height - (HeatMap.GetChartMargin() * 8.00)) / p.content.length);
            _dimension.width = (int) Math.round((_dimension.width - (HeatMap.GetChartMargin() * 5.0)) / p.content[0].length);

            if (_dimension.height < 1) _dimension.height = 1;
            if (_dimension.width < 1) _dimension.width = 1;
            heatChart.setCellSize(_dimension);

            PatternDetailPlot.setText("");
            PatternDetailPlot.setIcon(new javax.swing.ImageIcon(heatChart.getChartImage(true)));
        }

        private void ShowPatternOnDG(Space space)
        {
            DefaultTableModel spaceTabMod = new DefaultTableModel()
            {
                @Override
                public boolean isCellEditable(int row, int column)
                {
                    return false;
                }
            };

            String[] gotColumnTitle = space.colTitle;
            String[] gotRowTitle = space.rowTitle;
            String[] columnTitle = new String[gotColumnTitle.length + 2];
            columnTitle[0] = "index";
            columnTitle[1] = "Region label";
            System.arraycopy(gotColumnTitle, 0, columnTitle, 2, gotColumnTitle.length);

            spaceTabMod.setColumnIdentifiers(columnTitle);

            int spaceColSize = space.content[0].length;

            int col = 0;
            for (int r = 0 ; r < space.content.length ; r++)
            {
                String[] row = new String[spaceColSize + 2];
                row[0] = Integer.toString(r);
                row[1] = gotRowTitle[r];

                for (col = 0 ; col < spaceColSize ; col++)
                {
                    row[col + 2] = Double.toString(space.content[r][col]);
                }

                spaceTabMod.addRow(row);
            }

            PatternDetailDG.setModel(spaceTabMod);
            PatternDetailDG.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            ResizeColumnWidth(PatternDetailDG);
        }
    }

    public void ResizeColumnWidth(JTable table)
    {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0 ; column < table.getColumnCount() ; column++)
        {
            int width = 50; // Min width

            TableColumn tableColumn = columnModel.getColumn(column);
            TableCellRenderer renderer = tableColumn.getHeaderRenderer();
            if (renderer == null)
            {
                renderer = table.getTableHeader().getDefaultRenderer();
            }
            Component component
                      = renderer.getTableCellRendererComponent(
                            table,
                            tableColumn.getHeaderValue(), false, false, -1, column);
            width = Math.max(component.getPreferredSize().width + 5, width);

            for (int row = 0 ; row < table.getRowCount() ; row++)
            {
                renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 5, width);
            }
            if (width > 400)
                width = 400;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    private void EnableDiableColPatternDetailAttributes(Boolean enable)
    {
        DeterminedAtts_L.setEnabled(enable);
        DeterminedAtts_L1.setEnabled(enable);
        Columns_CB.setEnabled(enable);
    }
    private void EnableDiableRowPatternDetailAttributes(Boolean enable)
    {
        DeterminedAtts_L2.setEnabled(enable);
        UseRowID_RB.setEnabled(enable);
        UseSampleRegionTXTAttributes_RB.setEnabled(enable);
        ChooseSampleForTXTAttributes_CB.setEnabled(enable);
        jLabel2.setEnabled(enable);
        jLabel3.setEnabled(enable);
        SampleAttribute_CB.setEnabled(enable);
        UseReferenceAnnotation_RB.setEnabled(enable);
        ReferenceSampleAttributes_CB.setEnabled(enable);
    }

    private void EnableDisableMetadataAggregates(Boolean enable)
    {
        DeterminedAtts_L4.setEnabled(enable);
        DeterminedAtts_L3.setEnabled(enable);
        Columns_CB1.setEnabled(enable);
    }

    private void EnableDisableClusterSlidersGroups(Boolean enable)
    {
        NoOfClustersL.setEnabled(enable);
        ClusterCountSlider.setEnabled(enable);
        ClusterCountSliderValue.setEnabled(enable);
        ClusterCountSuggestedL.setEnabled(enable);

        HeightL.setEnabled(enable);
        HeightSlider.setEnabled(enable);
        HeightSliderValue.setEnabled(enable);
        ClusterHeightSuggestedL.setEnabled(enable);

        DistanceL.setEnabled(enable);
        DistanceSlider.setEnabled(enable);
        DistanceSliderValue.setEnabled(enable);
        ClusterDistanceSuggestedL.setEnabled(enable);
    }
}
