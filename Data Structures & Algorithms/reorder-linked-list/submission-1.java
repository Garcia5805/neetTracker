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

class Solution {
    public void reorderList(ListNode head) {
        //get middle
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //reverse
        ListNode tail = slow.next;
        ListNode prev = slow.next = null;

        while(tail != null){
            ListNode temp = tail.next;
            tail.next = prev;
            prev = tail;
            tail = temp;

        }

        // prev holds the head of reversed list
        //place
        ListNode cur = head;
        tail = prev; // tail now holds head of reversed list
        
        while(tail != null){
            ListNode l1 = cur.next;
            ListNode l2 = tail.next;
            
            cur.next = tail;
            tail.next = l1;
            cur = l1;
            tail = l2;
            
        }
    }
}
