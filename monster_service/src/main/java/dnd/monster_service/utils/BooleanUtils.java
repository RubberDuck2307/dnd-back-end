package dnd.monster_service.utils;

public class BooleanUtils {

    /**
     * @param x        the amount of true values that need to be in the array
     * @param booleans the array of booleans
     * @return true if there are more than x true values in the array
     */
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
