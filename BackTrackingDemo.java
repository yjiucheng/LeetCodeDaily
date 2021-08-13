import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackTrackingDemo {

    public static void main(String[] args) {
        System.err.println(combine(4, 2));
    }


    /**
     * 77. 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * <p>
     * 你可以按 任何顺序 返回答案。
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
//        if (k > n) {
//            return ans;
//        }
//        int startIndex = 1;
//        while (startIndex + k <= n) {
//            List<Integer> list = new ArrayList<>();
//            for (int i = 0; i < k; i++) {
//                list.add(startIndex + i);
//            }
//            ans.add(list);
//            startIndex++;
//        }
        return ans;
    }
}
