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
import kiwiland.trains.weight.Measure;

/**
 * Finds all different routes from the given source to destination
 *
 */
public class TripsFinder {

    private Measure measure;

    public TripsFinder(Measure measure) {
        this.measure = measure;
    }

    public Integer find(Graph graph, String startTownS, Integer maxWeight) {

        Node startTown = graph.getTowns().get(startTownS);
        Stack<Node> townStack = new Stack<>();
        townStack.push(startTown);
        Integer currentDistance = 0;
        Set<Trip> routes = new HashSet<>();
        routes = getMinimalRoutes(startTown, currentDistance, startTown, townStack, routes, maxWeight);
        routes = extendMinimalRoutes(routes);
        Stack<Trip> addedRoutes = new Stack<>();
        Set<String> allRoutes = new HashSet<>();
        allRoutes = getRoutes(routes, currentDistance, allRoutes, addedRoutes, maxWeight);
        return allRoutes.size();
    }

    private Set<Trip> extendMinimalRoutes(Set<Trip> minimalRoutes) {
        Set<Trip> extendedRoutes = new HashSet<>();
        for (Trip route : minimalRoutes) {
            Integer times = 30 / route.getDistance();
            for (int i = 0; i < times - 1; i++) {
                extendedRoutes.add(new Trip(route.getTowns(), route.getDistance()));
            }
        }
        minimalRoutes.addAll(extendedRoutes);
        return minimalRoutes;
    }

    private Set<String> getRoutes(Set<Trip> extendedMinimalRoutes, Integer currentDistance, Set<String> allRoutes, Stack<Trip> addedRoutes, Integer maxWeight) {
        for(Trip minimalRoute : extendedMinimalRoutes) {
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

    private String takeSnapshot(Stack<Trip> addedRoutes) {
        StringBuilder sb = new StringBuilder();
        for (Trip route : addedRoutes) {
            for (Node town : route.getTowns()) {
                sb.append(town.getName());
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(sb.charAt(0));
        return sb.toString();
    }

    private Set<Trip> getMinimalRoutes(Node currentTown, Integer currentDistance, Node startTown, Stack<Node> townStack, Set<Trip> minimalRoutes, Integer maxWeight) {
        Map<Edge, Node> edges = currentTown.getWieghtedEdges();
        for (Entry<Edge, Node> edgeEntry : edges.entrySet()) {
            Node nextTown = edgeEntry.getValue();
            townStack.push(nextTown);
            Integer newDistance = currentDistance + this.measure.getWeight(edgeEntry);
            if (nextTown == startTown) {
                minimalRoutes.add(new Trip(new ArrayList<>(townStack), newDistance));
            } else if (newDistance <= maxWeight) {
                minimalRoutes = getMinimalRoutes(nextTown, newDistance, startTown, townStack, minimalRoutes, maxWeight);
            }
            townStack.pop();
        }
        return minimalRoutes;
    }

}
