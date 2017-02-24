/**
 * 
 */
package kiwiland.trains.different;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import kiwiland.trains.domain.Edge;
import kiwiland.trains.domain.Graph;
import kiwiland.trains.domain.Node;
import kiwiland.trains.weight.Weightage;

/**
 * Finds all different routes from the given source to destination
 *
 */
public class DifferentRoutesFinder {

    private Weightage weightage;

    public DifferentRoutesFinder(Weightage weightage) {
        this.weightage = weightage;
    }

    public Integer find(Graph graph, String startTownS, Integer maxWeight) {

        Node startTown = graph.getTowns().get(startTownS);
        Stack<Node> townStack = new Stack<>();
        townStack.push(startTown);
        Integer currentDistance = 0;
        Set<Route> routes = new HashSet<>();
        routes = getMinimalRoutes(startTown, currentDistance, startTown, townStack, routes, maxWeight);
        routes = extendMinimalRoutes(routes);
        Stack<Route> addedRoutes = new Stack<>();
        Set<String> allRoutes = new HashSet<>();
        allRoutes = getRoutes(routes, currentDistance, allRoutes, addedRoutes, maxWeight);
        return allRoutes.size();
    }

    private Set<Route> extendMinimalRoutes(Set<Route> minimalRoutes) {
        Set<Route> extendedRoutes = new HashSet<>();
        for (Route route : minimalRoutes) {
            Integer times = 30 / route.getDistance();
            for (int i = 0; i < times - 1; i++) {
                extendedRoutes.add(new Route(route.getTowns(), route.getDistance()));
            }
        }
        minimalRoutes.addAll(extendedRoutes);
        return minimalRoutes;
    }

    private Set<String> getRoutes(Set<Route> extendedMinimalRoutes, Integer currentDistance, Set<String> allRoutes, Stack<Route> addedRoutes, Integer maxWeight) {
        for(Route minimalRoute : extendedMinimalRoutes) {
            addedRoutes.push(minimalRoute);
            Integer newDistance = currentDistance + minimalRoute.getDistance();
            if (newDistance < maxWeight) {
                String route = takeSnapshot(addedRoutes);
                allRoutes.add(route);
                allRoutes = getRoutes(extendedMinimalRoutes, newDistance, allRoutes, addedRoutes, maxWeight);
            }
            addedRoutes.pop();
        }
        return allRoutes;
    }

    private String takeSnapshot(Stack<Route> addedRoutes) {
        StringBuilder sb = new StringBuilder();
        for (Route route : addedRoutes) {
            for (Node town : route.getTowns()) {
                sb.append(town.getName());
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(sb.charAt(0));
        return sb.toString();
    }

    private Set<Route> getMinimalRoutes(Node currentTown, Integer currentDistance, Node startTown, Stack<Node> townStack, Set<Route> minimalRoutes, Integer maxWeight) {
        Map<Edge, Node> edges = currentTown.getWieghtedEdges();
        for (Entry<Edge, Node> edgeEntry : edges.entrySet()) {
            Node nextTown = edgeEntry.getValue();
            townStack.push(nextTown);
            Integer newDistance = currentDistance + this.weightage.getWeight(edgeEntry);
            if (nextTown == startTown) {
                minimalRoutes.add(new Route(new ArrayList<>(townStack), newDistance));
            } else if (newDistance <= maxWeight) {
                minimalRoutes = getMinimalRoutes(nextTown, newDistance, startTown, townStack, minimalRoutes, maxWeight);
            }
            townStack.pop();
        }
        return minimalRoutes;
    }

}
