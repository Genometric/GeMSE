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

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.inference.TTest;



/**
 *
 * @author Vahid Jalili
 */
public final class TwoSampleTTestPanel extends javax.swing.JPanel
{
    public TwoSampleTTestPanel()
    {
        initComponents();
        _sample1 = new double[0];
        _sample2 = new double[0];

        _decFor = new DecimalFormat("#.#########");
        _decFor.setRoundingMode(RoundingMode.CEILING);
        DecimalFormatSymbols decFors = _decFor.getDecimalFormatSymbols();
        decFors.setNaN("NaN");
        decFors.setInfinity("∞");
        _decFor.setDecimalFormatSymbols(decFors);

        _variance = new Variance();
        _studentTest = new TTest();
    }

    private double[] _sample1;
    private double[] _sample2;
    private DecimalFormat _decFor;
    private final Variance _variance;
    private TTest _studentTest;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel6 = new javax.swing.JPanel();
        DFL5 = new javax.swing.JLabel();
        TStatisticL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel7 = new javax.swing.JPanel();
        DFL6 = new javax.swing.JLabel();
        PairedTStatisticL = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jPanel8 = new javax.swing.JPanel();
        DFL7 = new javax.swing.JLabel();
        TStatisticL1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jPanel11 = new javax.swing.JPanel();
        DFL10 = new javax.swing.JLabel();
        DFLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane5 = new javax.swing.JTextPane();
        jPanel12 = new javax.swing.JPanel();
        DFL11 = new javax.swing.JLabel();
        HomoscedasticityL = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPane6 = new javax.swing.JTextPane();
        jPanel13 = new javax.swing.JPanel();
        DFL12 = new javax.swing.JLabel();
        PValue2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        jPanel14 = new javax.swing.JPanel();
        DFL13 = new javax.swing.JLabel();
        TStatisticL3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        AlphaTF = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextPane7 = new javax.swing.JTextPane();
        jPanel15 = new javax.swing.JPanel();
        DFL14 = new javax.swing.JLabel();
        TStatisticL4 = new javax.swing.JLabel();
        AlphaTF1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextPane8 = new javax.swing.JTextPane();

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setPreferredSize(new java.awt.Dimension(450, 80));

        DFL5.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        DFL5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DFL5.setText("t-Statistic");

        TStatisticL.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        TStatisticL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TStatisticL.setText("NaN");

