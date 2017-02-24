package kiwiland.trains.trips;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import kiwiland.trains.domain.Graph;
import kiwiland.trains.domain.Node;

public class TripsFinder {

    public Integer find(Graph graph, String startTownS, String endTownS, Integer stopsCount) {
        Node startTown = graph.getTowns().get(startTownS);
        Node endTown = graph.getTowns().get(endTownS);
        Stack<Node> townStack = new Stack<>();
        townStack.push(startTown);
        Integer currentDistance = 0;
        Set<String> allRoutes = new HashSet<>();
        allRoutes = getMinimalRoutes(startTown, currentDistance, endTown, townStack, allRoutes, stopsCount);
        return allRoutes.size();
    }

    private Set<String> getMinimalRoutes(Node currentTown, Integer currentDistance, Node endTown, Stack<Node> townStack, Set<String> minimalRoutes, Integer stopsCount) {
        Map<Node, Integer> edges = currentTown.getWieghtedEdges();
        for (Entry<Node, Integer> edgeEntry : edges.entrySet()) {
            Node nextTown = edgeEntry.getKey();
            townStack.push(nextTown);
            Integer newDistance = currentDistance + 1;
            if (nextTown == endTown && newDistance == stopsCount) {
                String route = takeSnapshot(townStack);
                minimalRoutes.add(route);
            } else if (newDistance < stopsCount) {
                minimalRoutes = getMinimalRoutes(nextTown, newDistance, endTown, townStack, minimalRoutes, stopsCount);
            }
            townStack.pop();
        }
        return minimalRoutes;
    }

    private String takeSnapshot(Stack<Node> addedRoutes) {
        StringBuilder sb = new StringBuilder();
        for (Node town : addedRoutes) {
            sb.append(town.getName());
        }
        return sb.toString();
    }

}
