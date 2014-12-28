package ss.week7;

import java.util.Random;

public class QuickSort {
    public static void qsort(int[] a) {
        qsort(a, 0, a.length - 1);
    }

    public static void qsort(int[] a, int first, int last) {
        //if (first < last) {
        //    int position = partition(a, first, last);
        //    qsort(a, first, position - 1);
        //    qsort(a, position + 1, last);
        //}
        Thread thread = new QSortThread(a, first, last);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int partition(int[] a, int first, int last) {

        int mid = (first + last) / 2;
        int pivot = a[mid];
        swap(a, mid, last); // put pivot at the end of the array
        int pi = first;
        int i = first;
        while (i != last) {
            if (a[i] < pivot) {
                swap(a, pi, i);
                pi++;
            }
            i++;
        }
        swap(a, pi, last); // put pivot in its place "in the middle"
        return pi;
    }

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        int[] test = new int[4096];
        Random random = new Random();

        for (int i = 0; i < test.length; i++) {
            test[i] = random.nextInt();
        }

        QuickSort.qsort(test);

        int last = -1;
        boolean isFirst = true;
        for (int i : test) {
            if (isFirst) {
                isFirst = false;
            } else {
                if (last > i) {
                    throw new RuntimeException("BAD");
                }
            }

            System.out.println(i);
            last = i;
        }
    }
}

class QSortThread extends Thread {
    private final int[] a;
    private final int first;
    private final int last;

    QSortThread(int[] argA, int argFirst, int argLast) {
        this.a = argA;
        this.first = argFirst;
        this.last = argLast;
    }

    @Override
    public void run() {
        if (first < last) {
            int position = QuickSort.partition(a, first, last);
            QuickSort.qsort(a, first, position - 1);
            QuickSort.qsort(a, position + 1, last);
        }
    }
}
