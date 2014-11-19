package ss.week5;

import java.util.LinkedList;
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

    /**
     * Sorts the data using the mergesort algorithm.
     * @param data The data to sort.
     */
    public static <T extends Comparable<T>> void mergeSort(List<T> data) {
        if (data.size() > 1) {
            int mid = data.size() / 2;
            List<T> segmentL = getRange(data, 0, mid);
            List<T> segmentR = getRange(data, mid, data.size());

            mergeSort(segmentL);
            mergeSort(segmentR);

            int iL = 0;
            int iR = 0;

            for (int i = 0; i < data.size(); i++) {
                if (iL >= segmentL.size()) {
                    data.set(i, segmentR.get(iR++));
                } else if (iR >= segmentR.size()) {
                    data.set(i, segmentL.get(iL++));
                } else if (segmentL.get(iL).compareTo(segmentR.get(iR)) > 0) {
                    data.set(i, segmentL.get(iL++));
                } else {
                    data.set(i, segmentR.get(iR++));
                }
            }
        }
    }

    private static <T> List<T> getRange(List<T> data, int start, int end) {
        List<T> result = new LinkedList<T>();

        for (int i = start; i < end; i++) {
            result.add(data.get(i));
        }

        return result;
    }
}
