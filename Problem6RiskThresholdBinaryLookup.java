import java.util.*;

public class Problem6RiskThresholdBinaryLookup {

    static int linearSearchBand(int[] bands, int target) {
        for (int i = 0; i < bands.length; i++) {
            if (bands[i] == target) return i;
        }
        return -1;
    }

    static int binaryFloor(int[] bands, int target) {
        int low = 0, high = bands.length - 1;
        int floor = -1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (bands[mid] == target) return bands[mid];
            if (bands[mid] < target) {
                floor = bands[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return floor;
    }

    static int binaryCeiling(int[] bands, int target) {
        int low = 0, high = bands.length - 1;
        int ceil = -1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (bands[mid] == target) return bands[mid];
            if (bands[mid] > target) {
                ceil = bands[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ceil;
    }

    public static void main(String[] args) {
        int[] bands = {10, 25, 50, 100};
        System.out.println("Linear find 30: " + linearSearchBand(bands, 30));
        System.out.println("Binary floor 30: " + binaryFloor(bands, 30));
        System.out.println("Binary ceiling 30: " + binaryCeiling(bands, 30));
    }
}
