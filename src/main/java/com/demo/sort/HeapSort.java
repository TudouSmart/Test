package com.demo.sort;

import java.util.Arrays;

public class HeapSort {

    public static void sort(int arr[]) {

        for (int i = (arr.length - 1) / 2; i >= 0 ; i --) {
            help(arr, i, arr.length);
        }

        for (int i = arr.length - 1; i > 0; i --) {
            swap(arr, i, 0);
            help(arr, 0, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int a = arr[i];
        arr[i] = arr[j];
        arr[j] = a;
    }

    private static void help(int arr[], int i, int size) {
        int temp = arr[i];

        for (int k = i * 2 + 1; k < size; k = k * 2 + 1) {

            if (k < size - 1 && arr[k + 1] > arr[k]) {
                k = k + 1;
            }

            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }

        arr[i] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,2,1,2,2,335,1,-1,2,0,3};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
