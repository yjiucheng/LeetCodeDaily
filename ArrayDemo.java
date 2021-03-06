import java.util.*;

/**
 * 数组相关
 */
public class ArrayDemo {
    public static void main(String[] args) {
//        int[] nums = new int[]{18,29,38,59,98,100,99,98,90};
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
//        System.err.println(Arrays.toString(smallerNumbersThanCurrent(nums)));
//        String a = "leetcode";
        rotate(nums, 3);
        System.err.println(Arrays.toString(nums));
//        System.err.println(subarraySum(nums, 3));
    }

    /**
     * 33. 搜索旋转排序数组
     */
    public int search33(int[] nums, int target) {
        int index = -1;
        if (nums.length == 0) {
            return index;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : index;
        }

        int statIndex = 0;
        int endIndex = nums.length - 1;
        int midIndex;
        while (endIndex >= statIndex) {
            midIndex = (endIndex - statIndex) / 2 + statIndex;
            if (nums[midIndex] == target) {
                index = midIndex;
                break;
            }

            //左边是有序数组
            if (nums[statIndex] <= nums[midIndex]) {
                if (target >= nums[statIndex] && target < nums[midIndex]) {
                    endIndex = midIndex - 1;
                } else {
                    statIndex = midIndex + 1;
                }
            } else {
                if (target > nums[midIndex] && target <= nums[endIndex]) {
                    statIndex = midIndex + 1;
                } else {
                    endIndex = midIndex - 1;
                }
            }
        }

        return index;
    }


    /**
     * 347. 前 K 个高频元素
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     */
//    public int[] topKFrequent(int[] nums, int k) {
//    }


    /**
     * 268. 丢失的数字
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     */
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int total = n * (n + 1) / 2;
        int arrSum = 0;
        for (int i = 0; i < n; i++) {
            arrSum += nums[i];
        }
        return total - arrSum;
    }


    /**
     * 56. 合并区间
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     * <p>
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * <p>
     */
    public int[][] merge(int[][] intervals) {
        int length = intervals.length * 2;
        Map<Integer, Integer> map = new HashMap<>();
        int[] nums = new int[length];
        for (int i = 0; i < intervals.length; i++) {
            nums[i * 2] = intervals[i][0];
            nums[i * 2 + 1] = intervals[i][1];
            map.put(intervals[i][0], Math.max(map.getOrDefault(intervals[i][0], Integer.MIN_VALUE), intervals[i][1]));
        }
        Arrays.sort(nums);
        int index = 1;
        int start = nums[0];
        int maxEnd = map.get(start);
        while (index < nums.length) {
            int temp = nums[index];
            if (temp <= maxEnd) {
                if (map.get(temp) != null) {
                    maxEnd = Math.max(map.get(temp), maxEnd);
                }
                map.remove(temp);
                map.put(start, maxEnd);
            } else {
                start = temp;
                maxEnd = map.get(start);
            }
            index++;
        }
        int[][] ans = new int[map.size()][2];
        int ansIndex = 0;
        for (int key : map.keySet()) {
            int[] temp = new int[2];
            temp[0] = key;
            temp[1] = map.get(key);
            ans[ansIndex] = temp;
            ansIndex++;
        }
        return ans;
    }


    /**
     * 217. 存在重复元素
     *
     * @给定一个整数数组，判断是否存在重复元素。 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     */
    public boolean containsDuplicate(int[] nums) {
//        Set<Integer> set = new HashSet<>();
//        for (int num : nums) {
//            if(!set.add(num)){
//                return true;
//            }
//        }
//        return false;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 31. 下一个排列
     * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
     * <p>
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * <p>
     * 必须 原地 修改，只允许使用额外常数空间。
     * 输入：nums = [1,2,3]
     * 输出：[1,3,2]
     * <p>
     * 输入：
     * <p>
     * = [3,2,1]
     * 输出：[1,2,3]
     * <p>
     * 输入：nums = [1,1,5]
     * 输出：[1,5,1]
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return;
        }
        int firstIndex = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstIndex = i;
                break;
            }
        }
        if (firstIndex == -1) {
            reverse(nums, 0);
            return;
        }
        int secondIndex = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > nums[firstIndex]) {
                secondIndex = i;
                break;
            }
        }

        swap(nums, firstIndex, secondIndex);
        reverse(nums, firstIndex + 1);
    }

    private void swap(int[] nums, int i, int i1) {
        int tmp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = tmp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    int[] cache = new int[40];


    /**
     * 1646. 获取生成数组中的最大值
     */
    public int getMaximumGenerated(int n) {
        if (n == 0) return 0;
        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        for (int i = 0; i < n; i++) {
            if (2 * i <= n) nums[2 * i] = nums[i];
            if (2 * i + 1 <= n) nums[2 * i + 1] = nums[i] + nums[i + 1];
        }
        int ans = 0;
        for (int i : nums) ans = Math.max(ans, i);
        return ans;
    }


    /**
     * 189. 旋转数组
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     */
    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }


    /**
     * 1137. 第 N 个泰波那契数
     *
     * @param n
     * @return
     */
    public int tribonacci(int n) {
//        if (n == 0) {
////            return 0;
////        }
////        if (n == 1 || n == 2) {
////            return 1;
////        }
////        if (cache[n] != 0) return cache[n];
////        cache[n] = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
////        return cache[n];

        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        int a = 0, b = 1, c = 1;
        for (int i = 3; i <= n; i++) {
            int d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return c;
    }

    /**
     * 1337. 矩阵中战斗力最弱的 K 行
     *
     * @param mat
     * @param k
     * @return
     */
    public int[] kWeakestRows(int[][] mat, int k) {
        return null;
    }


    /**
     * 1929. 数组串联
     *
     * @param nums
     * @return
     */
    public int[] getConcatenation(int[] nums) {
        int[] ans = new int[nums.length * 2];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i];
            ans[i + nums.length] = nums[i];
        }
        return ans;
    }


    /**
     * 1920. 基于排列构建数组
     *
     * @param nums
     * @return
     */
    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }

    /**
     * 1743. 从相邻元素对还原数组
     *
     * @param adjacentPairs
     * @return
     */
