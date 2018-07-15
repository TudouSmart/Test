package com.demo.leetcode;

import java.util.*;

/*
https://leetcode-cn.com/problems/remove-k-digits/description/
 */
public class RemoveKdigits {
    public String removeKdigits(String num, int k) {
        Stack<Integer> stack = new Stack<>();
        int len = num.length() - k;

        for (char c : num.toCharArray()) {
            int n = c - '0';

            while (! stack.isEmpty() && k > 0) {
                int last = stack.peek();
                if (n < last) {
                    stack.pop();
                    k --;
                } else {
                    break;
                }
            }

            stack.push(n);
        }

        StringBuilder sb = new StringBuilder();

        while(! stack.isEmpty()) {
            sb.append(stack.pop());
        }

        int index = 0;
        String result = sb.reverse().toString();
        while (index < len && result.charAt(index) == '0') {
            index ++;
        }

        return index >= len? "0" : result.substring(index, len);
    }

    public static void main(String[] args) {
        System.out.println((new RemoveKdigits()).removeKdigits("10", 2));
    }
}
