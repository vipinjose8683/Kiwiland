package kiwiland.trains;

import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * Creates the queue of unvisited towns starting from given town
 *
 */
class UnvisitedQueueCreator {

    public PriorityQueue<NodeDistance> create(Graph graph, Node startTown) {
        PriorityQueue<NodeDistance> queue = new PriorityQueue<>();
        for (Entry<String,Node> townEntry : graph.getTowns().entrySet()) {
            Node town = townEntry.getValue();
            if (!startTown.equals(town)) {
                queue.add(new NodeDistance(town));
            }
        }
        return queue;
    }

}
