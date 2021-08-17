import java.util.*;

public class TreeDemo {
    public static void main(String[] args) {
        System.err.println(4 / 2);
        System.err.println(4 % 2);
        System.err.println(5 / 2);
        System.err.println(5 % 2);
        int[] preorder = {3, 9, 20, 15, 7}, inorder = {9, 3, 15, 20, 7};
//        System.err.println(buildTree(preorder, inorder));
//        TreeNode treeNode = new TreeNode(4, new TreeNode(9, new TreeNode(5), new TreeNode(1)), new TreeNode(0));
//        sumNumbers(treeNode);
//        System.err.println(sum);
    }


    /**
     * 701. 二叉搜索树中的插入操作
     * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。
     * 返回插入后二叉搜索树的根节点。
     * 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
     * <p>
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode ans = root;
        insertIntoBST2(root, val);
        return ans;
    }

    private void insertIntoBST2(TreeNode root, int val) {
        if (root.val > val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
                return;
            } else {
                insertIntoBST2(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(val);
                return;
            } else {
                insertIntoBST2(root.right, val);
            }
        }
    }


    /**
     * 235. 二叉搜索树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor3(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor3(root.left, p, q);
        } else {
            return root;
        }
    }


    /**
     * 面试题 04.02. 最小高度树
     * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] num, int start, int end) {
        if (start > end)
            return null;
        int mid = (start + end) >> 1;
        TreeNode root = new TreeNode(num[mid]);
        root.left = sortedArrayToBST(num, start, mid - 1);
        root.right = sortedArrayToBST(num, mid + 1, end);
        return root;
    }

    /**
     * 112. 路径总和
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，
     * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
     * <p>
     * 叶子节点 是指没有子节点的节点。
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }
        return hasPath(root.left, targetSum, root.val) || hasPath(root.right, targetSum, root.val);
    }

    private boolean hasPath(TreeNode root, int target, int sum) {
        if (root == null) {
            return false;
        }
        if (sum + root.val == target && (root.left == null && root.right == null)) {
            return true;
        }
        return hasPath(root.left, target, root.val + sum) || hasPath(root.right, target, root.val + sum);
    }

    /**
     * 100. 相同的树
     * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
     * <p>
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }


    /**
     * 129. 求根节点到叶节点数字之和
     * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
     * 每条从根节点到叶节点的路径都代表一个数字：
     * <p>
     * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
     * 计算从根节点到叶节点生成的 所有数字之和 。
     * <p>
     * 叶节点 是指没有子节点的节点。
     */
    static int sum = 0;

