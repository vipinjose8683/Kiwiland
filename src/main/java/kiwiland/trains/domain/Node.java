package kiwiland.trains.domain;

import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a node
 *
 */
public class Node implements Comparable<Node> {
    
    private String name;
    private Map<Edge,Node> weightedEdges = new TreeMap<>();
    
    public Node(String name) {
        super();
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public Map<Edge,Node> getWieghtedEdges() {
        return weightedEdges;
    }
    
    public void addEdge(Edge edge) {
        weightedEdges.put(edge, edge.getEndTown());
    }

    @Override
    public int compareTo(Node o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
    
    
    
}
