package kiwiland.trains;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Creates the queue of unvisited towns starting from given town
 *
 */
class UnvisitedQueueCreator {

    public List<NodeDistance> create(Graph graph, Node startTown) {
        List<NodeDistance> list = new ArrayList<>();
        for (Entry<String,Node> townEntry : graph.getTowns().entrySet()) {
            Node town = townEntry.getValue();
            if (!startTown.equals(town)) {
                list.add(new NodeDistance(town));
            }
        }
        return list;
    }

}
