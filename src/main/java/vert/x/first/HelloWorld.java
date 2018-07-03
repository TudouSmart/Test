package vert.x.first;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * Created by hugang on 2018/1/5.
 *
 */
public class HelloWorld {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(MyVertical.class.getName());
    }


    public static class MyVertical extends AbstractVerticle {
        public void start() {
            vertx.createHttpServer().requestHandler(req -> {
                req.response()
                        .putHeader("content-type", "text/plain")
                        .end("hello world");
            }).listen(8080);
        }
    }
}
