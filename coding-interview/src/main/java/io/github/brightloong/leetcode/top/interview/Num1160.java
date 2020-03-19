package io.github.brightloong.leetcode.top.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BrightLoong
 * @date 2020/3/17 09:35
 * @description
 */
public class Num1160 {
    public  int countCharacters(String[] words, String chars) {
        Map<Character, Integer> charsCount = countWord(chars);
        int result = 0;
        for (String word : words) {
            Map<Character, Integer> wordCount = countWord(word);
            for (int i = 0; i < word.length(); i++) {
                if (charsCount.get(word.charAt(i)) == null || wordCount.get(word.charAt(i)) > (charsCount.get(word.charAt(i)))) {
                    break;
                }
                if (i == word.length() - 1) {
                    result += word.length();
                }
            }
        }
        return  result;
    }

    public Map<Character, Integer> countWord(String word) {
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            result.merge(word.charAt(i), 1, Integer::sum);
        }
        return result;
    }

    public static void main(String[] args) {
        Num1160 num1160 = new Num1160();
       num1160.countCharacters(new String[]{"hello","world","leetcode"}, "welldonehoneyr");
    }
}
