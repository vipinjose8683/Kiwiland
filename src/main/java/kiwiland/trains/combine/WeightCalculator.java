package kiwiland.trains.combine;

/**
 * Initiates and calculates the threshold of combination
 *
 */
public interface WeightCalculator<T> {

    int initiate();
    
    int calculate(T element, Integer currentWeight);

}
