package com.demo.leetcode;

/*
https://leetcode-cn.com/problems/longest-palindromic-substring/description/
 */
public class LongestPalindrome {

    public String longestPalindrome_old(String s) {
        int length = s.length();

        String res = "";
        int max = 0;
        boolean[][] isPalindrom = new boolean[s.length()][s.length()];

        for(int len = 1; len <= length; len ++){
            for(int i = 0; i <= length - len; i++){
                if(s.charAt(i) == s.charAt(i + len - 1)){
                    if(len <= 2){
                        isPalindrom[i][i + len - 1] = true;
                    }else {
                        isPalindrom[i][i + len - 1] = isPalindrom[i+1][i+len-2];
                        if(!isPalindrom[i+1][i+len-2]){
                            continue;
                        }
                    }
                    if (max < len) {
                        max = len;
                        res = s.substring(i, i + len);
                    }
                }else{
                    isPalindrom[i][i + len - 1] = false;
                }
            }
        }

        return res;
    }

    public static String longestPalindrome(String s) {
        int[][] arr = new int[s.length()][s.length()];

        int max = 1;
        String res = "";
        for (int len = 1; len <= s.length(); len ++) {
            for (int index = 0; index + len <= s.length(); index ++) {
                if (equal(s, index, index + len -1)
                        && arr[index][index + len -1] == len) {
                    arr[index][index + len -1] = len;
                    if (max <= len) {
                        res = s.substring(index, index + len);
                    }
                } else if (index < s.length() - 1 && index > 0) {
                    arr[index][index + len -1] = Math.max(arr[index + 1][index + len - 1], arr[index][index + len - 2]);
                }

                max = Math.max(max, arr[index][index + len - 1]);
            }
        }

        return res;
    }

    private static boolean equal(String s, int i ,int j) {
        return s.charAt(i) == s.charAt(j);
    }



    private static boolean help(String str) {
        int start = 0;
        int end = str.length() - 1;
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }

            start ++;
            end --;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(0.1 + 0.2);
    }
}
