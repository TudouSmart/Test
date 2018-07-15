package com.demo.leetcode;

import java.util.Arrays;

public class LeastInterval {
    public int leastInterval(char[] tasks, int n) {
        int[] ch = new int[26];

        for (char c : tasks) {
            ch[c-'A'] ++;
        }

        Arrays.sort(ch);
        int i = 25;
        while (i >= 0 && ch[i] == ch[25]) i--;
        return Math.max(tasks.length, ((ch[25] - 1) * (n + 1) + 25 - i));
    }
}
