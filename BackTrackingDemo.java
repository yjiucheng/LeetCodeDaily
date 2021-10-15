import java.util.ArrayList;
import java.util.List;

/**
 * 回溯算法
 */
public class BackTrackingDemo {

    public static void main(String[] args) {
        System.err.println(combine(1, 1));
    }

    /**
     * 77. 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * <p>
     * 你可以按 任何顺序 返回答案。
     */

    static List<List<Integer>> ans77 = new ArrayList<>();

    public static List<List<Integer>> combine(int n, int k) {
        backtracking(n,k,1,new ArrayList<>());
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
