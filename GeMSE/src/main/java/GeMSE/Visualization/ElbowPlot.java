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
package GeMSE.Visualization;

import GeMSE.GlobalVariables;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;


/**
 *
 * @author Vahid Jalili
 */
public class ElbowPlot extends JFrame
{
    private static final long serialVersionUID = GlobalVariables.serialVersionUID;

    public ElbowPlot(final String title)
    {
        super(title);
    }

    public void Plot(ArrayList<Double[]> pvData, ArrayList<Double[]> dData, int cut)
    {
        double maxY = 0;

        float[] secYColor = new float[3];
        Color.RGBtoHSB(153, 245, 255, secYColor);

        float[] priYColor = new float[3];
        Color.RGBtoHSB(255, 255, 255, priYColor);

        float[] cutColor = new float[3];
        Color.RGBtoHSB(255, 255, 0, cutColor);


        //create the series - add some dummy data
        XYSeries pvSeries = new XYSeries("Percentage of variance        ");
        for (int i = 1 ; i < pvData.size() ; i++)
        {
            pvSeries.add(pvData.get(i)[0], pvData.get(i)[1]);
            maxY = Math.max(maxY, pvData.get(i)[1]);
        }

        XYSeries dSeries = new XYSeries("Percentage of differences between slopes        ");
        for (int i = 0 ; i < dData.size() ; i++)
            dSeries.add(dData.get(i)[0], dData.get(i)[1]);

        XYSeries cutSeries = new XYSeries("Cut        ");
        cutSeries.add(cut, 0.0);
        cutSeries.add(cut, maxY);



        //create the datasets
        XYSeriesCollection pvDataSeries = new XYSeriesCollection();
        pvDataSeries.addSeries(pvSeries);

        XYSeriesCollection cutDataSeries = new XYSeriesCollection();
        cutDataSeries.addSeries(cutSeries);

        XYSeriesCollection dDataSeries = new XYSeriesCollection();
        dDataSeries.addSeries(dSeries);

        //construct the plot
        XYPlot plot = new XYPlot();
        plot.setDataset(0, pvDataSeries);
        plot.setDataset(1, cutDataSeries);
        plot.setDataset(2, dDataSeries);

        // use XYSplineRenderer if you want to smooth the lines.
        XYLineAndShapeRenderer pvRenderer = new XYLineAndShapeRenderer();
        pvRenderer.setSeriesPaint(0, Color.getHSBColor(priYColor[0], priYColor[1], priYColor[2]));

        BasicStroke dstroke
                    = new BasicStroke(
                        2.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND,
                        1.0f, new float[]
                        {
                            1.0f, 10.0f
                        }, 0.0f);
        XYLineAndShapeRenderer dRenderer = new XYLineAndShapeRenderer();
        dRenderer.setSeriesPaint(0, Color.getHSBColor(secYColor[0], secYColor[1], secYColor[2]));
        dRenderer.setSeriesStroke(0, dstroke);

        BasicStroke cutStoke = new BasicStroke(4);
        // use XYSplineRenderer if you want to smooth the lines.
        //XYSplineRenderer cutRenderer = new XYSplineRenderer();
        XYLineAndShapeRenderer cutRenderer = new XYLineAndShapeRenderer();
        cutRenderer.setSeriesPaint(0, Color.getHSBColor(cutColor[0], cutColor[1], cutColor[2]));
        cutRenderer.setSeriesStroke(0, cutStoke);


        plot.setRenderer(0, pvRenderer);
        plot.setRenderer(1, cutRenderer);
        plot.setRenderer(2, dRenderer);

        plot.setRangeAxis(0, new NumberAxis("\n\nPercentage of Variance"));
        plot.setRangeAxis(1, new NumberAxis("Percentage of Difference Between Slopes"));
        plot.setDomainAxis(new NumberAxis("Number of Clusters\n\n"));

        //Map the data to the appropriate axis
        plot.mapDatasetToRangeAxis(0, 0);
        plot.mapDatasetToRangeAxis(1, 0);
        plot.mapDatasetToRangeAxis(2, 1);

        float[] hsbValues = new float[3];
        Color.RGBtoHSB(16, 23, 67, hsbValues);
        plot.setBackgroundPaint(Color.getHSBColor(hsbValues[0], hsbValues[1], hsbValues[2]));

        Font axisLabelFont = new Font("Dialog", Font.PLAIN, 14);
        Font axisTickLabelFont = new Font("Dialog", Font.PLAIN, 12);
        Font legendFont = new Font("Dialog", Font.PLAIN, 14);

        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint(Color.gray);

        plot.getDomainAxis().setTickLabelPaint(Color.white);
        plot.getDomainAxis().setLabelPaint(Color.white);
        plot.getDomainAxis().setLabelFont(axisLabelFont);
        plot.getDomainAxis().setTickLabelFont(axisTickLabelFont);

        plot.getRangeAxis().setTickLabelPaint(Color.getHSBColor(priYColor[0], priYColor[1], priYColor[2]));
        plot.getRangeAxis().setLabelPaint(Color.getHSBColor(priYColor[0], priYColor[1], priYColor[2]));
        plot.getRangeAxis().setLabelFont(axisLabelFont);
        plot.getRangeAxis().setTickLabelFont(axisTickLabelFont);


        plot.getRangeAxis(1).setTickLabelPaint(Color.getHSBColor(secYColor[0], secYColor[1], secYColor[2]));
        plot.getRangeAxis(1).setLabelPaint(Color.getHSBColor(secYColor[0], secYColor[1], secYColor[2]));
        plot.getRangeAxis(1).setLabelFont(axisLabelFont);
        plot.getRangeAxis(1).setTickLabelFont(axisTickLabelFont);

        //generate the chart
        JFreeChart chart
                   = new JFreeChart(
                        "\nSuggested number of clusters determined using Elbow method",
                        getFont(),
                        plot,
                        true);

        chart.getTitle().setPaint(Color.white);
        chart.getLegend().setBackgroundPaint(Color.black);
        chart.getLegend().setItemPaint(Color.white);
        chart.getLegend().setPosition(RectangleEdge.BOTTOM);
        chart.getLegend().setItemFont(legendFont);

        float[] hsbValues2 = new float[3];
        Color.RGBtoHSB(36, 43, 87, hsbValues2);
        chart.setBackgroundPaint(Color.getHSBColor(hsbValues2[0], hsbValues2[1], hsbValues2[2]));
        JPanel chartPanel = new ChartPanel(chart);

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        chartPanel.setPreferredSize(new java.awt.Dimension(
                (int) Math.round((gd.getDisplayMode().getWidth() * 1.5) / 3.0),
                (int) Math.round((gd.getDisplayMode().getHeight() * 1.5) / 3.0)));

        setContentPane(chartPanel);
    }
}
