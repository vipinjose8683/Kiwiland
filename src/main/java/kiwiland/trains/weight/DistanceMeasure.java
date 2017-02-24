/**
 * 
 */
package kiwiland.trains.weight;

import java.util.Map.Entry;

import kiwiland.trains.domain.Node;

/**
 * This class weighs the distance of the node
 *
 */
public class DistanceMeasure implements Measure {

    @Override
    public Integer getWeight(Entry<Node, Integer> routeEntry) {
        return routeEntry.getValue();
    }

}
