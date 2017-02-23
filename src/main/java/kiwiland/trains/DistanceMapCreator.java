package kiwiland.trains;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DistanceMapCreator {

    public Map<Node, NodeDistance> create(List<NodeDistance> unvisitedTowns, Node startTown) {
        Map<Node, NodeDistance> map = new HashMap<>();
        for (NodeDistance distance : unvisitedTowns) {
            map.put(distance.getTown(), distance);
        }
        map.put(startTown, new NodeDistance(startTown));
        return map;
    }

}
