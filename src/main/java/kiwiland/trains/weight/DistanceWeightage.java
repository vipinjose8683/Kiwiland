/**
 * 
 */
package kiwiland.trains.weight;

import java.util.Map.Entry;

import kiwiland.trains.domain.Edge;
import kiwiland.trains.domain.Node;

/**
 * This class weighs the distance of the node
 *
 */
public class DistanceWeightage implements Weightage {

    @Override
    public Integer getWeight(Entry<Edge,Node> routeEntry) {
        return routeEntry.getKey().getDistance();
    }

}