    public static int sumNumbers(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        } else {
            getVal(root, new StringBuilder());
        }
        return sum;
    }

    private int getVal(TreeNode root, int pre) {
        if (root == null) {
            return 0;
        }
        int sum = pre * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return getVal(root.left, sum) + getVal(root.right, sum);
        }
    }


    private static String getVal(TreeNode root, StringBuilder sb) {
        if (root.left == null && root.right == null) {
            return root.val + "";
        }
        sb.append(root.val + "");
        if (root.left != null) {
            String left = getVal(root.left, sb);
            if (left != null) {
                sb.append(left);
                sum += Integer.valueOf(sb.toString());
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        if (root.right != null) {
            String right = getVal(root.right, sb);
            if (right != null) {
                sb.append(right);
                sum += Integer.valueOf(sb.toString());
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return null;
    }


    /**
     * 236. 二叉树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestor1(root, p, q);
    }

    private TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        if (left != null && right != null) {// 左右子树分别找到了，说明此时的root就是要求的结果
            return root;
        }
        if (left == null) {
            return right;
        }
        return left;
    }

    /**
     * 543. 二叉树的直径
     * 给定一棵二叉树，你需要计算它的直径长度。
     * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
     * 这条路径可能穿过也可能不穿过根结点。
     */
    int maxLength = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        maxLength = Math.max(maxLength, leftDepth + rightDepth + 1);

        return Math.max(leftDepth, rightDepth) + 1; // 返回该节点为根的子树的深度
    }


    /**
     * 501. 二叉搜索树中的众数
     *
     * @param root
     * @return
     */
    TreeNode preNode = null;
    int maxCount = 0;
    int count = 0;
    List<Integer> list = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        dfsfindMode(root);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void dfsfindMode(TreeNode root) {
        if (root == null) {
            return;
        }
        dfsfindMode(root.left);
        if (preNode == null || preNode.val != root.val) {
            count = 1;
        } else {
            count++;
        }

        if (count > maxCount) {
            list.clear();
            list.add(root.val);
            maxCount = count;
        } else if (count == maxCount) {
            list.add(root.val);
        }
        preNode = root;
        dfsfindMode(root.right);
    }


    /**
     * 530. 二叉搜索树的最小绝对差
     * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
     */
    int ans2 = Integer.MAX_VALUE;
    int pre = -1;

    public int getMinimumDifference(TreeNode root) {
        dfsMid(root);
        return ans2;
    }

    private void dfsMid(TreeNode node) {
        if (node == null) {
            return;
        }
        dfsMid(node.left);
        if (pre == -1) {
            pre = node.val;
        } else {
            ans2 = Math.min(ans2, node.val - pre);
            pre = node.val;
        }
        dfsMid(node.right);
    }

    /**
     * 98. 验证二叉搜索树
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 假设一个二叉搜索树具有如下特征：
     * <p>
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     */
    TreeNode max;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = isValidBST(root.left);
        if (!left) {
            return false;
        }
        // 中
        if (max != null && root.val <= max.val) {
            return false;
        }
        max = root;
        // 右
        boolean right = isValidBST(root.right);
        return right;
    }


    /**
     * 700. 二叉搜索树中的搜索
     * 给定二叉搜索树（BST）的根节点和一个值。
     * 你需要在BST中找到节点值等于给定值的节点。
     * 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    private

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * <p>
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * <p>
     * 注意:
     * 你可以假设树中没有重复的元素。
     * <p>
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     */

    static Map<Integer, Integer> map = new HashMap<>();

    //todo 过几天在回顾
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        //前序排列：[根节点,(左边节点),(右边节点)]
        //中序排列：[(左边节点),根节点,(右边节点)]
        //后序排列：[(左边节点),(右边节点),根节点]
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(postorder[i], i);
        }
        return null;
    }


    /**
     * 105. 从前序与中序遍历序列构造二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        //前序排列：[根节点,(左边节点),(右边节点)]
        //中序排列：[(左边节点),根节点,(右边节点)]
        //前序排列的第一个元素就是根节点，通过根节点在中序排列中的下标，可确定左边节点和右边节点的长度
        //记录中序排列中所有(元素，下标),方便后续直接取下标
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildChildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private static TreeNode buildChildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }
        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = map.get(preorder[preorder_root]);
        TreeNode node = new TreeNode();
        node.val = preorder[preorder_root];

        int size = inorder_root - inorder_left;
//        int leftTreeStartIndex = preorder_left  + 1;
//        int leftTreeEndIndex = leftTreeStartIndex + size;
        node.left = buildChildTree(preorder, inorder, preorder_left + 1, preorder_left + size, inorder_left, inorder_root - 1);
        node.right = buildChildTree(preorder, inorder, preorder_left + size + 1, preorder_right, inorder_root + 1, inorder_right);
        return node;
    }


    /**
     * 671. 二叉树中第二小的节点
     *
     * @param root
     * @return
     */
    int ans;
    int rootvalue;

    public int findSecondMinimumValue(TreeNode root) {
        ans = -1;
        rootvalue = root.val;
        findLessMin(root);
        return ans;
    }

    private void findLessMin(TreeNode root) {
        if (root == null) {
            return;
        }
        if (ans != -1 && root.val >= ans) {
            return;
        }
        if (root.val > rootvalue) {
            ans = root.val;
        }

        findLessMin(root.left);
        findLessMin(root.right);
    }


    /**
     * 513. 找树左下角的值
     * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
     * <p>
     * 假设二叉树中至少有一个节点。
     */
    public int findBottomLeftValue(TreeNode root) {
        int ans = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    ans = node.val;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return ans;
    }

    /**
     * 404. 左叶子之和
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        int ans = 0;
        if (root == null) {
            return 0;
        }
        if (root.left != null && root.left.left == null && root.left.right == null) {
            ans += root.left.val;
        }
        ans += sumOfLeftLeaves(root.left);
        ans += sumOfLeftLeaves(root.right);
        return ans;
    }


    /**
     * 559. N 叉树的最大深度
     */
    public int maxDepth(Node2 root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }

