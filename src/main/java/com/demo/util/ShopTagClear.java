package com.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import okhttp3.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ShopTagClear {

    public static final String URL = "http://10.9.91.154:8418/scp/tools/hg_test/clearTag";
    public static final String PATH = "/Users/hugang/Desktop/data_clear";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static OkHttpClient client = new OkHttpClient();
    public static void main(String[] args) throws Exception {
        System.out.println("========================data clear begin========================");
        List<String> lines = Files.readAllLines(Paths.get(PATH));

        List<List<String>> lists = Lists.partition(lines, 200);
        lists.forEach(list -> {
            List<Integer> intList = list.stream()
                    .map(ShopTagClear::parseInt).filter(Objects::nonNull).collect(Collectors.toList());

            try {
                RequestBody body = RequestBody.create(JSON, JSONObject.toJSONString(intList));
                Request request = new Request.Builder().url(URL).addHeader("userId", "2055689").post(body).build();
                Response response = client.newCall(request).execute();
                ResponseBody respBody = response.body();
                JSONObject object = JSONObject.parseObject(respBody.string());
                if (object.get("status") != null) {
                    Integer status = (Integer) object.get("status");
                    if (status != 200) {
                        System.out.println(respBody.string());
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        System.out.println("========================data clear end========================");
    }

    public static Integer parseInt(String str) {
        if (str == null) {
            return null;
        }
        String trimed = str.trim();
        try {
            return Integer.parseInt(trimed);
        } catch (Exception e) {
            return null;
        }
    }
}
