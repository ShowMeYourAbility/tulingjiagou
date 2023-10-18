package com.llmcu.tulingjiagou.l01jvm;

/**
 * xxxx
 *
 * @author liuling
 * @date 2022/11/28 13:36
 */
public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static boolean isPalindrome(ListNode head) {
        ListNode fastCursor = head;
        ListNode slowCursor = head;
        while(fastCursor.next!=null&&fastCursor.next.next!=null){
            fastCursor=fastCursor.next.next;
            slowCursor=slowCursor.next;
        }
        if(fastCursor.next!=null){
            // slowCursor=slowCursor.next;
            fastCursor=fastCursor.next;
        }
        // reverse()
        reverse(slowCursor);
        slowCursor=head;

        while(slowCursor!=null){
            if(slowCursor.val!=fastCursor.val){
                return false;
            }
            slowCursor=slowCursor.next;
            fastCursor=fastCursor.next;
        }
        return true;
    }

    public static void reverse(ListNode head){
        ListNode pre = head;
        ListNode current = head.next;
        while(current!=null){
            ListNode temp =current.next;
            current.next=pre;
            pre=current;
            current=temp;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        System.out.println(ListNode.isPalindrome(node1));
    }
}
