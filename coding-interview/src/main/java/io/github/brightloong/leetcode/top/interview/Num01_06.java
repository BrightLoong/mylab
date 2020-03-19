package io.github.brightloong.leetcode.top.interview;

/**
 * @author BrightLoong
 * @date 2020/3/16 09:38
 * @description
 */
public class Num01_06 {
    public String compressString(String S) {
        if (S == null || "".equals(S)) {
            return S;
        }

        char[] chars = S.toCharArray();
        StringBuilder result = new StringBuilder();
        char currentChar = S.charAt(0);
        int currentCount = 1;
        for (int i = 1; i < S.length(); i++) {
            if (currentChar == chars[i]) {
                currentCount ++;
            } else {
                result.append(currentChar).append(currentCount);
                currentChar = chars[i];
                currentCount = 1;
            }
        }
        result.append(currentChar).append(currentCount);
        return result.toString().length() < S.length() ? result.toString() : S;
    }
}
