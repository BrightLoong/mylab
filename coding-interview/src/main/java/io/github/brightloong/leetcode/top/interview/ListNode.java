package io.github.brightloong.leetcode.top.interview;

import javax.xml.soap.Node;
import java.util.Objects;

/**
 * @author BrightLoong
 * @date 2020/1/13 10:16
 * @description
 */
 public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

 class Solution {

     public ListNode reverse(ListNode node) {
         if (Objects.nonNull(node)) {
             ListNode before = node;
             ListNode result = node;
             node = node.next;
             result.next = null;
             while (Objects.nonNull(node)) {
                 result = node;
                 node = node.next;
                 result.next = before;
                 before = result;
             }
             return result;
         }
         return null;
     }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        //进位
        int temp = 0;
        do {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int i = val1 + val2 + temp;
            l1 = l1.next;
            l2 = l2.next;
            temp = i / 10;
            ListNode listNode = new ListNode(i % 10);
            if (result == null) {
                result = listNode;
            } else {
                result.next = listNode;
            }
        } while (l1 != null || l2 != null);

        return result;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(5);
        node.next = new ListNode(4);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(2);
        Solution solution = new Solution();
        ListNode reverse = solution.reverse(node);
        System.out.println(reverse);
    }
}
