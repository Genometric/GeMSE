/**
 * *****************************************************************************
 * Copyright 2013 Lars Behnke
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *****************************************************************************
 */
package ExternalLibraries;

import GeMSE.GlobalVariables;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cluster implements Serializable
{
    public static final long serialVersionUID = GlobalVariables.serialVersionUID;
    private String name;
    private Cluster parent;
    private List<Cluster> children;
    private Double distance;

    public Double getDistance()
    {
        return distance;
    }

    public void setDistance(Double distance)
    {
        this.distance = distance;
    }

    public List<Cluster> getChildren()
    {
        if (children == null)
        {
            children = new ArrayList<Cluster>();
        }

        return children;
    }

    public void setChildren(List<Cluster> children)
    {
        this.children = children;
    }

    public Cluster getParent()
    {
        return parent;
    }

    public void setParent(Cluster parent)
    {
        this.parent = parent;
    }


    public Cluster(String name)
    {
        this.name = name;
    }

    /*public Cluster(Cluster cluster)
    {
        this.children = cluster.getChildren();
        this.distance = cluster.getDistance();
        this.name = cluster.getName();
        this.parent = cluster.getParent();
    }*/

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void addChild(Cluster cluster)
    {
        getChildren().add(cluster);

    }

    public boolean contains(Cluster cluster)
    {
        return getChildren().contains(cluster);
    }

    @Override
    public String toString()
    {
        return "Cluster " + name;
    }

    @Override
    public boolean equals(Object obj)
    {
        String otherName = obj != null ? obj.toString() : "";
        return toString().equals(otherName);
    }

    @Override
    public int hashCode()
    {
        return toString().hashCode();
    }

    public boolean isLeaf()
    {
        return getChildren().size() == 0;
    }

    public int countLeafs()
    {
        return countLeafs(this, 0);
    }

    public int countLeafs(Cluster node, int count)
    {
        if (node.isLeaf()) count++;
        for (Cluster child : node.getChildren())
        {
            count += child.countLeafs();
        }
        return count;
    }

    public void toConsole(int indent)
    {
        for (int i = 0 ; i < indent ; i++)
        {
            System.out.print("  ");

        }
        String name = getName() + (isLeaf() ? " (leaf)" : "") + (distance != null ? "  distance: " + distance : "");
        System.out.println(name);
        for (Cluster child : getChildren())
        {
            child.toConsole(indent + 1);
        }
    }

    public double getTotalDistance()
    {
        double dist = getDistance() == null ? 0 : getDistance();
        if (getChildren().size() > 0)
        {
            dist += children.get(0).getTotalDistance();
        }
        return dist;

    }


    public Cluster clone()
    {
        Cluster rtv = new Cluster(this.name);
        rtv.distance = this.distance;

        if (this.children != null)
        {
            rtv.children = new ArrayList<>();
            for (Cluster child : this.children)
                rtv.children.add(child.clone());
        }

        return rtv;
    }

}
