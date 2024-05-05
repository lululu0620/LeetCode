class LC234 {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode reverse = reverseList(slow);
        while (head != null && reverse != null) {
            if (head.val != reverse.val) return false;
            head = head.next;
            reverse = reverse.next;
        }
        return true;
    }

    // Copy code from LC-206
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