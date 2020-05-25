package com.demo.util;

import java.io.BufferedWriter;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveDuplicate {

    public static void main(String[] args) throws Exception{
        Path path = Paths.get("/Users/hugang/Desktop/data_status_clear_3");
        List<String> lines = Files.readAllLines(path, Charset.forName("utf-8"));

        Set<String> lineSet = new HashSet<>(lines);

        Path to = Paths.get("");
        BufferedWriter bf = Files.newBufferedWriter(to, Charset.forName("utf-8"));

        System.out.println("======================");
        for (String line : lineSet) {
            System.out.println(line);
        }
        System.out.println("======================");
    }
}
