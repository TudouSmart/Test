package com.demo.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class RespFilter {

    public static void main1(String[] args) throws Exception{
        byte[] bytes = Files.readAllBytes(Paths.get("/Users/hugang/Desktop/res.json"));
        String str = new String(bytes);

        JSONArray jsonArray = JSONObject.parseArray(str);
        List<String> metdhodName = new LinkedList<>();
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            boolean value = (boolean)jsonObject.get("isQ1Method");
            if (value) {
//                metdhodName.add((String)jsonObject.get("name"));
                System.out.println(jsonObject.get("name"));
            }
        }

    }
}
