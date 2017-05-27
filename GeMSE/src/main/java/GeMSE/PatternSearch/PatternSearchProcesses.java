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
package GeMSE.PatternSearch;

import GeMSE.GS.ClusteringFunctions;
import GeMSE.OperationsOptions.ClusteringOptions.ClusteringDomains;
import GeMSE.OperationsOptions.ClusteringOptions.DistanceType;
import GeMSE.OperationsOptions.ClusteringOptions.LinkageCriterias;
import GeMSE.GS.Space;
import GeMSE.OperationsOptions.ClusteringOptions;
import GeMSE.OperationsOptions.ClusteringOptions.Metrics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import ExternalLibraries.Cluster;

/**
 *
 * @author Vahid Jalili
 */
public class PatternSearchProcesses
{
    public PatternSearchProcesses(Space source, ClusteringDomains clusteringDomain, Metrics metric)
    {
        _source = source;
        _cF = new ClusteringFunctions(source);
        _cF.SetDistanceType(DistanceType.Height);
        _clusteringOptions = new ClusteringOptions();
        _clusteringOptions.domain = clusteringDomain;
        _clusteringOptions.metric = metric;
        _clusteringOptions.linkage = LinkageCriterias.Average;
        _clusteringOptions.dendrogram = _cF.AggHieClu(_clusteringOptions);
        _maxHeight = _cF.GetMaxHeight();
        _maxDistance = _cF.GetMaxDistance();
        _minDistance = _cF.GetMinDistance();
        _maxClusterCount = _cF.GetMaxClusterCount();

        // I want to do following in one line, or even better, use double-to-int casting !!
        Double t = _cF.GetDistanceToRoot();
        _defaultDistance = t.intValue();

        _defaultClusterCount = _cF.GetClusterCount(_clusteringOptions.dendrogram, DistanceType.Height);
    }

    public PatternSearchProcesses(ClusteringOptions options)
    {
        _clusteringOptions = options;

        // I want to do following in one line, or even better, use double-to-int casting !!
        Double t = options.cutDistance;
        _defaultDistance = t.intValue();
    }


    private Space _source;
    private ClusteringFunctions _cF;
    private ArrayList _groupedSimilars;
    private double[][] _patternCount;
    private Space _patternSpace;
    private ClusteringOptions _clusteringOptions;
    HashMap<String, ArrayList<String>> _idPointers;

    private int _maxHeight;
    public int MaxHeight()
    {
        return _maxHeight;
    }

    private int _maxClusterCount;
    public int MaxClusterCount()
    {
        return _maxClusterCount;
    }

    private double _maxDistance;
    public double MaxDistance()
    {
        return _maxDistance;
    }

    private double _minDistance;
    public double MinDistance()
    {
        return _minDistance;
    }


    private int _defaultDistance;
    public int DefaultDistance()
    {
        return _defaultDistance;
    }

    private int _defaultClusterCount;
    public int DefaultClusterCount()
    {
        return _defaultClusterCount;
    }

    public Space GetPatternSpace()
    {
        return _patternSpace;
    }
    public double[][] GetPatternCount()
    {
        return _patternCount;
    }

    public void SearchPattern(double distance, int numberOfClusters)
    {
        if (Double.isNaN(distance))
            distance = _cF.GetDistance(numberOfClusters, DistanceType.Distance);

        _cF.SetDistanceToRoot(distance);
        _groupedSimilars = _cF.GetRequestedCluster(_clusteringOptions.dendrogram, DistanceType.Distance);
        Collections.sort(_groupedSimilars, new PatternComparator());

        _idPointers = new HashMap<>();

        if (_clusteringOptions.domain == ClusteringDomains.Rows)
            _patternSpace = ApplyRowPattern();
        else
            _patternSpace = ApplyColPattern();
    }

