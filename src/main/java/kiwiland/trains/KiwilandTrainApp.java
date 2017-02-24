package kiwiland.trains;

import kiwiland.trains.domain.Graph;
import kiwiland.trains.shortest.ShortestRouteFinder;
import kiwiland.trains.trips.CycleTripsFinder;
import kiwiland.trains.trips.TripsFinder;
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
    private static final int OUTPUT_11 = 10;

    private GraphCreator graphCreator = new GraphCreator();

    private ShortestRouteFinder spFinder = new ShortestRouteFinder();

    private CycleTripsFinder tripsFinderByDistance = new CycleTripsFinder(new DistanceMeasure());

    private CycleTripsFinder tripsFinderByStops = new CycleTripsFinder(new StopsMeasure());

    private TripsFinder tripsWithoutCycle = new TripsFinder();

    private TripCalculator calculator = new TripCalculator();

    public String[] process(String inputString) {
        String[] result = new String[10];
        Graph graph = graphCreator.create(inputString);
        result[OUTPUT_1] = "" + calculator.calculate(graph, "ABC");
        result[OUTPUT_2] = "" + calculator.calculate(graph, "AD");
        result[OUTPUT_3] = "" + calculator.calculate(graph, "ADC");
        result[OUTPUT_4] = "" + calculator.calculate(graph, "AEBCD");
        result[OUTPUT_5] = "" + calculator.calculate(graph, "AED");
        result[OUTPUT_6] = "" + tripsFinderByStops.find(graph, "C", 4);
        result[OUTPUT_7] = "" + tripsWithoutCycle.find(graph, "A", "C", 4);
        result[OUTPUT_8] = "" + spFinder.find(graph, "A", "C");
        result[OUTPUT_9] = "" + spFinder.find(graph, "B", "B");
        result[OUTPUT_10] = "" + tripsFinderByDistance.find(graph, "C", 30);
//        TripsFinder2 finder2 = new TripFinder2(graph, new DistanceMeasure(), 30);
//        result[OUTPUT_11] = "" + finder2.find("A", "C");
        return result;
    }

}
