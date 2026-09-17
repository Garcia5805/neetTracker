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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null; 
        ListNode cur = head; //Create copy
        while(cur != null){
            ListNode temp = cur.next; //Save the next node, that links the rest of list
            cur.next = prev; // set next to null so 1->null
            prev = cur; // prev = 1;
            cur = temp; // cur = 2; and repeat.
        }
        return prev;
    }
}
