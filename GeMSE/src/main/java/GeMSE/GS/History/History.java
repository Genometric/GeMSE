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
package GeMSE.GS.History;

import GeMSE.GS.Operations;
import GeMSE.GS.Space;
import GeMSE.GlobalVariables;
import java.io.Serializable;

/**
 *
 * @author Vahid Jalili
 */
public class History implements Serializable
{
    public static final long serialVersionUID = GlobalVariables.serialVersionUID;
    public Space initialSpace;
    private final OperationsHistory _opHistory;
    private final SpacesHistory _spHistory;

    public History()
    {
        _opHistory = new OperationsHistory();
        _spHistory = new SpacesHistory();
    }

    public void PutSpace(String parentSpaceID, NodeData node, Space space)
    {
        _spHistory.Insert(node.GetSpaceID(), space);
        _opHistory.Insert(parentSpaceID, node);
    }

    public String GetRootID()
    {
        return _opHistory.rootID;
    }

    public Space GetSpace(String spaceID)
    {
        Space rtv;
        rtv = _spHistory.GetSpace(spaceID);
        if (rtv == null) // No space with specified ID exist in space history. 
            rtv = ReGenerateSpace(spaceID);

        return rtv;
    }

    public Node<NodeData> GetTree()
    {
        return _opHistory.GetTree();
    }

    private Space ReGenerateSpace(String spaceID)
    {
        NodeData[] transformationPath = _opHistory.FindTransformationPath(spaceID);

        int i = 0;
        String closestParentSpaceID = "";
        Operations operations = new Operations();
        if (transformationPath.length == 1)
        {
            operations.source = initialSpace;
        }
        else
        {
            for (i = transformationPath.length - 1 ; i >= 0 ; i--)
            {
                if (_spHistory.IsSpaceAvailable(transformationPath[i].GetSpaceID()) == true)
                {
                    closestParentSpaceID = transformationPath[i].GetSpaceID();
                    break;
                }
            }

            // if this condition is satisfied, it means that none of the spaces till root are available in space_history.
            if (closestParentSpaceID.isEmpty())
            {
                i = 0;
                operations.source = initialSpace;
            }
            else
            {
                operations.source = _spHistory.GetSpace(closestParentSpaceID);
            }
        }

        for (int j = i ; j < transformationPath.length ; j++)
        {
            operations.Multiplexer(
                    transformationPath[j].GetUserDefinedTitle(),
                    transformationPath[j].GetOperation(),
                    transformationPath[j].GetOperationParameters());

            operations.source = operations.result;
        }
        
        return operations.result;
    }
}
