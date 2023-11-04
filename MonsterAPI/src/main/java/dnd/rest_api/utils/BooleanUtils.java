package dnd.rest_api.utils;

public class BooleanUtils {
    public static boolean isMoreThanXTrue(int x, boolean... booleans) {
        int count = 0;
        for (boolean b : booleans) {
            if (b) {
                count++;
            }
        }
        return count > x;
    }
}