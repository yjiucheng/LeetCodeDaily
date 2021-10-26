import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 回溯算法
 */
public class BackTrackingDemo {

    public static void main(String[] args) {
//        System.err.println(combine(1, 1));
//        System.err.println(combinationSum3(3, 9));
//        System.err.println(letterCombinations(""));
        int[] nums = new int[]{10, 1, 2, 7, 6, 1, 5};
        int[] nums2 = new int[]{2, 5, 2, 1, 2};
//        System.err.println(combinationSum(nums, 7));
        System.err.println(combinationSum2(nums, 8));
//        System.err.println(combinationSum2(nums2, 5));
    }


    /**
     * 131. 分割回文串
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     * <p>
     * 回文串 是正着读和反着读都一样的字符串。
     * <p>
     * 输入：s = "aab"
     * 输出：[["a","a","b"],["aa","b"]]`
     */
    public List<List<String>> partition(String s) {

        return null;
    }


    /**
     * 40. 组合总和 II
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的每个数字在每个组合中只能使用一次。
     * <p>
     * 注意：解集不能包含重复的组合。 
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 输出:
     * [
     * [1,1,6],
     * [1,2,5],
     * [1,7],
     * [2,6]
     * ]
     * <p>
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 输出:
     * [
     * [1,2,2],
     * [5]
     * ]
     */
    static List<List<Integer>> ans40 = new ArrayList<>();

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSum40(candidates, target, 0, new ArrayList<>());
        return ans40;
    }

    public static void combinationSum40(int[] candidates, int target, int startIndex, List<Integer> temp) {
        if (0 == target) {
            ans40.add(new ArrayList<>(temp));
            return;
        }
        if (0 > target) {
            return;
        }
        for (int index = startIndex; index < candidates.length; index++) {
            if (index > startIndex && candidates[index] == candidates[index - 1]) {
                continue;
            }
            if (candidates[index] > target) {
                break;
            }
            int curNum = candidates[index];
            temp.add(curNum);
            combinationSum40(candidates, target - curNum, index + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }


    /**
     * 39. 组合总和
     * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
     * <p>
     * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。 
     * <p>
     * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static List<List<Integer>> ans39 = new ArrayList<>();

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSum39(candidates, target, new ArrayList<>(), 0, 0);
        return ans39;
    }

    public static void combinationSum39(int[] candidates, int target, List<Integer> temp, int startIndex, int sum) {
        if (sum == target) {
            ans39.add(new ArrayList<>(temp));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            int curNum = candidates[i];
            if (sum + curNum > target) {
                break;
            }
            temp.add(curNum);
            combinationSum39(candidates, target, temp, i, sum + curNum);
            temp.remove(temp.size() - 1);
        }
    }


    /**
     * 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
     * <p>
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     */
    static String[] map17 = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    static List<String> resultList17 = new ArrayList<>();
    static StringBuilder stringBuilder = new StringBuilder();

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }

        getWords(digits, 0);
        return resultList17;
    }


    private static void getWords(String digits, int curIndex) {
        if (curIndex == digits.length()) {
            resultList17.add(stringBuilder.toString());
            return;
        }
        String str = map17[digits.charAt(curIndex) - '2'];
        for (int i = 0; i < str.length(); i++) {
            stringBuilder.append(str.charAt(i));
            getWords(digits, curIndex + 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
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
