package io.github.brightloong.leetcode.top.interview;

/**
 * @author BrightLoong
 * @date 2020/3/8 18:17
 * @description
 */
public class Num322 {
    public  int coinChange(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        return coinChange(coins, amount, new int[amount + 1]);
    }

    /**
     *
     * @param coins
     * @param amount
     * @param memo 备忘录
     * @return
     */
    public  int coinChange(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo[amount] != 0 ) {
            return memo[amount];
        }
        int result = amount + 1;
        for (int coin : coins){
            int rem = coinChange(coins, amount - coin, memo);
            if (rem >= 0 && rem + 1 < result) {
                result = rem + 1;
            }
        }
        memo[amount] = result != amount + 1 ? result : -1;
        return memo[amount];
    }


    public static void main(String[] args) {
        //System.out.println(coinChange(new int[]{2}, 3));
    }
}
