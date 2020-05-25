package com.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuickSortTask<T extends Comparable> extends RecursiveAction {
    private T[] array;
    private final int THRESHOLD;
    private int start;
    private int end;
    private final ForkJoinPool pool;

    public QuickSortTask(T[] array) {
        this(array, 16, 0, array.length);
    }

    public QuickSortTask(T[] array, int start, int size) {
        this(array, 16, start, size);
    }

    public QuickSortTask(T[] array, int threShold, int start, int size) {
        this.array = array;
        this.THRESHOLD = threShold;
        this.start = start;
        this.end = size;
        this.pool = ForkJoinPool.commonPool();
    }

    public T[] doSort() {
        pool.invoke(this);
        return getArray();
    }

    public T[] getArray() {
        return Arrays.copyOf(array, array.length);
    }
    @Override
    protected void compute() {
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            insertionSort();
        } else {
            int mid = partition();
            QuickSortTask leftTask = new QuickSortTask(array, start, mid);
            QuickSortTask rightTask = new QuickSortTask(array, mid + 1, end);
            invokeAll(leftTask, rightTask);
            leftTask.join();
            rightTask.join();
        }
    }

    private void insertionSort() {
        for (int i = 1; i < array.length; i++) {
            T var0 = array[i];
            int j;
            for (j = i-1; j >= 0 && lessThan(var0, array[j]); j--) {
                array[j+1] = array[j];
            }
            array[++j] = var0;
        }
    }

    private int partition() {
        T standard = array[array.length-1];
        int j = - 1;
        for (int i = 0, len = array.length - 1; i < len; i++) {
            if (isGreaterOrEquals(array[i], standard)) {
                swap(++j, i);
            }
        }
        swap(++j, array.length - 1);
        return j;
    }

    private void swap(int a, int b) {
        T c = array[a];
        array[a] = array[b];
        array[b] = c;
    }

    private boolean isGreaterOrEquals(T a1, T a2) {
        return greaterThan(a1, a2) || isEquals(a1, a2);
    }

    private boolean greaterThan(T a1, T a2) {
        return nullSafeCompare(a1, a2) == 1;
    }

    private boolean isEquals(T a1, T a2) {
        return nullSafeCompare(a1, a2) == 0;
    }

    private boolean lessThan(T a1, T a2) {
        return nullSafeCompare(a1, a2) == -1;
    }

    private int nullSafeCompare(T a1, T a2) {
        if (a1 == a2) {
            return 0;
        }
        if (a1 == null) {
            return - a2.compareTo(a1);
        }
        return a1.compareTo(a2);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Integer> in = new ArrayList<>();
        while (input.hasNext()) {
            String[] ins = input.nextLine().split(" ");
            for (String s : ins) {
                in.add(Integer.parseInt(s));
            }
            Integer[] res = new QuickSortTask<>(in.toArray(new Integer[in.size()])).doSort();
            System.out.println(Arrays.toString(res));
        }
    }
}
