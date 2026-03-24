import java.util.*;

public class Problem3TradeVolumeAnalysis {
    static class Trade {
        String id;
        int volume;

        Trade(String id, int volume) {
            this.id = id;
            this.volume = volume;
        }

        @Override
        public String toString() {
            return id + ":" + volume;
        }
    }

    static void mergeSort(List<Trade> list) {
        if (list.size() <= 1) return;
        int mid = list.size() / 2;
        List<Trade> left = new ArrayList<>(list.subList(0, mid));
        List<Trade> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSort(left);
        mergeSort(right);

        list.clear();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).volume <= right.get(j).volume) {
                list.add(left.get(i++));
            } else {
                list.add(right.get(j++));
            }
        }
        while (i < left.size()) list.add(left.get(i++));
        while (j < right.size()) list.add(right.get(j++));
    }

    static int partitionDesc(List<Trade> list, int low, int high) {
        int mid = low + (high - low) / 2;
        Trade pivot = list.get(mid);
        Collections.swap(list, mid, high);
        int store = low;
        for (int i = low; i < high; i++) {
            if (list.get(i).volume > pivot.volume) {
                Collections.swap(list, i, store);
                store++;
            }
        }
        Collections.swap(list, store, high);
        return store;
    }

    static void quickSortDesc(List<Trade> list, int low, int high) {
        if (low < high) {
            int p = partitionDesc(list, low, high);
            quickSortDesc(list, low, p - 1);
            quickSortDesc(list, p + 1, high);
        }
    }

    static List<Trade> mergeLists(List<Trade> a, List<Trade> b) {
        List<Trade> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            if (a.get(i).volume <= b.get(j).volume) merged.add(a.get(i++));
            else merged.add(b.get(j++));
        }
        while (i < a.size()) merged.add(a.get(i++));
        while (j < b.size()) merged.add(b.get(j++));
        return merged;
    }

    static long totalVolume(List<Trade> list) {
        long sum = 0;
        for (Trade t : list) sum += t.volume;
        return sum;
    }

    public static void main(String[] args) {
        List<Trade> trades = new ArrayList<>(Arrays.asList(
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        ));

        mergeSort(trades);
        System.out.println("MergeSort: " + trades);

        List<Trade> trades2 = new ArrayList<>(Arrays.asList(
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        ));
        quickSortDesc(trades2, 0, trades2.size() - 1);
        System.out.println("QuickSort (desc): " + trades2);

        List<Trade> morning = new ArrayList<>(Arrays.asList(new Trade("t1", 100), new Trade("t2", 210)));
        List<Trade> afternoon = new ArrayList<>(Arrays.asList(new Trade("t3", 150), new Trade("t4", 400)));
        mergeSort(morning);
        mergeSort(afternoon);
        List<Trade> merged = mergeLists(morning, afternoon);
        System.out.println("Merged morning+afternoon total: " + totalVolume(merged));
    }
}

