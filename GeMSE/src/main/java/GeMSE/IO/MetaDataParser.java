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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vahid Jalili
 */
public class MetaDataParser
{
    public MetaDataParser(String source)
    {
        _source = source;
    }

    private final String _source;

    public HashMap<String, String[]> Parse()
    {
        HashMap<String, String[]> rtv = new HashMap<>();

        File f = new File(_source);
        if (f.exists() && !f.isDirectory())
        {
            BufferedReader fileReader;
            String line = "";

            try
            {
                fileReader = new BufferedReader(new FileReader(new File(_source)));
                while ((line = fileReader.readLine()) != null)
                {
                    String[] splittedLine = line.split("\\s*\t\\s*");

                    if (rtv.containsKey(splittedLine[0]))
                    {
                        String[] temp = rtv.get(splittedLine[0]);
                        rtv.put(splittedLine[0], Concatenate(temp, splittedLine[1]));
                    }
                    else
                    {
                        rtv.put(splittedLine[0], new String[]
                        {
                            splittedLine[1]
                        });
                    }
                }
            }
            catch (FileNotFoundException ex)
            {
                Logger.getLogger(MetaDataParser.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex)
            {
                Logger.getLogger(MetaDataParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return rtv;
    }

    private String[] Concatenate(String[] a, String b)
    {
        String[] C = new String[a.length + 1];
        System.arraycopy(a, 0, C, 0, a.length);
        C[C.length - 1] = b;
        return C;
    }
}
