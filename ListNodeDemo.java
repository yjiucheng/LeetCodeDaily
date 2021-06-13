import java.util.*;

/**
 * 链表
 */
public class ListNodeDemo {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        listNode1.next = listNode2;

//        ListNode demo = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        System.err.println(reverseBetween(demo, 2, 4));


        ListNode demo1 = new ListNode(7, new ListNode(1, new ListNode(6)));
        ListNode demo2 = new ListNode(5, new ListNode(9, new ListNode(2)));
        ListNode demo3 = new ListNode(9, new ListNode(9));
        ListNode demo4 = new ListNode(1);
        ListNode[] dat = new ListNode[]{demo1, demo2, demo3};
//        System.err.println(mergeKLists(dat));

        ListNode demo = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(2,new ListNode(1))))));
        System.err.println(isPalindrome(demo));
    }

    /**
     * 234. 回文链表
     * 请判断一个链表是否为回文链表。
     * 输入: 1->2
     * 输出: false
     * 输入: 1->2->2->1
     * 输出: true
     * <p>
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     */
    public static boolean isPalindrome(ListNode head) {
        //常规思路
//        List<Integer> list = new ArrayList<>();
//        ListNode temp = head;
//        while (temp != null) {
//            list.add(temp.val);
//            temp = temp.next;
//        }
//        if (list.size() == 1) {
//            return true;
//        }
//        for (int index = 0; index < list.size() / 2; index++) {
//            if (list.get(index) != list.get(list.size() - 1 - index)) {
//                return false;
//            }
//        }

        //O(n) 时间复杂度和 O(1) 空间复杂度
        int cout = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            cout++;
        }
        if (cout == 1) {
            return true;
        }
        //反转前count/2部分链表
        int index = 1;
        ListNode pre = null;
        ListNode cur = head;
        while (index <= cout / 2) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            index++;
        }
        cur = cout % 2 == 0 ? cur : cur.next;
        while (pre != null) {
            if (pre.val != cur.val) {
                return false;
            }
            pre = pre.next;
            cur = cur.next;
        }
        return true;
    }


    /**
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
     * 如果两个链表没有交点，返回 null 。
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode planA = headA;
        ListNode planB = headB;
        while (planA != planB) {
            if (planA == null) {
                planA = headB;
            } else {
                planA = planA.next;
            }
            if (planB == null) {
                planB = headA;
            } else {
                planB = planB.next;
            }
        }
        return planA;
    }


    /**
     * 给定两个用链表表示的整数，每个节点包含一个数位。
     * <p>
     * 这些数位是反向存放的，也就是个位排在链表首部。
     * <p>
     * 编写函数对这两个整数求和，并用链表形式返回结果。
     * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
     * 输出：2 -> 1 -> 9，即912
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        int val = l1.val + l2.val;

        boolean needPlusNext = false;
        ListNode dummy = new ListNode(-1);
        ListNode ans = new ListNode();
        if (val >= 10) {
            needPlusNext = true;
            ans.val = val - 10;
        } else {
            ans.val = val;
        }

        dummy.next = ans;
        ListNode first = l1.next;
        ListNode second = l2.next;
        while (first != null || second != null) {
            if (first == null || second == null) {
                if (needPlusNext) {
                    needPlusNext = false;
                    if (first == null) {
                        int sum = second.val + 1;
                        if (sum >= 10) {
                            second.val = sum - 10;
                            needPlusNext = true;
                        } else {
                            second.val = sum;
                        }
                        ans.next = second;
                        second = second.next;
                    } else {
                        int sum = first.val + 1;
                        if (sum >= 10) {
                            first.val = sum - 10;
                            needPlusNext = true;
                        } else {
                            first.val = sum;
                        }
                        ans.next = first;
                        first = first.next;
                    }
                }
            } else {
                int sum = first.val + second.val;
                if (needPlusNext) {
                    sum += 1;
                    needPlusNext = false;
                }
                ListNode node = new ListNode();
                if (sum >= 10) {
                    needPlusNext = true;
                    node.val = sum - 10;
                } else {
                    node.val = sum;
                }
                ans.next = node;
                ans = node;
                first = first.next;
                second = second.next;
            }
        }
        if (needPlusNext) {
            ans.next = new ListNode(1);
        }

        return dummy.next;
    }


    /**
     * 存在一个按升序排列的链表，给你这个链表的头节点 head ，
     * 请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
     * <p>
     * 返回同样按升序排列的结果链表。
     * <p>
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = head;
        int lastDelVal = Integer.MIN_VALUE;
        while (cur != null) {
            if (cur.val == lastDelVal) {
                pre.next = cur.next;
                cur = cur.next;
            } else {
                if (cur.next != null) {
                    if (cur.val == cur.next.val) {
                        lastDelVal = cur.val;
                        cur = cur.next.next;
                        pre.next = cur;
                    } else {
                        pre = cur;
                        cur = cur.next;
                    }
                } else {
                    break;
                }
            }
        }
        return dummy.next;
    }

    public int[] reversePrint(ListNode head) {

        if (head == null) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        int[] ans = new int[list.size()];
        for (int index = list.size() - 1; index >= 0; index--) {
            ans[list.size() - 1 - index] = list.get(index);
        }

        return ans;
    }

    /**
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        ListNode curr = head;
        ListNode target = null;
        int count = 1;
        HashMap<Integer, ListNode> map = new HashMap<>();
        while (curr != null) {
            map.put(count, curr);
            curr = curr.next;
            if (curr != null) {
                count++;
            }
        }
        if (count == 1) {
            return head;
        }

        int targetIndex = 0;
        if (k % count == 0) {
            return head;
        } else {
            targetIndex = count + 1 - k % count;
        }
        target = map.get(targetIndex);

        ListNode preTarget = map.get(targetIndex - 1);
        if (preTarget == null) {
            return head;
        }
        preTarget.next = null;
        map.get(count).next = map.get(1);

        return target;
    }


    /**
     * 给你一个链表数组，每个链表都已经按升序排列。
     * <p>
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * <p>
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode ans = lists[0];
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = ans;
        for (int index = 1; index < lists.length; index++) {
            ListNode node = lists[index];
            ans = mergeTwo(ans, node);
        }
        return ans;
    }

    private static ListNode mergeTwo(ListNode target, ListNode node) {
        if (target == null) {
            return node;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE, target);
        ListNode pre = dummy;
        ListNode curr = target;
        while (node != null) {
            if (curr.val <= node.val) {
                if (curr.next == null) {
                    curr.next = node;
                    return dummy.next;
                } else {
                    pre = curr;
                    curr = curr.next;
                }
            } else {
                ListNode tempNext = node.next;
                pre.next = node;
                node.next = curr;
                curr = node;
                node = tempNext;
            }
        }
        return dummy.next;
    }


    /**
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
     * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * <p>
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
     * <p>
     * 输入：head = [1,2,3,4,5], left = 2, right = 4
     * 输出：[1,4,3,2,5]
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (right == left) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode curr = pre;
        ListNode end = null;
        ListNode start = null;
        int index = 0;
        while (curr != null) {
            if (index == left - 1) {
                pre = curr;
            }
            if (index == left) {
                start = curr;
            }
            if (index == right) {
                end = curr;
                break;
            }
            curr = curr.next;
            index++;
        }
        ListNode next = end.next;

        ListNode tempP = end.next;
        ListNode tempC = start;
        while (tempP != end) {
            ListNode temp = tempC.next;
            tempC.next = tempP;
            tempP = tempC;
            tempC = temp;
        }
        start.next = next;
        pre.next = end;
        return dummy.next;
    }


    /**
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * <p>
     * k 是一个正整数，它的值小于或等于链表的长度。
     * <p>
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * <p>
     * 进阶：
     * <p>
     * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     * <p>
     * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode start = head;
        ListNode dummy = new ListNode(-1);
        dummy.next = start;
        ListNode pre = dummy;
        while (start != null) {
            ListNode end = start;
            for (int i = 1; i < k; i++) {
                end = end.next;
                if (end == null) {
                    return dummy.next;
                }
            }
            ListNode next = end.next;
            ListNode[] data = reverse(start, end);
            start = data[0];
            end = data[1];
            pre.next = start;
            end.next = next;
            pre = end;
            start = next;
        }

        return dummy.next;
    }


    private static ListNode[] reverse(ListNode head, ListNode end) {
        /**
         *     ListNode prev = tail.next;
         ListNode curr = head;
         while (prev != tail) {
         ListNode nex = curr.next;
         curr.next = prev;
         prev = p;
         curr = nex;
         }
         return new ListNode[]{tail, head};
         */

        ListNode curr = head;
        ListNode pre = end.next;
        while (pre != end) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return new ListNode[]{end, head};
    }


    /**
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * <p>
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
     * <p>
     * 说明：不允许修改给定的链表。
     * <p>
     * 进阶：
     * <p>
     * 你是否可以使用 O(1) 空间解决此题？
     * <p>
     * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        ListNode newIndex = head;
        while (newIndex != slow) {
            newIndex = newIndex.next;
            slow = slow.next;
        }
        return newIndex;
    }


    /**
     * 给定两个（单向）链表，判定它们是否相交并返回交点。
     * 请注意相交的定义基于节点的引用，而不是基于节点的值。
     * 换句话说，如果一个链表的第k个节点与另一个链表的第j个节点是同一节点（引用完全相同），则这两个链表相交。
     * <p>
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        HashMap<ListNode, Integer> map = new HashMap<>();
        Set<ListNode> set = new HashSet<>();
        ListNode currA = headA;
        while (currA != null) {
            if (!set.contains(currA)) {
                set.add(currA);
            }
            currA = currA.next;
        }
        ListNode currB = headB;
        while (currB != null) {
            if (set.contains(currB)) {
                return currB;
            }
            currB = currB.next;
        }
        return null;
    }

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * <p>
     * 进阶：你能尝试使用一趟扫描实现吗？
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        //todo  双指针法
        if (head == null) {
            return null;
        }

        int cout = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            cout++;
        }
        int targetCount = 0;
        ListNode preNode = new ListNode(-1);
        ListNode curr = head;
        preNode.next = curr;
        ListNode pre = preNode;
        while (curr != null) {
            if (targetCount == cout - n) {
                pre.next = curr.next;
                break;
            } else {
                pre = curr;
                curr = curr.next;
            }
            targetCount++;
        }
        return preNode.next;
    }

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode sentinelData = new ListNode(-1);
        ListNode curr = head;
        sentinelData.next = curr;
        ListNode pre = sentinelData;
        while (curr != null && curr.next != null) {
            ListNode nexNode = curr.next;
            ListNode temp = nexNode.next;
//            ListNode data = curr;
            curr.next = temp;
            nexNode.next = curr;
            pre.next = nexNode;
            pre = nexNode.next;
            curr = nexNode.next.next;
        }
        return sentinelData.next;
    }


    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * 输入：head = [1,2,3,4,5]
     * 输出：[5,4,3,2,1]
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode curr = head;
        ListNode pre = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }


    /**
     * 203. 移除链表元素
     * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
     * (https://leetcode-cn.com/problems/remove-linked-list-elements/)
     * <p>
     * 输入：head = [1,2,6,3,4,5,6], val = 6
     * 输出：[1,2,3,4,5]
     * <p>
     * 输入：head = [], val = 1
     * 输出：[]
     * <p>
     * 输入：head = [7,7,7,7], val = 7
     * 输出：[]
     */
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode preHead = new ListNode(Integer.MAX_VALUE);
        preHead.next = head;

        while (head.next != null) {
            if (head.next.val == val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return preHead.next.val != val ? preHead.next : preHead.next.next;
    }
}
