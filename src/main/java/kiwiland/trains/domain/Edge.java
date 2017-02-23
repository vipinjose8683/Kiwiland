package kiwiland.trains.domain;

/**
 * Represents a route between two cities
 *
 */
public class Edge implements Comparable<Edge> {
    
    private Node startTown;
    private Node endTown;
    private Integer distance;
    
    public Edge(Node startTown, Node endTown, Integer distance) {
        super();
        this.startTown = startTown;
        this.endTown = endTown;
        this.distance = distance;
    }

    public Node getStartTown() {
        return startTown;
    }

    public Node getEndTown() {
        return endTown;
    }

    public Integer getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Edge o) {
        int value = this.distance.compareTo(o.distance);
        if (value == 0) {
            value = this.endTown.compareTo(o.endTown);
        }
        return value;
    }
    
}
