package io.github.brightloong.leetcode.top.interview;

/**
 * @author BrightLoong
 * @date 2020/1/14 09:35
 * @description
 */
public class Num28 {
    public int strStr(String haystack, String needle) {
        if (needle == null || "".equals(needle)) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        char[] haystackChars = haystack.toCharArray();
        char[] needleChars = needle.toCharArray();
        for (int i = 0; i < haystackChars.length - needleChars.length + 1; i++) {
            int num = 0;
            for (int j = 0; j < needleChars.length; j++) {
                if (needleChars[j] == haystackChars[j+i]) {
                    num++;
                    if (num == needleChars.length) {
                        return i;
                    }
                } else {
                    break;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Num28 num28 = new Num28();
        System.out.println(num28.strStr("mississippi", "issipi"));
    }
}
