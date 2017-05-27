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

import GeMSE.GS.SampleData;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vahid Jalili
 */
public class CSVParser
{
    protected CSVParser(
            String source,
            CSVOptions options)
    {
        _source = source;
        _options = options;

        File file = new File(_source);
        _data.fullPath = file.getAbsolutePath();
        _data.fileName = file.getName();
    }


    private final String _source;
    private final byte _chrColumn = 0;
    private final byte _startColumn = 1;
    private final byte _stopColumn = 2;
    private boolean _dropLine = false;
    private final CSVOptions _options;
    private final SampleData _data = new SampleData();



    protected SampleData Parse()
    {
        _dropLine = false;
        String chr = "";
        byte chrCount = 0;
        String line;
        BufferedReader fileReader;
        int lineCounter = 0;
        ArrayList<String> lineDropMessage = new ArrayList<>();

        try
        {
            fileReader = new BufferedReader(new FileReader(new File(_source)));

            // skip header lines
            for (int i = 0 ; i < _options.headerCounter ; i++)
            {
                fileReader.readLine();
                lineCounter++;
            }

            while ((line = fileReader.readLine()) != null)
            {
                lineCounter++;

                if (line.trim().length() > 0)
                {
                    _dropLine = false;
                    SampleData.Feature readingFeature = _data.new Feature();
                    String[] splittedLine = line.split("\\s*\t\\s*");

                    if (_chrColumn < splittedLine.length)
                        chr = splittedLine[_chrColumn];
                    else
                    {
                        _dropLine = true;
                        lineDropMessage.add("\tLine " + Integer.toString(lineCounter) + "\t:\tInvalid chromosome column number ( " + splittedLine[_chrColumn] + " )");
                    }

                    if (_startColumn < splittedLine.length && _stopColumn < splittedLine.length)
                        try
                        {
                            readingFeature.start = Integer.parseInt(splittedLine[_startColumn]);
                            readingFeature.stop = Integer.parseInt(splittedLine[_stopColumn]);
                        }
                        catch (NumberFormatException id)
                        {
                            _dropLine = true;
                            lineDropMessage.add("\tLine " + Integer.toString(lineCounter) + "\t:\tInvalid Start\\Stop value.");
                        }
                    else
                    {
                        _dropLine = true;
                        lineDropMessage.add("\tLine " + Integer.toString(lineCounter) + "\t:\tInvalid Start\\Stop value.");
                    }

                    for (GTDColumns col : _options.columns)
                        if (col.GetRead())
                        {
                            if (col.GetColumnIndex() < splittedLine.length)
                            {
                                try
                                {
                                    readingFeature.AddAttribute(
                                            col.GetColumnLabel(),
                                            Double.parseDouble(splittedLine[col.GetColumnIndex()]));
                                }
                                catch (NumberFormatException id)
                                {
                                    _dropLine = true;
                                    lineDropMessage.add("\tLine " + Integer.toString(lineCounter) + "\t:\tInvalid " + col.GetColumnLabel());
                                }
                            }
                            else
                            {
                                _dropLine = true;
                                lineDropMessage.add("\tLine " + Integer.toString(lineCounter) + "\t:\tInvalid " + col.GetColumnLabel());
                            }
                        }

                    if (!_dropLine)
                    {
                        _data.featuresCount++;
                        if (_data.features.containsKey(chr) == false)
                        {
                            _data.features.put(chr, new ArrayList<>());
                            chrCount++;
                        }
                        _data.features.get(chr).add(readingFeature);
                    }
                }
            }

            _data.messages = lineDropMessage;

            _data.determinedFeatures.add(new String[]
            {
                "CSV", Integer.toString(_data.featuresCount)
            });

            fileReader.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(GTFParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        _data.chrCount = chrCount;
        _data.sampleID = GetSampleID(_data.featuresCount, _data.fullPath.length());
        _data.lineCounter = lineCounter;

        return _data;
    }

    private String GetSampleID(int determinedFeaturesCount, int filePathLength)
    {
        Random rnd = new Random();
        String rtv = String.valueOf(determinedFeaturesCount) + String.valueOf(filePathLength);
        char[] char_Set1 = new char[]
        {
            'a', 'h', 'e', 'm', 'd', '0', '1', '3', '5', '8', '9'
        };
        char[] char_Set2 = new char[]
        {
            'V', 'J', 'L', 'I', 'H', '0', '1', '3', '5', '8', '9'
        };

        while (rtv.length() < 13)
        {
            rtv += String.valueOf(char_Set1[(int) (Math.round(rnd.nextFloat() * 10))]);
            rtv += String.valueOf(char_Set2[(int) (Math.round(rnd.nextFloat() * 10))]);
        }

        return rtv;
    }
}
