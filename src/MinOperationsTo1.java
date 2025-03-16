public class MinOperationsTo1 {

    // HW3 - Ex.6

    /**
     * Return minimum number of operations & sequence of operations to transform n into 1
     * Operations:
     *  - n = n-1
     *  - if n % 2 == 0 -> n = n/2
     *  - if n % 3 == 0 -> n = n/3
     */

    public static Object[] solution(int n){
        int dp[] = new int[n + 1];
        dp[1] = 0;
        int p[] = new int[n + 1];
        p[1] = -1;


        for(int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + 1;
            p[i] = i - 1;

            if(i % 2 == 0 && dp[i / 2] + 1 < dp[i]){
                dp[i] = dp[i / 2] + 1;
                p[i] = i / 2;
            }

            if(i % 3 == 0 && dp[i / 3] + 1 < dp[i]){
                dp[i] = dp[i / 3] + 1;
                p[i] = i / 3;
            }
        }

        return new Object[]{dp, p};
    }
    
    public static void main(String[] args) {
        int num = 10;
        Object[] result = solution(num);

        int D[] = (int[])result[0];
        int P[] = (int[])result[1];

        System.out.println("Tranformed " + num + " to 1 in: " + D[num] + " operations.");
        System.out.println("Sequence of operations:");
        while(num > 0){
            System.out.print(num + " -> ");
            num = P[num];
        }
        System.out.println();
    }

}
