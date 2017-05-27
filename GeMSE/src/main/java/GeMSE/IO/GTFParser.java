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
import java.util.ArrayList;
import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vahid Jalili
 *
 * Provides features to parse a GTF file with arbitrary column order and feature
 * types.
 */
public class GTFParser
{

    protected GTFParser(
            String source,
            Boolean atLeastOneNumericAVP, // AVP: attribute-value pair
            Boolean addChrPrefixIfMissing)
    {
        _source = source;
        _atLeastOneNumericAVP = atLeastOneNumericAVP;
        File file = new File(_source);
        _data.fullPath = file.getAbsolutePath();
        _data.fileName = file.getName();
        _addChrPrefixIfMissing = addChrPrefixIfMissing;
    }


    protected GTFParser(
            String source,
            Boolean atLeastOneNumericAVP,
            byte startOffset,
            byte chrColumn,
            byte featureStartColumn,
            byte featureStopColumn,
            byte featureColumn,
            byte attributeColumn,
            Boolean addChrPrefixIfMissing)
    {
        _source = source;
        _atLeastOneNumericAVP = atLeastOneNumericAVP;
        _startOffset = startOffset;
        _chrColumn = chrColumn;
        _startColumn = featureStartColumn;
        _stopColumn = featureStopColumn;
        _featureColumn = featureColumn;
        _attributeColumn = attributeColumn;

        File file = new File(_source);
        _data.fullPath = file.getAbsolutePath();
        _data.fileName = file.getName();
        _addChrPrefixIfMissing = addChrPrefixIfMissing;
    }

    /**
     * Full path of source file name
     */
    private final String _source;

    /**
     * If the source file comes with header, the number of headers lines needs
     * to be specified so that parser can ignore them. If not specified and
     * header is present, header might be dropped because of improper format it
     * might have.
     */
    private byte _startOffset = 0;

    /**
     * The column number of chromosome name
     */
    private byte _chrColumn = 0;

    /**
     * The column number of feature start position
     */
    private byte _startColumn = 1;

    /**
     * The column number of feature stop position
     */
    private byte _stopColumn = 2;

    /**
     * The column number of feature
     */
    private byte _featureColumn = 3;

    /**
     * The column number of attribute
     */
    private byte _attributeColumn = 4;

    private final boolean _atLeastOneNumericAVP;

    private final boolean _addChrPrefixIfMissing;

    /**
     * Contains all read information from the input BED file, and will be
     * returned as read process result.
     */
    private final SampleData _data = new SampleData();


