package com.demo.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

public class Heap {

    private int[] arr;

    private int size;

    public Heap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("容量必须大于0");
        }
        arr = new int[capacity];
        this.size = 0;
    }

    public void add(int n) {
        if (size > arr.length - 1) {
            grow();
        }

        if (size == 0) {
            arr[0] = n;
        } else {
            int index = size;
            while (index > 0) {
                int parentIndex = (index - 1) >> 1;
                if (arr[parentIndex] <= n) {
                    break;
                }
                arr[index] = arr[parentIndex];
                index = parentIndex;
            }
            arr[index] = n;
        }

        size++;
    }

    public Integer remove() {
        if (size == 0) {
            return null;
        }

        int res = arr[0];
        size--;
        arr[0] = arr[size];
        arr[size] = 0;

        int k = arr[0];
        int index = 0;
        int half = size >> 1;
        while (index < half) {
            int childIndex = index >> 1 + 1;
            int n = arr[childIndex];

            if (childIndex + 1 < size && arr[childIndex + 1] < arr[n]) {
                childIndex = childIndex + 1;
                n = arr[childIndex];
            }

            if (n >= k) {
                break;
            }

            arr[index] = n;
            index = childIndex;
        }
        arr[index] = k;

        return res;
    }

    private void grow() {
        int newCapacity = size << 1;
        arr = Arrays.copyOf(arr, newCapacity);
    }



    public static void main(String[] args) {
        System.out.println(compareVersion("0.1", "1.1"));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int allLen = nums1.length + nums2.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        int queueSize = allLen / 2 + 1;
        int index1 = 0;
        for (; index1 < nums1.length && index1 < queueSize; index1 ++) {
            queue.add(nums1[index1]);
        }

        int index2;
        for (index2 = 0; index2 < nums2.length && index2 < queueSize; index2 ++) {
            queue.add(nums2[index2]);
        }

        for (; index1 < nums1.length; index1 ++) {
            int maxVal = queue.peek();
            if (maxVal < nums1[index1]) {
                queue.remove();
                queue.add(nums1[index1]);`
            }
        }

        for (; index2 < nums2.length; index2 ++) {
            int maxVal = queue.peek();
            if (maxVal < nums1[index2]) {
                queue.remove();
                queue.add(nums1[index2]);
            }
        }

        if ((allLen & 1) == 1) {
            return queue.peek();
        } else {
            return (queue.remove() + queue.remove()) / 2;
        }
    }
}
