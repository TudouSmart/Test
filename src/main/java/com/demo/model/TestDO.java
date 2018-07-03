package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by hugang on 2017/7/26.
 */
public class TestDO {
    private int id;
    private String msg;

    public int getId() {
        return id;
    }

    public TestDO setId(int id) {
        this.id = id;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public TestDO setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "TestDO{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                '}';
    }
}
