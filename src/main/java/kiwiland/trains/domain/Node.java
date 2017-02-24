package kiwiland.trains.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a node
 *
 */
public class Node {
    
    private String name;
    private Map<Node, Integer> weightedEdges = new HashMap<>();
    
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
    public String toString() {
        return "[" + name + "]";
    }
    
    
    
}
