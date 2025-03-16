import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KayakRental {

    // - Given n rental stations for kayaks
    // - For two stations i,j so that 1 <= i < j <= n, you are given the rent cost to rent 
    // a kayak from i and leave it at j, let cost(i,j)
    // - You start from station 1 and have to finish at n
    // - Goal: Return rental stations used to minimize rent cost

    public static Object[] solution(int costs[][]){
        int n = costs.length - 1;

        int[] dp = new int[n + 1]; // dp[i]: minimum cost to reach station i
        dp[1] = 0; // starting point

        int[] p = new int[n + 1]; // p[i]: station visited before i
        p[1] = -1;

        // initialization dp[] = INF p[] = -1
        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            p[i] = -1;
        }

        for(int i = 2; i <= n; i++){
            for(int j = 1; j < i; j++){
                // costs[j][i]: cost to go from j to i
                if(costs[j][i] != 0 && dp[j] != Integer.MAX_VALUE){
                    int cost = dp[j] + costs[j][i];
                    if(cost < dp[i]){
                        dp[i] = cost;
                        p[i] = j;
                    }
                }
            }
        }

        // dp[n] holds minimum cost to go from 1->n
        List<Integer> path = new ArrayList<>();
        int current = n;
        while(current != -1){
            path.add(current);
            current = p[current];
        }

        Collections.reverse(path);

        return new Object[]{path, dp[n]};
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int[][] cost = {
            {0, 0, 0, 0, 0, 0},  
            {0, 0, 5, 10, 5, 20},   // Costs from station 1
            {0, 0, 0, 4, 8, 15},    // Costs from station 2
            {0, 0, 0, 0, 3, 10},    // Costs from station 3
            {0, 0, 0, 0, 0, 6},     // Costs from station 4
            {0, 0, 0, 0, 0, 0}      // Station 5 (destination, no outgoing cost)
        };

        Object[] result = solution(cost);

        List<Integer> path = (List<Integer>)result[0];
        int minCost = (int)result[1];

        System.out.println("Minimum cost: " + minCost);
        System.out.println("Stations path: " + path);
    }
    
}
