package ss.week5;

import java.util.HashMap;
import java.util.HashSet;
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

    //@ requires f != null;
    public static <K, V> Map<V, Set<K>> inverse(Map<K, V> f) {
        // Result must be a map of <V, Set<K>> as several keys might
        // be mapped to the same value.
        Map<V, Set<K>> result = new HashMap<>();

        for (Map.Entry<K, V> entry : f.entrySet()) {
            if (!result.containsKey(entry.getValue())) {
                result.put(entry.getValue(), new HashSet<K>());
            }

            result.get(entry.getValue()).add(entry.getKey());
        }

        return result;
    }

    //@ requires f != null;
    public static <K, V> Map<V, K> inverseBijection(Map<K, V> f) {
        Map<V, K> result = new HashMap<>();

        for (Map.Entry<K, V> entry : f.entrySet()) {
            result.put(entry.getValue(), entry.getKey());
        }

        return result;
    }
}
