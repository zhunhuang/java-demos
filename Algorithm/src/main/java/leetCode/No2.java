package leetCode;

/**
 * description:
 *
 * https://leetcode-cn.com/problems/add-two-numbers/
 * @author zhun.huang 2019-08-02
 */
public class No2 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            ListNode result = new ListNode(0);
            ListNode head = result;
            ListNode tail = result;

            int incr = 0;
            ListNode cl = l1;
            ListNode cr = l2;

            while (cl != null && cr !=null) {
                int x = (cl.val+cr.val+incr)%10;
                incr = (cl.val+cr.val+incr)/10;
                tail = result;
                result.val=x;
                ListNode listNode = new ListNode(0);
                result.next = listNode;
                result = listNode;
                cl = cl.next;
                cr = cr.next;
            }

            while (cl != null) {
                int x = (cl.val+incr)%10;
                incr = (cl.val+incr)/10;
                tail = result;
                result.val = x;
                ListNode listNode = new ListNode(0);
                result.next = listNode;
                result = listNode;
                cl = cl.next;
            }

            while (cr != null) {
                int x = (cr.val+incr)%10;
                incr = (cr.val+incr)/10;
                tail = result;
                result.val = x;
                ListNode listNode = new ListNode(0);
                result.next = listNode;
                result = listNode;
                cr = cr.next;
            }
            if (incr==0) {
                tail.next = null;
            } else {
                tail.next.val=incr;
                tail.next.next=null;
            }
            return head;
        }
    };
}
