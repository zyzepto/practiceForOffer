package AlgorithmDemo;

import java.util.List;

public class LinkedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
//        reorderList(node1);
//        node1 = reverseLinkedList(node1);
        node1 = sortList(node1);
        while (node1 != null){
            System.out.println(node1.val);
            node1 = node1.next;
        }
    }

    public static ListNode reverseLinkedList(ListNode head){
        ListNode preNode = null;
        ListNode current = head;
        ListNode nextNode = null;
        while (current.next != null){
            nextNode = current.next;
            current.next = preNode;
            preNode = current;
            current = nextNode;
        }
        current.next = preNode;
        return current;
    }

    /**
     * @author zepto
     * @Description leetCode 82 删除链表重复节点 设置一个辅助头结点
     * @date 2018/9/26
     * @return AlgorithmDemo.ListNode
     **/
    public static ListNode deleteDuplicates(ListNode head){
        ListNode newHead = new ListNode(0);
        ListNode cur = newHead;
        if (head == null || head.next == null) return head;
        while (head != null){
            ListNode p = head;
            int count = 0;
            while (p != null && p.val == head.val){
                p = p.next;
                count++;
            }
            if (count == 1){
                cur.next = head;
                cur = cur.next;
                head = p;
            }else {
                head = p;
            }
        }
        cur.next = null;
        return newHead.next;
    }

    /**
     * @author zepto
     * @Description leetCode 86 链表分割 重新建立链表
     * @date 2018/9/27
     * @return AlgorithmDemo.ListNode
     **/
    public static ListNode partition(ListNode head, int x) {
        ListNode smallBefore = null;
        ListNode smallAfter = null;
        ListNode bigBefore = null;
        ListNode bigAfter = null;
        ListNode cur = head;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = null;
            if (cur.val < x){
                if (smallBefore == null){
                    smallBefore = cur;
                    smallAfter = cur;
                }else {
                    smallAfter.next = cur;
                    smallAfter = cur;
                }
            }else {
                if (bigBefore == null){
                    bigBefore = cur;
                    bigAfter = cur;
                }else {
                    bigAfter.next = cur;
                    bigAfter = cur;
                }
            }
            cur = temp;
        }
        if (smallBefore == null){
            return bigBefore;
        }
        smallAfter.next = bigBefore;
        return smallBefore;
    }

    /**
     * @author zepto
     * @Description leetCode 链表反转 2
     * @date 2018/10/16
     * @return AlgorithmDemo.ListNode
     **/
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode current = head;
        ListNode preNode = null, next = null;
        ListNode left1 = null, left2 = null;
        int index = 1;
        while (index < m){
            left1 = current;
            current = current.next;
            index++;
        }
        left2 = current;
        while (current != null && index <= n){
            next = current.next;
            current.next = preNode;
            preNode = current;
            current = next;
            index++;
        }
        left1.next = preNode;
        left2.next = current;
        return head;
    }

    /**
     * @author zepto
     * @Description leetcode 环的入口
     * @date 2018/10/24
     * @return AlgorithmDemo.ListNode
     **/
    public static ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return null;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * @author zepto
     * @Description leetCode 143. 重排链表
     * @date 2018/10/25
     * @return void
     **/
    public static void reorderList(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next;
        slow.next = null;
        slow = head;
        fast = reverseLinkedList(fast);
        while (fast != null){
            ListNode temp = slow.next;
            slow.next = fast;
            fast = fast.next;
            slow = slow.next;
            slow.next = temp;
            slow =slow.next;
        }
    }

    /**
     * @author zepto
     * @Description 147. 对链表进行插入排序
     * @date 2018/10/25
     * @return AlgorithmDemo.ListNode
     **/
    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode unSortedHead = head.next, sortedHead = head, sortedTail = head, pre = null;
        ListNode cur = head;
        while (unSortedHead != null){
            ListNode next = unSortedHead.next;
            sortedTail.next = null;
            while (cur != null) {
                if (cur.val > unSortedHead.val){
                    if (pre != null){
                        pre.next = unSortedHead;
                        unSortedHead.next = cur;
                    }else {
                        unSortedHead.next = cur;
                        head = unSortedHead;
                    }
                    break;
                }else {
                    pre = cur;
                    cur = cur.next;
                }
            }
            if (cur == null){
                pre.next = unSortedHead;
                sortedTail = unSortedHead;
            }
            unSortedHead = next;
            pre = null;
            cur = head;
        }
        return head;
    }

    /**
     * @author zepto
     * @Description 148. 排序链表
     * @date 2018/10/25
     * @return AlgorithmDemo.ListNode
     **/
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next;
        slow.next = null;
        slow = head;
        ListNode node1 = sortList(slow);
        ListNode node2 = sortList(fast);
        return merge(node1, node2);
    }

    public static ListNode merge(ListNode node1, ListNode node2){
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        ListNode head = null, cur = null;
        if (node1.val < node2.val){
            head = node1;
            node1 = node1.next;
        }else {
            head = node2;
            node2 = node2.next;
        }
        cur = head;
        while (node1 != null && node2 != null){
            if (node1.val < node2.val){
                cur.next = node1;
                cur = cur.next;
                node1 = node1.next;
            }else {
                cur.next = node2;
                cur = cur.next;
                node2 = node2.next;
            }
        }
        if (node1 == null){
            cur.next = node2;
        }
        if (node2 == null){
            cur.next = node1;
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