    /**
     * Reads the regions presented in source file and generates chromosome-wide
     * statistics regarding regions length and p-values.
     *
     * @return Returns an object of Input_BED_Data class
     */
    protected SampleData Parse()
    {
        boolean dropLine = false;
        int lineCounter = 0;
        ArrayList<String> lineDropMessage = new ArrayList<>();
        HashMap<String, Byte> featureConvertionHT = new HashMap<>();
        Hashtable featureCountHT = new Hashtable();
        int numericAttributeValuePairCount = 0;

        String line = "";
        String chr = "";
        byte chrCount = 0;
        BufferedReader fileReader;

        try
        {
            fileReader = new BufferedReader(new FileReader(new File(_source)));

            for (int i = 0 ; i < _startOffset ; i++)
            {
                line = fileReader.readLine();
                lineCounter++;
            }

            while ((line = fileReader.readLine()) != null)
            {
                lineCounter++;
                numericAttributeValuePairCount = 0;

                if (line.trim().length() > 0)
                {
                    SampleData.Feature readingFeature = _data.new Feature();
                    //Annotations.Annotation readingAnnotation = _annotations.new Annotation();

                    dropLine = false;

                    String[] splittedLine = line.split("\\s*\t\\s*");

                    // <editor-fold defaultstate="collapsed" desc=".::.     Process Start / Stop        .::.">
                    if (_startColumn < splittedLine.length && _stopColumn < splittedLine.length)
                    {
                        try
                        {
                            readingFeature.start = Integer.parseInt(splittedLine[_startColumn]);
                            readingFeature.stop = Integer.parseInt(splittedLine[_stopColumn]);

                        }
                        catch (NumberFormatException id)
                        {
                            dropLine = true;
                            lineDropMessage.add("\tLine " + Integer.toString(lineCounter) + "\t:\tInvalid Start\\Stop value.");
                        }
                    }
                    else
                    {
                        dropLine = true;
                        lineDropMessage.add("\tLine " + Integer.toString(lineCounter) + "\t:\tInvalid Start\\Stop value.");
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc=".::.     Process Feature             .::.">
                    if (_featureColumn < splittedLine.length)
                    {
                        if (featureConvertionHT.containsKey(splittedLine[_featureColumn]) == false)
                            featureConvertionHT.put(splittedLine[_featureColumn], (byte) featureConvertionHT.size());

                        readingFeature.feature = (byte) featureConvertionHT.get(splittedLine[_featureColumn]);
                    }
                    else
                    {
                        dropLine = true;
                        lineDropMessage.add("\tLine " + Integer.toString(lineCounter) + "\t:\tInvalid feature value.");
                    }

                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc=".::.     Process Attribute           .::.">
                    if (_attributeColumn < splittedLine.length)
                    {
                        String[] splittedAttributes = splittedLine[_attributeColumn].split("\\s*;\\s*");
                        for (String attribute : splittedAttributes)
                        {
                            // attribute-value pair
                            String[] avp = attribute.split("\\s*[\"]\\s*");

                            try
                            {
                                if (avp.length != 2 || avp[1].equals(""))
                                    readingFeature.AddAttribute(avp[0], 0.0);
                                else
                                    readingFeature.AddAttribute(avp[0], Double.parseDouble(avp[1]));

                                numericAttributeValuePairCount++;
                            }
                            catch (NumberFormatException wrongValue)
                            {
                                readingFeature.AddAttribute(avp[0], avp[1]);
                            }
                        }
                    }
                    // </editor-fold>

                    if (_chrColumn < splittedLine.length)
                    {
                        chr = splittedLine[_chrColumn];
                        if (_addChrPrefixIfMissing && chr.length() < 3)
                            chr = "chr" + chr;
                    }
                    else
                    {
                        dropLine = true;
                        lineDropMessage.add("\tLine " + Integer.toString(lineCounter) + "\t:\tInvalid chromosome column number ( " + splittedLine[_chrColumn] + " )");
                    }

                    if (!dropLine && ((_atLeastOneNumericAVP && numericAttributeValuePairCount > 0) || !_atLeastOneNumericAVP))
                    {
                        _data.featuresCount++;
                        if (_data.features.containsKey(chr) == false)
                        {
                            _data.features.put(chr, new ArrayList<SampleData.Feature>());
                            chrCount++;
                        }

                        _data.features.get(chr).add(readingFeature);

                        if (featureCountHT.containsKey(readingFeature.feature) == false)
                            featureCountHT.put(readingFeature.feature, 1);
                        else
                            featureCountHT.put(readingFeature.feature, (Integer) featureCountHT.get(readingFeature.feature) + 1);
                    }
                }
            }

            _data.messages = lineDropMessage;

            for (byte i = 0 ; i < featureConvertionHT.size() ; i++)
            {
                if (featureCountHT.containsKey((byte) i))
                {
                    for (Map.Entry entry : featureConvertionHT.entrySet())
                    {
                        if ((byte) entry.getValue() == i)
                        {
                            _data.determinedFeatures.add(new String[]
                            {
                                entry.getKey().toString(),
                                featureCountHT.get((byte) i).toString()
                            });
                            break;
                        }
                    }
                }
            }

            fileReader.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(GTFParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        _data.chrCount = chrCount;
        _data.sampleID = GenerateSampleID(_data.featuresCount, _data.fullPath.length());
        _data.lineCounter = lineCounter;
        return _data;
    }

    private String GenerateSampleID(int determinedFeaturesCount, int filePathLength)
    {
        Random rnd = new Random();
        String rtv = String.valueOf(determinedFeaturesCount) + String.valueOf(filePathLength);
        char[] charSet1 = new char[]
        {
            'a', 'h', 'e', 'm', 'd', '0', '1', '3', '5', '8', '9'
        };
        char[] charSet2 = new char[]
        {
            'V', 'J', 'L', 'I', 'H', '0', '1', '3', '5', '8', '9'
        };

        while (rtv.length() < 13)
        {
            rtv += String.valueOf(charSet1[(int) (Math.round(rnd.nextFloat() * 10))]);
            rtv += String.valueOf(charSet2[(int) (Math.round(rnd.nextFloat() * 10))]);
        }

        return rtv;
    }
}
