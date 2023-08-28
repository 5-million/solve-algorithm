/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// blog: https://velog.io/@eello/LeetCode-2.-Add-Two-Numbers
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode cursor = result;

        int val = 0;
        while (l1 != null || l2 != null) {
            val += l1 != null ? l1.val : 0;
            val += l2 != null ? l2.val : 0;

            cursor.next = new ListNode(val % 10);
            val /= 10;

            cursor = cursor.next;

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (val != 0) {
            cursor.next = new ListNode(val);
        }

        return result.next;
    }
}