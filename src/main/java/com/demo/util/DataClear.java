package com.demo.util;

import okhttp3.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.List;

public class DataClear {

    private static final String PROD_URL = "http://scp.zc.vip.sankuai.com";

    public static void main(String[] args) throws Exception{
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

    public static void main1(String[] args) {
        BitSet bitSet = new BitSet(0);
        bitSet.set(0);
        System.out.println(bitSet);
    }
}
