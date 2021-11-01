import java.math.BigDecimal;
import java.util.*;


public class TestDemo {
    public static void main(String[] args) {
        String s = "aabb";
        System.err.println(s.substring(0, 4));
//        //l1 = [2,4,3], l2 = [5,6,4]
//        //[9]
//        //[1,9,9,9,9,9,9,9,9,9]
//        ListNode listNode1 = new ListNode(9);
//        ListNode listNode2 = new ListNode(1, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))));
//        TestDemo d = new TestDemo();
//
//        System.err.println("result:" + d.addTwoNumbers(listNode1, listNode2));
//        int[] nums = new int[]{1, 3, 6, 9, 12, 15, 25, 67, 67, 68, 81, 83, 100};
        int[] nums = new int[]{-1, 0, 1, 0};
        int[] num2 = new int[]{1, 2, 3, 4, 10};
//        int[] num2 = new int[]{1, 2, 3, 4, 6, 9, 11, 12, 14, 15, 25, 30, 32, 35, 40, 67, 67, 68, 81, 83, 100};
//        System.err.println("中位数：" + findMedianSortedArrays(nums, num2));

//        System.err.println(threeSum(nums));
//        System.err.println(letterCombinations("23"));
//        ListNode listNode1 = new ListNode(1, new ListNode(2, new ListNode(4)));
//        ListNode listNode2 = new ListNode(1);
//        System.err.println(mergeTwoLists(null, listNode2));
//        int[] num3 = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] num3 = new int[]{23, 2, 6, 4, 7};
//        System.err.println(checkSubarraySum(num3, 13));
//        moveZeroes(num3);
//        System.err.println(minSubArrayLen(4, num3));

//        String s = "aaa###a";
//        String t = "aaaa###a";
//        System.err.println(backspaceCompare(s, t));
        int n = 3;
        int[][] result = generateMatrix(n);
//        for (int i = 0; i < n; i++) {
//            System.err.println(Arrays.toString(result[i]));
//        }


        //[[1,2,3,4],[5,6,7,8],[9,10,11,12]]
        int[][] newData = new int[][]{{1, 2, 3, 4}};
//        System.err.println(newData.length * newData[0].length);
//        for (int i = 0; i < newData.length; i++) {
//            System.err.println(Arrays.toString(newData[i]));
//        }
//        System.err.println(spiralOrder(newData));
    }


    /**
     * 575. 分糖果
     * 输入: candies = [1,1,2,2,3,3]
     * 输出: 3
     * 解析: 一共有三种种类的糖果，每一种都有两个。
     * 最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
     * <p>
     */
    public int distributeCandies(int[] candyType) {
        Set<Integer> set = new HashSet<>();
        for (int type : candyType) {
            set.add(type);
        }
        return Math.min(set.size(), candyType.length / 2);
    }

    /**
     * 240. 搜索二维矩阵 II
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * <p>
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[m][n]) {
            return false;
        }
        int startM = 0, startN = 0;
        int endM = m - 1, endN = n - 1;
        int midM, midN;
        while (startM <= endM || startN <= endN) {
            midM = (endM - startM) / 2 + startM;
            midN = (endN - startN) / 2 + startN;
            int num = matrix[midM][midN];
            if (num < target) {
                startM = midM + 1;
                startN = midN + 1;
            } else if (num == target) {
                return true;
            } else {
                endM = midM - 1;
                endN = midN - 1;
            }
        }

        return false;
    }


    /**
     * 453. 最小操作次数使数组元素相等
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int mix = Integer.MAX_VALUE;
        int count = 0;
        for (int num : nums) {
            mix = Math.min(num, mix);
        }
        for (int num : nums) {
            count += num - mix;
        }
        return count;
    }


    /**
     * 278. 第一个错误的版本
     * @param n
     * @return
     */
//    public int firstBadVersion(int n) {
//
//    }


