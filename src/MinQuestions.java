public class MinQuestions {

    // Given n questions (1...n) in form of (mi, li) where mi:points li:time for question
    // return minimum number of minutes to achieve 'B' points in the exam

    // Define a very large number to represent "infinity" (an unreachable state)
    private static final int INF = Integer.MAX_VALUE;

    public static int solution(int[] m, int[] l, int B) {
        int n = m.length;
        
        // maximum possible points
        int sumM = 0;
        for (int points : m) {
            sumM += points;
        }

        // dp[i][v]: minimum time to get exactly v points using the first i questions
        int[][] dp = new int[n + 1][sumM + 1];

        // Base case
        // No questions -> 0 points in 0 minutes.
        dp[0][0] = 0;
        // For any positive score with no questions, set to INF (impossible)
        for (int v = 1; v <= sumM; v++) {
            dp[0][v] = INF;
        }

        for (int i = 1; i <= n; i++) {
            int points = m[i - 1];
            int time = l[i - 1];
            
            for (int v = 0; v <= sumM; v++) {
                // Option 1: Dont solve current question i.
                dp[i][v] = dp[i - 1][v];
                
                // Option 2: Solve question i, if we have dont overload grade
                if (v >= points) {
                    dp[i][v] = Math.min(dp[i][v], dp[i - 1][v - points] + time);
                }
            }
        }

        // Find the minimum time among all ways to achieve at least B points.
        int answer = INF;
        for (int v = B; v <= sumM; v++) {
            answer = Math.min(answer, dp[n][v]);
        }
        
        return (answer == INF ? -1 : answer);
    }

    public static void main(String[] args) {
        int m[] = {20, 40, 10, 30};
        int l[] = {5, 20, 2, 6};
        int B = 50;

        int result = solution(m, l, B);

        System.out.println("Min. Minutes to achieve " + B + "/100 : " + result);
    }
}
