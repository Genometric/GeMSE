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
package GeMSE.GS.clustering;

import ExternalLibraries.Cluster;
import GeMSE.GS.Space;
import GeMSE.GlobalVariables;
import GeMSE.OperationsOptions.ClusteringOptions.ClusteringDomains;
import GeMSE.OperationsOptions.ClusteringOptions.DistanceType;
import java.util.ArrayList;
import java.io.File;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Vahid Jalili
 */
public class ClusterToGraphML
{
    private static String _spaceID;
    private static String _nodeLabel;
    private static double _d2r;// = 2;
    private static int _clusterNumber;
    private static DistanceType _dt;
    private static ArrayList<String[]> _nodes;
    private static ArrayList<String[]> _edges;
    private static HashMap<String, String> _ID2Label;
    private static ClusteringDomains _domain;
    private static final boolean _merge = false;
    private static final String _beforeCut = "before";
    private static final String _afterCut = "after";


    public static void Convert(String spaceID, Cluster cluster, String filename, ClusteringDomains domain, DistanceType distanceType, double distanceToRoot)
    {
        _nodeLabel = "Root";
        _spaceID = spaceID;
        _nodes = new ArrayList<>();
        _edges = new ArrayList<>();
        _d2r = distanceToRoot;
        _dt = distanceType;
        _domain = domain;
        _clusterNumber = 0;
        NodeIDToLabel();
        _Convert(cluster, 0.0);
        _WriteToGraphML(filename);
    }

    private static void NodeIDToLabel()
    {
        Space tmp = GlobalVariables.space.GetSpace(_spaceID);
        _ID2Label = new HashMap<>();

        if (null != _domain)
            switch (_domain)
            {
                case Rows:
                    tmp.UpdateRowsTitles();
                    for (int row = 0 ; row < tmp.rowsID.length ; row++)
                        _ID2Label.put(tmp.rowsID[row], tmp.rowTitle[row]);
                    break;

                case Columns:
                    tmp.UpdateColumnsTitles();
                    for (int col = 0 ; col < tmp.colsID.length ; col++)
                        _ID2Label.put(tmp.colsID[col], tmp.colTitle[col]);
                    break;

                default:
                    break;
            }
    }

    private static void _Convert(Cluster c, double d2r)
    {
        if (c.getChildren().isEmpty()
            || (_dt == DistanceType.Height && d2r >= _d2r)
            || (_dt == DistanceType.Distance && c.getDistance() < _d2r))
        {
            _clusterNumber++;
            if (!c.getChildren().isEmpty())
                _TraverseLeaves(c, d2r);
            else
                _nodes.add(new String[]
                {
                    GetHashKey(c.getName()), _ID2Label.get(c.getName()), _afterCut, String.valueOf(_clusterNumber)
                });
        }
        else
        {
            if (_dt == DistanceType.Height)
                d2r++;
            else
                d2r = c.getDistance();

            _nodes.add(new String[]
            {
                GetHashKey(c.getName()), _nodeLabel, _beforeCut, "0"
            });
            _nodeLabel = "";

            for (Cluster child : c.getChildren())
            {
                _edges.add(new String[]
                {
                    GetHashKey(c.getName() + child.getName()), GetHashKey(c.getName()), GetHashKey(child.getName()), Double.toString(d2r)
                });

                _Convert(child, d2r);
            }
        }
    }

    private static void _TraverseLeaves(Cluster c, double d2r)
    {
        if (_dt == DistanceType.Height)
            d2r++;
        else
            d2r = c.getDistance();

        _nodes.add(new String[]
        {
            GetHashKey(c.getName()), "", _afterCut, String.valueOf(_clusterNumber)
        });

        for (Cluster child : c.getChildren())
        {
            _edges.add(new String[]
            {
                GetHashKey(c.getName() + child.getName()), GetHashKey(c.getName()), GetHashKey(child.getName()), Double.toString(d2r)
            });

            if (!child.getChildren().isEmpty())
                _TraverseLeaves(child, d2r);
            else
                _nodes.add(new String[]
                {
                    GetHashKey(child.getName()), _ID2Label.get(child.getName()), _afterCut, String.valueOf(_clusterNumber)
                });
        }
    }

    private static String GetHashKey(String key)
    {
        if (key.hashCode() > 0)
            return Integer.toString(key.hashCode());
        else
            return "V" + Integer.toString(key.hashCode() * -1);
    }

    private static void _WriteToGraphML(String filename)
    {
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            doc.setXmlVersion("1.0");
            doc.setXmlStandalone(true);

            Element rootElement = doc.createElement("graphml");
            doc.appendChild(rootElement);

            Element nodeNameKey = doc.createElement("key");
            rootElement.appendChild(nodeNameKey);
            nodeNameKey.setAttribute("id", "name"); // don't change this.
            nodeNameKey.setAttribute("for", "node");
            nodeNameKey.setAttribute("attr.name", "name");
            nodeNameKey.setAttribute("attr.type", "string");

            Element nodeCutKey = doc.createElement("key");
            rootElement.appendChild(nodeCutKey);
            nodeCutKey.setAttribute("id", "cut");
            nodeCutKey.setAttribute("for", "node");
            nodeCutKey.setAttribute("attr.name", "cut");
            nodeCutKey.setAttribute("attr.type", "string");
            
            Element nodeClusterKey = doc.createElement("key");
            rootElement.appendChild(nodeClusterKey);
            nodeClusterKey.setAttribute("id", "cluster");
            nodeClusterKey.setAttribute("for", "node");
            nodeClusterKey.setAttribute("attr.name", "cluster");
            nodeClusterKey.setAttribute("attr.type", "int");

            Element edgeKey = doc.createElement("key");
            rootElement.appendChild(edgeKey);
            edgeKey.setAttribute("id", "w");
            edgeKey.setAttribute("for", "edge");
            edgeKey.setAttribute("attr.name", "weight");
            edgeKey.setAttribute("attr.type", "double");

            Element graphKey = doc.createElement("graph");
            rootElement.appendChild(graphKey);
            graphKey.setAttribute("id", "g");
            graphKey.setAttribute("edgedefault", "undirected");

            for (String[] node : _nodes)
            {
                Element nE = doc.createElement("node");
                graphKey.appendChild(nE);
                nE.setAttribute("id", node[0]);

                Element nName = doc.createElement("data");
                nName.setAttribute("key", "name");
                nName.appendChild(doc.createTextNode(node[1]));
                nE.appendChild(nName);

                Element nCut = doc.createElement("data");
                nCut.setAttribute("key", "cut");
                nCut.appendChild(doc.createTextNode(node[2]));
                nE.appendChild(nCut);
                
                Element nCluster = doc.createElement("data");
                nCluster.setAttribute("key", "cluster");
                nCluster.appendChild(doc.createTextNode(node[3]));
                nE.appendChild(nCluster);
            }

            for (String[] edge : _edges)
            {
                Element eE = doc.createElement("edge");
                graphKey.appendChild(eE);
                eE.setAttribute("id", edge[0]);
                eE.setAttribute("source", edge[1]);
                eE.setAttribute("target", edge[2]);

                Element eEC = doc.createElement("data");
                eEC.setAttribute("key", "w");
                eEC.appendChild(doc.createTextNode(edge[3]));
                eE.appendChild(eEC);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source, result);
        }
        catch (ParserConfigurationException | TransformerException pce)
        {
        }
    }
}
