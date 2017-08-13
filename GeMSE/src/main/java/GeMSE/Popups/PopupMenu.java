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
package GeMSE.Popups;


import ExternalLibraries.Cluster;
import ExternalLibraries.DendrogramPanel;
import GeMSE.GS.Operations;
import GeMSE.GS.Operations.Functions;
import GeMSE.IO.Exporter;
import GeMSE.GlobalVariables;
import GeMSE.GS.clustering.ClusterToNewick;
import GeMSE.GeMSE;
import GeMSE.OperationsOptions.SelectOptions;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

enum PopupMenuType
{
    GSDataGrid, DataGrid, Dendrogram, Heatmap, Tree
};

/**
 *
 * @author Vahid Jalili
 */
public class PopupMenu extends JPopupMenu
{
    private final JComponent _component;
    private Boolean _isExtractSelected;
    private GeMSE _parent;

    public PopupMenu(JComponent component, PopupMenuType type)
    {
        _component = component;

        switch (type)
        {
            case GSDataGrid:
                GSCreateDataGridMenuItems();
                break;

            case DataGrid:
                CreateDataGridMenuItems();
                break;

            case Dendrogram:
                CreateDendrogramMenuItems();
                break;

            case Heatmap:
                CreateHeatmapMenuItems();
                break;

            case Tree:
                CreateTreeMenuItems();
                break;
        }
    }
    private void GSCreateDataGridMenuItems()
    {
        _isExtractSelected = false;
        JMenuItem save = new JMenuItem("   Save as text file   ");
        save.addActionListener(this::SaveDataGrid);

        JMenuItem extract = new JMenuItem("   Extract selected cells   ");
        extract.addActionListener(this::ExtractCells);

        add(save);
        add(extract);
    }
    private void CreateDataGridMenuItems()
    {
        JMenuItem save = new JMenuItem("   Save as text file   ");
        save.addActionListener(this::SaveDataGrid);

        add(save);
    }
    private void CreateDendrogramMenuItems()
    {
        JMenuItem saveAsImage = new JMenuItem("   Image      ");
        saveAsImage.addActionListener(this::SaveImage);

        JMenuItem saveAsTree = new JMenuItem("   Newick tree      ");
        saveAsTree.addActionListener(this::SaveNewickTreeFormat);

        JMenu saveMenu = new JMenu("   Save as ...   ");
        saveMenu.add(saveAsImage);
        saveMenu.add(saveAsTree);
        add(saveMenu);

        JMenuItem labelOptions = new JMenuItem("   Columns and rows labels    ");
        labelOptions.addActionListener(this::CRLabelsActionListener);
        add(labelOptions);
    }
    private void CreateHeatmapMenuItems()
    {
        JMenuItem saveAsItem = new JMenuItem("   Save as ...   ");
        saveAsItem.addActionListener(this::SaveImage);
        add(saveAsItem);

        JMenuItem labelOptions = new JMenuItem("   Columns and rows labels    ");
        labelOptions.addActionListener(this::CRLabelsActionListener);
        add(labelOptions);

        JMenuItem lValueColorItem = new JMenuItem("   Low value color   ");
        lValueColorItem.addActionListener(this::LowValueColorActionListener);

        JMenuItem hValueColorItem = new JMenuItem("   High value color   ");
        hValueColorItem.addActionListener(this::HighValueColorActionListener);

        JMenu colorMenu = new JMenu("   Color scale   ");
        colorMenu.add(lValueColorItem);
        colorMenu.add(hValueColorItem);
        add(colorMenu);
    }

    private void CreateTreeMenuItems()
    {
        JMenuItem saveAsItem = new JMenuItem("   Save as ...   ");
        saveAsItem.addActionListener(this::SaveImage);
        add(saveAsItem);
    }

