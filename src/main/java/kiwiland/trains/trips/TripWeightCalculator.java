/**
 * 
 */
package kiwiland.trains.trips;

import kiwiland.trains.combine.WeightCalculator;

/**
 * @author VXJ8774
 *
 */
class TripWeightCalculator implements WeightCalculator<Trip> {

    /* (non-Javadoc)
     * @see kiwiland.trains.combine.ThresholdCalculator#initiate()
     */
    @Override
    public int initiate() {
        return 0;
    }

    /* (non-Javadoc)
     * @see kiwiland.trains.combine.ThresholdCalculator#calculate(java.lang.Object)
     */
    @Override
    public int calculate(Trip element, Integer currentWeight) {
        return currentWeight + element.getDistance();
    }

}
