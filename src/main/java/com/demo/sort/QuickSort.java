package com.demo.sort;

import java.util.Arrays;

public class QuickSort {

    public static void sort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = partition(arr, start, end);
            sort(arr, start, mid - 1);
            sort(arr, mid + 1 , end);
        }
    }

    public static int partition(int[] arr, int start, int end) {
        int temp = arr[end];

        int j = start - 1;
        for (int i = start; i <= end; i ++) {
            if (arr[i] <= temp) {
                swap(arr, ++j, i);
            }
        }

        return j;
    }

    public static void swap(int[] arr, int i, int j) {
        int a = arr[i];
        arr[i] = arr[j];
        arr[j] = a;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,35,1,-1,2,0,3};
        sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
