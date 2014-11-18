package ss.week5;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapUtils {

    //@ requires f != null;
    public static <K, V> boolean isOneOnOne(Map<K, V> f) {
        Map<V, Integer> countMap = new HashMap<V, Integer>();

        for (V value : f.values()) {
            if (!countMap.containsKey(value)) {
                countMap.put(value, 0);
            }

            countMap.put(value, countMap.get(value) + 1);
        }

        for (Integer valueCount : countMap.values()) {
            if (valueCount > 1) {
                return false;
            }
        }

        return true;
    }

    //@ requires f != null && range != null;
    public static <K, V> boolean isSurjectiveOnRange(Map<K, V> f, Set<V> range) {
        for (V element : range) {
            if (!f.values().contains(element)) {
                return false;
            }
        }

        return true;
    }
}
