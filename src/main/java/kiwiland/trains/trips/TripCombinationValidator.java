package kiwiland.trains.trips;

import kiwiland.trains.combine.Validator;

class TripCombinationValidator implements Validator<Trip> {
    
    private Integer maxWeight;

    public TripCombinationValidator(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }

    @Override
    public boolean validate(Integer threshold) {
        return threshold < maxWeight;
    }


}
