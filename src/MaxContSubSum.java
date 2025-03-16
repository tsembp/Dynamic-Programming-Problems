public class MaxContSubSum {

    // LeetCode: https://leetcode.com/problems/maximum-subarray/description/

    public static int solution(int nums[]){
        int d[] = new int[nums.length]; // array to store max sum up until index i
        d[0] = nums[0];
        int maxSum = d[0];

        for(int i = 1; i < nums.length; i++){
            int current = d[i - 1] + nums[i];

            d[i] = Math.max(nums[i], current);

            if(d[i] > maxSum) maxSum = d[i];
        }

        return maxSum;
    }
    
    public static void main(String[] args) {
        int nums[] = {5, 15, -30, 10, -5, 40, 10};
        System.out.println("Solution:" + solution(nums));
    }

}
