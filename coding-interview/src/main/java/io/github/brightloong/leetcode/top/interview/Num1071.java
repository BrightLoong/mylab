package io.github.brightloong.leetcode.top.interview;

/**
 * @author BrightLoong
 * @date 2020/3/12 09:22
 * @description
 */
public class Num1071 {
    public static String gcdOfStrings(String str1, String str2) {
       String str = str1.length() > str2.length() ? str2 : str1;
       String result = "";
       for (int i = str.length(); i > 0; i--) {
           String template = str.substring(0, i);
           if (str1.length() % i != 0 || str2.length() % i != 0) {
               continue;
           }

           String strTemp = "";
           for (int j = 1; j <= str1.length() / i; j++) {
            strTemp += template;
           }
           String strTemp2 = "";
           for (int j = 1; j <= str2.length() / i; j++) {
               strTemp2 += template;
           }
           if (strTemp.equals(str1) && strTemp2.equals(str2)) {
               result = template;
               break;
           }
       }
       return result;
    }

    public static void main(String[] args) {
        System.out.println(gcdOfStrings("ABABAB", "ABABAB"));
    }
}
