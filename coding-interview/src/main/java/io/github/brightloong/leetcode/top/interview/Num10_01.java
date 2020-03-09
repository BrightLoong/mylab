package io.github.brightloong.leetcode.top.interview;

/**
 * @author BrightLoong
 * @date 2020/3/3 17:33
 * @description
 */
public class Num10_01 {
    public static void merge(int[] A, int m, int[] B, int n) {
        int[] result = new int[m + n];
        int i = 0, j = 0;
        for (int k = 0; k < m + n; k++) {
            if (n == 0 || j == n || (i != m && A[i] < B[j])) {
                result[k] = A[i];
                i++;
            } else {
                result[k] = B[j];
                j++;
            }
        }
        for (int k = 0; k < m + n; k++) {
            A[k] = result[k];
        }
    }

    public static void main(String[] args) {
        int[] ints = {2, 0};
        merge(ints, 1, new int[]{1}, 1);
        System.out.println(ints);
    }
}
