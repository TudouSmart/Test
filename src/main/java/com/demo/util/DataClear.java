package com.demo.util;

import okhttp3.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataClear {

    private static final String PROD_URL = "http://scp.zc.vip.sankuai.com";

    private static final String STAGE_URL = "http://10.5.213.181:8080";

    public static void main(String[] args) throws Exception{
        //map();
        //update("1");
        change();
    }

    public static void update(String o) throws Exception{
        OkHttpClient client = new OkHttpClient();

        List<String> stringList = Files.readAllLines(Paths.get("/Users/hugang/Desktop/tttt"));

        stringList.forEach(str -> {
            String[] strings = str.split(",");
            String url = STAGE_URL + "/scp/tools/lb/updateBusinessName?" + "shopId=" + strings[0] + "&businessName="+strings[1]+o;
            Request request = new Request.Builder().url(url).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("This is Error resp!");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    System.out.println(response.body().string());
                }
            });
        });
    }

    public static void change() throws Exception{
        OkHttpClient client = new OkHttpClient();

        List<String> stringList = Files.readAllLines(Paths.get("/Users/hugang/Desktop/tttt"));

        stringList.forEach(str -> {
            String[] strings = str.split(",");
            String url = PROD_URL + "/scp/tools/lb/changeBusinessName?" + "shopId=" + strings[0] + "&businessName="+strings[1];
            Request request = new Request.Builder().url(url).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("This is Error resp!");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    System.out.println(response.body().string());
                }
            });
        });
    }

    public static void map() throws Exception{
        List<String> bizUsers = Files.readAllLines(Paths.get("/Users/hugang/Desktop/biz_user_map"));
        List<String> bizUserQualificaitons = Files.readAllLines(Paths.get("/Users/hugang/Desktop/biz_user_qualification_map"));

        Map<Integer, Integer> bizUserIdShopIdMap = new HashMap<>();

        for (String line : bizUsers) {
            String[] arr = line.split(",");

            bizUserIdShopIdMap.put(Integer.parseInt(arr[0].trim()), Integer.parseInt(arr[1].trim()));
        }

        Map<Integer, String> bizUserIdNameMap = new HashMap<>();
        for (String line : bizUserQualificaitons) {
            String[] arr = line.split(",");

            bizUserIdNameMap.put(Integer.parseInt(arr[0].trim()), arr[1].trim());
        }

        for (Map.Entry<Integer, Integer> entry : bizUserIdShopIdMap.entrySet()) {
            Integer bizUserId = entry.getKey();

            Integer shopId = entry.getValue();

            String bussinesseName = bizUserIdNameMap.get(bizUserId);

            System.out.println(shopId+","+bussinesseName);
        }
    }

    public static void main1(String[] args) {
        BitSet bitSet = new BitSet(0);
        bitSet.set(0);
        System.out.println(bitSet);
    }
}
