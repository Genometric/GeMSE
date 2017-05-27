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

import GeMSE.GS.Operations.Functions;
import java.util.Random;

/**
 * Represents data required for a node to represent proper operation flow.
 *
 * @author Vahid Jalili
 */
public class NodeData
{

    /**
     * ID of the space generated with this operations.
     */
    private final String _spaceID;

    /**
     * Unique ID of the operation.
     */
    private final String _operationID;

    /**
     * The operation (e.g., select, filter, cluster) on parent node that
     * generated this space.
     */
    private final Functions _operation;

    /**
     * A user-defined title for the operation.
     */
    private final String _userDefinedTitle;

    /**
     * The parameters required for the operation.
     */
    private final Object _parameters;

    /**
     * The parent of the node defined in terms of tree nodes path from root. For
     * example: int[]{0, 3, 1} says the parent is the 2nd(1) child of 4th(3)
     * child or 1st(0) child of root.
     */
    private final int[] _parent;

    /**
     * Represents data required for a node to represent proper operation flow.
     *
     * @param parent The parent of the node defined in terms of tree nodes path
     * from root. For example: int[]{0, 3, 1} says the parent is the 2nd(1)
     * child of 4th(3) child or 1st(0) child of root.
     * @param operation The operation (e.g., select, filter, cluster) on parent
     * node that generated this space.
     * @param userDefinedTitle A user-defined title for the operation.
     * @param parameters The parameters required for the operation.
     */
    public NodeData(int[] parent, Functions operation, String userDefinedTitle, Object parameters)
    {
        _parent = parent;
        _spaceID = GenerateSpaceID(operation);
        _operation = operation;
        _userDefinedTitle = userDefinedTitle;
        _parameters = parameters;
        _operationID = GenerateOperationID();
    }

    /**
     * Represents data required for a node to represent proper operation flow.
     *
     * @param parent The parent of the node defined in terms of tree nodes path
     * from root. For example: int[]{0, 3, 1} says the parent is the 2nd(1)
     * child of 4th(3) child or 1st(0) child of root.
     * @param spaceID ID of the space generated with this operations.
     * @param operation The operation (e.g., select, filter, cluster) on parent
     * node that generated this space.
     * @param userDefinedTitle A user-defined title for the operation.
     * @param parameters The parameters required for the operation.
     */
    public NodeData(int[] parent, String spaceID, Functions operation, String userDefinedTitle, Object parameters)
    {
        _parent = parent;
        _spaceID = spaceID;
        _operation = operation;
        _userDefinedTitle = userDefinedTitle;
        _parameters = parameters;
        _operationID = GenerateOperationID();
    }

    /**
     * Returns unique ID of the operation.
     *
     * @return Returns unique ID of the operation.
     */
    public String GetOperationID()
    {
        return _operationID;
    }

    /**
     * Returns ID of the space generated with this operations.
     *
     * @return Returns ID of the space generated with this operations.
     */
    public String GetSpaceID()
    {
        return _spaceID;
    }

    /**
     * Returns the operation (e.g., select, filter, cluster) on parent node that
     * generated this space.
     *
     * @return Returns the operation (e.g., select, filter, cluster) on parent
     * node that generated this space.
     */
    public Functions GetOperation()
    {
        return _operation;
    }

    /**
     * Returns a user-defined title for the operation.
     *
     * @return Returns a user-defined title for the operation.
     */
    public String GetUserDefinedTitle()
    {
        return _userDefinedTitle;
    }

    /**
     * Returns the parameters required for the operation.
     *
     * @return Returns the parameters required for the operation.
     */
    public Object GetOperationParameters()
    {
        return _parameters;
    }

    /**
     * Returns the parent of the node defined in terms of tree nodes path from
     * root. For example: int[]{0, 3, 1} says the parent is the 2nd(1) child of
     * 4th(3) child or 1st(0) child of root.
     *
     * @return Returns the parent of the node defined in terms of tree nodes
     * path from root.
     */
    public int[] GetParent()
    {
        return _parent;
    }

    private String GenerateSpaceID(Functions spaceTransformation)
    {
        String rtv = "";
        String tchar;
        char[] charSet = new char[]
        {
            'a', 'h', 'e', 'd', 'm', '0', '1', '3', '5', '8', '9'
        };

        int spi = 0;
        while (rtv.length() < 4)
        {
            if (spaceTransformation.toString().length() > spi)
            {
                tchar = String.valueOf(spaceTransformation.toString().charAt(spi)).toUpperCase();

                switch (tchar)
                {
                    case "A":
                    case "E":
                    case "I":
                    case "O":
                        break;

                    default:
                        rtv += tchar;
                        break;
                }

                spi++;
            }
            else
            {
                while (rtv.length() < 4)
                {
                    rtv += "X";
                }
            }
        }

        while (rtv.length() < 13)
            rtv += String.valueOf(charSet[(int) (Math.round(new Random().nextFloat() * 10))]);

        return rtv;
    }

    private String GenerateOperationID()
    {
        char[] oSS = new char[3];
        _operation.toString().getChars(0, 3, oSS, 0);
        int parHashCode = 0;
        if (_parameters != null)
        {
            parHashCode = _parameters.hashCode();
        }

        return String.valueOf(oSS[0])
               + String.valueOf(oSS[1])
               + String.valueOf(oSS[2])
               + String.valueOf(_operation.toString().length())
               + String.valueOf(_userDefinedTitle.length())
               + String.valueOf(parHashCode);
    }
}
