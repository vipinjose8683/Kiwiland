/**
 * 
 */
package kiwiland.trains.weight;

import java.util.Map.Entry;

import kiwiland.trains.domain.Node;

/**
 * Measures the weightage by the stop so it will always be 1
 *
 */
public class StopsMeasure implements Measure {

    /* (non-Javadoc)
     * @see kiwiland.trains.Weightage#getWeight(java.util.Map.Entry)
     */
    @Override
    public Integer getWeight(Entry<Node, Integer> route) {
        return 1;
    }

}
