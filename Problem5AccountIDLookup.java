import java.util.*;

public class Problem5AccountIDLookup {

    static int linearFirstIndex(String[] ids, String target) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i].equals(target)) return i;
        }
        return -1;
    }

    static int linearLastIndex(String[] ids, String target) {
        int idx = -1;
        for (int i = 0; i < ids.length; i++) {
            if (ids[i].equals(target)) idx = i;
        }
        return idx;
    }

    static int binarySearchIndex(String[] ids, String target) {
        int low = 0, high = ids.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = ids[mid].compareTo(target);
            if (cmp == 0) return mid;
            if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    static int countOccurrences(String[] ids, String target) {
        int idx = binarySearchIndex(ids, target);
        if (idx == -1) return 0;
        int count = 1;
        int left = idx - 1;
        while (left >= 0 && ids[left].equals(target)) { count++; left--; }
        int right = idx + 1;
        while (right < ids.length && ids[right].equals(target)) { count++; right++; }
        return count;
    }

    public static void main(String[] args) {
        String[] logs = new String[]{"accB", "accA", "accB", "accC"};
        Arrays.sort(logs);

        System.out.println("Sorted logs: " + Arrays.toString(logs));
        System.out.println("Linear first accB: " + linearFirstIndex(logs, "accB"));
        System.out.println("Linear last accB: " + linearLastIndex(logs, "accB"));
        System.out.println("Binary accB: " + binarySearchIndex(logs, "accB") + ", count=" + countOccurrences(logs, "accB"));
    }
}