//    public int[] restoreArray(int[][] adjacentPairs) {
//    }


    /**
     * 1893. 检查是否区域内所有整数都被覆盖
     */

    public boolean isCovered(int[][] ranges, int left, int right) {
        if (ranges == null || ranges.length == 0) {
            return false;
        }

        for (int i = left; i <= right; i++) {
            boolean contain = false;
            for (int[] nums : ranges) {
                if (i >= nums[0] && i <= nums[1]) {
                    contain = true;
                    break;
                }
            }
            if (!contain) {
                return false;
            }
        }
        return true;

    }

    /**
     * 78. 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        //todo
        int count = 0;
        while (count < nums.length) {
            for (int i = 0; i < nums.length; i++) {
                List<Integer> inAns = new ArrayList<>();
                if (inAns.size() <= count) {

                } else {

                }
            }


            count++;
        }
        return ans;

    }

    /**
     * 136. 只出现一次的数字
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }

    /**
     * 剑指 Offer 53 - I. 在排序数组中查找数字 I
     * 统计一个数字在排序数组中出现的次数。
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: 2
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: 0
     */
    public int search(int[] nums, int target) {
        int count = 0;
        if (nums == null || nums.length == 0) {
            return count;
        }
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        int mid = 0;
        while (rightIndex > leftIndex) {
            mid = (leftIndex + rightIndex) / 2;
            if (nums[mid] > target) {
                rightIndex = mid - 1;
            } else if (nums[mid] < target) {
                leftIndex = mid + 1;
            } else {
                break;
            }
        }
        for (int i = mid; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;
            } else {
                break;
            }
        }
        for (int i = mid - 1; i >= 0; i--) {
            if (nums[i] == target) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }


    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     * <p>
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * <p>
     * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
     */
    public int majorityElement(int[] nums) {
        int candNum = nums[0], count = 1;
        for (int num : nums) {
            if (candNum == num) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    count = 1;
                    candNum = num;
                }
            }
        }
        return candNum;
    }


    /**
     * 645. 错误的集合
     * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导
     * 致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
     * <p>
     * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
     * <p>
     * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
     */
    public int[] findErrorNums(int[] nums) {
        int[] newData = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            newData[num] += 1;
        }
        int[] ans = new int[2];
        for (int i = 1; i < newData.length; i++) {
            if (newData[i] == 2) {
                ans[0] = i;
            }
            if (newData[i] == 0) {
                ans[1] = i;
            }
        }
        return ans;
    }


    /**
     * 560. 和为K的子数组
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     */
    public static int subarraySum(int[] nums, int k) {
        int ans = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                ans += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);

        }

        return ans;
    }


    /**
     * 852. 山脉数组的峰顶索引
     */
    public static int peakIndexInMountainArray(int[] arr) {
        int left = 1, right = arr.length - 2;
        while (right > left) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] <= arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }


    /**
     * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
     * 请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
     * <p>
     * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, 1);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!map.containsKey(i)) {
                ans.add(i);
            }
        }
        //todo
        return ans;
    }


    /**
     * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
     */
    public int maxProfit(int[] prices) {
        int maxSum = 0;
        int maxPrice = 0, maxIndex = 0, minPrice = Integer.MAX_VALUE, minIndex = 0;
        for (int index = 0; index < prices.length; index++) {
            if (prices[index] < minPrice) {
                minPrice = prices[index];
                minIndex = index;
            }
        }
        //todo
        return 0;
    }


    /**
     * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     * <p>
     * 初始化 A 和 B 的元素数量分别为 m 和 n。
     * <p>
     * 链接：https://leetcode-cn.com/problems/sorted-merge-lcci
     */
    public void merge(int[] A, int m, int[] B, int n) {
        for (int index = m, indexB = 0; index < A.length; index++, indexB++) {
            A[index] = B[indexB];
        }
        Arrays.sort(A);
    }


    /**
     * 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
     * <p>
     * 返回该 最大总和 。
     * <p>
     * 链接：https://leetcode-cn.com/problems/array-partition-i
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int index = 0; index < nums.length; index = index + 2) {
            sum += nums[index];
        }
        return sum;
    }


    /**
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     * <p>
     * <p>
     * <p>
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     * 1
     * 11
     * 121
     * 1331
     * 14641
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        int outInex = 0;
        List<Integer> preList = null;
        while (outInex < 5) {
            List<Integer> inList = new ArrayList<>();
            if (outInex == 0) {
                inList.add(1);
                preList = inList;
            } else {
                int count = 0;
                while (count < preList.size() + 1) {
                    if (count == 0 || count == preList.size()) {
                        inList.add(1);
                    } else {
                        int currentNum = preList.get(count - 1) + preList.get(count);
                        inList.add(currentNum);
                    }
                    count++;
                }
                preList = inList;
            }

            ans.add(inList);
            outInex++;
        }

        return ans;
    }


    /**
     * 给你一个正方形矩阵 mat，请你返回矩阵对角线元素的和。
     * <p>
     * 请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。
     */
    public int diagonalSum(int[][] mat) {
        if (mat == null || mat.length == 0) {
            return 0;
        }
        int count = mat.length;
        int priLineSum = 0;
        int secLineSum = 0;
        for (int index = 0; index < count; index++) {
            priLineSum += mat[index][index];
            secLineSum += mat[index][count - 1 - index];
        }

        return count % 2 == 0 ? priLineSum + secLineSum : priLineSum + secLineSum - mat[count / 2][count / 2];
    }


    /**
     * 平面上有 n 个点，点的位置用整数坐标表示 points[i] = [xi, yi] 。请你计算访问所有这些点需要的 最小时间（以秒为单位）。
     */
    public int minTimeToVisitAllPoints(int[][] points) {
        if (points == null || points.length < 2) {
            return 0;
        }
        int outLegth = points.length;
        int sumDuration = 0;
        for (int index = 1; index < outLegth - 1; index++) {
            int[] prePoint = points[index - 1];
            int[] curPoint = points[index];
            int xDistance = prePoint[0] > curPoint[0] ? prePoint[0] - curPoint[0] : curPoint[0] - prePoint[0];
            int yDistance = prePoint[1] > curPoint[1] ? prePoint[1] - curPoint[1] : curPoint[1] - prePoint[1];
            sumDuration += Math.max(xDistance, yDistance);
        }
        return sumDuration;
    }


    /**
     * 给你两个整数数组 nums 和 index。你需要按照以下规则创建目标数组：
     * <p>
     * 目标数组 target 最初为空。
     * 按从左到右的顺序依次读取 nums[i] 和 index[i]，在 target 数组中的下标 index[i] 处插入值 nums[i] 。
     * 重复上一步，直到在 nums 和 index 中都没有要读取的元素。
     * 请你返回目标数组。
     * <p>
     * 题目保证数字插入位置总是存在。
     */
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < index.length; i++) {
            list.add(index[i], nums[i]);
        }
        int[] ans = new int[list.size()];
        int pos = 0;
        for (Integer data : list) {
            ans[pos] = data;
            pos++;
        }
        return ans;
    }


    /**
     * 给你一个以行程长度编码压缩的整数列表 nums 。
     * <p>
     * 考虑每对相邻的两个元素 [freq, val] = [nums[2*i], nums[2*i+1]] （其中 i >= 0 ），每一对都表示解压后子列表中有 freq 个值为 val 的元素，你需要从左到右连接所有子列表以生成解压后的列表。
     * <p>
     * 请你返回解压后的列表。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/decompress-run-length-encoded-list
     * <p>
     * 输入：nums = [1,2,3,4]
     * 输出：[2,4,4,4]
     * 解释：第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。
     * 第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]。
     * 最后将它们串联到一起 [2] + [4,4,4] = [2,4,4,4]。
     */
    public int[] decompressRLElist(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i = i + 2) {
            int count = nums[i];
            while (count > 0) {
                list.add(nums[i + 1]);
                count--;
            }
        }
        int[] ans = new int[list.size()];
        int index = 0;
        for (Integer data : list) {
            ans[index] = data;
            index++;
        }
        return ans;
    }


    /**
     * 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
     * <p>
     * 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
     * <p>
     * 链接：https://leetcode-cn.com/problems/shuffle-the-array
     */
    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            if (i % 2 == 0) {
                ans[i] = nums[i / 2];
            } else {
                ans[i] = nums[n + i / 2];
            }
        }
        return ans;
    }


    /**
     * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
     *
     * @param astr
     * @return
     */
    public static boolean isUnique(String astr) {
        if (astr == null || astr.length() == 0) {
            return true;
        }
        HashMap<Character, Integer> map = new HashMap();
        for (int i = 0; i < astr.length(); i++) {
            if (!map.containsKey(astr.charAt(i))) {
                map.put(astr.charAt(i), 1);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
     * <p>
     * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
     * <p>
     * 以数组形式返回答案。
     * <p>
     * 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
     * <p>
     * 输入：nums = [8,1,2,2,3]
     * 输出：[4,0,1,1,3]
     * 解释：
     * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
     * 对于 nums[1]=1 不存在比它小的数字。
     * 对于 nums[2]=2 存在一个比它小的数字：（1）。
     * 对于 nums[3]=2 存在一个比它小的数字：（1）。
     * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
     * <p>
     */
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        if (nums == null) {
            return null;
        }
        int[] result = new int[nums.length];
        //计数排序 0 <= nums[i] <= 100
        //确定数组大小范围后，创建一个新数组：nums[i]的值即为新数组中的下标
        int[] cnt = new int[101];
        for (int i = 0; i < nums.length; i++) {
            //nums[i]的值即为新数组中的下标，value为出现次数
            cnt[nums[i]] += 1;
        }
        //对于任一nums[i]来说：小于nums[i]的数字出现的次数即为比它小的值
        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            //注意： result[i] = nums[i] == 0 ? cnt[0] : cnt[nums[i] - 1];
            //这种写法是错的：想想cnt[0]和0的区别
            result[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
        }
        return result;
    }

    /**
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * <p>
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * <p>
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * <p>
     * 链接：https://leetcode-cn.com/problems/plus-one
     * 输入：digits = [1,2,3]
     * 输出：[1,2,4]
     * 解释：输入数组表示数字 123。
     */
    public static int[] plusOne(int[] digits) {
        boolean preNeedPlus = false;
        int lastNum = digits[digits.length - 1];
        if (lastNum + 1 > 9) {
            preNeedPlus = true;
            digits[digits.length - 1] = 0;
        } else {
            digits[digits.length - 1] = lastNum + 1;
            return digits;
        }
        for (int index = digits.length - 2; index >= 0; index--) {
            int num = digits[index];
            if (preNeedPlus) {
                preNeedPlus = false;
                if (num + 1 > 9) {
                    digits[index] = 0;
                    preNeedPlus = true;
                } else {
                    digits[index] = num + 1;
                    break;
                }
            } else {
                break;
            }
        }
        if (preNeedPlus) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            for (int i = 1; i < result.length; i++) {
                result[i] = digits[i - 1];
                return result;
            }
        }
        return digits;
    }


    /**
     * 给你一个整数数组 nums 。
     * <p>
     * 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
     * <p>
     * 返回好数对的数目。
     * <p>
     * 链接：https://leetcode-cn.com/problems/number-of-good-pairs
     * <p>
     * 输入：nums = [1,2,3,1,1,3]
     * 输出：4
     * 解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
     */
    public static int numIdenticalPairs(int[] nums) {
        int count = 0;
        //方法二
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int newCount = map.get(nums[i]) + 1;
                map.put(nums[i], newCount);
            } else {
                map.put(nums[i], 1);
            }
        }

        for (Integer value : map.values()) {
            count += value * (value - 1) / 2;
        }


        //方法一
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] == nums[j]) {
//                    count++;
//                }
//            }
//        }
        return count;
    }


    /**
     * 1672. 最富有客户的资产总量
     * 给你一个 m x n 的整数网格 accounts ，其中 accounts[i][j] 是第 i​​​​​​​​​​​​ 位客户在第 j 家银行托管的资产数量。返回最富有客户所拥有的 资产总量 。
     * <p>
     * 客户的 资产总量 就是他们在各家银行托管的资产数量之和。最富有客户就是 资产总量 最大的客户。
     * 链接：https://leetcode-cn.com/problems/richest-customer-wealth
     * <p>
     * <p>
     * 输入：accounts = [[1,5],[7,3],[3,5]]
     * 输出：10
     * 解释：
     * 第 1 位客户的资产总量 = 6
     * 第 2 位客户的资产总量 = 10
     * 第 3 位客户的资产总量 = 8
     * 第 2 位客户是最富有的，资产总量是 10
     */
    public int maximumWealth(int[][] accounts) {
        if (accounts.length == 0) {
            return 0;
        }
        int maxSum = 0;

        for (int i = 0; i < accounts.length; i++) {
            int singleSum = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                singleSum += accounts[i][j];
            }
            maxSum = Math.max(maxSum, singleSum);
        }
        return maxSum;
    }


    /**
     * 1486. 数组异或操作
     * 链接：https://leetcode-cn.com/problems/xor-operation-in-an-array
     * 给你两个整数，n 和 start 。
     * 数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。
     * 请返回 nums 中所有元素按位异或（XOR）后得到的结果。
     */
    public int xorOperation(int n, int start) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result ^= start + 2 * i;

        }
        return result;
    }

    /**
     * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
     * <p>
     * 请返回 nums 的动态和。
     * <p>
     * 链接：https://leetcode-cn.com/problems/running-sum-of-1d-array
     */
    public int[] runningSum(int[] nums) {
        int[] ans = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            ans[i] = sum;
        }
        return ans;
    }

}
