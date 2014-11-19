package ss.week5;

import java.util.List;

public class Sorting {
    public static <T extends Comparable<T>> void bubbleSort(List<T> data) {
        int lastSwapIndex = data.size() - 1;

        while (lastSwapIndex != 0) {
            int currentSwapIndex = 0;

            for (int i = 0; i < lastSwapIndex; i++) {
                if (data.get(i).compareTo(data.get(i + 1)) > 0) {
                    swap(data, i, i + 1);
                    currentSwapIndex = i;
                }
            }

            lastSwapIndex = currentSwapIndex;
        }
    }

    private static <T> void swap(List<T> data, int index1, int index2) {
        T temp = data.get(index1);
        data.set(index1, data.get(index2));
        data.set(index2, temp);
    }
}
