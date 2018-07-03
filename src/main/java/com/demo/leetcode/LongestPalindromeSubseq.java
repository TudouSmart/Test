package com.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/description/
 */
public class LongestPalindromeSubseq {

    public int longestPalindromeSubseq(String s) {
        int[][] arr = new int[s.length()][s.length()];

        int max = 0;
        for (int i = 1; i <= s.length(); i ++) {
            for (int j = 0; j <= s.length() - i; j ++) {
                int index = j + i - 1;

                if (j == index) {
                    arr[j][index] = 1;
                } else if (s.charAt(j) == s.charAt(index)) {
                    arr[j][index] = arr[j+1][index-1] + 2;
                } else {
                    arr[j][index] = Math.max(arr[j + 1][index], arr[j][index - 1]);
                }

                max = Math.max(max, arr[j][index]);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println((new LongestPalindromeSubseq().longestPalindromeSubseq("bbbab")));
        System.out.println(new ArrayList<>(10).toString());
    }
}
