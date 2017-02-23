package kiwiland.trains.domain;

import java.util.Map;
import java.util.Set;

/**
 * Data structure that contains the vertices and edges
 *
 */
public class Graph {
    
    private Map<String,Node> towns;
    private Set<Edge> routes;
    
    public Map<String,Node> getTowns() {
        return towns;
    }
    public void setTowns(Map<String,Node> towns) {
        this.towns = towns;
    }
    public Set<Edge> getRoutes() {
        return routes;
    }
    public void setRoutes(Set<Edge> routes) {
        this.routes = routes;
    }

}
