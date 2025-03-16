import java.util.ArrayList;
import java.util.List;

public class MinTripCost {

    // HW3 - Ex.2

    // You want to travel from Athens to a Destination but visit in-between cities for exploration
    // Given n cities <Π1, Π2, ..., Πn-1, Destination> and their distance
    // from athens <α1, α2, ..., αn-1, αDestination>
    // Each day you can travel maximum x kilometers
    // If you travel k kilometers in one day, the cost for that day is (x-k)^2
    // Return sequence of cities to minimize total cost

    public static Object[] solution(int[] distances, int x){
        int n = distances.length;

        int[] dist = new int[n + 1];
        dist[0] = 0;
        for(int i = 1; i <= n; i++){
            dist[i] = distances[i - 1];
        }

        int dp[] = new int[n + 1]; // d[i]: mininum cost to get from Athens to city i (i=0 is Athens)
        dp[0] = 0; // from Athens to Athens -> cost 0
        for(int i = 1; i <= n; i++){
            dp[i] = 100000000; // infinity
        }

        int[] p = new int[n + 1];
        for(int i = 0; i <= n; i++){
            p[i] = -1;
        }

        for(int i = 1; i <= n; i++){ // for each city i (final destination)
            for(int j = 0; j < i; j++){ // coming from any previous city j towards i
                int travelDistance = dist[i] - dist[j];
                if(travelDistance <= x){
                    int cost = (int) Math.pow(x - travelDistance, 2);
                    if(dp[j] + cost < dp[i]){
                        dp[i] = dp[j] + cost;
                        p[i] = j;
                    }
                }
            }
        }

        return new Object[]{dp[n], p};
    }

    public static void main(String[] args) {
        int distances[] = {4, 7, 9, 11, 20};
        int x = 10;
        Object[] result = solution(distances, x);
        int cost = (int) result[0];
        int[] sequence = (int[]) result[1];

        System.out.println("Minimum cost for trip: " + cost);

        // Print path from start to end
        List<Integer> path = new ArrayList<>();
        int current = distances.length;
        while(current != -1) {
            path.add(current);
            current = sequence[current];
        }
        
        // Print in correct order (Athens to Berlin)
        for(int i = path.size() - 1; i >= 0; i--) {
            if(path.get(i) == 0) {
                System.out.print("Athens");
            } else {
                System.out.print(" -> City" + path.get(i));
            }
        }
        System.out.println();

    }
    
}
