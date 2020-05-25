package com.demo;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo {

    public static void main(String[] args) {
    }

    public String translate(String keyWord) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Semaphore semaphore = new Semaphore(0);

        Future<String> future = new FutureTask<>();
        new Thread(() -> {
            baidu(keyWord);
            condition.signal();
        }).start();
        new Thread(() -> {
            sogou(keyWord);
            condition.signal();
        }).start();
        new Thread(() -> {
            google(keyWord);
            condition.signal();
        }).start();
        try {
            condition.await();
        } catch (Exception e) {
            //异常处理
        }


    }

    private String baidu(String keyWord){

    }

    private String sogou(String keyWord){

    }

    private String google(String keyWord){

    }
}
