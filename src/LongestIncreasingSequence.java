public class LongestIncreasingSequence {
    
    public int solution(int[] nums){
        int dp[] = new int[nums.length]; // length of longest seq ending at i
        dp[0] = 1;
        int maxLength = 1;

        for(int i = 1; i < nums.length; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                // we want to ensure that the i-th number is greater than the j-th number
                //  since j is iterating through the numbers that are less than i
                
                // we want to check if adding the i-th number to the sequence of dp[j]
                //  constructs a larger increasing sequence 
                if(nums[i] > nums[j] && dp[i] < dp[j] + 1){
                    dp[i] = dp[j] + 1;
                }
            }

            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        
    }
}
