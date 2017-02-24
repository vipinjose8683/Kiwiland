package kiwiland.trains.combine;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Finds out various combinations that fit the criteria for the given set of inputs.
 *
 */
public class Permutation<T> {
    
    /**
     * Combines the input to the result so far
     */
    private Combiner<T> combiner;
    
    /**
     * The object that checks whether the given combination is valid
     */
    private Validator validator;
    
    /**
     * Calculates the threshold
     */
    private WeightCalculator<T> weightCalculator;
    
    /**
     * Creates the instance 
     * 
     * @param tripCombinationValidator
     * @param tripCombiner
     */
    public Permutation(Validator validator, Combiner<T> tripCombiner, WeightCalculator<T> thresholdCalculator) {
        super();
        this.combiner = tripCombiner;
        this.validator = validator;
        this.weightCalculator = thresholdCalculator;
    }

    public Set<String> get(Set<T> elements) {
        Deque<T> combinedElements = new LinkedList<>();
        Set<String> allCombinations = new HashSet<>();
        Integer currentWeight = weightCalculator.initiate();
        allCombinations = getCombinations(elements, currentWeight, allCombinations, combinedElements);
        return allCombinations;
    }

    private Set<String> getCombinations(Set<T> elements, Integer currentWeight, Set<String> allCombinations, Deque<T> combinedElements) {
        for(T element : elements) {
            combinedElements.push(element);
            Integer newWeight = weightCalculator.calculate(element, currentWeight);
            if (this.validator.validate(newWeight)) {
                String combination = combiner.combine(combinedElements);
                allCombinations.add(combination);
                allCombinations = getCombinations(elements, newWeight, allCombinations, combinedElements);
            }
            combinedElements.pop();
        }
        return allCombinations;
    }
    
    


}
