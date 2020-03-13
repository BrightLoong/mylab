package io.github.brightloong.leetcode.top.interview;

import java.util.Map;

/**
 * @author BrightLoong
 * @date 2020/3/10 09:44
 * @description
 */
public class Num543 {
    int depath = 0;
    //Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public  int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        depath(root);
        return depath;
    }

    public  int depath(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = depath(node.left);
        int right = depath(node.right);
        depath = Math.max(depath, left + right);
        return Math.max(left, right) + 1;
    }



    public static void main(String[] args) {
        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(-4);
        node.right = new TreeNode(-3);
        node.right.left = new TreeNode(-9);
        node.right.right = new TreeNode(-3);
        node.right.right.right = new TreeNode(9);
        node.right.right.left = new TreeNode(-7);
        node.right.right.right.right = new TreeNode(6);
        node.right.right.right.right.right = new TreeNode(6);
        node.right.right.right.right.left = new TreeNode(0);
        //System.out.println(diameterOfBinaryTree(node));
    }
}





