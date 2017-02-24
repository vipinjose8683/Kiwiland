package kiwiland.trains.domain;

import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a node
 *
 */
public class Node implements Comparable<Node> {
    
    private String name;
    private Map<Node, Integer> weightedEdges = new TreeMap<>();
    
    public Node(String name) {
        super();
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public Map<Node, Integer> getWieghtedEdges() {
        return weightedEdges;
    }
    
    public void addEdge(Node endTown, Integer distance) {
        weightedEdges.put(endTown, distance);
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
