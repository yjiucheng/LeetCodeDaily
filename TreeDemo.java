import java.util.*;

public class TreeDemo {
    public static void main(String[] args) {
        int sum = 5;
        System.err.println(sum / (double) 2);
    }


    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * <p>
     * 初始状态下，所有 next 指针都被设置为 NULL。
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }


        Node head = root;
        while (head.left != null) {
            Node cur = head;
            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            head = head.left;
        }

        //BFS
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(root);
//        while (queue.size() > 0) {
//            int count = queue.size();
//            Node pre = null;
//            for (int i = 0; i < count; i++) {
//                Node node = queue.poll();
//                if (pre != null) {
//                    pre.next = node;
//                }
//                pre = node;
//                if (node.left != null) {
//                    queue.add(node.left);
//                }
//                if (node.right != null) {
//                    queue.add(node.right);
//                }
//            }
//        }

        return root;
    }


    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }


    /**
     * 515. 在每个树行中找最大值
     * 您需要在二叉树的每一行中找到最大的值。
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        getMaxLevel(root, ans, 0);
        //BFS
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while (queue.size() > 0) {
//            int count = queue.size();
//            int max = Integer.MIN_VALUE;
//            for (int i = 0; i < count; i++) {
//                TreeNode node = queue.poll();
//                max = Math.max(max, node.val);
//                if (node.right != null) {
//                    queue.add(node.right);
//                }
//                if (node.left != null) {
//                    queue.add(node.left);
//                }
//            }
//            ans.add(max);
//        }
        return ans;
    }


    private void getMaxLevel(TreeNode node, List<Integer> ans, int depth) {
        if (node == null) {
            return;
        }
        if (ans.size() <= depth) {
            ans.add(node.val);
        } else {
            int cur = ans.get(depth);
            ans.set(depth, Math.max(cur, node.val));
        }

        if (node.left != null) {
            getMaxLevel(node.left, ans, depth + 1);
        }

        if (node.right != null) {
            getMaxLevel(node.right, ans, depth + 1);
        }

    }


    /**
     * 429. N 叉树的层序遍历
     * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
     * <p>
     * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
     * 输入：root = [1,null,3,2,4,null,5,6]
     * 输出：[[1],[3,2,4],[5,6]]
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        getLevelOrder(root, ans, 0);
        return ans;
    }

    private void getLevelOrder(Node node, List<List<Integer>> ans, int depth) {
//        if (node == null) {
//            return;
//        }
//        if (ans.size() <= depth) {
//            ans.add(new ArrayList<>());
//        }
//        ans.get(depth).add(node.val);
//        for (Node node1 : node.children) {
//            getLevelOrder(node1, ans, depth + 1);
//        }
    }

//    public static class Node {
//        public int val;
//        public List<Node> children;
//
//        public Node() {
//        }
//
//        public Node(int _val) {
//            val = _val;
//        }
//
//        public Node(int _val, List<Node> _children) {
//            val = _val;
//            children = _children;
//        }
//
//    }

    /**
     * 637. 二叉树的层平均值
     * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int count = queue.size();
            double sum = 0;
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.remove();
                sum += node.val;

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ans.add(sum / count);
        }

        return ans;

    }


    /**
     * 199. 二叉树的右视图
     * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        //DFS解法
        getRightVal(root, 0, ans);
//        BFS解法
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.add(root);
//        while (queue.size() > 0) {
//            int count = queue.size();
//            for (int i = 0; i < count; i++) {
//                TreeNode node = queue.poll();
//                if (i == count - 1) {
//                    ans.add(node.val);
//                }
//                if (node.left != null) {
//                    queue.add(node.left);
//                }
//                if (node.right != null) {
//                    queue.add(node.right);
//                }
//            }
//        }
        return ans;
    }


    private void getRightVal(TreeNode node, int depth, List<Integer> list) {
        if (node == null) {
            return;
        }
        if (depth == list.size()) {
            list.add(node.val);
        }
        depth++;
        getRightVal(node.right, depth, list);
        getRightVal(node.left, depth, list);
    }

    /**
     * 107. 二叉树的层序遍历 II
     * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int count = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

            }
            ans.add(0, list);
        }
        return ans;
    }


    /**
     * 102. 二叉树的层序遍历
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int count = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ans.add(list);
        }
        return ans;
    }

    private void getLevelQueue(TreeNode treeNode, Queue<TreeNode> queue) {

    }

    /**
     * 100. 相同的树
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     * <p>
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        boolean ans = true;

        return ans;
    }


    /**
     * 144. 二叉树的前序遍历
     * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (stack.size() > 0) {
            TreeNode node = stack.pop();
            ans.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return ans;
    }

    public void preOrder(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        ans.add(root.val);
        preOrder(root.left, ans);
        preOrder(root.right, ans);
    }

    /**
     * 145. 二叉树的后序遍历
     * 给定一个二叉树，返回它的 后序 遍历。
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> stackOri = new Stack<>();
        Stack<TreeNode> stackRes = new Stack<>();
        stackOri.push(root);
        while (!stackOri.isEmpty()) {
            TreeNode node = stackOri.pop();
            stackRes.push(node);

            if (node.left != null) {
                stackOri.push(node.left);
            }
            if (node.right != null) {
                stackOri.push(node.right);
            }
        }
        while (!stackRes.isEmpty()) {
            ans.add(stackRes.pop().val);
        }


        return ans;
    }

    public void afterOrder(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        afterOrder(root.left, ans);
        afterOrder(root.right, ans);
        ans.add(root.val);
    }

    /**
     * 94. 二叉树的中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            ans.add(node.val);
            if (node.right != null) {
                cur = node.right;
            }
        }


        return ans;
    }

    public void inorder(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        inorder(root.left, ans);
        ans.add(root.val);
        inorder(root.right, ans);
    }

}
