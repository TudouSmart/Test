package com.demo.leetcode;

import java.util.Stack;

public class CheckValidString {

    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;

        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == '(') {
                low ++;
                high ++;
            } else if (s.charAt(i) == ')') {
                low --;
                high --;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println((new CheckValidString()).checkValidString("()"));
    }
}
