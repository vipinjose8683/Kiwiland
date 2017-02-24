package kiwiland.trains;

import kiwiland.trains.different.TripsFinder;
import kiwiland.trains.domain.Graph;
import kiwiland.trains.shortest.ShortestRouteFinder;
import kiwiland.trains.weight.DistanceMeasure;
import kiwiland.trains.weight.StopsMeasure;

public class KiwilandTrainApp {
    
    private static final int OUTPUT_1 = 0;
    private static final int OUTPUT_2 = 1;
    private static final int OUTPUT_3 = 2;
    private static final int OUTPUT_4 = 3;
    private static final int OUTPUT_5 = 4;
    private static final int OUTPUT_6 = 5;
    private static final int OUTPUT_7 = 6;
    private static final int OUTPUT_8 = 7;
    private static final int OUTPUT_9 = 8;
    private static final int OUTPUT_10 = 9;
    
    private GraphCreator graphCreator = new GraphCreator();
    
    private ShortestRouteFinder spFinder = new ShortestRouteFinder();
    
    private TripsFinder tripsFinderByDistance = new TripsFinder(new DistanceMeasure());

    private TripsFinder tripsFinderByStops = new TripsFinder(new StopsMeasure());

    public String[] process(String inputString) {
        String[] result = new String[10];
        Graph graph = graphCreator.create(inputString);
        result[OUTPUT_6] = "" + tripsFinderByStops.find(graph,"C", 4);
        result[OUTPUT_8] = "" + spFinder.find(graph,"A","C");
        result[OUTPUT_9] = "" + spFinder.find(graph,"B","B");
        result[OUTPUT_10] = "" + tripsFinderByDistance.find(graph,"C", 30);
        // TODO Auto-generated method stub
        return result;
    }

}
