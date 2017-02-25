package kiwiland.trains.shortest;

import kiwiland.trains.domain.Node;

class NodeDistance implements Comparable<NodeDistance> {
    
    private Integer distance;
    
    private Node town;

    public NodeDistance(Node town) {
        super();
        this.town = town;
    }
    
    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Node getTown() {
        return town;
    }
    
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((distance == null) ? 0 : distance.hashCode());
        result = prime * result + ((town == null) ? 0 : town.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj instanceof NodeDistance) {
            NodeDistance other = (NodeDistance)obj;
            equals = this.town.getName().equals(other.town.getName());
        }
        return equals;
    }

    @Override
    public int compareTo(NodeDistance o) {
        int value = this.town.getName().compareTo(o.town.getName());
        if (value != 0) {
            if (this.distance == null) {
                if (o.distance == null) {
                    value = -1;
                } else {
                    value = 1;
                }
            } else {
                if (o.distance == null) {
                    value = -1;
                } else {
                    value = this.distance.compareTo(o.distance);
                }
            }
        }
        return value;
    }

    @Override
    public String toString() {
        return "[" + distance + ", " + town + "]";
    }
    
    
    
}
