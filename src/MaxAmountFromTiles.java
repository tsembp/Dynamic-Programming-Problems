public class MaxAmountFromTiles {

    // HW3 - Ex.7

    // Similar to Leetcode 64 Minimum Path Sum (but for max here)

    // Given an n*m grid where each tile may have an amount of money stored
    // Return the maximum amount of money someone can collect and which route he followed

    public static Object[] solution(int[][] P){
        int n = P.length;
        int m = P[0].length;
        int dp[][] = new int[n][m]; // dp[i][j]: max amount collected up until (i,j) tile
        dp[0][0] = P[0][0];

        int[][] prev = new int[n][m];   // 0: best route came from left (i, j - 1)
        prev[0][0] = -1;                // 1: best route came from up (i - 1, j)

        // Initialize 1st column
        for(int i = 1; i < n; i++){
            dp[i][0] = dp[i - 1][0] + P[i][0];
            prev[i][0] = 1;
        }
        
        // Initialize 1st row
        for(int j = 1; j < m; j++){
            dp[0][j] = dp[0][j - 1] + P[0][j];
            prev[0][j] = 0;
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                int left = dp[i][j - 1];
                int top = dp[i - 1][j];
                if (left >= top) {
                    dp[i][j] = left + P[i][j];
                    prev[i][j] = 0;
                } else {
                    dp[i][j] = top + P[i][j];
                    prev[i][j] = 1;
                }
            }
        }

        return new Object[]{dp[n - 1][m - 1], prev};
    }

    public static java.util.List<int[]> getPath(int[][] prev) {
        java.util.List<int[]> path = new java.util.ArrayList<>();
        int i = prev.length - 1;
        int j = prev[0].length - 1;
        // Add the final cell
        path.add(new int[]{i, j});
        while (!(i == 0 && j == 0)) {
            if (prev[i][j] == 0) {
                // Came from left: move left
                j = j - 1;
            } else { // prev[i][j] == 1
                // Came from top: move up
                i = i - 1;
            }
            path.add(new int[]{i, j});
        }
        // Reverse the list so that path starts from (0,0)
        java.util.Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        int[][] P = {
            {0,  0,  0,  0,  20, 0},
            {0, 10, 0,  15,  0,  0},
            {0,  0,  0,  6,  0,  2},
            {0,  0,  8,  0, 0,  10}, 
            {4,  0,  0,  0,  20, 0}
        };

        Object[] result = solution(P);
        int maxAmount = (int)result[0];
        int[][] prev = (int[][])result[1];

        System.out.println("Max amount collected: " + maxAmount);
        System.out.println("Path grid:");
        // Print the path
        java.util.List<int[]> path = getPath(prev);
        System.out.println("Path from (0,0) to (" + (P.length-1) + "," + (P[0].length-1) + "):");
        for (int[] cell : path) {
            System.out.print("(" + cell[0] + "," + cell[1] + ") ");
        }
        System.out.println();
    }
    
}
