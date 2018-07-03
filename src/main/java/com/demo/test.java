package com.demo;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by hugang on 2017/7/30.
 *
 */
public class test {

    public static void main(String[] args) throws Exception{
        List<String> ownerList = Files.readAllLines(Paths.get("/Users/hugang/Desktop/221334"));

        Set<String> ownerTestSet = new HashSet<>(ownerList);

        System.out.println(ownerTestSet.size());
    }


}
