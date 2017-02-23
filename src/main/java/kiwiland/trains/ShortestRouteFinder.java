package kiwiland.trains;

import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

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
//        Set<Node> visitedTowns = new HashSet<>();
        PriorityQueue<NodeDistance> unvisitedTowns = unvisitedQueueCreator.create(graph, startTown);
        Map<Node, NodeDistance> distance = distanceMapCreator.create(unvisitedTowns, startTown);
        NodeDistance current = distance.get(startTown);
        Integer currentTownDistance = 0;
        while(current != null) {
            distance = getUpdatedDistance(current.getTown(), distance, currentTownDistance);
//            visitedTowns.add(current);
            current = unvisitedTowns.poll();
            if (current != null) {
                currentTownDistance = current.getDistance();
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
