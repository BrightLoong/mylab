package io.github.brightloong.leetcode.top.interview;

/**
 * @author BrightLoong
 * @date 2020/1/20 17:10
 * @description
 */
public class Num27 {
    public int removeElement(int[] nums, int val) {
        if (nums == null) {
            return 0;
        }
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(new Num27().removeElement(new int[]{0,1,2,2,3,0,4,2}, 2));
    }
}
