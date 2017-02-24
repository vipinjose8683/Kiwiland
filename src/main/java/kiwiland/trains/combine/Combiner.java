package kiwiland.trains.combine;

import java.util.Collection;

public interface Combiner<T> {
    
    String combine(Collection<T> elements);

}
