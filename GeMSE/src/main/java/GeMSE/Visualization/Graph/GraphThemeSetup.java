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

import GeMSE.GlobalVariables;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import prefuse.util.ColorLib;

/**
 *
 * @author Vahid Jalili
 */
public class GraphThemeSetup extends javax.swing.JDialog
{

    /**
     * Creates new form GraphThemeSetup
     * @param parent
     */
    public GraphThemeSetup(java.awt.Frame parent)
    {
        super(parent, true);
        initComponents();
        setResizable(false);

        _background = GlobalVariables.GraphOptions.background;
        _foreground = GlobalVariables.GraphOptions.foreground;
        _edgeColor = GlobalVariables.GraphOptions.edgeColor;
        _textColor = GlobalVariables.GraphOptions.textColor;
        _textColorHover = GlobalVariables.GraphOptions.textColorHover;
        _nodeColorSelected = GlobalVariables.GraphOptions.nodeColorSelected;
        _nodeColorSearchResult = GlobalVariables.GraphOptions.nodeColorSearchResult;
        _nodeColor = GlobalVariables.GraphOptions.nodeColor;
        _nodeColorHover = GlobalVariables.GraphOptions.nodeColorHover;
        _nodeAggDefaultStroke = GlobalVariables.GraphOptions.nodeAggDefaultStroke;
        _nodeAggHoverStroke = GlobalVariables.GraphOptions.nodeAggHoverStroke;
        _aggDefaultStroke = GlobalVariables.GraphOptions.aggDefaultStroke;
        _aggHoverStroke = GlobalVariables.GraphOptions.aggHoverStroke;

        ApplyColors();
    }

