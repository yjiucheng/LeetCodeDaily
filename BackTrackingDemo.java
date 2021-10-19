import java.util.ArrayList;
import java.util.List;

/**
 * 回溯算法
 */
public class BackTrackingDemo {

    public static void main(String[] args) {
//        System.err.println(combine(1, 1));
        System.err.println(combinationSum3(3, 9));
    }


    /**
     * 216. 组合总和 III
     * 找出所有相中加之和为 n 的 k 个数的组合。组合只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     * <p>
     * 说明：
     * <p>
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。 
     * <p>
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * <p>
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     */
    static List<List<Integer>> ans216 = new ArrayList<>();

    public static List<List<Integer>> combinationSum3(int k, int n) {
//        for (int i = 1; i <= n - k; i++) {
        combinationSum3(k, n, 1, 0, new ArrayList<>());
//        }
        return ans216;
    }

    public static void combinationSum3(int targetCount, int targetSum, int startIndex, int lastSum, List<Integer> temp) {
        for (int i = startIndex; i <= targetSum - lastSum && i <= 9; i++) {
            if (lastSum + i < targetSum) {
                if (temp.size() < targetCount - 1) {
                    temp.add(i);
                    combinationSum3(targetCount, targetSum, i + 1, lastSum + i, temp);
                    temp.remove(temp.size() - 1);
                }
            } else {
                if (temp.size() == targetCount - 1) {
                    temp.add(i);
                    ans216.add(new ArrayList<>(temp));
                    temp.remove(temp.size() - 1);
                }
                return;
            }
        }
    }


    /**
     * 77. 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * <p>
     * 你可以按 任何顺序 返回答案。
     */

    static List<List<Integer>> ans77 = new ArrayList<>();

    public static List<List<Integer>> combine(int n, int k) {
        backtracking(n, k, 1, new ArrayList<>());
        return ans77;
    }

    public static void backtracking(int n, int k, int startIndex, List<Integer> temp) {
        if (temp.size() == k) {
            ans77.add(new ArrayList<>(temp));
            return;
        }
        for (int i = startIndex; i + k - temp.size() - 1 <= n; i++) {
            temp.add(i);
            backtracking(n, k, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }


}
