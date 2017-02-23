package kiwiland.trains;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class DistanceMapCreator {

    public Map<Node, NodeDistance> create(PriorityQueue<NodeDistance> unvisitedTowns, Node startTown) {
        Map<Node, NodeDistance> map = new HashMap<>();
        for (NodeDistance distance : unvisitedTowns) {
            map.put(distance.getTown(), distance);
        }
        map.put(startTown, new NodeDistance(startTown));
        return map;
    }

}
