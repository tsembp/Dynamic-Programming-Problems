import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuadAscendingSeq {

    // HW3 - Ex.8
    
    public static int solution(int nums[]){
        int dp[] = new int[nums.length];
        dp[0] = 1;
        int[] p = new int[nums.length];
        p[0] = -1;
        int maxLength = 1;
        int endIndex = 0;

        for(int i = 1; i < nums.length; i++){
            dp[i] = 1;
            p[i] = -1;
            for(int j = 0; j < i; j++){
                if(nums[i] > 4 * nums[j] && dp[i] < dp[j] + 1){
                    dp[i] = dp[j] + 1;
                    p[i] = j;
                }
            }

            if(dp[i] > maxLength){
                maxLength = dp[i];
                endIndex = i;
            }
        }

        List<Integer> seq = new ArrayList<>();
        while(endIndex != -1){
            seq.add(nums[endIndex]);
            endIndex = p[endIndex];
        }

        Collections.reverse(seq);

        return maxLength;
    }

    public static void main(String[] args) {
        int nums[] = {1, 40, 12, 20, 50, 28, 401, 1612, 480};
        System.out.println("Largest Quad-Increasing Sequence has length: " + solution(nums));
    }
}
