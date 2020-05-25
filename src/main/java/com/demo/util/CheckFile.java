package com.demo.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.List;

public class CheckFile {

    public static void main(String[] args) throws Exception{
        BitSet file1 = new BitSet();
        BitSet file2 = new BitSet();
        BitSet file3 = new BitSet();

        Path path1 = Paths.get("");
        Path path2 = Paths.get("");
        Path path3 = Paths.get("");

        List<String> line1 = Files.readAllLines(path1);
        List<String> line2 = Files.readAllLines(path2);
        List<String> line3 = Files.readAllLines(path3);

        for (String line : line1) {
            Integer id = Integer.parseInt(line);
            file1.set(id);
        }

        for (String line : line2) {
            Integer id = Integer.parseInt(line);
            file2.set(id);
        }

        for (String line : line3) {
            Integer id = Integer.parseInt(line);
            file3.set(id);
        }

        file1.and(file2);
        file1.and(file3);

        for (int i = 0; i < file1.size(); i ++) {
            if (file1.get(i)) {
                System.out.println(i);
            }
        }
    }

}
