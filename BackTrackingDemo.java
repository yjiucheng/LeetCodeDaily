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
        int[] nums2 = new int[]{1, 1, 3};
//        System.err.println(combinationSum(nums, 7));
//        System.err.println(combinationSum2(nums, 8));
//        System.err.println(partition("a"));
//        System.err.println(combinationSum2(nums2, 5));


        List<String> list1 = new ArrayList<>();
        list1.add("JFK");
        list1.add("KUL");

        List<String> list2 = new ArrayList<>();
        list2.add("JFK");
        list2.add("NRT");

        List<String> list3 = new ArrayList<>();
        list3.add("NRT");
        list3.add("JFK");


        List<List<String>> tickets = new ArrayList<>();
        tickets.add(list1);
        tickets.add(list2);
        tickets.add(list3);

        BackTrackingDemo demo = new BackTrackingDemo();
        System.err.println(demo.findItinerary(tickets));
    }


    /**
     * 332. 重新安排行程
     *
     * @param tickets
     * @return
     */

    boolean[] used332;
    String lastFrom = "JFK";
    String lastTo = "";
    int lastIndex = -1;

    public List<String> findItinerary(List<List<String>> tickets) {
        used332 = new boolean[tickets.size()];
        List<String> ans332 = new ArrayList<>();
        ans332.add("JFK");
        findItineraryMatch(tickets, ans332);
        return ans332;
    }

    public void findItineraryMatch(List<List<String>> tickets, List<String> ans) {
        for (int i = 0; i < tickets.size(); i++) {
            if (used332[i]) {
                continue;
            }
            List<String> list = tickets.get(i);
            //当前from与lastFrom相等
            if (list.get(0).equals(lastFrom)) {
                String curTo = list.get(1);
                if (originalIsFront(lastTo, curTo)) {
                    continue;
                } else {
                    used332[i] = true;
                    if (lastIndex != -1) {
                        used332[lastIndex] = false;
                        ans.remove(ans.size() - 1);
                    }
                    ans.add(curTo);
                    lastTo = curTo;
                    lastIndex = i;
                }
            }
        }
        for (boolean cur : used332) {
            if (!cur) {
                lastFrom = ans.get(ans.size() - 1);
                lastTo = "";
                lastIndex = -1;
                findItineraryMatch(tickets, ans);
                break;
            }
        }
    }


    /**
     * 比较两个字符串的字典大小 originalStr<= targetStr  返回 true ，originalStr>targetStr，返回 false
     */
    private boolean originalIsFront(String originalStr, String targetStr) {
        if (originalStr.length() == 0) {
            return false;
        }
        for (int index = 0; index < originalStr.length(); index++) {
            char origChar = originalStr.charAt(index);
            char tarChar = targetStr.charAt(index);
            if (origChar < tarChar) {
                return true;
            } else if (origChar == tarChar) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }


    /**
     * 47. 全排列 II
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     * 输入：nums = [1,1,2]
     * 输出：
     * [[1,1,2],
     * [1,2,1],
     * [2,1,1]]
     */
    List<List<Integer>> ans47 = new ArrayList<>();
    boolean[] used47;

    public List<List<Integer>> permuteUnique(int[] nums) {
        used47 = new boolean[nums.length];
        Arrays.sort(nums);
        permuteUnique(nums, new ArrayList<>());
        return ans47;
    }

    public void permuteUnique(int[] nums, List<Integer> temp) {
        if (temp.size() == nums.length) {
            ans47.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && used47[i - 1]) {
                continue;
            }
            if (!used47[i]) {
                temp.add(nums[i]);
                used47[i] = true;
                permuteUnique(nums, temp);
                used47[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }


    /**
     * 46. 全排列
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    List<List<Integer>> ans46 = new ArrayList<>();
    boolean[] used46;

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return ans46;
        }
        used46 = new boolean[nums.length];
        permute(nums, new ArrayList<>());
        return ans46;
    }


    public void permute(int[] nums, List<Integer> temp) {
        if (temp.size() == nums.length) {
            ans46.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used46[i]) {
                continue;
            }
            temp.add(nums[i]);
            used46[i] = true;
            permute(nums, temp);
            temp.remove(temp.size() - 1);
            used46[i] = false;
        }

    }


    /**
     * 301. 删除无效的括号
     * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
     * <p>
     * 返回所有可能的结果。答案可以按 任意顺序 返回。
     * <p>
     * 输入：s = "()())()"
     * 输出：["(())()","()()()"]
     * <p>
     * 输入：s = "(a)())()"
     * 输出：["(a())()","(a)()()"]
     * <p>
     * 输入：s = ")("
     * 输出：[""]
     */
    List<String> ans301 = new ArrayList<>();

    //todo 这题有点难
    public List<String> removeInvalidParentheses(String s) {
        return ans301;
    }


    /**
     * 491. 递增子序列
     * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
     * <p>
     * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
     * <p>
     * 输入：nums = [4,6,7,7]
     * 输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
     * <p>
     * 输入：nums = [4,4,3,2,1]
     * 输出：[[4,4]]
     */
    List<List<Integer>> ans491 = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        findSubsequencesBack(nums, new ArrayList<>(), 0);
        return ans491;
    }

    public void findSubsequencesBack(int[] nums, List<Integer> temp, int startIndex) {
        if (temp.size() >= 2) {
            ans491.add(new ArrayList<>(temp));
        }
        int[] used = new int[201];
        for (int i = startIndex; i < nums.length; i++) {
            if (temp.size() > 0 && temp.get(temp.size() - 1) > nums[i] || (used[nums[i] + 100] == 1)) {
                continue;
            }
            used[nums[i] + 100] = 1;
            temp.add(nums[i]);
            findSubsequencesBack(nums, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }


    /**
     * 90. 子集 II
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     * <p>
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subsets-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    List<List<Integer>> ans90 = new ArrayList<>();
    boolean[] used;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
        subsetsWithDupMatch(nums, 0, new ArrayList<>());
        return ans90;
    }

    private void subsetsWithDupMatch(int[] nums, int start, List<Integer> temp) {
        ans90.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            if (i - start > 0 && nums[i] == nums[i - 1] && !used[i]) {
                continue;
            }
            temp.add(nums[i]);
            used[i] = true;
            subsetsWithDupMatch(nums, i + 1, temp);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }

    /**
     * 78. 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * <p>
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * <p>
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * <p>
     * 输入：nums = [0]
     * 输出：[[],[0]]
     */

    List<List<Integer>> ans78 = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        subsetsMatc(nums, 0, new ArrayList<>());
        return ans78;
    }

    private void subsetsMatc(int[] nums, int start, List<Integer> temp) {
        ans78.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            subsetsMatc(nums, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }


    /**
     * 93. 复原 IP 地址
     * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
     * <p>
     * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     * <p>
     * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
     * <p>
     * 输入：s = "25525511135"
     * 输出：["255.255.11.135","255.255.111.35"]
     * <p>
     * 输入：s = "0000"
     * 输出：["0.0.0.0"]
     * <p>
     * 输入：s = "1111"
     * 输出：["1.1.1.1"]
     */
    List<String> ans93 = new ArrayList<>();
    int[] temp = new int[4];

    public List<String> restoreIpAddresses(String s) {
        restoreIpAddressesMatch(s, 0, 0);
        return ans93;
    }

    private void restoreIpAddressesMatch(String s, int start, int count) {
        if (count > 4) {
            return;
        }
        if (count == 4) {
            if (start >= s.length()) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    builder.append(temp[i]);
                    if (i != 3) {
                        builder.append(".");
                    }
                }
                ans93.add(builder.toString());
            }
            return;
        }
        for (int i = start; i < s.length() && i - start < 4; i++) {
            String sub = s.substring(start, i + 1);
            if (sub.startsWith("0")) {
                if (sub.length() > 1) {
                    break;
                }
            }
            int num = Integer.parseInt(sub);
            if (num > 255) {
                break;
            }
            temp[count] = num;
            restoreIpAddressesMatch(s, i + 1, count + 1);
            temp[count] = -1;
        }
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
    static List<List<String>> ans131 = new ArrayList<>();

    public static List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return ans131;
        }
        partitionMatch(s, 0, new ArrayList<>());
        return ans131;
    }

    private static void partitionMatch(String s, int start, List<String> list) {
        if (start >= s.length()) {
            ans131.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String sub = s.substring(start, i + 1);
            if (isBackString(sub)) {
                list.add(sub);
            } else {
                continue;
            }
            partitionMatch(s, i + 1, list);
            list.remove(list.size() - 1);
        }
    }


    private static boolean isBackString(String s) {
        if (s.length() == 1) {
            return true;
        }
        int start = 0, end = s.length() - 1;
        while (end >= start) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
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
