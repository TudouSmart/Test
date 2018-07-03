package com.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by hugang on 2017/7/23.
 */
public class CountTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 10;
    private int start;
    private int end;

    public CountTask(final int start, final int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;

        if (canCompute) {
            for (int i = start; i < end; i ++) {
                sum += i;
            }
        } else {
            int mid = (start + end) / 2;
            CountTask leftTask = new CountTask(start, mid);
            CountTask rightTask = new CountTask(mid, end);
            invokeAll(leftTask, rightTask);
            sum = leftTask.join() + rightTask.join();
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        long start = System.currentTimeMillis();
        CountTask task = new CountTask(1, 40000);
        Future<Integer> result = pool.submit(task);
        try {
            System.out.println(result.get());
            System.out.println(System.currentTimeMillis() - start);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
