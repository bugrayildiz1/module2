package ss.week4;

public class ArrayExercises {
    public static int numberOfNegativeElements(int[] values) {
        int result = 0;

        for (int value : values) {
            if (value < 0) {
                result++;
            }
        }

        return result;
    }

    public static void reverseElements(int[] elements) {
        int i2 = elements.length - 1;

        for (int i = 0; i < elements.length / 2; i++) {
            int temp = elements[i];

            elements[i] = elements[i2];
            elements[i2] = temp;

            i2--;
        }
    }
}
