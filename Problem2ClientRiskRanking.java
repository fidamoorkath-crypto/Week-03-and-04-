import java.util.*;

public class Problem2ClientRiskRanking {
    static class Client {
        String id;
        int riskScore;
        double accountBalance;

        Client(String id, int riskScore, double accountBalance) {
            this.id = id;
            this.riskScore = riskScore;
            this.accountBalance = accountBalance;
        }

        @Override
        public String toString() {
            return id + ":" + riskScore;
        }
    }

    static int bubbleSortByRiskScore(List<Client> list) {
        int swaps = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).riskScore > list.get(j + 1).riskScore) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                }
            }
        }
        return swaps;
    }

    static void insertionSortByRiskDescBalance(List<Client> list) {
        for (int i = 1; i < list.size(); i++) {
            Client key = list.get(i);
            int j = i - 1;
            while (j >= 0 && (list.get(j).riskScore < key.riskScore ||
                    (list.get(j).riskScore == key.riskScore && list.get(j).accountBalance > key.accountBalance))) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    public static void main(String[] args) {
        List<Client> clients = new ArrayList<>(Arrays.asList(
                new Client("C", 80, 5000),
                new Client("A", 20, 10000),
                new Client("B", 50, 7500)
        ));

        int swaps = bubbleSortByRiskScore(clients);
        System.out.println("Bubble (asc): " + clients + " // Swaps: " + swaps);

        List<Client> clients2 = new ArrayList<>(Arrays.asList(
                new Client("C", 80, 5000),
                new Client("A", 20, 10000),
                new Client("B", 50, 7500)
        ));

        insertionSortByRiskDescBalance(clients2);
        System.out.println("Insertion (desc): " + clients2);
        System.out.println("Top 3 risks: " + clients2.subList(0, Math.min(3, clients2.size())));
    }
}

