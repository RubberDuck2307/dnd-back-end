package dnd.monster_service.utils;

import java.util.List;
import java.util.Random;

public class ListUtils {

    private static final Random random = new Random();

    public static Integer BinarySearchHighestValueSmallerThanX(int[] array, Integer x) {

        if (array.length == 0) {
            throw new IllegalArgumentException("The array is empty");
        }
        if (array[0] > x) {
            throw new IllegalArgumentException("There is no number bigger than x");
        }
        int i = 0, j = array.length - 1;
        int index = 0;
        int lastValue = array[i + (j - i) / 2];
        int currentValue;
        while (i <= j) {
            index = i + (j - i) / 2;
            currentValue = array[index];
            if (currentValue == x)
                return array[index];
            else if (currentValue > x) {
                j = index - 1;
            } else {
                i = index + 1;
            }
            if (Math.abs(x - currentValue) < Math.abs(lastValue - x) && currentValue < x)
                lastValue = currentValue;
        }
        return lastValue;
    }

    public static <T> T getRandomElement(List<T> list) {
        int size = list.size();
        int item = random.nextInt(size);
        return list.get(item);
    }
}
