package com.demo.http;

import okhttp3.*;

import java.io.IOException;

public class Client1 {

    private static OkHttpClient okHttpClient = new OkHttpClient();

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void main(String[] args) {
        String url = "http://localhost:8080/scp/scm/audit/submit";
        String reqStr = "{\"bizUserId\":1685782,\"productTypes\":[2],\"shopId\":179507410,\"siteReferer\":1}";
        RequestBody body = RequestBody.create(JSON, reqStr);
        Request request = new Request.Builder().url(url).post(body).build();

        for (int i = 0; i < 10; i ++) {
            final int time = i;
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(call.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    System.out.println();
                    System.out.println("request " + time + " => " + response.body().string());
                }
            });
        }
    }
}
