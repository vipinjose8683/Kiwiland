package kiwiland.trains.shortest;

import kiwiland.trains.domain.Node;

class NodeDistance implements Comparable<NodeDistance> {
    
    private Integer distance;
    
    private Node town;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Node getTown() {
        return town;
    }

    public NodeDistance(Node town) {
        super();
        this.town = town;
    }

    @Override
    public int compareTo(NodeDistance o) {
        int value;
        if (this.distance == null) {
            if (o.distance == null) {
                value = 0;
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
        return value;
    }

    @Override
    public String toString() {
        return "[" + distance + ", " + town + "]";
    }
    
    
    
}
