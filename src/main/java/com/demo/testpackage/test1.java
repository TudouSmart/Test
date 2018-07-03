package com.demo.testpackage;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by hugang on 2017/11/2.
 *
 */
public class test1 {

    public static void main(String[] args) throws Exception{
        Class c = test1.class;
        Method[] ms = c.getDeclaredMethods();
        for (Method m:ms) {
            if (m.getParameterTypes().length > 0) {
                Class clazz = m.getParameterTypes()[0];
                if (List.class.isAssignableFrom(clazz)) {
                    
                }
            }
        }
    }

    public void test(List<Integer> p) {

    }
}
