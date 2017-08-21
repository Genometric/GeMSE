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
package GeMSE.Visualization.Graph;

import GeMSE.GeMSE;
import GeMSE.GlobalVariables;
import GeMSE.GlobalVariables.GraphOptions.Theme;
import GeMSE.IO.OpenWebpage;
import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import prefuse.data.Graph;
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLReader;
import prefuse.util.ColorLib;

/**
 *
 * @author Vahid Jalili
 */
public class GraphVis extends javax.swing.JFrame
{

    /**
     * Creates new form GraphVis
     *
     * @param graphMLFile
     */
    public GraphVis(String graphMLFile)
    {
        initComponents();
        sysChanging = true;

        ButtonGroup groupA = new ButtonGroup();
        groupA.add(RadialGraphRB);
        groupA.add(ForceDirectedGraphRB);
        if (GlobalVariables.GraphOptions.graphType == GraphType.Radial)
        {
            RadialGraphRB.setSelected(true);
            EnableDisableAngularPanel(true);
            GroupingAgg.setEnabled(false);
            enforceLayoutBoundsCB.setEnabled(false);
        }
        else
        {
            ForceDirectedGraphRB.setSelected(true);
            EnableDisableAngularPanel(false);
            GroupingAgg.setEnabled(true);
            enforceLayoutBoundsCB.setEnabled(true);
        }

        ButtonGroup groupB = new ButtonGroup();
        groupB.add(GroupingNone);
        groupB.add(GroupingColor);
        groupB.add(GroupingAgg);
        switch (GlobalVariables.GraphOptions.grouping)
        {
            case None:
                GroupingNone.setSelected(true);
                EnableDisableColorPanel(false);
                EnableDisableAggPanel(false);
                break;
            case Color:
                GroupingColor.setSelected(true);
                EnableDisableColorPanel(true);
                EnableDisableAggPanel(false);
                break;
            default:
                GroupingAgg.setSelected(true);
                EnableDisableColorPanel(false);
                EnableDisableAggPanel(true);
                break;
        }

        ButtonGroup groupC = new ButtonGroup();
        groupC.add(BrightThemeRB);
        groupC.add(DarkThemeRB);
        groupC.add(CustomThemeRB);
        switch (GlobalVariables.GraphOptions.theme)
        {
            case Bright:
                BrightThemeRB.setSelected(true);
                SetupCustomThemeB.setEnabled(false);
                break;

            case Dark:
                DarkThemeRB.setSelected(true);
                SetupCustomThemeB.setEnabled(false);
                break;

            case Custom:
                CustomThemeRB.setSelected(true);
                SetupCustomThemeB.setEnabled(true);
                break;
        }

        ButtonGroup groupD = new ButtonGroup();
        groupD.add(SolidColorAgg);
        groupD.add(FullSpectralColorAgg);
        if (GlobalVariables.GraphOptions.useSolidAggColor)
            SolidColorAgg.setSelected(true);
        else
            FullSpectralColorAgg.setSelected(true);

        AngularBoundDegree.setValue(GlobalVariables.GraphOptions.angularBoundDegree);
        AngularBoundRadius.setValue(GlobalVariables.GraphOptions.angularBoundRadius);

        sysChanging = false;

        BeforeCut.setBackground(GlobalVariables.GraphOptions.beforeCutColor);
        AfterCut.setBackground(GlobalVariables.GraphOptions.afterCutColor);
        SolidColorB.setBackground(GlobalVariables.GraphOptions.solidColor);

        try
        {
            _graph = new GraphMLReader().readGraph(graphMLFile);
        }
        catch (DataIOException ex)
        {
            Logger.getLogger(GraphTreeVis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Boolean sysChanging;
    private GraphTreeVis gtv;
    private Graph _graph;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenuBar6 = new javax.swing.JMenuBar();
        jMenu12 = new javax.swing.JMenu();
        jMenu13 = new javax.swing.JMenu();
        jMenuBar7 = new javax.swing.JMenuBar();
        jMenu14 = new javax.swing.JMenu();
        jMenu15 = new javax.swing.JMenu();
        jMenuBar8 = new javax.swing.JMenuBar();
        jMenu16 = new javax.swing.JMenu();
        jMenu17 = new javax.swing.JMenu();
        jMenuBar9 = new javax.swing.JMenuBar();
        jMenu18 = new javax.swing.JMenu();
        jMenu19 = new javax.swing.JMenu();
        jMenuBar10 = new javax.swing.JMenuBar();
        jMenu20 = new javax.swing.JMenu();
        jMenu21 = new javax.swing.JMenu();
        jMenuBar11 = new javax.swing.JMenuBar();
        jMenu22 = new javax.swing.JMenu();
        jMenu23 = new javax.swing.JMenu();
        jMenuBar12 = new javax.swing.JMenuBar();
        jMenu24 = new javax.swing.JMenu();
        jMenu25 = new javax.swing.JMenu();
        jMenuBar13 = new javax.swing.JMenuBar();
        jMenu26 = new javax.swing.JMenu();
        jMenu27 = new javax.swing.JMenu();
        jMenuBar14 = new javax.swing.JMenuBar();
        jMenu28 = new javax.swing.JMenu();
        jMenu29 = new javax.swing.JMenu();
        jMenuBar15 = new javax.swing.JMenuBar();
        jMenu30 = new javax.swing.JMenu();
        jMenu31 = new javax.swing.JMenu();
        jMenuBar16 = new javax.swing.JMenuBar();
        jMenu32 = new javax.swing.JMenu();
        jMenu33 = new javax.swing.JMenu();
        jMenuBar17 = new javax.swing.JMenuBar();
        jMenu34 = new javax.swing.JMenu();
        jMenu35 = new javax.swing.JMenu();
        jMenuBar18 = new javax.swing.JMenuBar();
        jMenu36 = new javax.swing.JMenu();
        jMenu37 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        ControllersPanel = new javax.swing.JPanel();
        AngularBoundsJP = new javax.swing.JPanel();
        SetAngularBoundsL = new javax.swing.JLabel();
        AngularBoundStartL = new javax.swing.JLabel();
        AngularBoundDegree = new javax.swing.JSpinner();
        AngularBoundStopL = new javax.swing.JLabel();
        AngularBoundRadius = new javax.swing.JSpinner();
        EnforceAngularBounds = new javax.swing.JCheckBox();
        RadialGraphRB = new javax.swing.JRadioButton();
        ForceDirectedGraphRB = new javax.swing.JRadioButton();
        enforceLayoutBoundsCB = new javax.swing.JCheckBox();
        GraphTypeL = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        GroupingLabel = new javax.swing.JLabel();
        GroupingNone = new javax.swing.JRadioButton();
        GroupingColor = new javax.swing.JRadioButton();
        GroupingAgg = new javax.swing.JRadioButton();
        BeforeCut = new javax.swing.JButton();
        BeforeCutL = new javax.swing.JLabel();
        AfterCutL = new javax.swing.JLabel();
        AfterCut = new javax.swing.JButton();
        SolidColorAgg = new javax.swing.JRadioButton();
        SolidColorB = new javax.swing.JButton();
        FullSpectralColorAgg = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        BrightThemeRB = new javax.swing.JRadioButton();
        DarkThemeRB = new javax.swing.JRadioButton();
        CustomThemeRB = new javax.swing.JRadioButton();
        SetupCustomThemeB = new javax.swing.JButton();
        graphPanel = new javax.swing.JPanel();
        jMenuBar19 = new javax.swing.JMenuBar();
        jMenu38 = new javax.swing.JMenu();
        SaveMenuItem = new javax.swing.JMenuItem();
        ExitMenuItem = new javax.swing.JMenuItem();
        HelpMenu = new javax.swing.JMenu();
        HOnline = new javax.swing.JMenu();
        HOnlineDocs = new javax.swing.JMenu();
        HDInterface = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        jMenu7.setText("File");
        jMenuBar4.add(jMenu7);

        jMenu8.setText("Edit");
        jMenuBar4.add(jMenu8);

        jMenu9.setText("File");
        jMenuBar5.add(jMenu9);

        jMenu10.setText("Edit");
        jMenuBar5.add(jMenu10);

        jMenu11.setText("jMenu11");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenu12.setText("File");
        jMenuBar6.add(jMenu12);

        jMenu13.setText("Edit");
        jMenuBar6.add(jMenu13);

        jMenu14.setText("File");
        jMenuBar7.add(jMenu14);

        jMenu15.setText("Edit");
        jMenuBar7.add(jMenu15);

        jMenu16.setText("File");
        jMenuBar8.add(jMenu16);

        jMenu17.setText("Edit");
        jMenuBar8.add(jMenu17);

        jMenu18.setText("File");
        jMenuBar9.add(jMenu18);

        jMenu19.setText("Edit");
        jMenuBar9.add(jMenu19);

        jMenu20.setText("File");
        jMenuBar10.add(jMenu20);

        jMenu21.setText("Edit");
        jMenuBar10.add(jMenu21);

        jMenu22.setText("File");
        jMenuBar11.add(jMenu22);

        jMenu23.setText("Edit");
        jMenuBar11.add(jMenu23);

        jMenu24.setText("File");
        jMenuBar12.add(jMenu24);

        jMenu25.setText("Edit");
        jMenuBar12.add(jMenu25);

        jMenu26.setText("File");
        jMenuBar13.add(jMenu26);

        jMenu27.setText("Edit");
        jMenuBar13.add(jMenu27);

        jMenu28.setText("File");
        jMenuBar14.add(jMenu28);

        jMenu29.setText("Edit");
        jMenuBar14.add(jMenu29);

        jMenu30.setText("File");
        jMenuBar15.add(jMenu30);

        jMenu31.setText("Edit");
        jMenuBar15.add(jMenu31);

        jMenu32.setText("File");
        jMenuBar16.add(jMenu32);

        jMenu33.setText("Edit");
        jMenuBar16.add(jMenu33);

        jMenu34.setText("File");
        jMenuBar17.add(jMenu34);

        jMenu35.setText("Edit");
        jMenuBar17.add(jMenu35);

        jMenu36.setText("File");
        jMenuBar18.add(jMenu36);

        jMenu37.setText("Edit");
        jMenuBar18.add(jMenu37);

        jMenuItem1.setText("jMenuItem1");

        setMinimumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(1300, 780));
        setSize(new java.awt.Dimension(1300, 780));

        ControllersPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        AngularBoundsJP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        SetAngularBoundsL.setText("Set angular bounds");

        AngularBoundStartL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AngularBoundStartL.setText("degree");

        AngularBoundDegree.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                AngularBoundDegreeStateChanged(evt);
            }
        });

        AngularBoundStopL.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AngularBoundStopL.setText("radius");

        AngularBoundRadius.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                AngularBoundRadiusStateChanged(evt);
            }
        });

        EnforceAngularBounds.setText("Enforce angular bounds");
        EnforceAngularBounds.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                EnforceAngularBoundsActionPerformed(evt);
            }
        });

        RadialGraphRB.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        RadialGraphRB.setText("Radial graph");
        RadialGraphRB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                RadialGraphRBActionPerformed(evt);
            }
        });

        ForceDirectedGraphRB.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        ForceDirectedGraphRB.setText("Force directed graph");
        ForceDirectedGraphRB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ForceDirectedGraphRBActionPerformed(evt);
            }
        });

        enforceLayoutBoundsCB.setText("Enforce layout bounds");
        enforceLayoutBoundsCB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                enforceLayoutBoundsCBActionPerformed(evt);
            }
        });

        GraphTypeL.setText("Graph type");

        javax.swing.GroupLayout AngularBoundsJPLayout = new javax.swing.GroupLayout(AngularBoundsJP);
        AngularBoundsJP.setLayout(AngularBoundsJPLayout);
        AngularBoundsJPLayout.setHorizontalGroup(
            AngularBoundsJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(AngularBoundsJPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AngularBoundsJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RadialGraphRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AngularBoundsJPLayout.createSequentialGroup()
                        .addComponent(AngularBoundStartL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AngularBoundDegree, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AngularBoundsJPLayout.createSequentialGroup()
                        .addComponent(AngularBoundStopL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AngularBoundRadius, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ForceDirectedGraphRB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AngularBoundsJPLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(AngularBoundsJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EnforceAngularBounds, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SetAngularBoundsL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enforceLayoutBoundsCB, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(GraphTypeL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        AngularBoundsJPLayout.setVerticalGroup(
            AngularBoundsJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AngularBoundsJPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GraphTypeL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RadialGraphRB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EnforceAngularBounds)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SetAngularBoundsL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AngularBoundsJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AngularBoundStartL)
                    .addComponent(AngularBoundDegree, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AngularBoundsJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AngularBoundStopL)
                    .addComponent(AngularBoundRadius, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ForceDirectedGraphRB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enforceLayoutBoundsCB)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        GroupingLabel.setText("Grouping");

        GroupingNone.setText("None");
        GroupingNone.setMaximumSize(new java.awt.Dimension(100, 23));
        GroupingNone.setMinimumSize(new java.awt.Dimension(100, 23));
        GroupingNone.setPreferredSize(new java.awt.Dimension(100, 23));
        GroupingNone.setSize(new java.awt.Dimension(100, 23));
        GroupingNone.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                GroupingNoneActionPerformed(evt);
            }
        });

        GroupingColor.setText("Color");
        GroupingColor.setMaximumSize(new java.awt.Dimension(100, 23));
        GroupingColor.setMinimumSize(new java.awt.Dimension(100, 23));
        GroupingColor.setPreferredSize(new java.awt.Dimension(100, 23));
        GroupingColor.setSize(new java.awt.Dimension(100, 0));
        GroupingColor.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                GroupingColorActionPerformed(evt);
            }
        });

        GroupingAgg.setText("Aggregate");
        GroupingAgg.setMaximumSize(new java.awt.Dimension(100, 23));
        GroupingAgg.setMinimumSize(new java.awt.Dimension(100, 23));
        GroupingAgg.setPreferredSize(new java.awt.Dimension(100, 23));
        GroupingAgg.setSize(new java.awt.Dimension(100, 23));
        GroupingAgg.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                GroupingAggActionPerformed(evt);
            }
        });

        BeforeCut.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BeforeCutActionPerformed(evt);
            }
        });

        BeforeCutL.setText("Before cut node color");
        BeforeCutL.setSize(new java.awt.Dimension(45, 0));

        AfterCutL.setText("After cut node color");

        AfterCut.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                AfterCutActionPerformed(evt);
            }
        });

        SolidColorAgg.setText("Solid color");
        SolidColorAgg.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                SolidColorAggActionPerformed(evt);
            }
        });

        SolidColorB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                SolidColorBActionPerformed(evt);
            }
        });

        FullSpectralColorAgg.setText("Full spectral");
        FullSpectralColorAgg.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                FullSpectralColorAggActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(GroupingLabel)
                                    .addComponent(GroupingNone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(GroupingAgg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(GroupingColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(AfterCut, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(BeforeCut, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BeforeCutL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(AfterCutL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FullSpectralColorAgg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(SolidColorAgg)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SolidColorB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GroupingLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GroupingNone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GroupingColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BeforeCut, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BeforeCutL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AfterCut, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AfterCutL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GroupingAgg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SolidColorB, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(SolidColorAgg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FullSpectralColorAgg)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        BrightThemeRB.setText("Bright theme");
        BrightThemeRB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BrightThemeRBActionPerformed(evt);
            }
        });

        DarkThemeRB.setText("Dark theme");
        DarkThemeRB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                DarkThemeRBActionPerformed(evt);
            }
        });

        CustomThemeRB.setText("Custom theme");
        CustomThemeRB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                CustomThemeRBActionPerformed(evt);
            }
        });

        SetupCustomThemeB.setText("Setup");
        SetupCustomThemeB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                SetupCustomThemeBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CustomThemeRB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SetupCustomThemeB))
                    .addComponent(DarkThemeRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BrightThemeRB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BrightThemeRB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DarkThemeRB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomThemeRB)
                    .addComponent(SetupCustomThemeB))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ControllersPanelLayout = new javax.swing.GroupLayout(ControllersPanel);
        ControllersPanel.setLayout(ControllersPanelLayout);
        ControllersPanelLayout.setHorizontalGroup(
            ControllersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControllersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ControllersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AngularBoundsJP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        ControllersPanelLayout.setVerticalGroup(
            ControllersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControllersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AngularBoundsJP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        graphPanel.setLayout(new java.awt.BorderLayout());

        jMenu38.setText("  File  ");

        SaveMenuItem.setText("     Save graph     ");
        SaveMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                SaveMenuItemActionPerformed(evt);
            }
        });
        jMenu38.add(SaveMenuItem);

        ExitMenuItem.setText("     Exit");
        ExitMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ExitMenuItemActionPerformed(evt);
            }
        });
        jMenu38.add(ExitMenuItem);

        jMenuBar19.add(jMenu38);

        HelpMenu.setText("  Help  ");

        HOnline.setText("     Online Support     ");
        HOnline.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                HOnlineActionPerformed(evt);
            }
        });
        HelpMenu.add(HOnline);

        HOnlineDocs.setText("     Online Docs     ");

        HDInterface.setText("     Interface and Controllers     ");
        HDInterface.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                HDInterfaceActionPerformed(evt);
            }
        });
        HOnlineDocs.add(HDInterface);

        HelpMenu.add(HOnlineDocs);

        jMenuBar19.add(HelpMenu);

        setJMenuBar(jMenuBar19);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ControllersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ControllersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(graphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void RadialGraphRBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_RadialGraphRBActionPerformed
    {//GEN-HEADEREND:event_RadialGraphRBActionPerformed
        if (sysChanging) return;
        if (RadialGraphRB.isSelected())
        {
            EnforceAngularBounds.setEnabled(true);
            enforceLayoutBoundsCB.setEnabled(false);
            EnableDisableAngularPanel(true);
            if (GroupingAgg.isSelected())
                GroupingNone.setSelected(true);
            GroupingAgg.setEnabled(false);
            UpdateGraph();
        }
    }//GEN-LAST:event_RadialGraphRBActionPerformed

    private void ForceDirectedGraphRBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ForceDirectedGraphRBActionPerformed
    {//GEN-HEADEREND:event_ForceDirectedGraphRBActionPerformed
        if (sysChanging) return;
        if (ForceDirectedGraphRB.isSelected())
        {
            EnforceAngularBounds.setEnabled(false);
            EnableDisableAngularPanel(false);
            GroupingAgg.setEnabled(true);
            enforceLayoutBoundsCB.setEnabled(true);
            UpdateGraph();
        }
    }//GEN-LAST:event_ForceDirectedGraphRBActionPerformed

    private void GroupingNoneActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_GroupingNoneActionPerformed
    {//GEN-HEADEREND:event_GroupingNoneActionPerformed
        if (sysChanging) return;
        if (GroupingNone.isSelected())
        {
            EnableDisableColorPanel(false);
            EnableDisableAggPanel(false);
            UpdateGraph();
        }
    }//GEN-LAST:event_GroupingNoneActionPerformed

    private void GroupingColorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_GroupingColorActionPerformed
    {//GEN-HEADEREND:event_GroupingColorActionPerformed
        if (sysChanging) return;
        if (GroupingColor.isSelected())
        {
            EnableDisableColorPanel(true);
            EnableDisableAggPanel(false);
            UpdateGraph();
        }
    }//GEN-LAST:event_GroupingColorActionPerformed

    private void GroupingAggActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_GroupingAggActionPerformed
    {//GEN-HEADEREND:event_GroupingAggActionPerformed
        if (sysChanging) return;
        if (GroupingAgg.isSelected())
        {
            EnableDisableColorPanel(false);
            EnableDisableAggPanel(true);
            UpdateGraph();
        }
    }//GEN-LAST:event_GroupingAggActionPerformed

    private void EnforceAngularBoundsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_EnforceAngularBoundsActionPerformed
    {//GEN-HEADEREND:event_EnforceAngularBoundsActionPerformed
        if (sysChanging) return;
        if (EnforceAngularBounds.isSelected())
        {
            EnableDisableAngularPanel(true);
            UpdateGraph();
        }
        else
        {
            EnableDisableAngularPanel(false);
        }
    }//GEN-LAST:event_EnforceAngularBoundsActionPerformed

    private void BrightThemeRBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BrightThemeRBActionPerformed
    {//GEN-HEADEREND:event_BrightThemeRBActionPerformed
        if (sysChanging) return;
        SetupCustomThemeB.setEnabled(false);
        if (BrightThemeRB.isSelected())
            UpdateGraph();
    }//GEN-LAST:event_BrightThemeRBActionPerformed

    private void DarkThemeRBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_DarkThemeRBActionPerformed
    {//GEN-HEADEREND:event_DarkThemeRBActionPerformed
        if (sysChanging) return;
        SetupCustomThemeB.setEnabled(false);
        if (DarkThemeRB.isSelected())
            UpdateGraph();
    }//GEN-LAST:event_DarkThemeRBActionPerformed

    private void BeforeCutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BeforeCutActionPerformed
    {//GEN-HEADEREND:event_BeforeCutActionPerformed
        BeforeCut.setBackground(JColorChooser.showDialog(new JFrame("Color_Chooser"), "Before cut node color", BeforeCut.getBackground()));
        UpdateGraph();
    }//GEN-LAST:event_BeforeCutActionPerformed

    private void AfterCutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AfterCutActionPerformed
    {//GEN-HEADEREND:event_AfterCutActionPerformed
        AfterCut.setBackground(JColorChooser.showDialog(new JFrame("Color_Chooser"), "After cut node color", AfterCut.getBackground()));
        UpdateGraph();
    }//GEN-LAST:event_AfterCutActionPerformed

    private void SolidColorBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SolidColorBActionPerformed
    {//GEN-HEADEREND:event_SolidColorBActionPerformed
        SolidColorB.setBackground(JColorChooser.showDialog(new JFrame("Color_Chooser"), "Solid color aggregate", SolidColorB.getBackground()));
        UpdateGraph();
    }//GEN-LAST:event_SolidColorBActionPerformed

    private void SolidColorAggActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SolidColorAggActionPerformed
    {//GEN-HEADEREND:event_SolidColorAggActionPerformed
        if (SolidColorAgg.isSelected())
            UpdateGraph();
    }//GEN-LAST:event_SolidColorAggActionPerformed

    private void FullSpectralColorAggActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_FullSpectralColorAggActionPerformed
    {//GEN-HEADEREND:event_FullSpectralColorAggActionPerformed
        if (FullSpectralColorAgg.isSelected())
            UpdateGraph();
    }//GEN-LAST:event_FullSpectralColorAggActionPerformed

    private void AngularBoundDegreeStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_AngularBoundDegreeStateChanged
    {//GEN-HEADEREND:event_AngularBoundDegreeStateChanged
        if (sysChanging) return;
        UpdateGraph();
    }//GEN-LAST:event_AngularBoundDegreeStateChanged

    private void AngularBoundRadiusStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_AngularBoundRadiusStateChanged
    {//GEN-HEADEREND:event_AngularBoundRadiusStateChanged
        if (sysChanging) return;
        UpdateGraph();
    }//GEN-LAST:event_AngularBoundRadiusStateChanged

    private void SaveMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SaveMenuItemActionPerformed
    {//GEN-HEADEREND:event_SaveMenuItemActionPerformed
        JFileChooser chooser = new JFileChooser(GlobalVariables.GetLastBrowsedDirectory());
        chooser.setDialogTitle("Choose Save Directory");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setApproveButtonText("Save");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF (Portable Document Format)", "PDF", "pdf"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG (Portable Network Graphics)", "PNG", "png"));

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            GlobalVariables.SetLastBrowsedDirectoryFromFile((chooser.getSelectedFile()).getAbsolutePath());
            File file;
            if (chooser.getFileFilter().getDescription().equals("PNG (Portable Network Graphics)"))
            {
                file = new File((chooser.getSelectedFile()).getAbsolutePath().concat(".png"));
                if (CheckWritePermission(file))
                    SaveAsPNG(file);
            }
            else
            {
                file = new File((chooser.getSelectedFile()).getAbsolutePath().concat(".pdf"));
                if (CheckWritePermission(file))
                    SaveAsPDF(file);
            }
        }
    }//GEN-LAST:event_SaveMenuItemActionPerformed

    private void ExitMenuItemActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ExitMenuItemActionPerformed
    {//GEN-HEADEREND:event_ExitMenuItemActionPerformed
        this.dispose();
    }//GEN-LAST:event_ExitMenuItemActionPerformed

    private void HDInterfaceActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_HDInterfaceActionPerformed
    {//GEN-HEADEREND:event_HDInterfaceActionPerformed
        try
        {
            OpenWebpage.open(new URI("https://github.com/Genometric/GeMSE/wiki/Graph-Visualization"));
        }
        catch (URISyntaxException ex)
        {
            Logger.getLogger(GeMSE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_HDInterfaceActionPerformed

    private void HOnlineActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_HOnlineActionPerformed
    {//GEN-HEADEREND:event_HOnlineActionPerformed
        try
        {
            OpenWebpage.open(new URI("https://github.com/Genometric/GeMSE/issues"));
        }
        catch (URISyntaxException ex)
        {
            Logger.getLogger(GeMSE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_HOnlineActionPerformed

    private void enforceLayoutBoundsCBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_enforceLayoutBoundsCBActionPerformed
    {//GEN-HEADEREND:event_enforceLayoutBoundsCBActionPerformed
        if (sysChanging) return;
        UpdateGraph();
    }//GEN-LAST:event_enforceLayoutBoundsCBActionPerformed

    private void CustomThemeRBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_CustomThemeRBActionPerformed
    {//GEN-HEADEREND:event_CustomThemeRBActionPerformed
        if (sysChanging) return;
        SetupCustomThemeB.setEnabled(true);
        if (BrightThemeRB.isSelected())
            UpdateGraph();
    }//GEN-LAST:event_CustomThemeRBActionPerformed

    private void SetupCustomThemeBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SetupCustomThemeBActionPerformed
    {//GEN-HEADEREND:event_SetupCustomThemeBActionPerformed
        if (sysChanging) return;
        GraphThemeSetup themeSetup = new GraphThemeSetup(this);
        themeSetup.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        themeSetup.setLocationRelativeTo(this);
        themeSetup.setVisible(true);
        UpdateGraph();
    }//GEN-LAST:event_SetupCustomThemeBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AfterCut;
    private javax.swing.JLabel AfterCutL;
    private javax.swing.JSpinner AngularBoundDegree;
    private javax.swing.JSpinner AngularBoundRadius;
    private javax.swing.JLabel AngularBoundStartL;
    private javax.swing.JLabel AngularBoundStopL;
    private javax.swing.JPanel AngularBoundsJP;
    private javax.swing.JButton BeforeCut;
    private javax.swing.JLabel BeforeCutL;
    private javax.swing.JRadioButton BrightThemeRB;
    private javax.swing.JPanel ControllersPanel;
    private javax.swing.JRadioButton CustomThemeRB;
    private javax.swing.JRadioButton DarkThemeRB;
    private javax.swing.JCheckBox EnforceAngularBounds;
    private javax.swing.JMenuItem ExitMenuItem;
    private javax.swing.JRadioButton ForceDirectedGraphRB;
    private javax.swing.JRadioButton FullSpectralColorAgg;
    private javax.swing.JLabel GraphTypeL;
    private javax.swing.JRadioButton GroupingAgg;
    private javax.swing.JRadioButton GroupingColor;
    private javax.swing.JLabel GroupingLabel;
    private javax.swing.JRadioButton GroupingNone;
    private javax.swing.JMenuItem HDInterface;
    private javax.swing.JMenu HOnline;
    private javax.swing.JMenu HOnlineDocs;
    private javax.swing.JMenu HelpMenu;
    private javax.swing.JRadioButton RadialGraphRB;
    private javax.swing.JMenuItem SaveMenuItem;
    private javax.swing.JLabel SetAngularBoundsL;
    private javax.swing.JButton SetupCustomThemeB;
    private javax.swing.JRadioButton SolidColorAgg;
    private javax.swing.JButton SolidColorB;
    private javax.swing.JCheckBox enforceLayoutBoundsCB;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu17;
    private javax.swing.JMenu jMenu18;
    private javax.swing.JMenu jMenu19;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu20;
    private javax.swing.JMenu jMenu21;
    private javax.swing.JMenu jMenu22;
    private javax.swing.JMenu jMenu23;
    private javax.swing.JMenu jMenu24;
    private javax.swing.JMenu jMenu25;
    private javax.swing.JMenu jMenu26;
    private javax.swing.JMenu jMenu27;
    private javax.swing.JMenu jMenu28;
    private javax.swing.JMenu jMenu29;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu30;
    private javax.swing.JMenu jMenu31;
    private javax.swing.JMenu jMenu32;
    private javax.swing.JMenu jMenu33;
    private javax.swing.JMenu jMenu34;
    private javax.swing.JMenu jMenu35;
    private javax.swing.JMenu jMenu36;
    private javax.swing.JMenu jMenu37;
    private javax.swing.JMenu jMenu38;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar10;
    private javax.swing.JMenuBar jMenuBar11;
    private javax.swing.JMenuBar jMenuBar12;
    private javax.swing.JMenuBar jMenuBar13;
    private javax.swing.JMenuBar jMenuBar14;
    private javax.swing.JMenuBar jMenuBar15;
    private javax.swing.JMenuBar jMenuBar16;
    private javax.swing.JMenuBar jMenuBar17;
    private javax.swing.JMenuBar jMenuBar18;
    private javax.swing.JMenuBar jMenuBar19;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuBar jMenuBar6;
    private javax.swing.JMenuBar jMenuBar7;
    private javax.swing.JMenuBar jMenuBar8;
    private javax.swing.JMenuBar jMenuBar9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    private void EnableDisableAngularPanel(Boolean enable)
    {
        Boolean sysStatus = sysChanging;
        sysChanging = true;
        SetAngularBoundsL.setEnabled(enable);
        AngularBoundStartL.setEnabled(enable);
        AngularBoundDegree.setEnabled(enable);
        AngularBoundStopL.setEnabled(enable);
        AngularBoundRadius.setEnabled(enable);
        sysChanging = sysStatus;
    }

    private void EnableDisableColorPanel(Boolean enable)
    {
        Boolean sysStatus = sysChanging;
        sysChanging = true;
        BeforeCut.setEnabled(enable);
        BeforeCutL.setEnabled(enable);
        AfterCut.setEnabled(enable);
        AfterCutL.setEnabled(enable);
        sysChanging = sysStatus;
    }

    private void EnableDisableAggPanel(Boolean enable)
    {
        Boolean sysStatus = sysChanging;
        sysChanging = true;
        SolidColorAgg.setEnabled(enable);
        SolidColorB.setEnabled(enable);
        FullSpectralColorAgg.setEnabled(enable);
        sysChanging = sysStatus;
    }

    public void UpdateGraph()
    {
        if (sysChanging) return;
        Boolean sysStatus = sysChanging;
        sysChanging = true;
        NodeGrouping grouping = NodeGrouping.None;
        if (GroupingNone.isSelected())
            grouping = NodeGrouping.None;
        else if (GroupingColor.isSelected())
            grouping = NodeGrouping.Color;
        else if (GroupingAgg.isSelected())
            grouping = NodeGrouping.Aggregate;
        else
            GroupingNone.setSelected(true);

        GraphType gType = GraphType.Radial;
        if (RadialGraphRB.isSelected())
            gType = GraphType.Radial;
        else if (ForceDirectedGraphRB.isSelected())
            gType = GraphType.ForceDirected;
        else
            RadialGraphRB.setSelected(true);
        sysChanging = sysStatus;

        gtv = new GraphTreeVis();
        ThemeSetup();
        graphPanel.removeAll();
        if (EnforceAngularBounds.isSelected())
            graphPanel.add(gtv.Visualize(
                    _graph, grouping, gType,
                    (int) AngularBoundDegree.getValue(),
                    (int) AngularBoundRadius.getValue(),
                    graphPanel.getSize(), false));
        else
            graphPanel.add(gtv.Visualize(
                    _graph,
                    grouping,
                    gType,
                    graphPanel.getSize(),
                    enforceLayoutBoundsCB.isSelected()));
        revalidate();
        repaint();

        if (RadialGraphRB.isSelected())
            GlobalVariables.GraphOptions.graphType = GraphType.Radial;
        else
            GlobalVariables.GraphOptions.graphType = GraphType.ForceDirected;

        if (EnforceAngularBounds.isSelected())
            GlobalVariables.GraphOptions.enforceAngularBound = true;
        else
            GlobalVariables.GraphOptions.enforceAngularBound = false;
        GlobalVariables.GraphOptions.angularBoundDegree = (int) AngularBoundDegree.getValue();
        GlobalVariables.GraphOptions.angularBoundRadius = (int) AngularBoundRadius.getValue();

        if (GroupingNone.isSelected())
            GlobalVariables.GraphOptions.grouping = NodeGrouping.None;
        else if (GroupingColor.isSelected())
            GlobalVariables.GraphOptions.grouping = NodeGrouping.Color;
        else
            GlobalVariables.GraphOptions.grouping = NodeGrouping.Aggregate;

        if (BrightThemeRB.isSelected())
            GlobalVariables.GraphOptions.theme = Theme.Bright;
        else if (DarkThemeRB.isSelected())
            GlobalVariables.GraphOptions.theme = Theme.Dark;
        else
            GlobalVariables.GraphOptions.theme = Theme.Custom;

        GlobalVariables.GraphOptions.beforeCutColor = BeforeCut.getBackground();
        GlobalVariables.GraphOptions.afterCutColor = AfterCut.getBackground();
        GlobalVariables.GraphOptions.solidColor = SolidColorB.getBackground();

        if (SolidColorAgg.isSelected())
            GlobalVariables.GraphOptions.useSolidAggColor = true;
        else
            GlobalVariables.GraphOptions.useSolidAggColor = false;
    }

    private void ThemeSetup()
    {
        if (BrightThemeRB.isSelected())
        {
            gtv.background = ColorTheme.BrightTheme.background;
            gtv.foreground = ColorTheme.BrightTheme.foreground;
            gtv.edgeColor = ColorTheme.BrightTheme.edgeColor;
            gtv.textColor = ColorTheme.BrightTheme.textColor;
            gtv.nodeColor = ColorTheme.BrightTheme.nodeColor;
            gtv.textColorHover = ColorTheme.BrightTheme.textColorHover;
            gtv.nodeColorHover = ColorTheme.BrightTheme.nodeColorHover;
            gtv.nodeColorSelected = ColorTheme.BrightTheme.nodeColorSelected;
            gtv.nodeColorSearchResult = ColorTheme.BrightTheme.nodeColorSearchResult;
            gtv.nodeAggHoverStroke = ColorTheme.BrightTheme.nodeAggHoverStroke;
            gtv.nodeAggDefaultStroke = ColorTheme.BrightTheme.nodeAggDefaultStroke;
            gtv.aggHoverStroke = ColorTheme.BrightTheme.aggHoverStroke;
            gtv.aggDefaultStroke = ColorTheme.BrightTheme.aggDefaultStroke;
        }
        else if(DarkThemeRB.isSelected())
        {
            gtv.background = ColorTheme.DarkTheme.background;
            gtv.foreground = ColorTheme.DarkTheme.foreground;
            gtv.edgeColor = ColorTheme.DarkTheme.edgeColor;
            gtv.textColor = ColorTheme.DarkTheme.textColor;
            gtv.nodeColor = ColorTheme.DarkTheme.nodeColor;
            gtv.textColorHover = ColorTheme.DarkTheme.textColorHover;
            gtv.nodeColorHover = ColorTheme.DarkTheme.nodeColorHover;
            gtv.nodeColorSelected = ColorTheme.DarkTheme.nodeColorSelected;
            gtv.nodeColorSearchResult = ColorTheme.DarkTheme.nodeColorSearchResult;
            gtv.nodeAggHoverStroke = ColorTheme.DarkTheme.nodeAggHoverStroke;
            gtv.nodeAggDefaultStroke = ColorTheme.DarkTheme.nodeAggDefaultStroke;
            gtv.aggHoverStroke = ColorTheme.DarkTheme.aggHoverStroke;
            gtv.aggDefaultStroke = ColorTheme.DarkTheme.aggDefaultStroke;

            float[] hsb = new float[4];
            Color.RGBtoHSB(0, 4, 40, hsb);
            SolidColorB.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
        }
        else
        {
            gtv.background = GlobalVariables.GraphOptions.background;
            gtv.foreground = GlobalVariables.GraphOptions.foreground;
            gtv.edgeColor = GlobalVariables.GraphOptions.edgeColor;
            gtv.textColor = GlobalVariables.GraphOptions.textColor;
            gtv.nodeColor = GlobalVariables.GraphOptions.nodeColor;
            gtv.textColorHover = GlobalVariables.GraphOptions.textColorHover;
            gtv.nodeColorHover = GlobalVariables.GraphOptions.nodeColorHover;
            gtv.nodeColorSelected = GlobalVariables.GraphOptions.nodeColorSelected;
            gtv.nodeColorSearchResult = GlobalVariables.GraphOptions.nodeColorSearchResult;
            gtv.nodeAggHoverStroke = GlobalVariables.GraphOptions.nodeAggHoverStroke;
            gtv.nodeAggDefaultStroke = GlobalVariables.GraphOptions.nodeAggDefaultStroke;
            gtv.aggHoverStroke = GlobalVariables.GraphOptions.aggHoverStroke;
            gtv.aggDefaultStroke = GlobalVariables.GraphOptions.aggDefaultStroke;

        }

        int[] palette = null;
        if (GroupingColor.isSelected())
        {
            Color bC = BeforeCut.getBackground();
            Color aC = AfterCut.getBackground();
            palette = new int[]
            {
                ColorLib.rgb(aC.getRed(), aC.getGreen(), aC.getBlue()),
                ColorLib.rgb(bC.getRed(), bC.getGreen(), bC.getBlue())
            };
            gtv.palette = palette;
        }
        else if (GroupingAgg.isSelected())
        {
            if (SolidColorAgg.isSelected())
            {
                Color sC = SolidColorB.getBackground();
                palette = new int[]
                {
                    ColorLib.rgb(sC.getRed(), sC.getGreen(), sC.getBlue())
                };
            }
            gtv.palette = palette;
        }
    }

    private void SaveAsPNG(File file)
    {
        BufferedImage bufferedImage;
        bufferedImage = new BufferedImage(graphPanel.getSize().width, graphPanel.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.createGraphics();
        graphPanel.paint(graphics);
        graphics.dispose();
        try
        {
            ImageIO.write(bufferedImage, "png", file);
        }
        catch (IOException e2)
        {
        }
    }

    private void SaveAsPDF(File file)
    {
        Document document = new Document(new Rectangle(graphPanel.getSize().width, graphPanel.getSize().height));
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            PdfContentByte contentByte = writer.getDirectContent();
            PdfTemplate template = contentByte.createTemplate(graphPanel.getSize().width, graphPanel.getSize().height);
            Graphics graphics = new PdfGraphics2D(template, graphPanel.getSize().width, graphPanel.getSize().height);
            graphPanel.print(graphics);
            graphics.dispose();
            contentByte.addTemplate(template, 0, 0);
        }
        catch (DocumentException | FileNotFoundException e2)
        {
        }
        finally
        {
            if (document.isOpen())
                document.close();
        }
    }

    private Boolean CheckWritePermission(File file)
    {
        if (file.exists())
            return JOptionPane.showConfirmDialog(
                    this,
                    "The file already exists. Do you want to overwrite it ?     ",
                    "Existing file",
                    JOptionPane.YES_NO_OPTION)
                   == JOptionPane.YES_OPTION;
        return true;
    }
}
