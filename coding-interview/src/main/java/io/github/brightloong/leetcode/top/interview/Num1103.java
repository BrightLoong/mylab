package io.github.brightloong.leetcode.top.interview;

import java.util.Arrays;

/**
 * @author BrightLoong
 * @date 2020/3/5 09:42
 * @description
 */
public class Num1103 {
    public static  int[] distributeCandies(int candies, int num_people) {
        int num = 0;
        int[] result = new int[num_people];
        while(candies > 0) {
            for (int i = 1; i <= num_people; i++) {
                int candy = num * num_people + i;
                if (candies <= candy) {
                    result[i-1] += candies;
                    candies = 0;
                    break;
                } else {
                    result[i-1] += candy;
                    candies -= candy;
                }
            }
            num ++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] ints = distributeCandies(10, 3);
        Arrays.stream(ints).forEach(System.out::println);
    }
}
