package kiwiland.trains.different;

import java.util.List;

import kiwiland.trains.domain.Node;

class Route {
    
    List<Node> towns;
    
    Integer distance;

    public Route(List<Node> towns, Integer distance) {
        super();
        this.towns = towns;
        this.distance = distance;
    }

    public List<Node> getTowns() {
        return towns;
    }

    public Integer getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "[" + towns + "=" + distance + "]";
    }

    
}
