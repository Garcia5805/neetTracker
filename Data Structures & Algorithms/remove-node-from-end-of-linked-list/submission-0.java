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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        ListNode prev = null;
        int size = 0;

        while(cur != null){
            size++;
            cur = cur.next;
        }
        int count = size - n ;
        cur = head;
        
        if(count == 0){return head.next;}

        size = 0;
        while(cur != null){
            if(size == count){
                prev.next = cur.next;
            }
            prev = cur;
            cur = cur.next;

            size++;

        }
        return head;
    }
}
