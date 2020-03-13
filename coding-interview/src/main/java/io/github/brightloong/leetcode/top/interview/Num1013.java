package io.github.brightloong.leetcode.top.interview;

/**
 * @author BrightLoong
 * @date 2020/3/11 09:28
 * @description
 */
public class Num1013 {
    public static  boolean canThreePartsEqualSum(int[] A) {
        if (A == null) {
            return false;
        }
        //计算和
        int sum = 0;
        for (int value : A) {
            sum += value;
        }
        //不能被3整除
        if (sum % 3 != 0) {
            return false;
        }
        int sum1 = 0;
        int i = 0;
        for (int value : A) {
            //取第一个满足的索引就好，反正到第二个满足的中间这一段相加=0
            sum1 += value;
            if (sum1 == sum/3) {
                sum1 = 0;
                i++;
            }
        }
        return i>=3;
    }

    public static void main(String[] args) {
        System.out.println(canThreePartsEqualSum(new int[]{1,-1,1,-1}));
    }
}
