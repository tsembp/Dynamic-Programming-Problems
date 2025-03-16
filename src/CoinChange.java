import java.util.Arrays;

public class CoinChange {

    // Example from lectures

    public static Object[] solution(int coins[], int amount){
        int K[] = new int[amount + 1]; // minimum number of coins for K[i] amount
        Arrays.fill(K, amount + 1); // at most 'amount' coins (1s coins)
        K[0] = 0;
        int B[] = new int[amount + 1]; // coin for B[i] amount

        for(int j = 1; j <= amount; j++){
            for(int ci : coins){
                if(j >= ci && K[j] > 1 + K[j - ci]){
                    K[j] = 1 + K[j - ci];
                    B[j] = ci;
                }
            }
        }

        return new Object[]{K, B};
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        int[] coins = {1, 10, 25};
        int amount = 30;
        Object[] result = solution(coins, amount);

        int[] K = (int[])result[0];
        int[] B = (int[])result[1];

        while(amount > 0){
            System.out.println("Used " + B[amount]);
            amount = amount - B[amount];
        }
    }
}