    private void SaveDataGrid(java.awt.event.ActionEvent e)
    {
        JFileChooser chooser = new JFileChooser(GlobalVariables.GetLastBrowsedDirectory());
        chooser.setDialogTitle("Choose Save Directory");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setApproveButtonText("Save");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text (Tab Delimited)", "TXT", "txt"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Excel Workbook", "XLSX", "xlsx"));

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            GlobalVariables.SetLastBrowsedDirectoryFromFile(chooser.getSelectedFile().getAbsolutePath());

            File file;
            if (chooser.getFileFilter().getDescription().equals("Text (Tab Delimited)"))
                file = new File((chooser.getSelectedFile()).getAbsolutePath().concat(".txt"));
            else
                file = new File((chooser.getSelectedFile()).getAbsolutePath().concat(".xlsx"));

            if (!CheckWritePermission(file))
                return;

            Exporter exporter = new Exporter(GetTableData((JTable) _component));
            exporter.Export(
                    chooser.getSelectedFile(),
                    chooser.getFileFilter().getDescription());
        }
    }
    public String[][] GetTableData(JTable table)
    {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();
        String[][] rtv = new String[rowCount][colCount];
        for (int row = 0 ; row < rowCount ; row++)
            for (int col = 0 ; col < colCount ; col++)
                rtv[row][col] = String.valueOf(model.getValueAt(row, col));
        return rtv;
    }

    private void ExtractCells(java.awt.event.ActionEvent e)
    {
        int[] cols = ((JTable) _component).getSelectedColumns();
        int[] rows = ((JTable) _component).getSelectedRows();
        int colMin = Integer.MAX_VALUE;
        int colMax = 0;
        int rowMin = Integer.MAX_VALUE;
        int rowMax = 0;
        for (int i = 0 ; i < cols.length ; i++)
        {
            if (cols[i] < 2) continue;
            if (cols[i] < colMin)
                colMin = cols[i];
            else if (cols[i] > colMax)
                colMax = cols[i];
        }
        if (colMin == Integer.MAX_VALUE)
        {
            JOptionPane.showMessageDialog(
                    this,
                    "Select at least one value column."
                    + "\nThe first two columns, as they contain only metadata, are not counted.     \n",
                    "Invalid Column",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        colMin = colMin - 2;
        if (colMax == 0)
            colMax = colMin + 1;
        else
            colMax = colMax - 1;

        for (int i = 0 ; i < rows.length ; i++)
        {
            if (rows[i] < rowMin)
                rowMin = rows[i];
            else if (rows[i] > rowMax)
                rowMax = rows[i];
        }
        if (rowMax == 0)
            rowMax = rowMin + 1;
        else
            rowMax = rowMax + 1;
        _isExtractSelected = true;

        GlobalVariables.space.RunOperation(
                GlobalVariables.selectedNodeID,
                "on gridview",
                Functions.Extract,
                new SelectOptions(colMin, colMax, rowMin, rowMax));
        _parent.CreateOperationsTree();
        GlobalVariables.sessionSerializationRequired = true;
    }

    private void SaveImage(java.awt.event.ActionEvent e)
    {
        JFileChooser chooser = new JFileChooser(GlobalVariables.GetLastBrowsedDirectory());
        chooser.setDialogTitle("Choose Save Directory");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setApproveButtonText("Save");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG (Portable Network Graphics)", "PNG", "png"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF (Portable Document Format)", "PDF", "pdf"));

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            GlobalVariables.SetLastBrowsedDirectoryFromFile((chooser.getSelectedFile()).getAbsolutePath());
            File file;
            if (chooser.getFileFilter().getDescription().equals("PNG (Portable Network Graphics)"))
            {
                file = new File((chooser.getSelectedFile()).getAbsolutePath().concat(".png"));
                if (CheckWritePermission(file))
                    SaveAsPNG(e, file);
            }
            else
            {
                file = new File((chooser.getSelectedFile()).getAbsolutePath().concat(".pdf"));
                if (CheckWritePermission(file))
                    SaveAsPDF(e, file);
            }
        }
    }
    private void SaveAsPDF(java.awt.event.ActionEvent e, File file)
    {
        Document document = new Document(new Rectangle(_component.getSize().width, _component.getSize().height));
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            PdfContentByte contentByte = writer.getDirectContent();
            PdfTemplate template = contentByte.createTemplate(_component.getSize().width, _component.getSize().height);
            Graphics graphics = new PdfGraphics2D(template, _component.getSize().width, _component.getSize().height);
            _component.print(graphics);
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
    private void SaveAsPNG(java.awt.event.ActionEvent e, File file)
    {
        BufferedImage bufferedImage;
        bufferedImage = new BufferedImage(_component.getSize().width, _component.getSize().height, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.createGraphics();
        _component.paint(graphics);
        graphics.dispose();
        try
        {
            ImageIO.write(bufferedImage, "png", file);
        }
        catch (IOException e2)
        {
        }
    }

    private void CRLabelsActionListener(java.awt.event.ActionEvent e)
    {
        ColumnsAndRowsLabelsOptions cNrOptions = new ColumnsAndRowsLabelsOptions();
        cNrOptions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cNrOptions.setLocationRelativeTo(this);
        cNrOptions.setVisible(true);
    }

    private void LowValueColorActionListener(java.awt.event.ActionEvent e)
    {
        Color previous_value = GlobalVariables.lowValueColor;
        GlobalVariables.lowValueColor = JColorChooser.showDialog(new JFrame("Color_Chooser"), "Low Value Color", GlobalVariables.lowValueColor);
        if (GlobalVariables.lowValueColor == null)
            GlobalVariables.lowValueColor = previous_value;
        GlobalVariables.sessionSerializationRequired = true;
    }
    private void HighValueColorActionListener(java.awt.event.ActionEvent e)
    {
        Color previous_value = GlobalVariables.hightValueColor;
        GlobalVariables.hightValueColor = JColorChooser.showDialog(new JFrame("Color_Chooser"), "High Value Color", GlobalVariables.hightValueColor);
        if (GlobalVariables.hightValueColor == null)
            GlobalVariables.hightValueColor = previous_value;
        GlobalVariables.sessionSerializationRequired = true;
    }

    private void SaveNewickTreeFormat(java.awt.event.ActionEvent e)
    {
        JFileChooser chooser = new JFileChooser(GlobalVariables.GetLastBrowsedDirectory());
        chooser.setDialogTitle("Choose Save Directory");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setApproveButtonText("Save");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text", "TXT", "txt"));

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            GlobalVariables.SetLastBrowsedDirectoryFromFile(chooser.getSelectedFile().getAbsolutePath());
            File file = new File((chooser.getSelectedFile()).getAbsolutePath().concat(".txt"));
            if (!CheckWritePermission(file))
                return;

            DendrogramPanel dPanel = (DendrogramPanel) _component;
            Cluster cluster = dPanel.getModel();

            try
            {
                file.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
                {
                    writer.write(ClusterToNewick.Convert(cluster));
                    writer.newLine();
                }
            }
            catch (IOException ex)
            {
                Logger.getLogger(PopupMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    public void SetParent(GeMSE parent)
    {
        _parent = parent;
    }
}
