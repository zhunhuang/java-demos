package leetCode;

/**
 * description:
 *
 * @author zhun.huang 2019-08-04
 */
public class No24 {

    public ListNode swapPairs(ListNode head) {
        if (head==null || head.next == null) {
            return head;
        }
        ListNode currentPairFirst = head;
        ListNode lastPairSecond = null;
        while (currentPairFirst!=null && currentPairFirst.next!=null) {
            ListNode nextPairFirst = currentPairFirst.next.next;
            ListNode currentPairSecond = currentPairFirst.next;

            // swap
            ListNode tmp = currentPairFirst;
            currentPairFirst = currentPairSecond;
            currentPairSecond = tmp;

            // 链接上层
            if (lastPairSecond != null) {
                lastPairSecond.next = currentPairFirst;
            } else {
                head = currentPairFirst;
            }
            // 链接当前
            currentPairFirst.next = currentPairSecond;
            if (nextPairFirst == null) {
                currentPairSecond.next = null;
                break;
            }
            lastPairSecond = currentPairSecond;
            currentPairFirst = nextPairFirst;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    /**
     * 反转链表
     */
    public ListNode revertList(ListNode current, ListNode prev) {
        if (current == null) {
            return prev;
        }

        if (current.next == null) {
            current.next = prev;
            return current;
        }
        ListNode next = current.next;
        ListNode listNode = revertList(next, current);
        current.next = prev;
        return listNode;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        ListNode listNode = new No24().swapPairs(head);
        while (listNode!=null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