        jTextPane1.setEditable(false);
        jTextPane1.setText("without the hypothesis of equal subpopulation variances");
        jTextPane1.setDragEnabled(false);
        jTextPane1.setFocusable(false);
        jTextPane1.setOpaque(false);
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DFL5, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(TStatisticL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(DFL5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TStatisticL, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setPreferredSize(new java.awt.Dimension(450, 80));
        jPanel7.setSize(new java.awt.Dimension(450, 80));

        DFL6.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        DFL6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DFL6.setText("Paired t-Statistic");

        PairedTStatisticL.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        PairedTStatisticL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PairedTStatisticL.setText("NaN");

        jScrollPane2.setViewportView(jTextPane2);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DFL6, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(PairedTStatisticL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(DFL6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PairedTStatisticL, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel8.setPreferredSize(new java.awt.Dimension(450, 80));
        jPanel8.setSize(new java.awt.Dimension(450, 80));

        DFL7.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        DFL7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DFL7.setText("p-value");

        TStatisticL1.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        TStatisticL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TStatisticL1.setText("NaN");

        jTextPane3.setText("associated with a paired, two-sample, two-tailed t-test");
        jScrollPane3.setViewportView(jTextPane3);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DFL7, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(TStatisticL1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(DFL7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TStatisticL1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setPreferredSize(new java.awt.Dimension(450, 80));
        jPanel11.setSize(new java.awt.Dimension(450, 80));

        DFL10.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        DFL10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DFL10.setText("degrees of freedom");

        DFLabel.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        DFLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DFLabel.setText("NaN");

        jTextPane5.setText("approximate degrees of freedom");
        jScrollPane5.setViewportView(jTextPane5);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DFL10, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(DFLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(DFL10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DFLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.setPreferredSize(new java.awt.Dimension(450, 80));
        jPanel12.setSize(new java.awt.Dimension(450, 80));

        DFL11.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        DFL11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DFL11.setText("Homoscedasticity");

        HomoscedasticityL.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        HomoscedasticityL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HomoscedasticityL.setText("NaN");

        jTextPane6.setText("under the hypothesis of equal subpopulation variances");
        jScrollPane6.setViewportView(jTextPane6);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DFL11, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(HomoscedasticityL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(DFL11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HomoscedasticityL, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel13.setPreferredSize(new java.awt.Dimension(450, 80));
        jPanel13.setSize(new java.awt.Dimension(450, 80));

        DFL12.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        DFL12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DFL12.setText("p-value");

        PValue2.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        PValue2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PValue2.setText("NaN");

        jTextPane4.setText("comparing the means of input; assumption: samples with equal variance");
        jScrollPane4.setViewportView(jTextPane4);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DFL12, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(PValue2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(DFL12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PValue2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel14.setPreferredSize(new java.awt.Dimension(450, 80));
        jPanel14.setSize(new java.awt.Dimension(450, 80));

        DFL13.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        DFL13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DFL13.setText("Homoscedasticity");

        TStatisticL3.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        TStatisticL3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TStatisticL3.setText("NaN");

        jLabel5.setFont(new java.awt.Font("Courier New", 0, 30)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("α");

        AlphaTF.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        AlphaTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AlphaTF.setText("0.05");
        AlphaTF.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                AlphaTFActionPerformed(evt);
            }
        });

        jTextPane7.setText("equal subpopulation variances");
        jScrollPane7.setViewportView(jTextPane7);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DFL13, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AlphaTF, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TStatisticL3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(DFL13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TStatisticL3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AlphaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jScrollPane7))
                .addContainerGap())
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel15.setPreferredSize(new java.awt.Dimension(450, 80));
        jPanel15.setSize(new java.awt.Dimension(450, 80));

        DFL14.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        DFL14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DFL14.setText("t-Test");

        TStatisticL4.setFont(new java.awt.Font("Courier New", 0, 24)); // NOI18N
        TStatisticL4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TStatisticL4.setText("NaN");

        AlphaTF1.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        AlphaTF1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AlphaTF1.setText("0.05");
        AlphaTF1.setSize(new java.awt.Dimension(80, 26));
        AlphaTF1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                AlphaTF1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Courier New", 0, 30)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("α");

        jTextPane8.setText("null hypothesis that the means are  equal can be rejected with α confidence");
        jScrollPane8.setViewportView(jTextPane8);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DFL14, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AlphaTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TStatisticL4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(DFL14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(AlphaTF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TStatisticL4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AlphaTFActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AlphaTFActionPerformed
    {//GEN-HEADEREND:event_AlphaTFActionPerformed
        RunHomoscedasticTTest();
    }//GEN-LAST:event_AlphaTFActionPerformed

    private void AlphaTF1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_AlphaTF1ActionPerformed
    {//GEN-HEADEREND:event_AlphaTF1ActionPerformed
        RunTTest();
    }//GEN-LAST:event_AlphaTF1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AlphaTF;
    private javax.swing.JTextField AlphaTF1;
    private javax.swing.JLabel DFL10;
    private javax.swing.JLabel DFL11;
    private javax.swing.JLabel DFL12;
    private javax.swing.JLabel DFL13;
    private javax.swing.JLabel DFL14;
    private javax.swing.JLabel DFL5;
    private javax.swing.JLabel DFL6;
    private javax.swing.JLabel DFL7;
    private javax.swing.JLabel DFLabel;
    private javax.swing.JLabel HomoscedasticityL;
    private javax.swing.JLabel PValue2;
    private javax.swing.JLabel PairedTStatisticL;
    private javax.swing.JLabel TStatisticL;
    private javax.swing.JLabel TStatisticL1;
    private javax.swing.JLabel TStatisticL3;
    private javax.swing.JLabel TStatisticL4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextPane jTextPane4;
    private javax.swing.JTextPane jTextPane5;
    private javax.swing.JTextPane jTextPane6;
    private javax.swing.JTextPane jTextPane7;
    private javax.swing.JTextPane jTextPane8;
    // End of variables declaration//GEN-END:variables

    public void RunAnalysis(double[] sample1, double[] sample2)
    {
        if (sample1 == null || sample2 == null) return;
        if (sample1.length < 3 || sample2.length < 3) return;

        _sample1 = sample1;
        _sample2 = sample2;

        TStatisticL.setText(String.valueOf(_decFor.format(_studentTest.t(sample1, sample2))));
        if (sample1.length == sample2.length)
        {
            PairedTStatisticL.setText(String.valueOf(_decFor.format(_studentTest.pairedT(sample1, sample2))));
            TStatisticL1.setText(String.valueOf(_decFor.format(_studentTest.pairedTTest(sample1, sample2))));
        }
        else
        {
            PairedTStatisticL.setText("NaN");
            TStatisticL1.setText("NaN");
        }
        DFLabel.setText(String.valueOf(_decFor.format(df(sample1, sample2))));
        HomoscedasticityL.setText(String.valueOf(_decFor.format(_studentTest.homoscedasticT(sample1, sample2))));
        PValue2.setText(String.valueOf(_decFor.format(_studentTest.homoscedasticTTest(sample1, sample2))));

        RunHomoscedasticTTest();
        RunTTest();
    }

    private void RunHomoscedasticTTest()
    {
        double alpha;
        try
        {
            alpha = Double.parseDouble(AlphaTF.getText());
        }
        catch (Exception e)
        {
            alpha = 0.05;
        }
        if (alpha <= 0 || alpha >= 0.5)
            alpha = 0.05;
        AlphaTF.setText(String.valueOf(alpha));
        TStatisticL3.setText(String.valueOf(_studentTest.homoscedasticTTest(_sample1, _sample2, alpha)));
    }

    private void RunTTest()
    {
        double alpha;
        try
        {
            alpha = Double.parseDouble(AlphaTF1.getText());
        }
        catch (Exception e)
        {
            alpha = 0.05;
        }
        if (alpha <= 0 || alpha >= 0.5)
            alpha = 0.05;
        AlphaTF1.setText(String.valueOf(alpha));
        TStatisticL4.setText(String.valueOf(_studentTest.tTest(_sample1, _sample2, alpha)));
    }

    protected double df(double[] sample1, double[] sample2)
    {
        double s1V = _variance.evaluate(sample1);
        double s2V = _variance.evaluate(sample2);
        double s1S = sample1.length;
        double s2S = sample2.length;

        return (((s1V / s1S) + (s2V / s2S)) * ((s1V / s1S) + (s2V / s2S)))
               / ((s1V * s1V) / (s1S * s1S * (s1S - 1d)) + (s2V * s2V)
                                                           / (s2S * s2S * (s2S - 1d)));
    }
}
