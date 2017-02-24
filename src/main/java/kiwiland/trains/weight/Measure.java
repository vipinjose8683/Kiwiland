/**
 * 
 */
package kiwiland.trains.weight;

import java.util.Map.Entry;

import kiwiland.trains.domain.Edge;
import kiwiland.trains.domain.Node;

/**
 * @author VXJ8774
 *
 */
public interface Measure {
    
    Integer getWeight(Entry<Edge,Node> route);

}