    private static Color _background;
    private static Color _foreground;
    private static int _edgeColor;
    private static int _textColor;
    private static int _nodeColor;
    private static int _textColorHover;
    private static int _nodeColorHover;
    private static int _nodeColorSelected;
    private static int _nodeColorSearchResult;
    private static int _nodeAggHoverStroke;
    private static int _nodeAggDefaultStroke;
    private static int _aggHoverStroke;
    private static int _aggDefaultStroke;

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
        BackgroundB = new javax.swing.JButton();
        BackgroundL = new javax.swing.JLabel();
        ForegroundB = new javax.swing.JButton();
        ForegroundL = new javax.swing.JLabel();
        EdgeB = new javax.swing.JButton();
        EdgeL = new javax.swing.JLabel();
        TextB = new javax.swing.JButton();
        TextL = new javax.swing.JLabel();
        TextHoverB = new javax.swing.JButton();
        TextHoverL = new javax.swing.JLabel();
        TextSelectedB = new javax.swing.JButton();
        TextSelectedL = new javax.swing.JLabel();
        TextSearchResultB = new javax.swing.JButton();
        TextSearchResultL = new javax.swing.JLabel();
        NodeB = new javax.swing.JButton();
        NodeL = new javax.swing.JLabel();
        NodeAggregateStrokeB = new javax.swing.JButton();
        NodeAggregateStrokeL = new javax.swing.JLabel();
        NodeAggregateHoverStrokeB = new javax.swing.JButton();
        NodeAggregateHoverStrokeL = new javax.swing.JLabel();
        AggregateStrokeB = new javax.swing.JButton();
        AggregateStrokeL = new javax.swing.JLabel();
        AggregateHoverStrokeB = new javax.swing.JButton();
        AggregateHoverStrokeL = new javax.swing.JLabel();
        NodeHoverL = new javax.swing.JLabel();
        NodeHoverB = new javax.swing.JButton();
        OKB = new javax.swing.JButton();
        CancelB = new javax.swing.JButton();
        CopyFromDarkThemeB = new javax.swing.JButton();
        CopyFromBrightThemeB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        BackgroundB.setText("...");
        BackgroundB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BackgroundBActionPerformed(evt);
            }
        });

        BackgroundL.setText("Background");

        ForegroundB.setText("...");
        ForegroundB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ForegroundBActionPerformed(evt);
            }
        });

        ForegroundL.setText("Foreground");

        EdgeB.setText("...");
        EdgeB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                EdgeBActionPerformed(evt);
            }
        });

        EdgeL.setText("Edge");

        TextB.setText("...");
        TextB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                TextBActionPerformed(evt);
            }
        });

        TextL.setText("Text");

        TextHoverB.setText("...");
        TextHoverB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                TextHoverBActionPerformed(evt);
            }
        });

        TextHoverL.setText("Text (Hover)");

        TextSelectedB.setText("...");
        TextSelectedB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                TextSelectedBActionPerformed(evt);
            }
        });

        TextSelectedL.setText("Text (Selected)");

        TextSearchResultB.setText("...");
        TextSearchResultB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                TextSearchResultBActionPerformed(evt);
            }
        });

        TextSearchResultL.setText("Text (Search Result)");

        NodeB.setText("...");
        NodeB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                NodeBActionPerformed(evt);
            }
        });

        NodeL.setText("Node");

        NodeAggregateStrokeB.setText("...");
        NodeAggregateStrokeB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                NodeAggregateStrokeBActionPerformed(evt);
            }
        });

        NodeAggregateStrokeL.setText("Node Aggregate Stroke");

        NodeAggregateHoverStrokeB.setText("...");
        NodeAggregateHoverStrokeB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                NodeAggregateHoverStrokeBActionPerformed(evt);
            }
        });

        NodeAggregateHoverStrokeL.setText("Node Aggregate Stroke (Hover)");

        AggregateStrokeB.setText("...");
        AggregateStrokeB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                AggregateStrokeBActionPerformed(evt);
            }
        });

        AggregateStrokeL.setText("Aggregate Stroke");

        AggregateHoverStrokeB.setText("...");
        AggregateHoverStrokeB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                AggregateHoverStrokeBActionPerformed(evt);
            }
        });

        AggregateHoverStrokeL.setText("Aggregate Stroke (Hover)");

        NodeHoverL.setText("Node (Hover)");

        NodeHoverB.setText("...");
        NodeHoverB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                NodeHoverBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(BackgroundL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BackgroundB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(ForegroundL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ForegroundB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(EdgeL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EdgeB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(TextL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(TextHoverL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextHoverB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(TextSelectedL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextSelectedB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(TextSearchResultL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextSearchResultB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(NodeL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NodeB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(NodeAggregateStrokeL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NodeAggregateStrokeB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(NodeAggregateHoverStrokeL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NodeAggregateHoverStrokeB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(AggregateStrokeL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AggregateStrokeB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(AggregateHoverStrokeL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AggregateHoverStrokeB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(NodeHoverL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NodeHoverB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BackgroundB)
                    .addComponent(BackgroundL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ForegroundB)
                    .addComponent(ForegroundL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EdgeB)
                    .addComponent(EdgeL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextB)
                    .addComponent(TextL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextHoverB)
                    .addComponent(TextHoverL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextSelectedB)
                    .addComponent(TextSelectedL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextSearchResultB)
                    .addComponent(TextSearchResultL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NodeB)
                    .addComponent(NodeL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NodeHoverB)
                    .addComponent(NodeHoverL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NodeAggregateStrokeB)
                    .addComponent(NodeAggregateStrokeL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NodeAggregateHoverStrokeB)
                    .addComponent(NodeAggregateHoverStrokeL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AggregateStrokeB)
                    .addComponent(AggregateStrokeL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AggregateHoverStrokeB)
                    .addComponent(AggregateHoverStrokeL))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        OKB.setText("OK");
        OKB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                OKBActionPerformed(evt);
            }
        });

        CancelB.setText("Cancel");
        CancelB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                CancelBActionPerformed(evt);
            }
        });

        CopyFromDarkThemeB.setText("Copy from dark theme");
        CopyFromDarkThemeB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                CopyFromDarkThemeBActionPerformed(evt);
            }
        });

        CopyFromBrightThemeB.setText("Copy from bright theme");
        CopyFromBrightThemeB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                CopyFromBrightThemeBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CancelB, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(OKB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(CopyFromDarkThemeB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CopyFromBrightThemeB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CopyFromBrightThemeB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CopyFromDarkThemeB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OKB)
                    .addComponent(CancelB))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackgroundBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_BackgroundBActionPerformed
    {//GEN-HEADEREND:event_BackgroundBActionPerformed
        _background = JColorChooser.showDialog(
                new JFrame("Color_Chooser"), "Background", _background);
    }//GEN-LAST:event_BackgroundBActionPerformed

    private void ForegroundBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ForegroundBActionPerformed
    {//GEN-HEADEREND:event_ForegroundBActionPerformed
        _foreground = JColorChooser.showDialog(
                new JFrame("Color_Chooser"), "Foreground", _foreground);
    }//GEN-LAST:event_ForegroundBActionPerformed

    private void EdgeBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_EdgeBActionPerformed
    {//GEN-HEADEREND:event_EdgeBActionPerformed
        Color tmp = JColorChooser.showDialog(
                new JFrame("Color_Chooser"), "Edge color", ColorLib.getColor(_edgeColor));
        _edgeColor = ColorLib.rgb(tmp.getRed(), tmp.getGreen(), tmp.getBlue());
        ApplyColors();
    }//GEN-LAST:event_EdgeBActionPerformed

    private void TextBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_TextBActionPerformed
    {//GEN-HEADEREND:event_TextBActionPerformed
        Color tmp = JColorChooser.showDialog(
                new JFrame("Color_Chooser"), "Text color", ColorLib.getColor(_textColor));
        _textColor = ColorLib.rgb(tmp.getRed(), tmp.getGreen(), tmp.getBlue());
        ApplyColors();
    }//GEN-LAST:event_TextBActionPerformed

    private void TextHoverBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_TextHoverBActionPerformed
    {//GEN-HEADEREND:event_TextHoverBActionPerformed
        Color tmp = JColorChooser.showDialog(
                new JFrame("Color_Chooser"), "Text color (hover)", ColorLib.getColor(_textColorHover));
        _textColorHover = ColorLib.rgb(tmp.getRed(), tmp.getGreen(), tmp.getBlue());
        ApplyColors();
    }//GEN-LAST:event_TextHoverBActionPerformed

    private void TextSelectedBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_TextSelectedBActionPerformed
    {//GEN-HEADEREND:event_TextSelectedBActionPerformed
        Color tmp = JColorChooser.showDialog(
                new JFrame("Color_Chooser"), "Text color (selected)", ColorLib.getColor(_nodeColorSelected));
        _nodeColorSelected = ColorLib.rgb(tmp.getRed(), tmp.getGreen(), tmp.getBlue());
        ApplyColors();
    }//GEN-LAST:event_TextSelectedBActionPerformed

    private void TextSearchResultBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_TextSearchResultBActionPerformed
    {//GEN-HEADEREND:event_TextSearchResultBActionPerformed
        Color tmp = JColorChooser.showDialog(
                new JFrame("Color_Chooser"), "Text color (search result)", ColorLib.getColor(_nodeColorSearchResult));
        _nodeColorSearchResult = ColorLib.rgb(tmp.getRed(), tmp.getGreen(), tmp.getBlue());
        ApplyColors();
    }//GEN-LAST:event_TextSearchResultBActionPerformed

    private void NodeBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_NodeBActionPerformed
    {//GEN-HEADEREND:event_NodeBActionPerformed
        Color tmp = JColorChooser.showDialog(
                new JFrame("Color_Chooser"), "Node color", ColorLib.getColor(_nodeColor));
        _nodeColor = ColorLib.rgb(tmp.getRed(), tmp.getGreen(), tmp.getBlue());
        ApplyColors();
    }//GEN-LAST:event_NodeBActionPerformed

    private void NodeAggregateStrokeBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_NodeAggregateStrokeBActionPerformed
    {//GEN-HEADEREND:event_NodeAggregateStrokeBActionPerformed
        Color tmp = JColorChooser.showDialog(
                new JFrame("Color_Chooser"), "Node aggregate stroke", ColorLib.getColor(_nodeAggDefaultStroke));
        _nodeAggDefaultStroke = ColorLib.rgb(tmp.getRed(), tmp.getGreen(), tmp.getBlue());
        ApplyColors();
    }//GEN-LAST:event_NodeAggregateStrokeBActionPerformed

    private void NodeAggregateHoverStrokeBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_NodeAggregateHoverStrokeBActionPerformed
    {//GEN-HEADEREND:event_NodeAggregateHoverStrokeBActionPerformed
        Color tmp = JColorChooser.showDialog(
                new JFrame("Color_Chooser"), "Node aggregate stroke (hover)", ColorLib.getColor(_nodeAggHoverStroke));
        _nodeAggHoverStroke = ColorLib.rgb(tmp.getRed(), tmp.getGreen(), tmp.getBlue());
        ApplyColors();
    }//GEN-LAST:event_NodeAggregateHoverStrokeBActionPerformed

    private void AggregateStrokeBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AggregateStrokeBActionPerformed
    {//GEN-HEADEREND:event_AggregateStrokeBActionPerformed
        Color tmp = JColorChooser.showDialog(
                new JFrame("Color_Chooser"), "Aggregate stroke", ColorLib.getColor(_aggDefaultStroke));
        _aggDefaultStroke = ColorLib.rgb(tmp.getRed(), tmp.getGreen(), tmp.getBlue());
        ApplyColors();
    }//GEN-LAST:event_AggregateStrokeBActionPerformed

    private void AggregateHoverStrokeBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AggregateHoverStrokeBActionPerformed
    {//GEN-HEADEREND:event_AggregateHoverStrokeBActionPerformed
        Color tmp = JColorChooser.showDialog(
                new JFrame("Color_Chooser"), "Aggregate stroke (hover)", ColorLib.getColor(_aggHoverStroke));
        _aggHoverStroke = ColorLib.rgb(tmp.getRed(), tmp.getGreen(), tmp.getBlue());
        ApplyColors();
    }//GEN-LAST:event_AggregateHoverStrokeBActionPerformed

    private void NodeHoverBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_NodeHoverBActionPerformed
    {//GEN-HEADEREND:event_NodeHoverBActionPerformed
        Color tmp = JColorChooser.showDialog(
                new JFrame("Color_Chooser"), "Node color (color)", ColorLib.getColor(_nodeColorHover));
        _nodeColorHover = ColorLib.rgb(tmp.getRed(), tmp.getGreen(), tmp.getBlue());
        ApplyColors();
    }//GEN-LAST:event_NodeHoverBActionPerformed

    private void CopyFromDarkThemeBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_CopyFromDarkThemeBActionPerformed
    {//GEN-HEADEREND:event_CopyFromDarkThemeBActionPerformed
        _background = ColorTheme.DarkTheme.background;
        _foreground = ColorTheme.DarkTheme.foreground;
        _edgeColor = ColorTheme.DarkTheme.edgeColor;
        _textColor = ColorTheme.DarkTheme.textColor;
        _textColorHover = ColorTheme.DarkTheme.textColorHover;
        _nodeColorSelected = ColorTheme.DarkTheme.nodeColorSelected;
        _nodeColorSearchResult = ColorTheme.DarkTheme.nodeColorSearchResult;
        _nodeColor = ColorTheme.DarkTheme.nodeColor;
        _nodeColorHover = ColorTheme.DarkTheme.nodeColorHover;
        _nodeAggDefaultStroke = ColorTheme.DarkTheme.nodeAggDefaultStroke;
        _nodeAggHoverStroke = ColorTheme.DarkTheme.nodeAggHoverStroke;
        _aggDefaultStroke = ColorTheme.DarkTheme.aggDefaultStroke;
        _aggHoverStroke = ColorTheme.DarkTheme.aggHoverStroke;
        ApplyColors();
    }//GEN-LAST:event_CopyFromDarkThemeBActionPerformed

    private void CopyFromBrightThemeBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_CopyFromBrightThemeBActionPerformed
    {//GEN-HEADEREND:event_CopyFromBrightThemeBActionPerformed
        _background = ColorTheme.BrightTheme.background;
        _foreground = ColorTheme.BrightTheme.foreground;
        _edgeColor = ColorTheme.BrightTheme.edgeColor;
        _textColor = ColorTheme.BrightTheme.textColor;
        _textColorHover = ColorTheme.BrightTheme.textColorHover;
        _nodeColorSelected = ColorTheme.BrightTheme.nodeColorSelected;
        _nodeColorSearchResult = ColorTheme.BrightTheme.nodeColorSearchResult;
        _nodeColor = ColorTheme.BrightTheme.nodeColor;
        _nodeColorHover = ColorTheme.BrightTheme.nodeColorHover;
        _nodeAggDefaultStroke = ColorTheme.BrightTheme.nodeAggDefaultStroke;
        _nodeAggHoverStroke = ColorTheme.BrightTheme.nodeAggHoverStroke;
        _aggDefaultStroke = ColorTheme.BrightTheme.aggDefaultStroke;
        _aggHoverStroke = ColorTheme.BrightTheme.aggHoverStroke;
        ApplyColors();
    }//GEN-LAST:event_CopyFromBrightThemeBActionPerformed

    private void OKBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_OKBActionPerformed
    {//GEN-HEADEREND:event_OKBActionPerformed
        GlobalVariables.GraphOptions.background = _background;
        GlobalVariables.GraphOptions.foreground = _foreground;
        GlobalVariables.GraphOptions.edgeColor = _edgeColor;
        GlobalVariables.GraphOptions.textColor = _textColor;
        GlobalVariables.GraphOptions.textColorHover = _textColorHover;
        GlobalVariables.GraphOptions.nodeColorSelected = _nodeColorSelected;
        GlobalVariables.GraphOptions.nodeColorSearchResult = _nodeColorSearchResult;
        GlobalVariables.GraphOptions.nodeColor = _nodeColor;
        GlobalVariables.GraphOptions.nodeColorHover = _nodeColorHover;
        GlobalVariables.GraphOptions.nodeAggDefaultStroke = _nodeAggDefaultStroke;
        GlobalVariables.GraphOptions.nodeAggHoverStroke = _nodeAggHoverStroke;
        GlobalVariables.GraphOptions.aggDefaultStroke = _aggDefaultStroke;
        GlobalVariables.GraphOptions.aggHoverStroke = _aggHoverStroke;
        GlobalVariables.sessionSerializationRequired = true;
        dispose();
    }//GEN-LAST:event_OKBActionPerformed

    private void CancelBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_CancelBActionPerformed
    {//GEN-HEADEREND:event_CancelBActionPerformed
        dispose();
    }//GEN-LAST:event_CancelBActionPerformed

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
            java.util.logging.Logger.getLogger(GraphThemeSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(GraphThemeSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(GraphThemeSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(GraphThemeSetup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                GraphThemeSetup dialog = new GraphThemeSetup(new javax.swing.JFrame());
                dialog.addWindowListener(new java.awt.event.WindowAdapter()
                {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e)
                    {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AggregateHoverStrokeB;
    private javax.swing.JLabel AggregateHoverStrokeL;
    private javax.swing.JButton AggregateStrokeB;
    private javax.swing.JLabel AggregateStrokeL;
    private javax.swing.JButton BackgroundB;
    private javax.swing.JLabel BackgroundL;
    private javax.swing.JButton CancelB;
    private javax.swing.JButton CopyFromBrightThemeB;
    private javax.swing.JButton CopyFromDarkThemeB;
    private javax.swing.JButton EdgeB;
    private javax.swing.JLabel EdgeL;
    private javax.swing.JButton ForegroundB;
    private javax.swing.JLabel ForegroundL;
    private javax.swing.JButton NodeAggregateHoverStrokeB;
    private javax.swing.JLabel NodeAggregateHoverStrokeL;
    private javax.swing.JButton NodeAggregateStrokeB;
    private javax.swing.JLabel NodeAggregateStrokeL;
    private javax.swing.JButton NodeB;
    private javax.swing.JButton NodeHoverB;
    private javax.swing.JLabel NodeHoverL;
    private javax.swing.JLabel NodeL;
    private javax.swing.JButton OKB;
    private javax.swing.JButton TextB;
    private javax.swing.JButton TextHoverB;
    private javax.swing.JLabel TextHoverL;
    private javax.swing.JLabel TextL;
    private javax.swing.JButton TextSearchResultB;
    private javax.swing.JLabel TextSearchResultL;
    private javax.swing.JButton TextSelectedB;
    private javax.swing.JLabel TextSelectedL;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private void ApplyColors()
    {
        BackgroundB.setBackground(_background);
        ForegroundB.setBackground(_foreground);
        EdgeB.setBackground(ColorLib.getColor(_edgeColor));
        TextB.setBackground(ColorLib.getColor(_textColor));
        TextHoverB.setBackground(ColorLib.getColor(_textColorHover));
        TextSelectedB.setBackground(ColorLib.getColor(_nodeColorSelected));
        TextSearchResultB.setBackground(ColorLib.getColor(_nodeColorSearchResult));
        NodeB.setBackground(ColorLib.getColor(_nodeColor));
        NodeHoverB.setBackground(ColorLib.getColor(_nodeColorHover));
        NodeAggregateStrokeB.setBackground(ColorLib.getColor(_nodeAggDefaultStroke));
        NodeAggregateHoverStrokeB.setBackground(ColorLib.getColor(_nodeAggHoverStroke));
        AggregateStrokeB.setBackground(ColorLib.getColor(_aggDefaultStroke));
        AggregateHoverStrokeB.setBackground(ColorLib.getColor(_aggHoverStroke));
    }
}