    /**
     * 猜数字游戏的规则如下：
     * <p>
     * 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
     * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
     * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
     * <p>
     * -1：我选出的数字你猜的数字小 pick < num
     * 1：我选出的数字比你猜的数字大 pic比k > num
     * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
     * 返回我选出的数字。
     * <p>
     * 链接：https://leetcode-cn.com/problems/guess-number-higher-or-lower
     */
    public static int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
//            if (guess(mid) <= 0) {
//                right = mid;
//            } else {
//                left = mid + 1;
//            }
        }
        return left;
    }


    public static boolean checkSubarraySum(int[] nums, int k) {
        for (int startIndex = 0; startIndex < nums.length - 1; startIndex++) {
            int sum = nums[startIndex];
            for (int endIndex = startIndex + 1; endIndex < nums.length; endIndex++) {
                sum += nums[endIndex];
                if (sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }


    public static int[] spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int count = 1;
        int matNums = matrix.length * matrix[0].length;
        int top = 0, right = matrix[0].length - 1, bottom = matrix.length - 1, left = 0;
        int i = 0, j = 0;
        while (ans.size() <= matNums) {
            for (; j <= right; j++) {
                ans.add(matrix[top][j]);
                count++;
            }
            top++;
            i = top;
            if (top > bottom) {
                break;
            }
            for (; i <= bottom; i++) {
                ans.add(matrix[i][right]);
                count++;
            }
            right--;
            j = right;
            if (left > right) {
                break;
            }
            for (; j >= left; j--) {
                ans.add(matrix[bottom][j]);
                count++;
            }
            bottom--;
            i = bottom;
            if (top > bottom) {
                break;
            }
            for (; i >= top; i--) {
                ans.add(matrix[i][left]);
                count++;
            }
            left++;
            j = left;
            if (left > right) {
                break;

            }
        }
        int[] result = new int[ans.size()];
        for (int index = 0; i < ans.size(); index++) {
            result[index] = ans.get(index);
        }
        return result;
    }


    /**
     * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     *
     * @param n
     * @return
     */
    public static int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int top = 0, right = n - 1, bottom = n - 1, left = 0;
        int count = 1;
        int i = 0, j = 0;
        while (count <= n * n) {
            //顶部：从左到右
            for (; j <= right; j++) {
                ans[top][j] = count;
                count++;
            }
            top++;
            i = top;
            //左边：从上到下
            for (; i <= bottom; i++) {
                ans[i][right] = count;
                count++;
            }
            right--;
            j = right;
            for (; j >= left; j--) {
                ans[bottom][j] = count;
                count++;
            }
            bottom--;
            i = bottom;
            for (; i >= top; i--) {
                ans[i][left] = count;
                count++;
            }
            left++;
            j = left;
        }
        return ans;
    }


    /**
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * <p>
     * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
     * <p>
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * <p>
     * 输入：s = "a", t = "a"
     * 输出："a"
     */
    public String minWindow(String s, String t) {
        if ("".equals(s) || "".equals(t)) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        int minStart = 0;
        int minEnd = 0;
        int leftIndex = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), 1);
        }
        StringBuilder builder = new StringBuilder();
        for (int rightIndex = 0; rightIndex < s.length(); rightIndex++) {
            if (map.get(s.charAt(rightIndex)) == 1) {

            }
        }
