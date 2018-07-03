package com.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.model.TestDO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugang on 2017/7/26.
 */
public class JacksonTest {

    public static void main(String[] args) throws Exception{
        String Json = "{\"id\":1, \"msg\":\"233\", \"data\":3333}";
        ObjectMapper mapper = new ObjectMapper();
        //TestDO t = mapper.readValue(Json, TestDO.class);
        System.out.println(JSONObject.parseObject(Json, TestDO.class));
    }
}
