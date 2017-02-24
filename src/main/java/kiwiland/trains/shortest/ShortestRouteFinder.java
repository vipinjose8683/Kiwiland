package kiwiland.trains.shortest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kiwiland.trains.domain.Graph;
import kiwiland.trains.domain.Node;

/**
 * Finds the shortest path between two towns
 *
 */
public class ShortestRouteFinder {

    public Integer find(Graph graph, String startTownS, String endTownS) {
        /*
         * Uses dijkstra's algorithm
         */
        Node startTown = graph.getTowns().get(startTownS);
        Node endTown = graph.getTowns().get(endTownS);
        List<NodeDistance> unvisitedTowns = createUnvisitedTowns(graph, startTown);
        Map<Node, NodeDistance> distance = createDistanceMap(unvisitedTowns, startTown);
        NodeDistance current = distance.get(startTown);
        Integer currentTownDistance = 0;
        while(!unvisitedTowns.isEmpty()) {
            Node town = current.getTown();
            distance = getUpdatedDistance(town, distance, currentTownDistance);
            if (!unvisitedTowns.isEmpty()) {
                Collections.sort(unvisitedTowns);
                current = unvisitedTowns.remove(0);
                currentTownDistance = current.getDistance();
                if (currentTownDistance == null) {
                    break;
                }
            }
        }
        return distance.get(endTown).getDistance();
    }

    private Map<Node, NodeDistance> getUpdatedDistance(Node current, Map<Node, NodeDistance> townDistances, Integer currentTownDistance) {
        for (Entry<Node, Integer> edgeEntry : current.getWieghtedEdges().entrySet()) {
            Node endTown = edgeEntry.getKey();
            Integer distance = edgeEntry.getValue();
            NodeDistance endTownDistance = townDistances.get(endTown);
            Integer newDistance = currentTownDistance + distance;
            Integer currentEndTownDistance = endTownDistance.getDistance();
            if (currentEndTownDistance == null || newDistance < currentEndTownDistance) {
                endTownDistance.setDistance(newDistance);
            }
        }
        return townDistances;
    }

    private Map<Node, NodeDistance> createDistanceMap(List<NodeDistance> unvisitedTowns, Node startTown) {
        Map<Node, NodeDistance> map = new HashMap<>();
        for (NodeDistance distance : unvisitedTowns) {
            map.put(distance.getTown(), distance);
        }
        map.put(startTown, new NodeDistance(startTown));
        return map;
    }
    
    private List<NodeDistance> createUnvisitedTowns(Graph graph, Node startTown) {
        List<NodeDistance> list = new ArrayList<>();
        for (Entry<String,Node> townEntry : graph.getTowns().entrySet()) {
            Node town = townEntry.getValue();
            if (!startTown.equals(town)) {
                list.add(new NodeDistance(town));
            }
        }
        return list;
    }


}
