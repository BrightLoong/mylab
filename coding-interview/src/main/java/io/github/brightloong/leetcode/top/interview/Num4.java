package io.github.brightloong.leetcode.top.interview;

/**
 * @author BrightLoong
 * @date 2020/1/13 16:40
 * @description
 */
public class Num4 {
    static class Solution {
        public static  double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int l1 = nums1.length;
            int l2 = nums2.length;
            int k1 = (l1 + l2 + 1) / 2;
            int k2 = (l1 + l2 + 2) / 2;
            int kth1 = getKth(0, l1 - 1, nums1, 0, l2 - 1, nums2, k1);
            int kth2 = getKth(0, l1 - 1, nums1, 0, l2 - 1, nums2, k2);
            return (kth1 + kth2) * 0.5;
        }

        private static int getKth(int start1, int end1,int[] nums1, int start2, int end2, int[] nums2, int k) {
            int l1 = end1 - start1 + 1;
            int l2 = end2 - start2 + 1;
            if (l1 > l2) {
                return getKth(start2, end2, nums2, start1, end1, nums1, k);
            }

            if (l1 == 0) {
                return nums2[start2 + k - 1];
            }

            if (k ==  1) {
                return Math.min(nums1[start1], nums2[start2]);
            }

            int n1 = start1 + Math.min(l1 - 1, k / 2 - 1);
            int n2 = start2 + Math.min(l2 - 1, k / 2 - 1);

            if (nums1[n1] > nums2[n2]) {
                return getKth(start1, end1, nums1, n2 + 1, end2, nums2, k - (n2 - start2 + 1));
            } else {
                return getKth(n1 + 1, end1, nums1, start2, end2, nums2, k - (n1 - start1 + 1));
            }
        }

    }

    public static void main(String[] args) {
        double medianSortedArrays = Solution.findMedianSortedArrays(new int[]{1}, new int[]{2,3,4,5,6});
    }
}


