package io.github.brightloong.leetcode.top.interview;

/**
 * @author BrightLoong
 * @date 2020/3/19 09:35
 * @description
 */
public class Num409 {
    public int longestPalindrome(String s) {
        int result = 0;
        int[] ints = new int[58];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //统计数量
            ints[c - 'A'] += 1;
        }
        for (int i = 0; i < 58; i++) {
            result += (ints[i] - (ints[i] & 1));
        }
        return result < s.length() ? result + 1 : result;
    }
}
