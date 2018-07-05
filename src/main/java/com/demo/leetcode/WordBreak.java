package com.demo.leetcode;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/word-break/description/
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] arr = new boolean[s.length() + 1];
        arr[0] = true;

        for (int i = 1; i <= s.length(); i ++) {
            for (int j = 0; j < i; j ++) {
                if (arr[j] && wordDict.contains(s.substring(j, i)) ) {
                    arr[i] = true;
                    break;
                }
            }
        }

        return arr[s.length()];
    }

    public static void main(String[] args) {
        System.out.println((new WordBreak()).wordBreak(null, null));
    }
}
