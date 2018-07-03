package vert.x.first;

import io.vertx.core.AbstractVerticle;

/**
 * Created by hugang on 2018/1/5.
 */
public class MainVerticle extends AbstractVerticle {
    public void start() {
        vertx.deployVerticle(HelloWorld.MyVertical.class.getName());
    }
}
