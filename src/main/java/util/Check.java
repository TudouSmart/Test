package util;

import com.google.common.collect.Lists;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by hugang on 2018/4/12.
 */
public class Check {

    public static void main1(String[] args) throws Exception{
        String path1 = "/Users/hugang/Desktop/file1";
        String path2 = "/Users/hugang/Desktop/file2";

        List<String> first = Files.readAllLines(Paths.get(path1));
        List<String> second = Files.readAllLines(Paths.get(path2));

        List<String> notContain = Lists.newArrayList();
        List<String> notContain2 = Lists.newArrayList();

        int count = 0;
        for (String s:second) {
            if (first.contains(s)) {
                count ++;
            } else {
                notContain2.add(s);
            }
        }

        for (String s:first) {
            if (!second.contains(s)) {
                notContain.add(s);
            }
        }



        if (count == first.size()) {
            System.out.println("全部包含");
        } else if (count > 0) {
            System.out.println("部分包含");
        }

        System.out.println("count->: "+count);

        System.out.println(notContain);
        System.out.println(notContain2);
    }

    public static void main(String[] args) throws Exception {
        String path1 = "/Users/hugang/Desktop/wrong_poi";
        String path2 = "/Users/hugang/Desktop/open_all";
        String path3 = "/Users/hugang/Desktop/new_open_all";
        System.out.println("begin");

        Charset charset = Charset.forName("utf-8");

        List<String> path1Lines = Files.readAllLines(Paths.get(path1), charset);
        List<String> path2Lines = Files.readAllLines(Paths.get(path2), charset);

        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(path3));

        for (String l2:path2Lines) {
            String[] arr = l2.split(",");
            String shopId = arr[0].trim();

            if (!path1Lines.contains(shopId)) {
                bufferedWriter.write(shopId+","+arr[1]+System.getProperty("line.separator"));
            }
        }

        bufferedWriter.flush();
        bufferedWriter.close();

        System.out.println("end");

    }
}
