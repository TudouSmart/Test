package com.demo.leetcode;

/*
https://leetcode-cn.com/problems/target-sum/description/
 */
public class FindTargetSumWays {
    public static int findTargetSumWays(int[] nums, int s) {
        int n = 0;
        for (int k : nums) {
            n += k;
        }

        return n < s || ((n + s % 2 == 1)) ? 0 : subsetSum(nums, (n + s) >>> 1);
    }

    public static int subsetSum(int[] nums, int target) {
        int[] dp = new int[target + 1];

        dp[0] = 1;

        for (int n : nums) {
            for (int i = target; i >= n; i --) {
                dp[i] += dp[i-n];
            }
        }

        return dp[target];
    }

    public static void main1(String[] args) {
        int[] arr = new int[] {1,1,1,1,1};
        System.out.println(subsetSum(arr, 4));
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 1, 1, 1, 1};
        System.out.println(findTargetSumWays(arr, 3));
    }
}
