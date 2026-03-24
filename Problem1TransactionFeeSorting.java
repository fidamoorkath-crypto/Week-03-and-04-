import java.util.*;

public class Problem1TransactionFeeSorting {
    static class Transaction {
        String id;
        double fee;
        String timestamp;

        Transaction(String id, double fee, String timestamp) {
            this.id = id;
            this.fee = fee;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return id + ":" + fee + "@" + timestamp;
        }
    }

    static class SortMetrics {
        int passes;
        int swaps;

        SortMetrics(int passes, int swaps) {
            this.passes = passes;
            this.swaps = swaps;
        }
    }

    static SortMetrics bubbleSortByFee(List<Transaction> list) {
        int n = list.size();
        int swaps = 0;
        int passes = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return new SortMetrics(passes, swaps);
    }

    static void insertionSortByFeeAndTimestamp(List<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;
            while (j >= 0 && (list.get(j).fee > key.fee ||
                    (list.get(j).fee == key.fee && list.get(j).timestamp.compareTo(key.timestamp) > 0))) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    static List<Transaction> highFeeOutliers(List<Transaction> list, double threshold) {
        List<Transaction> outliers = new ArrayList<>();
        for (Transaction t : list) {
            if (t.fee > threshold) outliers.add(t);
        }
        return outliers;
    }

    public static void main(String[] args) {
        List<Transaction> tx = new ArrayList<>(Arrays.asList(
            new Transaction("id1", 10.5, "10:00"),
            new Transaction("id2", 25.0, "09:30"),
            new Transaction("id3", 5.0, "10:15")
        ));

        SortMetrics m = bubbleSortByFee(tx);
        System.out.println("BubbleSort (fees): " + tx + " // " + m.passes + " passes, " + m.swaps + " swaps");

        List<Transaction> tx2 = new ArrayList<>(Arrays.asList(
            new Transaction("id1", 10.5, "10:00"),
            new Transaction("id2", 25.0, "09:30"),
            new Transaction("id3", 5.0, "10:15")
        ));
        insertionSortByFeeAndTimestamp(tx2);
        System.out.println("InsertionSort (fee+ts): " + tx2);
        System.out.println("High-fee outliers: " + highFeeOutliers(tx2, 50.0));
    }
}

