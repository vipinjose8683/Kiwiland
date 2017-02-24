/**
 * 
 */
package kiwiland.trains;

import java.util.Map.Entry;

import kiwiland.trains.domain.Edge;
import kiwiland.trains.domain.Graph;
import kiwiland.trains.domain.Node;

/**
 * Calculates the distance of the trip
 *
 */
public class TripCalculator {

    public String calculate(Graph graph, String path) {
        String result = null;
        Integer distance = 0;
        Integer i = 0;
        Node currentTown = graph.getTowns().get("" + path.charAt(i++));
        Integer pathLength = path.length();
        boolean pathDoesNoExist = false;
        for (; i < pathLength;i++) {
            String nextTownName = "" + path.charAt(i);
            boolean foundNextTown = false;
            for (Entry<Edge, Node> edgeEntry : currentTown.getWieghtedEdges().entrySet()) {
                Node nextTown = edgeEntry.getValue();
                if (nextTown.getName().equals(nextTownName)) {
                    distance += edgeEntry.getKey().getDistance();
                    currentTown = nextTown;
                    foundNextTown = true;
                    break;
                }
            }
            if (!foundNextTown) {
                pathDoesNoExist = true;
                break;
            }
        }
        if (pathDoesNoExist) {
            result = "NO SUCH ROUTE";
        } else {
            result = "" + distance;
        }
        return result;
    }

}