    private Space ApplyRowPattern()
    {
        Space rtv;
        String searchKey;
        ArrayList<String> ids;

        if (AllLeaves())
        {
            rtv = new Space(1, _source.colsID.length);
            _patternCount = new double[1][1];
            _patternCount[0][0] = _groupedSimilars.size();

            searchKey = (String) _groupedSimilars.get(0);
            ids = new ArrayList<>();
            ids.add(searchKey);
            for (int i = 0 ; i < _source.rowsID.length ; i++)
            {
                if (searchKey.equals(_source.rowsID[i]))
                {
                    _idPointers.put(searchKey, ids);
                    rtv.rowsID[0] = searchKey;
                    for (int k = 0 ; k < rtv.colsID.length ; k++)
                        rtv.content[0][k] = _source.content[i][k];
                    break;
                }
            }
        }
        else
        {
            rtv = new Space(_groupedSimilars.size(), _source.colsID.length);
            _patternCount = new double[_groupedSimilars.size()][1];

            for (int row = 0 ; row < _groupedSimilars.size() ; row++)
            {
                if (_groupedSimilars.get(row) instanceof ArrayList)
                {
                    _patternCount[row][0] = ((ArrayList) _groupedSimilars.get(row)).size();
                    searchKey = (String) ((ArrayList) _groupedSimilars.get(row)).get(0);
                    ids = new ArrayList<>((ArrayList) _groupedSimilars.get(row));
                }
                else
                {
                    _patternCount[row][0] = 1;
                    searchKey = (String) _groupedSimilars.get(row);
                    ids = new ArrayList<>();
                    ids.add((String) _groupedSimilars.get(row));
                }

                for (int i = 0 ; i < _source.rowsID.length ; i++)
                {
                    if (searchKey.equals(_source.rowsID[i]))
                    {
                        _idPointers.put(searchKey, ids);
                        rtv.rowsID[row] = searchKey;
                        for (int k = 0 ; k < rtv.colsID.length ; k++)
                            rtv.content[row][k] = _source.content[i][k];
                        break;
                    }
                }
            }
        }

        for (int i = 0 ; i < _source.colsID.length ; i++)
            rtv.colsID[i] = _source.colsID[i];

        rtv.UpdateColumnsTitles();
        rtv.UpdateRowsTitles();
        return rtv;
    }

    private Space ApplyColPattern()
    {
        Space rtv;
        String searchKey;
        ArrayList<String> ids;

        if (AllLeaves())
        {
            rtv = new Space(_source.rowsID.length, 1);
            _patternCount = new double[1][1];
            _patternCount[0][0] = _groupedSimilars.size();

            searchKey = (String) _groupedSimilars.get(0);
            ids = new ArrayList<>();
            ids.add(searchKey);
            for (int i = 0 ; i < _source.colsID.length ; i++)
            {
                if (searchKey.equals(_source.colsID[i]))
                {
                    _idPointers.put(searchKey, ids);
                    rtv.colsID[0] = searchKey;
                    for (int k = 0 ; k < rtv.rowsID.length ; k++)
                        rtv.content[k][0] = _source.content[k][i];
                    break;
                }
            }
        }
        else
        {
            rtv = new Space(_source.rowsID.length, _groupedSimilars.size());
            _patternCount = new double[1][_groupedSimilars.size()];

            for (int col = 0 ; col < _groupedSimilars.size() ; col++)
            {
                if (_groupedSimilars.get(col) instanceof ArrayList)
                {
                    _patternCount[0][col] = ((ArrayList) _groupedSimilars.get(col)).size();
                    searchKey = (String) ((ArrayList) _groupedSimilars.get(col)).get(0);
                    ids = new ArrayList<>((ArrayList) _groupedSimilars.get(col));
                }
                else
                {
                    _patternCount[0][col] = 1;
                    searchKey = (String) _groupedSimilars.get(col);
                    ids = new ArrayList<>();
                    ids.add((String) _groupedSimilars.get(col));
                }

                for (int i = 0 ; i < _source.colsID.length ; i++)
                {
                    if (searchKey.equals(_source.colsID[i]))
                    {
                        _idPointers.put(searchKey, ids);
                        rtv.colsID[col] = searchKey;
                        for (int k = 0 ; k < rtv.rowsID.length ; k++)
                            rtv.content[k][col] = _source.content[k][i];

                        break;
                    }
                }
            }
        }

        for (int i = 0 ; i < _source.rowsID.length ; i++)
            rtv.rowsID[i] = _source.rowsID[i];

        rtv.UpdateColumnsTitles();
        rtv.UpdateRowsTitles();
        return rtv;
    }

    private boolean AllLeaves()
    {
        for (int item = 0 ; item < _groupedSimilars.size() ; item++)
            if (_groupedSimilars.get(item) instanceof ArrayList)
                return false;
        return true;
    }

    public ArrayList<String> GetRelativeIDs(String key)
    {
        return _idPointers.get(key);
    }

    public class PatternComparator implements Comparator<Object>
    {
        @Override
        public int compare(Object o1, Object o2)
        {
            if (o1 == null) return -1;
            if (o2 == null) return 1;
            if (o1 == o2) return 0;
            if (o1 instanceof ArrayList && o2 instanceof ArrayList) return (-1) * ((Integer) ((ArrayList) o1).size()).compareTo(((ArrayList) o2).size());
            return 0;
        }
    }

    public int ExchangeC4H(int count)
    {
        return _cF.ExchangeC4H(count);
    }
    public int ExchangeH4C(int height)
    {
        return _cF.ExchangeH4C(height);
    }
    public double ExchangeC4D(int count)
    {
        return _cF.ExchangeC4D(count);
    }
    public int ExchangeD4C(double distance)
    {
        return _cF.ExchangeD4C(distance);
    }
    public double ExchangeH4D(int height)
    {
        return _cF.ExchangeH4D(height);
    }
    public int ExchangeD4H(double distance)
    {
        return _cF.ExchangeD4H(distance);
    }
}