//        Queue<Node2> queue = new LinkedList<>();
//        queue.add(root);
//        while (queue.size() > 0) {
//            int count = queue.size();
//            for (int i = 0; i < count; i++) {
//                Node2 node2 = queue.poll();
//                if (node2.children != null && node2.children.size() > 0) {
//                    queue.addAll(node2.children);
//                }
//            }
//            depth++;
//        }
        return depth;
    }

    private int getDepth(Node2 node2) {
        if (node2 == null) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < node2.children.size(); i++) {
            max = Math.max(max, getDepth(node2.children.get(i)));
        }
        return max + 1;
    }

    class Node2 {
        public int val;
        public List<Node2> children;

        public Node2() {
        }

        public Node2(int _val) {
            val = _val;
        }

        public Node2(int _val, List<Node2> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    /**
     * 257. 二叉树的所有路径
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        getAllPath(ans, root, new ArrayList<>());
        return ans;
    }


    private void getAllPath(List<String> ans, TreeNode node, List<Integer> path) {
        path.add(node.val);
        if (node.left == null && node.right == null) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                builder.append(path.get(i));
                if (i != path.size() - 1) {
                    builder.append("->");
                }

            }
            ans.add(builder.toString());
            return;
        }
        if (node.left != null) {
            getAllPath(ans, node.left, path);
            path.remove(path.size() - 1);
        }
        if (node.right != null) {
            getAllPath(ans, node.right, path);
            path.remove(path.size() - 1);
        }
    }


    /**
     * 110. 平衡二叉树
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * <p>
     * 本题中，一棵高度平衡二叉树定义为：
     * <p>
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(getOneDepth(root.left) - getOneDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }


    private int getOneDepth(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(getOneDepth(node.left), getOneDepth(node.right)) + 1;
        }
    }


    /**
     * 222. 完全二叉树的节点个数
     * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
     * 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //常规遍历
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     * 111. 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     * <p>
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * <p>
     * 说明：叶子节点是指没有子节点的节点。
     */
    public int minDepth(TreeNode root) {


        if (root == null) {
            return 0;
        }
        //广度优先
        int minDepth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int count = queue.size();
            minDepth++;
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return minDepth;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

            }
        }
        return minDepth;


        //深度优先
//        int leftDepth = minDepth(root.left);
//        int rightDepth = minDepth(root.right);
//        if (root.left == null) {
//            return rightDepth + 1;
//        }
//        if (root.right == null) {
//            return leftDepth + 1;
//        }
//
//        return Math.min(leftDepth, rightDepth);
    }


    /**
     * 617. 合并二叉树
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode ans = new TreeNode(root1.val + root2.val);
        ans.left = mergeTrees(root1.left, root2.left);
        ans.right = mergeTrees(root1.right, root2.right);
        return ans;
    }

    private void merge(TreeNode ans, TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return;
        }
        if (root1 == null) {
            ans.val = root2.val;
            ans.left = root2.left;
            ans.right = root2.right;
            return;
        } else if (root2 == null) {
            ans.val = root1.val;
            ans.left = root1.left;
            ans.right = root1.right;
            return;
        } else {
            ans.val = root1.val + root2.val;
        }
        if (root1.left == null && root2.left == null) {
            ans.left = null;
        } else {
            TreeNode left = new TreeNode();
            ans.left = left;
            merge(ans.left, root1.left, root2.left);
        }

        if (root1.right == null && root2.right == null) {
            ans.right = null;
        } else {
            TreeNode right = new TreeNode();
            ans.right = right;
            merge(ans.right, root1.right, root2.right);
        }
    }

    /**
     * 104. 二叉树的最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int count = queue.size();
            if (count > 0) {
                depth++;
            }
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return depth;
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
        //递归解法
//        return compareTwo(root.left, root.right);

        //迭代
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        while (queue.size() > 0) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (right == null && left == null) {
                continue;
            }

            if (right == null || left == null || left.val != right.val) {
                return false;
            }
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);

        }

        return true;
    }


    private boolean compareTwo(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null && right != null) {
            return false;
        }
        if (left != null && right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        boolean out = compareTwo(left.left, right.right);
        boolean in = compareTwo(left.right, right.left);


        return out && in;

    }

    /**
     * 226. 翻转二叉树
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        //递归
//        preinvertTree(root);

        //迭代
//        Stack<TreeNode> stack = new Stack<>();
//        stack.push(root);
//        while (stack.size() > 0) {
//            TreeNode node = stack.pop();
//            TreeNode left = node.left;
//            node.left = node.right;
//            node.right = left;
//            if (node.left != null) {
//                stack.add(node.left);
//            }
//            if (node.right != null) {
//                stack.add(node.right);
//            }
//        }
        //BFS


        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() > 0) {
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                TreeNode lef = node.left;
                node.left = node.right;
                node.right = lef;

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return root;
    }

    private void preinvertTree(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode leftNode = node.left;
        node.left = node.right;
        node.right = leftNode;

        preinvertTree(node.left);
        preinvertTree(node.right);
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
