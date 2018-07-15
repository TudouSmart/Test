package com.demo.leetcode;

import java.util.Arrays;

public class ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        double[] arr = new double[6];
        arr[0] = distinct(p1, p2);
        arr[1] = distinct(p1, p3);
        arr[2] = distinct(p1, p4);
        arr[3] = distinct(p2, p3);
        arr[4] = distinct(p2, p4);
        arr[5] = distinct(p3, p4);
        Arrays.sort(arr);

        if (arr[0] == arr[1] &&  arr[1] == arr[2] && arr[2] == arr[3] && arr[4] == arr[5]) {
            if (arr[0] < arr[4]) {
                return true;
            }
        }

        return false;
    }

    public double distinct(int[] a, int[] b) {
        return Math.pow(b[1] - a[1], 2) + Math.pow(b[0] - a[0], 2);
    }

    public static void main(String[] args) {
        int[] p1 = new int[]{0, 0};
        int[] p2 = new int[]{-1, 0};
        int[] p3 = new int[]{1, 0};
        int[] p4 = new int[]{0, 1};
        System.out.println((new ValidSquare()).validSquare(p2, p1, p3, p4));
    }
}
