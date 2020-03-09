package io.github.brightloong.leetcode.top.interview;

/**
 * @author BrightLoong
 * @date 2020/3/3 16:42
 * @description
 */
public class Num5 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        int max = 1;
        String result = String.valueOf(chars[0]);
        for (int i = 1; i < chars.length; i ++) {
            int length = Math.min(i + 1 , chars.length - i);
            int temp = 1;
            for (int j = 0; j < length; j ++) {
                if (chars[i - j] == chars[i + j ]) {
                    temp ++;
                    if (temp > max) {
                        max = temp;
                        result = s.substring(i - j, i + j  + 1);
                    }
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < chars.length; i ++) {
            int length = Math.min(i + 1 , chars.length - i - 1);

            if (i < chars.length - 1 && chars[i] == chars[i + 1]) {
                int temp = 2;
                if (temp > max) {
                    max = temp;
                    result = s.substring(i, i + 2);
                }
                for (int j = 0; j < length; j ++) {
                    if (chars[i - j] == chars[i + j + 1]) {
                        temp ++;
                        if (temp > max) {
                            max = temp;
                            result = s.substring(i - j, i + j + 2);
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Num5 num5 = new Num5();
        System.out.println(num5.longestPalindrome("fsdfweaabba4fwwf"));
    }
}
