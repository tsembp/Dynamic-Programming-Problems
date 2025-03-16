import java.util.Arrays;

public class MaxPopulationStations {

    // HW3 - Ex.4
    
    // - Given a sequence A=<a1, a2, ..., an> where ai:popilation of village i
    // - We want to place cellphone stations in villages BUT two adjacent villages cannot have stations and
    // each station covers needs of the village it has been placed in
    // - We want to maximize the number of civilians that will have access to cell service

    public static boolean[] solution(int A[]){
        int n = A.length;

        // selected[i]: true when i village has cell
        boolean[] selected = new boolean[n];
        Arrays.fill(selected, false);

        if(n == 0) return selected;
        if(n == 1) {
            selected[0] = true;
            return selected;
        }

        // dp[i]: maximum population we can cover up until village i (adhering to rules)
        int[] dp = new int[n];
        dp[0] = A[0];
        dp[1] = Math.max(A[0], A[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + A[i]);
        }

        int i = n - 1;
        while (i >= 0) {
            if (i == 0) { // this means that i=1 village was not selected (see if statement below so i=i-1 and 0th village is selected)
                selected[i] = true;
                break;
            }
            if (i == 1) {
                // For the first two villages, choose the one with the higher population.
                if (A[1] > A[0]) {
                    selected[1] = true;
                } else {
                    selected[0] = true;
                }
                break;
            }
            // If dp[i] equals dp[i-1], village i was not chosen.
            if (dp[i] == dp[i-1]) {
                i = i - 1;
            } else {
                // Otherwise, village i was chosen.
                selected[i] = true;
                i = i - 2;  // skip the adjacent village
            }
        }

        System.out.println("Exiting algorithm having " + dp[n - 1] + " satisfied villagers");

        return selected;
    }

    public static void main(String[] args) {
        int[] A = {12, 20, 15, 30, 25};

        boolean[] villages = solution(A);

        System.out.println("Placement of stations at villages: ");
        for(int i = 0; i < A.length; i++){
            if(villages[i]){
                System.out.println("Village: " + i);
            }
        }
    }

}
