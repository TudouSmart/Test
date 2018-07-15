package com.demo.leetcode;

import java.util.Arrays;

public class SingleNumber {
    public int[] singleNumber(int[] nums) {
        int n = 0;
        for (int num : nums) {
            n ^= num;
        }

        int tmp = n;
        int k = 1;
        if (tmp != 0) {
            while ((k & tmp) == 0) {
                k <<= 1;
            }
        }

        int n1 = 0;
        int n2 = 0;
        for (int num : nums) {
            if ((num & k) == 0) {
                n1 ^= num;
            } else {
                n2 ^= num;
            }
        }

        return new int[]{n1, n2};
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,1,3,2,5};
        System.out.println(Arrays.toString((new SingleNumber()).singleNumber(arr)));
    }
}
