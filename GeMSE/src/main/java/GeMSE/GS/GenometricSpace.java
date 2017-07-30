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

import GeMSE.GS.History.History;
import GeMSE.GS.History.Node;
import GeMSE.GS.History.NodeData;
import GeMSE.GS.Operations.Functions;
import ExternalLibraries.DendrogramPanel;
import GeMSE.GlobalVariables;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author Vahid Jalili
 */
public class GenometricSpace implements Serializable
{
    public GenometricSpace()
    {
        _history = new History();
        _operations = new Operations();
    }
    
    public static final long serialVersionUID = 1;
    /**
     * Private Genometric Space populated by Initialize() function.
     */
    private Space _rootSpace;

    /**
     * Genometric Space populated by an operation.
     */
    public Space space;

    public DendrogramPanel dendrogramPanel;

    private final History _history;

    private final Operations _operations;

    /**
     * Initializes the genometric space (i.e., variable space) with specified
     * attribute of pointed feature in ascertained chromosomes of listed
     * samples.
     *
     * @param samples Index of source samples in global variable cached
     * features. Only these samples will be used in space creation.
     * @param chromosomes The index of chromosomes that should be used.
     * @param feature The feature that its attributes should be used. Set
     * feature value to "ALL" if NO classification over feature is intended.
     * @param attribute The attribute that its value will be used as genometric
     * space cell value.
     * @param regionCount Maximum number of regions reserved for selected
     * feature over all selected samples.
     */
    public void Initialize(int[] samples, Set<String> chromosomes, String feature, String attribute, int regionCount)
    {
        _rootSpace = new Space(regionCount, samples.length);

        ArrayList<SampleData.Feature> features;

        /**
         * This variable can be set to any number as the ID of "ALL" keyword for
         * the feature that should be read into plot. The value should be
         * unique, i.e., it should NOT be equal to any other feature IDs',
         * otherwise it will eventuate to incorrect plot.
         */
        byte anyFeatureID = Byte.MAX_VALUE;

        int k;
        for (int i = 0 ; i < samples.length ; i++)
        {
            k = 0;
            byte featureID;
            _rootSpace.colsID[i] = GlobalVariables.samples.get(i).sampleID;

            if (feature.equals("ALL"))
                featureID = anyFeatureID;
            else
                featureID = GlobalVariables.samples.get(samples[i]).GetFeatureID(feature);

            if (featureID != -1) // if feature is determined in the sample
            {
                SampleData selectedSample = GlobalVariables.samples.get(samples[i]);

                for (String chr : chromosomes)
                {
                    features = selectedSample.features.get(chr);
                    for (int j = 0 ; j < features.size() ; j++)
                    {
                        if (featureID == anyFeatureID || ((byte) features.get(j).feature == featureID))
                        {
                            _rootSpace.content[k][i] = features.get(j).GetNumValue(attribute);
                            _rootSpace.rowsID[k] = String.valueOf(chr) + "_" + String.valueOf(features.get(j).start) + "_" + String.valueOf(features.get(j).stop);
                            k++;
                        }
                    }
                }
            }
        }

        _history.initialSpace = _rootSpace;
    }

    public void UpdateSpace(String spaceID)
    {
        if (spaceID.equals(_history.GetRootID()))
            space = _rootSpace;
        else
            space = _history.GetSpace(spaceID);
    }

    public Node<NodeData> GetTree()
    {
        return _history.GetTree();
    }

    public void RunOperation(String parentSpaceID, String userDefinedTitle, Functions command, Object parameters)
    {
        _operations.source = GetSpace(parentSpaceID);
        _operations.Multiplexer(userDefinedTitle, command, parameters);
        NodeData newNode = new NodeData(null, command, userDefinedTitle, _operations.parameters);
        _history.PutSpace(parentSpaceID, newNode, _operations.result);
    }

    public Space GetSpace(String parentSpaceID)
    {
        if (parentSpaceID.equals("Root") == false)
            return _history.GetSpace(parentSpaceID);
        else
            return _rootSpace;
    }

    public void UpdateOperation(String spaceID, Functions command, Object parameters)
    {
        if (spaceID.equals("Root") == false)
        {
            _operations.source = _history.GetSpace(spaceID);
            _operations.Multiplexer(null, command, parameters);
        }
    }

    public Dimension GetRootSize()
    {
        return new Dimension(_rootSpace.content[0].length, _rootSpace.content.length);
    }
}
