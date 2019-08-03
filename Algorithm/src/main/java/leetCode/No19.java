package leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author zhun.huang 2019-08-02
 */
public class No19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cn = head;
        List<ListNode> save = new ArrayList<>();
        while (cn!=null) {
            save.add(cn);
            cn = cn.next;
        }
        if (n==save.size()) {
            head = head.next;
            return head;
        }
        if (n==1) {
            save.get(save.size()-2).next = null;
            return head;
        }
        save.get(save.size()-n-1).next = save.get(save.size()-n+1);
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
