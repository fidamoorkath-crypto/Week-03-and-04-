import java.util.*;

public class Problem4PortfolioReturnSorting {
    static class Asset {
        String symbol;
        double returnRate;
        double volatility;

        Asset(String symbol, double returnRate, double volatility) {
            this.symbol = symbol;
            this.returnRate = returnRate;
            this.volatility = volatility;
        }

        @Override
        public String toString() {
            return symbol + ":" + returnRate + "%";
        }
    }

    static void mergeSortByReturn(List<Asset> list) {
        if (list.size() <= 1) return;
        int mid = list.size() / 2;
        List<Asset> left = new ArrayList<>(list.subList(0, mid));
        List<Asset> right = new ArrayList<>(list.subList(mid, list.size()));

        mergeSortByReturn(left);
        mergeSortByReturn(right);

        list.clear();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).returnRate <= right.get(j).returnRate) {
                list.add(left.get(i++));
            } else {
                list.add(right.get(j++));
            }
        }
        while (i < left.size()) list.add(left.get(i++));
        while (j < right.size()) list.add(right.get(j++));
    }

    static int partition(List<Asset> list, int low, int high) {
        int mid = low + (high - low) / 2;
        Asset pivot = list.get(mid);
        Collections.swap(list, mid, high);
        int store = low;
        for (int i = low; i < high; i++) {
            Asset cur = list.get(i);
            boolean less = cur.returnRate > pivot.returnRate ||
                    (cur.returnRate == pivot.returnRate && cur.volatility < pivot.volatility);
            if (less) {
                Collections.swap(list, i, store);
                store++;
            }
        }
        Collections.swap(list, store, high);
        return store;
    }

    static void quickSortByReturnDescVolAsc(List<Asset> list, int low, int high) {
        if (low < high) {
            int p = partition(list, low, high);
            quickSortByReturnDescVolAsc(list, low, p - 1);
            quickSortByReturnDescVolAsc(list, p + 1, high);
        }
    }

    public static void main(String[] args) {
        List<Asset> assets = new ArrayList<>(Arrays.asList(
                new Asset("AAPL", 12, 1.5),
                new Asset("TSLA", 8, 2.2),
                new Asset("GOOG", 15, 1.2)
        ));

        mergeSortByReturn(assets);
        System.out.println("Merge: " + assets);

        List<Asset> assets2 = new ArrayList<>(Arrays.asList(
                new Asset("AAPL", 12, 1.5),
                new Asset("TSLA", 8, 2.2),
                new Asset("GOOG", 15, 1.2)
        ));
        quickSortByReturnDescVolAsc(assets2, 0, assets2.size() - 1);
        System.out.println("Quick (desc): " + assets2);
    }
}