//todo
        return "";
    }

    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * <p>
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int leftIndex = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        for (int rightIndex = 0; rightIndex < nums.length; rightIndex++) {
            sum += nums[rightIndex];
            while (sum >= target) {
                minLength = Math.min(minLength, rightIndex - leftIndex + 1);
                sum -= nums[leftIndex];
                leftIndex++;
            }
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }


    /**
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     */
    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        for (int left = 0, right = nums.length - 1, position = nums.length - 1; left < right; ) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                ans[position] = nums[left] * nums[left];
                left++;
            } else {
                ans[position] = nums[right] * nums[right];
                right--;
            }
            position--;
        }
        return ans;
    }

    /**
     * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
     * <p>
     * 注意：如果对空文本输入退格字符，文本继续为空。
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean backspaceCompare(String s, String t) {
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 0; index < sArray.length; index++) {
            if (sArray[index] == '#') {
                if (stringBuilder.length() != 0) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
            } else {
                stringBuilder.append(sArray[index]);
            }
        }
        int backNum = 0;
        for (int index = tArray.length - 1; index >= 0; index--) {
            if (tArray[index] == '#') {
                backNum++;
            } else {
                if (backNum > 0) {
                    backNum--;
                    continue;
                }
                if (stringBuilder.length() == 0 || stringBuilder.charAt(stringBuilder.length() - 1) != tArray[index]) {
                    return false;
                } else {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
            }
        }
        return stringBuilder.length() == 0;
    }


    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * [0,1,0,3,12]
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int firstZeroIndex = 0;
        for (int normalIndex = 1; normalIndex < nums.length; normalIndex++) {
            if (nums[firstZeroIndex] != 0) {
                firstZeroIndex++;
            } else {
                if (nums[normalIndex] != 0) {
                    nums[firstZeroIndex] = nums[normalIndex];
                    nums[normalIndex] = 0;
                    firstZeroIndex++;
                }
            }
        }
    }

    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int temp = 1;
            for (int j = 0; j < nums.length; j++) {
                if (j != i) {
                    temp = temp * nums[j];
                }
            }
            result[i] = temp;
        }
        return result;
    }

    public int removeDuplicates(int[] nums) {
        int slowIndex = 0;
        for (int fastIndex = 1; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != nums[slowIndex]) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex + 1;
    }

    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        for (int index = 0; index < length; ) {
            if (nums[index] == val) {
                nums[index] = nums[length - 1];
                length--;
            } else {
                index++;
            }
        }
        return length;
    }


    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * <p>
     * 你可以假设数组中无重复元素。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        if (nums[0] > target) {
            return 0;
        }
        if (nums[nums.length - 1] < target) {
            return nums.length;
        }
        int left = 0;
        int right = nums.length - 1;
        int targetIndex = -1;
        while (right >= left) {
            int middleIndex = left + (right - left) / 2;
            if (nums[middleIndex] > target) {
                right = middleIndex - 1;
                if (right < left) {
                    targetIndex = left;
                }
            } else if (nums[middleIndex] == target) {
                return middleIndex;
            } else {
                left = middleIndex + 1;
                if (right < left) {
                    targetIndex = left;
                }
            }
        }
        return targetIndex;
    }

    /**
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     */
    public static int search(int[] nums, int target) {
        if (nums[0] > target || nums[nums.length - 1] < target) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (right >= left) {
            int middleIndex = (right + left) / 2;
            if (nums[middleIndex] > target) {
                right = middleIndex - 1;
            } else if (nums[middleIndex] == target) {
                return middleIndex;
            } else {
                left = middleIndex + 1;
            }
        }
        return -1;
    }

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * eg: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 连续子数组 [4,-1,2,1] 的和最大，为 6 。
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int max = 0;
        for (int startIndex = 0; startIndex < nums.length; startIndex++) {
            if (startIndex == 0) {
                max = nums[startIndex];
            }
            int tempMax = nums[startIndex];
            for (int endIndex = startIndex + 1; endIndex < nums.length; ) {
                if (nums[endIndex] >= 0) {
                    tempMax += nums[endIndex];
                    endIndex++;
                } else {
                    if (nums[endIndex] + tempMax >= 0) {
                        tempMax += nums[endIndex];
                        endIndex++;
                    } else {
                        break;

                    }
//                    if (endIndex + 1 < nums.length && nums[endIndex] + nums[endIndex + 1] >= 0) {
//                        tempMax += nums[endIndex] + nums[endIndex + 1];
//                        endIndex += 2;
//                    } else {
//                        break;
//                    }
                }
            }
            max = Math.max(max, tempMax);
        }
        return max;
    }


    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode preListNode = new ListNode(-1);
        ListNode listNode = preListNode;
