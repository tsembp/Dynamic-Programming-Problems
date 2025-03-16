public class MaxStickCutCost {

    // Assume you have a stick that can be cut as so:
    // Μήκος i (cm) 1 2 3 4 5  6  7  8  9  10
    // Τιμή τi (€)  1 5 8 9 10 17 17 20 24 30
    // We want to find the best way to cut the stick to maximize the total cost we get

    public static int solution(int n, int[] cost){

        int[] dp = new int[n + 1]; // dp[i]: max cost if we cut at i
        for(int i = 0; i <= n; i++){
            dp[i] = Integer.MIN_VALUE;
        }
        dp[0] = 0;

        for(int i = 1; i <= n; i++ ){
            for(int j = 1; j <= i; j++){
                // Max of leaving it as it is or cut it at j -> cost of remaining + cost of j piece
                dp[i] = Math.max(dp[i], dp[i - j] + cost[j - 1]);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 4;
        int cost[] = {1,5,8,9};
        System.out.println("Max cost: " + solution(n, cost));
    }
    
}
