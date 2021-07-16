import java.util.LinkedList;
import java.util.Queue;

/**
 * 复习题目
 */
public class RetryDemo {
    public static void main(String[] args) {
        ListNode demo = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        System.err.println(reverseKGroup(demo, 2));
        System.err.println(reverseList(demo));
    }


    /**
     * 101. 对称二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (queue.size() > 0) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }

            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);

        }
        return true;
    }


    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    /**
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * <p>
     * k 是一个正整数，它的值小于或等于链表的长度。
     * <p>
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * <p>
     * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
     * 输入：head = [1,2,3,4,5], k = 3
     * 输出：[3,2,1,4,5]
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode preNode = dummy;
        ListNode start = head;
        while (start != null) {
            ListNode end = start;
            for (int i = 1; i < k; i++) {
                end = end.next;
                if (end == null) {
                    return dummy.next;
                }
            }
            ListNode next = end.next;
            ListNode[] reverseData = reverse(start, end);
            preNode.next = reverseData[0];
            end = reverseData[1];
            end.next = next;

            preNode = end;
            start = next;
        }

        return dummy.next;
    }

    private static ListNode[] reverse(ListNode start, ListNode end) {
        ListNode pre = end.next;
        ListNode curr = start;
        while (pre != end) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return new ListNode[]{end, start};
    }

}
