package kiwiland.trains.domain;

import java.util.Map;

/**
 * Data structure that contains the vertices and edges
 *
 */
public class Graph {
    
    private Map<String,Node> towns;
    
    public Map<String,Node> getTowns() {
        return towns;
    }
    public void setTowns(Map<String,Node> towns) {
        this.towns = towns;
    }
}
