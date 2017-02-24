/**
 * 
 */
package kiwiland.trains.trips;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import kiwiland.trains.combine.Combination;
import kiwiland.trains.combine.Combiner;
import kiwiland.trains.combine.WeightCalculator;
import kiwiland.trains.combine.Validator;
import kiwiland.trains.domain.Edge;
import kiwiland.trains.domain.Graph;
import kiwiland.trains.domain.Node;
import kiwiland.trains.weight.Measure;

/**
 * Finds all different routes from the given source to destination
 *
 */
public class CycleTripsFinder {

    private Measure measure;

    public CycleTripsFinder(Measure measure) {
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
        Combiner<Trip> tripCombiner = new TripCombiner();
        Validator<Trip> tripCombinationValidator = new TripCombinationValidator(maxWeight);
        WeightCalculator<Trip> tripWeightCalculator = new TripWeightCalculator();
        Combination<Trip> combination = new Combination<>(tripCombinationValidator, tripCombiner, tripWeightCalculator);
        
        return combination.get(routes).size();
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
