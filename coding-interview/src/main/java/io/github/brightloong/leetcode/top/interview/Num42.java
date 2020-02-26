package io.github.brightloong.leetcode.top.interview;

import java.util.Stack;

/**
 * @author BrightLoong
 * @date 2020/1/20 17:36
 * @description
 */
public class Num42 {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            if (stack.empty()) {
                stack.push(height[i]);
            } else {
                //取栈顶
                Integer pop1 = stack.pop();
                Integer pop2 = stack.pop();
                //if (height[i])
            }
        }
        return 0;
    }
}
