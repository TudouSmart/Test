package com.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by hugang on 2017/6/27.
 */
public class Main {

    public static void main(String[] args) {

        /**
         * 1111
         * 1000000
         * 111111
         * 10000000
         */
        UUID.randomUUID();
        System.out.println(Long.toBinaryString(0x0f));
        System.out.println(Long.toBinaryString(0x40));
        System.out.println(Long.toBinaryString(0x3f));
        System.out.println(Long.toBinaryString(0x80));
        System.out.println(Long.toBinaryString(0xff));
        System.out.println(Long.toBinaryString(0x40));
        System.out.println(Long.toBinaryString(0x3f));
    }
}
