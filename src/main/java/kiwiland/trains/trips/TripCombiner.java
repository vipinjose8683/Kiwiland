package kiwiland.trains.trips;

import java.util.Collection;

import kiwiland.trains.combine.Combiner;
import kiwiland.trains.domain.Node;

class TripCombiner implements Combiner<Trip> {

    @Override
    public String combine(Collection<Trip> addedRoutes) {
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


}
