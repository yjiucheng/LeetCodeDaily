import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 回溯算法
 */
public class BackTrackingDemo {

    public static void main(String[] args) {
//        System.err.println(combine(1, 1));
//        System.err.println(combinationSum3(3, 9));
        System.err.println(letterCombinations(""));
    }


    /**
     * 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
     *
     * 输入：digits = "23"
     输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     */
  static   HashMap<Character, List<String>> map17 = new HashMap<>();
    static   List<String> resultList17 = new ArrayList<>();
    static  StringBuilder stringBuilder = new StringBuilder();

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list2.add("c");
        map17.put('2', list2);
        List<String> list3 = new ArrayList<>();
        list3.add("d");
        list3.add("e");
        list3.add("f");
        map17.put('3', list3);
        List<String> list4 = new ArrayList<>();
        list4.add("g");
        list4.add("h");
        list4.add("i");
        map17.put('4', list4);
        List<String> list5 = new ArrayList<>();
        list5.add("j");
        list5.add("k");
        list5.add("l");
        map17.put('5', list5);
        List<String> list6 = new ArrayList<>();
        list6.add("m");
        list6.add("n");
        list6.add("o");
        map17.put('6', list6);
        List<String> list7 = new ArrayList<>();
        list7.add("p");
        list7.add("q");
        list7.add("r");
        list7.add("s");
        map17.put('7', list7);
        List<String> list8 = new ArrayList<>();
        list8.add("t");
        list8.add("u");
        list8.add("v");
        map17.put('8', list8);
        List<String> list9 = new ArrayList<>();
        list9.add("w");
        list9.add("x");
        list9.add("y");
        list9.add("z");
        map17.put('9', list9);

        getWords(digits, 0);
        return resultList17;
    }


    private static void getWords(String digits, int curIndex) {
        if (curIndex == digits.length()) {
            resultList17.add(stringBuilder.toString());
            return;
        }
        List<String> list = map17.get(digits.charAt(curIndex));
        for (int i = 0; i < list.size(); i++) {
            String curStr = list.get(i);
            stringBuilder.append(curStr);
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
