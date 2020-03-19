package io.github.brightloong.leetcode.top.interview;

/**
 * @author BrightLoong
 * @date 2020/3/18 09:39
 * @description
 */
public class Nub836 {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[3] < rec2[1] || rec1[1] > rec2[3] || rec1[0] > rec2[2] || rec1[2] < rec2[0]);
    }
}
