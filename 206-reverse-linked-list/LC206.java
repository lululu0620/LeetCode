class LC206 {
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode node = head;
        while (node != null) {
            ListNode next = node.next;
            node.next = dummy.next;
            dummy.next = node;
            node = next;
        }
        return dummy.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}