//        List<ListNode> list = new ArrayList<>();
//        createNewList(list, l1, l2);
//        if (list.size() >= 2) {
//            for (int i = list.size() - 2; i >= 0; i--) {
//                listNode = list.get(i);
//                listNode.next = list.get(i + 1);
//            }
//        } else {
//            listNode = list.get(0);
//        }

        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                listNode.next = l2;
                l2 = l2.next;
            } else {
                listNode.next = l1;
                l1 = l1.next;
            }
            listNode = listNode.next;
        }
        listNode.next = l1 == null ? l2 : l1;

        return preListNode.next;
    }

    private static void createNewList(List<ListNode> list, ListNode l1, ListNode l2) {
        if (l1 != null && l2 != null) {
            int temp;
            if (l1.val >= l2.val) {
                temp = l2.val;
                l2 = l2.next;
            } else {
                temp = l1.val;
                l1 = l1.next;
            }
            list.add(new ListNode(temp));
            createNewList(list, l1, l2);
        } else {
            if (l1 == null) {
                list.add(l2);
            }
            if (l2 == null) {
                list.add(l1);
            }
        }
    }


    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * x
     */
    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }
        HashMap<Character, List<String>> map = new HashMap<>();
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list2.add("c");
        map.put('2', list2);
        List<String> list3 = new ArrayList<>();
        list3.add("d");
        list3.add("e");
        list3.add("f");
        map.put('3', list3);
        List<String> list4 = new ArrayList<>();
        list4.add("g");
        list4.add("h");
        list4.add("i");
        map.put('4', list4);
        List<String> list5 = new ArrayList<>();
        list5.add("j");
        list5.add("k");
        list5.add("l");
        map.put('5', list5);
        List<String> list6 = new ArrayList<>();
        list6.add("m");
        list6.add("n");
        list6.add("o");
        map.put('6', list6);
        List<String> list7 = new ArrayList<>();
        list7.add("p");
        list7.add("q");
        list7.add("r");
        list7.add("s");
        map.put('7', list7);
        List<String> list8 = new ArrayList<>();
        list8.add("t");
        list8.add("u");
        list8.add("v");
        map.put('8', list8);
        List<String> list9 = new ArrayList<>();
        list9.add("w");
        list9.add("x");
        list9.add("y");
        list9.add("z");
        map.put('9', list9);


        List<String> resultList = new ArrayList<>();

        getWord(digits, resultList, map, 0, new StringBuilder());
        return resultList;
    }

    private static void getWord(String digits, List<String> resultList, HashMap<Character, List<String>> map, int index, StringBuilder builder) {
        if (index == digits.length()) {
            resultList.add(builder.toString());
        } else {
            char digit = digits.charAt(index);
            List<String> wordsList = map.get(digit);
            for (int i = 0; i < wordsList.size(); i++) {
                builder.append(wordsList.get(i));
                getWord(digits, resultList, map, index + 1, builder);
                builder.deleteCharAt(index);
            }
        }
    }


    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        Arrays.sort(nums);
        System.err.println(Arrays.toString(nums));
        List<List<Integer>> results = new ArrayList<>();
        int n = nums.length;
        for (int first = 0; first < n; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int needFirstNum = -nums[first];
            int third = n - 1;
            for (int second = first + 1; second < n; second++) {
                if (second - first > 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < third && nums[second] + nums[third] > needFirstNum) {
                    third--;
                }
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == needFirstNum) {
                    List<Integer> integers = new ArrayList<>();
                    integers.add(nums[first]);
                    integers.add(nums[second]);
                    integers.add(nums[third]);
                    results.add(integers);
                }

            }
        }


        return results;
    }


    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] resultNums = new int[m + n];
        int indexNum1 = 0;
        int indexNum2 = 0;
        int index = 0;
        while (index < m + n) {
            int min = 0;
            if (indexNum1 == m || indexNum2 == n) {
                if (indexNum1 == m) {
                    min = nums2[indexNum2];
                }
                if (indexNum2 == n) {
                    min = nums1[indexNum1];
                }
                if (indexNum1 == m) {
                    indexNum2++;
                }
                if (indexNum2 == n) {
                    indexNum1++;
                }
            } else {
                min = Math.min(nums1[indexNum1], nums2[indexNum2]);
                if (nums1[indexNum1] < nums2[indexNum2]) {
                    indexNum1++;
                } else {
                    indexNum2++;
                }
            }
            resultNums[index] = min;
            index++;
        }
        System.err.println(Arrays.toString(resultNums));
        System.err.println(resultNums.length);

        double result = 0d;
        if (resultNums.length % 2 == 0) {
            result = (resultNums[resultNums.length / 2] + resultNums[resultNums.length / 2 - 1]) / 2d;
        } else {
            result = resultNums[(resultNums.length - 1) / 2];
        }
        return result;
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        ListNode result = null;
        long resultNum = calculation(l1) + calculation(l2);
        String str = String.valueOf(resultNum);
        System.err.println("计算的结果：" + str);
        for (int i = 0; i < str.length(); i++) {
            result = new ListNode(Integer.valueOf(str.substring(i, i + 1)), result);
        }
        return result;
    }

    private int calculation(ListNode listNode) {
        BigDecimal bigDecimal;
        int inputOne = 0;
        int count = 0;
        do {
            inputOne += listNode.val * Math.pow(10, count);
            count++;
            listNode = listNode.next;
        } while (listNode != null);
        return inputOne;
    }

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
