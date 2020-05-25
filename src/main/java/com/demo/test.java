package com.demo;

import com.alibaba.fastjson.JSONObject;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.target.SimpleBeanTargetSource;
import org.springframework.aop.target.SingletonTargetSource;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by hugang on 2017/7/30.
 *
 */
public class test {

    public static int[] retainAll(int[] array1, int[] array2) {
        List<Integer> list = new ArrayList<>();
        int index1 = 0, index2 = 0;
        while (index1 < array1.length && index2 < array2.length) {
            if (array1[index1] == array2[index2]) {
                list.add(array1[index1]);
                index1++;
                index2++;
            } else if (array1[index1] < array2[index2]) {
                index1 ++;
            } else {
                index2 ++;
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static char find(char[] chars1, char[] chars2) {
        int size = Math.max(chars1.length, chars2.length);
        for (int i = 0; i < size; i ++) {
            if (chars1[i] != chars2[i]) {
                return chars1.length > chars2.length ? chars1[i] : chars2[i];
            }
        }

        return '\0';
    }

    public static void main(String[] args) throws Exception{
        int[] a1 = new int[]{1,2,3,-1,0,0};
        int[] a2 = new int[]{2,5,6};

        merge(a1, 4, a2 , 3);
        System.out.println(Arrays.toString(a1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int size = m;
        for (int i = 0; i < n; i ++) {
            int j;
            for (j = size-1; j >= 0 && nums1[j] > nums2[i]; j --) {
                nums1[j+1] = nums1[j];
            }

            nums1[j+1] = nums2[i];
            size += 1;
        }
    }



}
