import java.util.*;

/**
 * hash
 */
public class HashDemo {
    public static void main(String[] args) {
        long starttime = System.currentTimeMillis();

        int[] s1 = new int[]{1, -2, -5, -4, -3, 3, 3, 5};
        int[] s2 = new int[]{2, 2};

//        System.err.println(Arrays.toString(intersect(s1, s2)));

        int s = 3;

//        System.err.println(fourSum(s1, -11));
        System.err.println(getHint("1123", "0111"));

//        System.err.println("本次耗时：" + (System.currentTimeMillis() - starttime));

    }


    /**
     * 299. 猜数字游戏
     * <p>
     * "1123"
     * "0111"
     */
    public static String getHint(String secret, String guess) {
        char[] secretChars = secret.toCharArray();
        char[] guessChars = guess.toCharArray();
        int countA = 0;
        int countB = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < secretChars.length; i++) {
            if (secretChars[i] == guessChars[i]) {
                guessChars[i] = ' ';
                countA++;
            } else {
                map.put(secretChars[i], map.getOrDefault(secretChars[i], 0) + 1);
            }
        }
        for (int i = 0; i < guessChars.length; i++) {
            if (map.get(guessChars[i]) != null) {
                countB++;
                int temp = map.get(guessChars[i]);
                if (temp == 1) {
                    map.remove(guessChars[i]);
                } else {
                    map.put(guessChars[i], temp - 1);
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(countA);
        builder.append("A");
        builder.append(countB);
        builder.append("B");
        return builder.toString();
    }


    /**
     * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
     * <p>
     * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，
     * 且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 2^31 - 1 。
     * <p>
     * 链接：https://leetcode-cn.com/problems/4sum-ii
     * <p>
     * 输入:
     * A = [ 1, 2]
     * B = [-2,-1]
     * C = [-1, 2]
     * D = [ 0, 2]
     * <p>
     * 输出:
     * 2
     * <p>
     * 解释:
     * 两个元组如下:
     * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
     * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
     * <p>
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int ansCount = 0;
        Map<Integer, Integer> countAB = new HashMap<Integer, Integer>();
        for (int first : nums1) {
            for (int second : nums2) {
                countAB.merge(first + second, 1, Integer::sum);
//                countAB.put(first + second, countAB.getOrDefault(first + second, 0) + 1);
            }
        }
        for (int third : nums3) {
            for (int forth : nums4) {
                int sum = third + forth;
                if (countAB.get(-sum) != null) {
                    ansCount += countAB.get(-sum);
                }
            }
        }

        return ansCount;
    }


    /**
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
     * 判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
     * 找出所有满足条件且不重复的四元组。
     * <p>
     * 注意：答案中不可以包含重复的四元组。
     * <p>
     * 链接：https://leetcode-cn.com/problems/4sum
     * <p>
     * 输入：nums = [1,0,-1,0,-2,2], target = 0
     * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        //排序：双指针都要先排序
        Arrays.sort(nums);
        //a:
        for (int firtIndex = 0; firtIndex < nums.length - 4; firtIndex++) {
            int first = nums[firtIndex];
            //去重：答案中不可以包含重复的四元组。如果nums[firtIndex]与nums[firtIndex - 1]相等，
            //那上一轮满足的答案这轮必然还满足，需要排除掉
            if (firtIndex > 0 && nums[firtIndex] == nums[firtIndex - 1]) {
                continue;
            }
            //b:
            for (int secondIndex = firtIndex + 1; secondIndex < nums.length - 3; secondIndex++) {
                int second = nums[secondIndex];
                //同理：与firstIndex一样的去重逻辑
                if (secondIndex - firtIndex > 1 && nums[secondIndex] == nums[secondIndex - 1]) {
                    continue;
                }

                int thirdIndex = secondIndex + 1;
                int fouthIndex = nums.length - 1;
                while (fouthIndex > thirdIndex) {
                    int sum = first + second + nums[thirdIndex] + nums[fouthIndex];
                    if (sum == target) {
                        List<Integer> list = new ArrayList<>();
                        list.add(first);
                        list.add(second);
                        list.add(nums[thirdIndex]);
                        list.add(nums[fouthIndex]);
                        ans.add(list);

                        while (fouthIndex > thirdIndex && nums[thirdIndex] == nums[thirdIndex + 1]) {
                            thirdIndex++;
                        }
                        thirdIndex++;
                        while (fouthIndex > thirdIndex && nums[fouthIndex] == nums[fouthIndex - 1]) {
                            fouthIndex--;
                        }
                        fouthIndex--;

                    } else if (sum < target) {
                        thirdIndex++;
                    } else {
                        fouthIndex--;
                    }
                }
            }
        }

        return ans;
    }


    /**
     * 编写一个算法来判断一个数 n 是不是快乐数。
     * <p>
     * 「快乐数」定义为：
     * <p>
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     * 如果 可以变为  1，那么这个数就是快乐数。
     * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
     * <p>
     * 1 <= n <= 2^31 - 1
     * <p>
     * 链接：https://leetcode-cn.com/problems/happy-number
     * <p>
     * 输入：19
     * 输出：true
     * 解释：
     * 1^2 + 9^2 = 82
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     */
    public static boolean isHappy(int n) {
        int sum = 0;
        Set<Integer> numSet = new HashSet<>();
        while (true) {
            int lastNum = n % 10;
            sum += lastNum * lastNum;
            n = n / 10;
            while (n > 0) {
                lastNum = n % 10;
                sum += lastNum * lastNum;
                n = n / 10;
            }
            n = sum;
            sum = 0;
            if (n == 1) {
                return true;
            } else {
                if (numSet.contains(n)) {
                    return false;
                } else {
                    numSet.add(n);
                }
            }
        }
    }


    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
     * 我们可以不考虑输出结果的顺序。
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int index = 0; index < nums1.length; index++) {
            map.put(nums1[index], map.getOrDefault(nums1[index], 0) + 1);
        }
        Stack<Integer> stack = new Stack<>();
        for (int index = 0; index < nums2.length; index++) {
            int num = nums2[index];
            if (map.containsKey(num)) {
                stack.push(num);
                if (map.get(num) > 1) {
                    map.put(num, map.get(num) - 1);
                } else {
                    map.remove(num);
                }
            }
        }
        int[] ans = new int[stack.size()];
        int index = 0;
        while (!stack.empty()) {
            ans[index] = stack.pop();
            index++;
        }
        return ans;
    }


    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     * <p>
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[9,4]
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int index = 0; index < nums1.length; index++) {
            map.put(nums1[index], 1);
        }
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int index = 0; index < nums2.length; index++) {
            int num = nums2[index];
            if (map.containsKey(num)) {
                result.put(num, 1);
            }
        }
        int[] ans = new int[result.size()];
        int index = 0;
        for (Integer num : result.keySet()) {
            ans[index] = num;
            index++;
        }
        return ans;
    }

    /**
     * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     * <p>
     * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
     * <p>
     * 说明：
     * <p>
     * 字母异位词指字母相同，但排列不同的字符串。
     * 不考虑答案输出的顺序。
     * <p>
     * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
     * "cbaebabacd"
     * "abc"
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0 || p.length() > s.length()) {
            return ans;
        }
        int targetLength = p.length();
        char[] tagetChars = p.toCharArray();
        char[] souChars = s.toCharArray();
        int[] targetCharNums = new int[26];
        int[] souCharNums = new int[26];
        for (int index = 0; index < targetLength; index++) {
            targetCharNums[tagetChars[index] - 'a'] += 1;
            souCharNums[souChars[index] - 'a'] += 1;
        }

        if (Arrays.equals(targetCharNums, souCharNums)) {
            ans.add(0);
        }

        int length = s.length();
        for (int fastIndex = targetLength; fastIndex < length; fastIndex++) {
            souCharNums[souChars[fastIndex] - 'a'] += 1;
            souCharNums[souChars[fastIndex - targetLength] - 'a'] -= 1;
            if (Arrays.equals(targetCharNums, souCharNums)) {
                ans.add(fastIndex - targetLength + 1);
            }
        }
        return ans;
    }


    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * <p>
     * 链接：https://leetcode-cn.com/problems/group-anagrams
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int index = 0; index < strs.length; index++) {
            String str = strs[index];
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortStr = String.valueOf(chars);
            if (map.containsKey(sortStr)) {
                int listIndex = map.get(sortStr);
                List<String> intList = ans.get(listIndex);
                intList.add(str);
            } else {
                List<String> inList = new ArrayList<>();
                inList.add(str);
                int size = ans.size();
                ans.add(inList);
                map.put(sortStr, size);
            }
        }
        return ans;
    }


    /**
     * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，
     * 判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
     * <p>
     * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
     * <p>
     * 链接：https://leetcode-cn.com/problems/ransom-note
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int index = 0; index < ransomNote.length(); index++) {
            map.put(ransomNote.charAt(index), map.getOrDefault(ransomNote, 0) + 1);
        }
        for (int index = 0; index < magazine.length(); index++) {
            if (map.containsKey(magazine.charAt(index))) {
                int cout = map.get(magazine.charAt(index));
                cout--;
                if (cout == 0) {
                    map.remove(magazine.charAt(index));
                } else {
                    map.put(magazine.charAt(index), cout);
                }

            }
        }
        if (map.size() > 0) {
            return false;
        }
        return true;
    }


    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * <p>
     * 输入: s = "rat", t = "car"`
     * 输出: false
     */
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int index = 0; index < s.length(); index++) {
            if (map.containsKey(s.charAt(index))) {
                int cout = map.get(s.charAt(index));
                map.put(s.charAt(index), cout + 1);
            } else {
                map.put(s.charAt(index), 1);
            }
        }

        for (int index = 0; index < t.length(); index++) {
            if (!map.containsKey(t.charAt(index))) {
                return false;
            } else {
                int cout = map.get(t.charAt(index));
                cout--;
                if (cout == 0) {
                    map.remove(t.charAt(index));
                } else {
                    map.put(t.charAt(index), cout);
                }
            }
        }
        if (map.size() != 0) {
            return false;
        }

        return true;
    }
}
