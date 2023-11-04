package dnd.rest_api;

import dnd.rest_api.utils.ListUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UtilsTest {

    @Test
    public void BinarySearchTest() {
        int[] array = new int[]{0, 2, 4, 6, 8, 10};
        assertEquals(4, ListUtils.BinarySearchHighestValueSmallerThanX(array, 5));
        int[] array2 = new int[]{5, 12, 27, 47, 628};
        assertEquals(12, ListUtils.BinarySearchHighestValueSmallerThanX(array2, 15));
        int[] array3 = new int[]{50};
        assertEquals(50, ListUtils.BinarySearchHighestValueSmallerThanX(array3, 60));
        assertThrows(IllegalArgumentException.class, () -> ListUtils.BinarySearchHighestValueSmallerThanX(array3, 0));
        int[] negativeArray = new int[]{-600, -200, -100, -50, 0};
        assertEquals(-100, ListUtils.BinarySearchHighestValueSmallerThanX(negativeArray, -75));
        int[] array4 = new int[]{1, 4, 5, 6, 8, 9, 12, 34, 46, 81, 99};
        assertEquals(46, ListUtils.BinarySearchHighestValueSmallerThanX(array4, 50));
        int[] array5 = new int[]{-200, -65, -31, -19, 0, 5, 13, 42, 68, 98, 100, 1200, 3658465};
        assertEquals(3658465, ListUtils.BinarySearchHighestValueSmallerThanX(array5, 3658466));
        assertEquals(-200, ListUtils.BinarySearchHighestValueSmallerThanX(array5, -100));
        assertEquals(0, ListUtils.BinarySearchHighestValueSmallerThanX(array5, 1));
        assertEquals(100, ListUtils.BinarySearchHighestValueSmallerThanX(array5, 101));
        assertEquals(1200, ListUtils.BinarySearchHighestValueSmallerThanX(array5, 1201));
    }


}
