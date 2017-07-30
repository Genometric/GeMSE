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
package GeMSE.GS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Vahid Jalili
 */
public class SampleData implements Serializable
{
    public SampleData()
    {
        messages = new ArrayList<>();
        features = new HashMap<>();
        determinedFeatures = new ArrayList<>();
        metaData = new HashMap<>();
        _numAttributes = new HashMap<>();
        _txtAttributes = new HashMap<>();
    }

    public String fileName;
    public String fullPath;
    public static final long serialVersionUID = 1;

    /**
     * Chromosome count of the sample which is set by features parser based on
     * the selected species.
     */
    public byte chrCount;

    public int featuresCount;

    /**
     * Total number of lines in the file.
     */
    public int lineCounter;

    public ArrayList<String> messages;

    /**
     * Parsed features grouped chromosome-wide.
     */
    public HashMap<String, ArrayList<Feature>> features;

    /**
     * Contains the determined features.
     * <para>Each string[] is a unique feature; the feature index in the list is
     * the position of the "feature" in "Feature" class.</para>
     * <para>The second value of the string[] is total number of the
     * feature.</para>
     */
    public ArrayList<String[]> determinedFeatures;

    public HashMap<String, String[]> metaData;

    public String sampleID;

    private HashMap<String, Byte> _txtAttributes;

    private HashMap<String, Byte> _numAttributes;

    public Set<String> GetNumAttributes()
    {
        return _numAttributes.keySet();
    }

    public ArrayList<String> GetNumAttributesArray()
    {
        ArrayList<String> rtv = new ArrayList<>();
        for (Map.Entry entry : _numAttributes.entrySet())
            rtv.add((String) entry.getKey());

        return rtv;
    }

    public String GetNumAttribute(String chr, int start, int stop, String attribute)
    {
        for (Feature f : features.get(chr))
            if (f.start == start && f.stop == stop)
                if (!Double.isNaN(f.GetNumValue(attribute)))
                    return String.valueOf(f.GetNumValue(attribute));

        return "NA";
    }

    public HashMap<String, Byte> CopyNumAttributes()
    {
        return _numAttributes;
    }

    public void PasteNumAttributes(HashMap<String, Byte> numAttributes)
    {
        _numAttributes = numAttributes;
    }

    public Set<String> GetTXTAttributes()
    {
        return _txtAttributes.keySet();
    }

    public ArrayList<String> GetTXTAttributesArray()
    {
        ArrayList<String> rtv = new ArrayList<>();
        for (Map.Entry entry : _txtAttributes.entrySet())
            rtv.add((String) entry.getKey());

        return rtv;
    }

    public String GetTXTAttribute(String chr, int start, int stop, String attribute)
    {
        for (Feature f : features.get(chr))
            if (f.start == start && f.stop == stop)
                return String.valueOf(f.GetTXTValue(attribute));

        return "NA";
    }

    public HashMap<String, Byte> CopyTXTAttributes()
    {
        return _txtAttributes;
    }

    public void PasteTXTAttributes(HashMap<String, Byte> txtAttributes)
    {
        _txtAttributes = txtAttributes;
    }

    /**
     * Returns the feature sampleID of given feature. The sampleID is the index
     * of feature in determinedFeatures variable.
     *
     * @param feature The feature that its sampleID should be returned
     * @return The sampleID of the feature.
     */
    public byte GetFeatureID(String feature)
    {
        for (byte i = 0 ; i < determinedFeatures.size() ; i++)
            if (determinedFeatures.get(i)[0].equals(feature))
                return i;
        return -1;
    }

    public class Feature implements Comparable<Feature>, Serializable
    {
        public static final long serialVersionUID = 1;
        /**
         * Sets and Gets Start position of the feature, with sequence numbering
         * starting at 1
         */
        public int start;

        /**
         * Sets and Gets Stop position of the feature, with sequence numbering
         * starting at 1.
         */
        public int stop;

        /**
         * Sets and Gets Feature type name (e.g. exon, start_codon).
         * <para>The value is a byte being the hash key of the feature name at
         * feature_title_conversion hashtable</para>
         */
        public byte feature;


        private class _numAttribute implements Serializable
        {
            private _numAttribute(byte attribute, double value)
            {
                _attribute = attribute;
                _value = value;
            }

            private final byte _attribute;
            private final double _value;
            public static final long serialVersionUID = 1;
        }
        private final ArrayList<_numAttribute> _numAVP = new ArrayList<>();


        private class _txtAttribute implements Serializable
        {
            private _txtAttribute(byte attribute, String value)
            {
                _attribute = attribute;
                _value = value;
            }

            private final byte _attribute;
            private final String _value;
            public static final long serialVersionUID = 1;
        }
        private final ArrayList<_txtAttribute> _txtAVP = new ArrayList<>();


        public void AddAttribute(String attribute, double value)
        {
            if (_numAttributes.containsKey(attribute) == false)
                _numAttributes.put(attribute, (byte) _numAttributes.size());
            _numAVP.add(new _numAttribute((byte) _numAttributes.get(attribute), value));
        }
        public void AddAttribute(String attribute, String value)
        {
            if (_txtAttributes.containsKey(attribute) == false)
                _txtAttributes.put(attribute, (byte) _txtAttributes.size());
            _txtAVP.add(new _txtAttribute((byte) _txtAttributes.get(attribute), value));
        }

        /**
         * Returns the value of given attribute. It returns -1 if the feature
         * does not have given attribute.
         *
         * @param attribute The attribute that its value should be returned.
         * @return Returns the value of given attribute. It returns -1 if the
         * feature does not have given attribute.
         */
        public double GetNumValue(String attribute)
        {
            if (_numAttributes.containsKey(attribute) == false)
                return Double.NaN;

            byte attID = (byte) _numAttributes.get(attribute);
            for (_numAttribute att : _numAVP)
                if (att._attribute == attID)
                    return att._value;

            return Double.NaN;
        }

        public String GetTXTValue(String attribute)
        {
            if (_txtAttributes.containsKey(attribute) == false)
                return "NA";

            byte attID = (byte) _txtAttributes.get(attribute);
            for (_txtAttribute att : _txtAVP)
                if (att._attribute == attID)
                    return att._value;

            return "NA";
        }


        @Override
        public int compareTo(Feature that)
        {
            if (this == that) return 0; // equal

            if (this.start < that.start) return -1; // before
            if (this.start > that.start) return 1; // after

            // reach here only when two starts are equal
            if (this.stop < that.stop) return -1; // before
            if (this.stop > that.stop) return 1; // after

            if (this.feature < that.feature) return -1; // before
            if (this.feature > that.feature) return 1; // after

            return 0; // equal
        }

        @Override
        public boolean equals(Object item)
        {
            if (this == item) return true;
            if (!(item instanceof Feature)) return false;

            Feature that = (Feature) item;
            return (this.start == that.start)
                   && (this.stop == that.stop)
                   && (this.feature == that.feature);
        }
    }
}
