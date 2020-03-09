package io.github.brightloong.leetcode.top.interview;

/**
 * @author BrightLoong
 * @date 2020/3/9 16:51
 * @description
 */
public class Num121 {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                if (maxProfit < prices[i] - minPrice) {
                    maxProfit = prices[i] - minPrice;
                }
            }
        }
        return maxProfit;
    }
}
