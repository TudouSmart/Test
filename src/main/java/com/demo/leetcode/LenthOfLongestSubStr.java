package com.demo.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/description/
 */
public class LenthOfLongestSubStr {

    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();

        int length = 0;
        int j = -1;
        for (int i = 0; i < s.length(); i ++) {
            Integer index = map.get(s.charAt(i));

            if (index != null && index > j) {
                length = i - index;
                j = index;
            } else {
                length ++;
            }

            map.put(s.charAt(i), i);
            max = Math.max(length, max);
        }
        return max;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();

        int j = 0;
        for (int i = 0; i < s.length(); i ++) {
            Integer index = map.get(s.charAt(i));
            if (index == null) {
                index = 0;
            }

            j = Math.max(j, index);
            map.put(s.charAt(i), i);
            max = Math.max(i - j, max);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("abcabcbb"));
    }
}
