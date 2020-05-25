package util;

import com.google.common.collect.Lists;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Created by hugang on 2018/4/12.
 */
public class Check {

    public static void main1(String[] args) throws Exception{
        final List<FileCount> list = new ArrayList<>(128);
        Files.walkFileTree(Paths.get("/Users/hugang/works/scm/zcm-scp/zcm-scp-web"), new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                File f = file.toFile();
                if (f.isDirectory()
                        || ! f.getName().endsWith(".java")
                        ||  f.getName().contains("Controller")
                        ||  f.getName().contains("Thrift")) {
                    return FileVisitResult.CONTINUE;
                }
//                System.out.println(f.getName()+":"+countLine(f.getAbsolutePath()));
                list.add(new FileCount(f.getName(), countLine(f.getAbsolutePath())));
                return FileVisitResult.CONTINUE;
            }
        });

        list.sort(Comparator.comparing(FileCount::getCount));
        list.forEach(o -> System.out.println(o.getName()+":"+o.getCount()));
    }


    public static int countLine(String path) throws IOException{
        Process process = Runtime.getRuntime().exec("wc -l " + path);
        Scanner scanner = new Scanner(process.getInputStream());
        String res = scanner.nextLine().trim();
        return Integer.parseInt(res.split(" ")[0]);
    }

    public static class FileCount {
        private String name;
        private int count;

        public FileCount(String name, int count) {
            this.name = name;
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public FileCount setName(String name) {
            this.name = name;
            return this;
        }

        public int getCount() {
            return count;
        }

        public FileCount setCount(int count) {
            this.count = count;
            return this;
        }
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<Integer>(){{
           add(1);
           add(2);
           add(3);
           add(4);
           add(5);
        }};

        Spliterator<Integer> test = Spliterators.spliterator(arr, 0);
        test.forEachRemaining(System.out::println);
        System.out.println("-------------------");
        test.forEachRemaining(System.out::println);
        new ArrayList<>().stream();
    }
}
