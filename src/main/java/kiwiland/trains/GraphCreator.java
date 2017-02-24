package kiwiland.trains;

import java.util.HashMap;
import java.util.Map;

import kiwiland.trains.domain.Graph;
import kiwiland.trains.domain.Node;

/**
 * Creates the graph out of the input string
 *
 */
class GraphCreator {

    public Graph create(String inputString) {
        Graph graph = new Graph();
        Map<String,Node> towns = new HashMap<>();
        graph.setTowns(towns);
        
        String[] trips = inputString.split(", ");
        for (String trip : trips) {
            String startTownS = trip.substring(0, 1);
            String endTownS = trip.substring(1, 2);
            String distance = trip.substring(2, 3);
            Node startTown = getTown(startTownS, graph);
            Node endTown = getTown(endTownS, graph);
            startTown.addEdge(endTown, Integer.parseInt(distance));
        }
        return graph;
    }

    private Node getTown(String townS, Graph graph) {
        Node town = graph.getTowns().get(townS);
        if (town == null) {
            town = new Node(townS);
            graph.getTowns().put(townS, town);
        }
        return town;
    }

}
