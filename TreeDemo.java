import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeDemo {
    public static void main(String[] args) {

    }

    /**
     * 144. 二叉树的前序遍历
     * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
//        preOrder(root, ans);
        //递归遍历
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode tree = stack.pop();
            ans.add(tree.val);
            if (tree.right != null) {
                stack.push(tree.right);
            }
            if (tree.left != null) {
                stack.push(tree.left);
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
        afterOrder(root, ans);
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
        inorder(root, ans);
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
