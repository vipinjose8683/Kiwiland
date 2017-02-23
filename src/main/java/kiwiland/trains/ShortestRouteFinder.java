package kiwiland.trains;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Finds the shortest path between two towns
 *
 */
class ShortestRouteFinder {

    private UnvisitedQueueCreator unvisitedQueueCreator = new UnvisitedQueueCreator();
    
    private DistanceMapCreator distanceMapCreator = new DistanceMapCreator();

    public Integer find(Graph graph, String startTownS, String endTownS) {
        /*
         * Uses dijkstra's algorithm
         */
        Node startTown = graph.getTowns().get(startTownS);
        Node endTown = graph.getTowns().get(endTownS);
        List<NodeDistance> unvisitedTowns = unvisitedQueueCreator.create(graph, startTown);
        Map<Node, NodeDistance> distance = distanceMapCreator.create(unvisitedTowns, startTown);
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

    private Map<Node, NodeDistance> getUpdatedDistance(Node current, Map<Node, NodeDistance> distance, Integer currentTownDistance) {
        for (Entry<Edge,Node> edgeEntry : current.getWieghtedEdges().entrySet()) {
            Edge route = edgeEntry.getKey();
            Node endTown = edgeEntry.getValue();
            NodeDistance endTownDistance = distance.get(endTown);
            Integer newDistance = currentTownDistance + route.getDistance();
            Integer currentEndTownDistance = endTownDistance.getDistance();
            if (currentEndTownDistance == null || newDistance < currentEndTownDistance) {
                endTownDistance.setDistance(newDistance);
            }
        }
        return distance;
    }

}
