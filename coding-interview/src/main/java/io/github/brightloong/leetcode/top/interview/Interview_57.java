package io.github.brightloong.leetcode.top.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BrightLoong
 * @date 2020/3/6 09:39
 * @description
 */
public class Interview_57 {
    public static int[][] findContinuousSequence(int target) {
        List<int[]> result = new ArrayList<>();
        //滑动窗口
        int start = 1;
        int end = start + 1;
        while (end <= (target + 1)/2 && start < end) {
            int i = (start + end) * (end - start + 1) / 2;
            if (i == target) {
                int[] nums = new int[end - start + 1];
                for (int n = start; n <= end; n++) {
                    nums[n-start] = n;
                }
                result.add(nums);
                start++;
            } else if (i < target) {
                end ++;
            } else {
                start++;
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] continuousSequence = findContinuousSequence(15);
        Arrays.stream(continuousSequence).forEach(ints -> {System.out.print("[");
            for (int anInt : ints) {
                System.out.print(anInt + ",");
            }
            System.out.println("]");
        });
    }
}
