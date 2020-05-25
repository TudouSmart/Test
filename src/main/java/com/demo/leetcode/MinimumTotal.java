package com.demo.leetcode;

import com.google.common.collect.Lists;

import java.util.List;

/*
https://leetcode-cn.com/problems/triangle/description/
 */
public class MinimumTotal {
    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }

        for (int i = triangle.size() - 2; i >= 0; i --) {
            List<Integer> row = triangle.get(i);
            List<Integer> next = triangle.get(i + 1);
            for (int j = 0; j < row.size(); j ++) {
                row.set(j, row.get(j) + Math.min(next.get(j), next.get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }

    public static void main(String[] args) {
        List<List<Integer>> list = Lists.newArrayList(
                Lists.newArrayList(2),
                Lists.newArrayList(3,4),
                Lists.newArrayList(5,6,7),
                Lists.newArrayList(4,1,8,3)
        );

        System.out.println(minimumTotal(list));
    }
}
