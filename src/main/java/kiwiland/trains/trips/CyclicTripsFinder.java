/**
 * 
 */
package kiwiland.trains.trips;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import kiwiland.trains.combine.Permutation;
import kiwiland.trains.combine.Combiner;
import kiwiland.trains.combine.Validator;
import kiwiland.trains.combine.WeightCalculator;
import kiwiland.trains.domain.Graph;
import kiwiland.trains.domain.Node;
import kiwiland.trains.weight.Measure;

/**
 * Finds all different routes from the given source to destination
 *
 */
public class CyclicTripsFinder {

    private Measure measure;

    public CyclicTripsFinder(Measure measure) {
        this.measure = measure;
    }

    public Integer find(Graph graph, String startTownS, Integer maxWeight) {

        Node startTown = graph.getTowns().get(startTownS);
        Deque<Node> townStack = new LinkedList<>();
        townStack.push(startTown);
        Integer currentDistance = 0;
        Set<Trip> routes = new HashSet<>();
        routes = getMinimalRoutes(startTown, currentDistance, startTown, townStack, routes, maxWeight);
        routes = extendMinimalRoutes(routes);
        Combiner<Trip> tripCombiner = new TripCombiner();
        Validator tripCombinationValidator = new TripCombinationValidator(maxWeight);
        WeightCalculator<Trip> tripWeightCalculator = new TripWeightCalculator();
        Permutation<Trip> permutation = new Permutation<>(tripCombinationValidator, tripCombiner, tripWeightCalculator);
        
        return permutation.get(routes).size();
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

    private Set<Trip> getMinimalRoutes(Node currentTown, Integer currentDistance, Node startTown, Deque<Node> townStack, Set<Trip> minimalRoutes, Integer maxWeight) {
        Map<Node, Integer> edges = currentTown.getWieghtedEdges();
        for (Entry<Node, Integer> edgeEntry : edges.entrySet()) {
            Node nextTown = edgeEntry.getKey();
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
