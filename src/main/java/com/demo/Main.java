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
    public static void main1(String[] args) {
        Path in = Paths.get("/Users/hugang/Documents/in");
        Path out = Paths.get("/Users/hugang/Documents/out");
        Set<Integer> sets = new HashSet();
        try {
            List<String> ss = Files.readAllLines(in);
            BufferedWriter writer = Files.newBufferedWriter(out);

            for (String s : ss) {
                Integer n = Integer.valueOf(s.split(",")[0].trim());
                if (!sets.contains(n)) {
                    sets.add(n);
                    System.out.println(s);
                }
            }
        } catch (IOException e) {
            System.out.println("wrong!"+e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Path in = Paths.get("/Users/hugang/Desktop/shop_pos");
        Path in2 = Paths.get("/Users/hugang/Desktop/pos_sn");
        try {
            List<String> shopPos = Files.readAllLines(in);
            List<String> posSn = Files.readAllLines(in2);
            List<String> result = new ArrayList<>(shopPos.size());
            Map<String, String> map = new HashMap<>();

            for (String str:posSn) {
                String[] ss = str.split(",");
                map.put(ss[0], ss[1]);
            }

            for (String str:shopPos) {
                String[] ss = str.split(",");
                StringBuilder s = new StringBuilder(ss[0]).append(",");
                s.append(map.get(ss[1]));
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
