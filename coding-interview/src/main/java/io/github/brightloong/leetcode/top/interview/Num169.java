package io.github.brightloong.leetcode.top.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BrightLoong
 * @date 2020/3/13 09:22
 * @description
 */
public class Num169 {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer count = map.get(num);
            if (count == null) {
                count = 1;
                map.put(num, count);
            } else {
                count = count + 1;
                map.put(num, count);
            }
            if (count > nums.length/2) {
                return num;
            }
        }
        return 0;
    }
}
