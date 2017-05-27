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
package GeMSE.IO;

import java.io.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


/**
 *
 * @author Vahid Jalili
 */
public class Exporter
{
    private final String[][] _data;

    public Exporter(String[][] data)
    {
        _data = data;
    }

    public void Export(File file, String extension_Description)
    {
        switch (extension_Description)
        {
            case "Text (Tab Delimited)":
                ExportToTabDelimited(new File(file.getAbsolutePath().concat(".txt")));
                break;

            case "Excel Workbook":
                ExportToExcelWorkbook(new File(file.getAbsolutePath().concat(".xls")));
                break;
        }
    }

    private void ExportToTabDelimited(File file)
    {
        try
        {
            file.createNewFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
            {
                String line;
                for (String[] row : _data)
                {
                    line = "";
                    for (String item : row)
                        line += item.concat("\t");

                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ExportToExcelWorkbook(File file)
    {
        Workbook wb = new HSSFWorkbook();

        try
        {
            try (FileOutputStream fileOut = new FileOutputStream(file))
            {
                Sheet sheet = wb.createSheet("GeMSE_sheet");

                //Create 2D Cell Array
                Row[] row = new Row[_data.length];
                Cell[][] cell = new Cell[row.length][];

                for (int i = 0 ; i < row.length ; i++)
                {
                    row[i] = sheet.createRow(i);
                    cell[i] = new Cell[_data[i].length];

                    for (int j = 0 ; j < cell[i].length ; j++)
                    {
                        cell[i][j] = row[i].createCell(j);
                        cell[i][j].setCellValue(_data[i][j]);
                    }
                }

                wb.write(fileOut);
            }

        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
