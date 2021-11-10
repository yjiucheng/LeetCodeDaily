import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 贪心算法
 */
public class GreedyAlgorithmDemo {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,2,2,2};
//        int[] nums = new int[]{1, 2, 22, -23, -9, -30, -6, -9, 1, 8, 24, 2, 21, 29, 10, -25, 18, 30, 1, 9, -8, -11, -22, -23, -17, -12, 19, 28, 19, 28};
        String s = "AAAAAABCDEFG";
        GreedyAlgorithmDemo demo = new GreedyAlgorithmDemo();
        System.err.println(demo.findUnsortedSubarray(nums));
    }


    /**
     * 581. 最短无序连续子数组
     * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     * <p>
     * 请你找出符合题意的 最短 子数组，并输出它的长度。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1;
        int minn = Integer.MAX_VALUE, left = -1;

        for (int i = 1; i < n; i++) {
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }
            if (minn < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minn = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }


    /**
     * 621. 任务调度器
     * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。
     * 其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
     * 在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
     * <p>
     * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     * <p>
     * 你需要计算完成所有任务所需要的 最短时间 。
     * <p>
     * 链接：https://leetcode-cn.com/problems/task-scheduler
     */
    public int leastInterval(char[] tasks, int n) {
        int minTime = tasks.length;
        int[] nums = new int[40];
        for (int i = 0; i < tasks.length; i++) {
            nums[tasks[i] - 'A'] += 1;
        }
        Arrays.sort(nums);
        int maxCount = nums[nums.length - 1];
        int count = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (maxCount == nums[i]) {
                count++;
            } else {
                break;
            }
        }
        int sumTime = (maxCount - 1) * (n + 1) + count;

        return sumTime >= minTime ? sumTime : minTime;
    }


    /**
     * 968. 监控二叉树
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     * <p>
     * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
     * <p>
     * 计算监控树的所有节点所需的最小摄像头数量。
     */
    int sum968 = 0;


    public int minCameraCover(TreeNode root) {
        if (root == null) {
            return sum968;
        }
        if (dealTree(root) == 2) {
            sum968++;
        }
        return sum968;
    }

    private int dealTree(TreeNode temp) {
        if (temp == null) {
            return 1;
        }
        int left = dealTree(temp.left);
        int rigth = dealTree(temp.right);
        if (left == 2 || rigth == 2) {
            sum968++;
            return 0;
        } else if (left == 0 || rigth == 0) {
            return 1;
        } else {
            return 2;
        }
    }


    /**
     * 714. 买卖股票的最佳时机含手续费
     * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
     * <p>
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * <p>
     * 返回获得利润的最大值。
     * <p>
     * 注意：这里的一笔交易买入持并卖出股票的整个过程，每笔交易你只指需要有为支付一次手续费。
     */
    public int maxProfit(int[] prices, int fee) {
        int sum = 0;
        int buy = prices[0] + fee;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                sum += prices[i] - buy;
                buy = prices[i];
            }
        }
        return sum;
    }


    /**
     * 738. 单调递增的数字
     * 给定一个非，负整数 N，找出小于或等于 N 的最大的整数同时这个整数需要满足其各个位数上的数字是单调递增。
     * <p>
     * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
     * <p>
     * 输入: N = 10
     * 输出: 9
     * 输入: N = 1234
     * 输出: 1234
     * <p>
     * 输入: N = 332
     * 输出: 299
     */
    public int monotoneIncreasingDigits(int n) {
        String numStr = String.valueOf(n);
        char[] chars = numStr.toCharArray();
        int firstLow = -1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i - 1] > chars[i]) {
                firstLow = i - 1;
                break;
            }
        }
        if (firstLow == -1) {
            return n;
        }
        while (firstLow > 0 && chars[firstLow] == chars[firstLow - 1]) {
            firstLow--;
        }
        int tem = Integer.valueOf(numStr.substring(0, firstLow + 1));
        tem = tem - 1;
        StringBuilder builder = new StringBuilder();
        builder.append(tem);
        for (int i = firstLow + 1; i < numStr.length(); i++) {
            builder.append(9);
        }
        return Integer.parseInt(builder.toString());
    }


    /**
     * 763. 划分字母区间
     * 字符串 S 由小写字母组成。
     * 我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
     * 返回一个表示每个字符串片段的长度的列表。
     * <p>
     * 输入：S = "ababcbacadefegdehijhklij"
     * 输出：[9,7,8]
     * 解释：
     * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
     * 每个字母最多出现在一个片段中。
     * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
     * <p>
     * <p>
     * S的长度在[1, 500]之间。
     * S只包含小写字母 'a' 到 'z' 。
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        int[] edge = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            edge[chars[i] - 'a'] = i;
        }
        int idx = 0;
        int last = -1;
        for (int i = 0; i < chars.length; i++) {
            idx = Math.max(idx, edge[chars[i] - 'a']);
            if (i == idx) {
                list.add(i - last);
                last = i;
            }
        }
        return list;
    }

    private int getLastIndex(char[] chars, char target) {
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == target) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 435. 无重叠区间
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     * <p>
     * 注意:
     * <p>
     * 可以认为区间的终点总是大于它的起点。
     * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
     * <p>
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        sort(intervals);
        int end = intervals[0][1];
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                count++;
                end = Math.min(end, intervals[i][1]);
            } else {
                end = intervals[i][1];
            }
        }
        return count;
    }

    private void sort(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] > o2[1]) {
                    return 1;
                } else if (o1[1] == o2[1]) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
    }

    /**
     * 452. 用最少数量的箭引爆气球
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        sort(points);
        int end = points[0][1];
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > end) {
                count++;
                end = points[i][1];
            } else {
                end = Math.min(end, points[i][1]);
            }
        }
        return count;
    }

    /**
     * 406. 根据身高重建队列
     * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
     * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
     * <p>
     * 请你重新构造并返回输入数组 people 所表示的队列。
     * 返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
     * <p>
     */
    //todo 与135题类似
    public int[][] reconstructQueue(int[][] people) {

        return people;
    }


    /**
     * 860. 柠檬水找零
     */
    public boolean lemonadeChange(int[] bills) {
        if (bills[0] != 5) {
            return false;
        }
        int fiveCount = 1;
        int tenCount = 0;
        for (int i = 1; i < bills.length; i++) {
            if (bills[i] == 5) {
                fiveCount++;
            }
            if (bills[i] == 10) {
                tenCount++;
                fiveCount--;
            }
            if (bills[i] == 20) {
                if (tenCount > 0) {
                    tenCount--;
                    fiveCount--;
                } else {
                    fiveCount = fiveCount - 3;
                }
            }
            if (fiveCount < 0 || tenCount < 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 135. 分发糖果
     * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
     * <p>
     * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
     * <p>
     * 每个孩子至少分配到 1 个糖果。
     * 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
     * 那么这样下来，老师至少需要准备多少颗糖果呢？
     * <p>
     * 输入：[1,0,2]
     * 输出：5
     * 解释：你可以分别给这三个孩子分发 2、1、2 颗糖果。
     * <p>
     * 输入：[1,2,2]
     * 输出：4
     * 解释：你可以分别给这三个孩子分发 1、2、1 颗糖果。
     * 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
     */
    //todo 这题没明白
    public int candy(int[] ratings) {
        int sum = 0;
        int[] ans = new int[ratings.length];
        ans[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                ans[i] = ans[i - 1] + 1;
            } else {
                ans[i] = 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                ans[i] = Math.max(ans[i], ans[i + 1] + 1);
            }
        }

        for (int num : ans) {
            sum += num;
        }
        return sum;
    }


    /**
     * 134. 加油站
     * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     * <p>
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
     * 你从其中的一个加油站出发，开始时油箱为空。
     * <p>
     * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
     * <p>
     * 说明: 
     * <p>
     * 如果题目有解，该答案即为唯一答案。
     * 输入数组均为非空数组，且长度相同。
     * 输入数组中的元素均为非负数。
     * <p>
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int ans = 0;
        int gasSum = 0;
        int costSum = 0;
        int lastGas = 0;
        for (int i = 0; i < gas.length; i++) {
            gasSum += gas[i];
            costSum += cost[i];
            lastGas += gas[i] - cost[i];
            if (lastGas < 0) {
                ans = i + 1;
                lastGas = 0;
            }
        }

        if (gasSum - costSum < 0) {
            return -1;
        }
        return ans;
    }

    /**
     * 1005. K 次取反后最大化的数组和
     * 给定一个整数数组 A，我们只能用以下方法修改该数组：
     * 我们选择某个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）
     * <p>[-2,5,0,2,-2]
     * 3
     * 以这种方式修改数组后，返回数组可能的最大和。
     * <p>
     * 输入：A = [4,2,3], K = 1
     * 输出：5
     * 解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        int minAbs = Integer.MAX_VALUE;
        int minABxIndex = -1;
        for (int i = 0; i < k; i++) {
            if (i < nums.length) {
                if (minAbs >= Math.abs(nums[i])) {
                    if (minAbs == Math.abs(nums[i])) {
                        if (nums[i] < 0) {
                            minABxIndex = i;
                        }
                    } else {
                        minABxIndex = i;
                    }
                    minAbs = Math.abs(nums[i]);
                }
            }
            nums[minABxIndex] = -nums[minABxIndex];
        }
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    /**
     * 45. 跳跃游戏 II
     * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
     * <p>
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * <p>
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * <p>
     * 假设你总是可以到达数组的最后一个位置。
     * <p>
     */
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }


    /**
     * 55. 跳跃游戏
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * <p>
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * <p>
     * 判断你是否能够到达最后一个下标。
     */
    public boolean canJump(int[] nums) {
        if (nums.length < 2) {
            return true;
        }
//        int lastMaxStep = nums[0];
//        if (lastMaxStep == 0) {
//            return false;
//        }
//        for (int i = 1; i < nums.length - 1; i++) {
//            int curNum = nums[i];
//            if (lastMaxStep - 1 == 0 && curNum == 0) {
//                return false;
//            }
//            lastMaxStep = Math.max(lastMaxStep - 1, curNum);
//        }
//        return lastMaxStep > 0;
        int coverRange = nums[0];
        //在覆盖范围内更新最大的覆盖范围
        for (int i = 0; i <= coverRange; i++) {
            coverRange = Math.max(coverRange, i + nums[i]);
            if (coverRange >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }


    /**
     * 122. 买卖股票的最佳时机 II
     * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */
    public int maxProfit(int[] prices) {
        //如果没有，股票：今天价格比明天低，就买进来
////          如果有股票：明天价格比今天低就卖出去
        boolean has = false;
        int sum = 0;
        int buyPrice = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (has) {
                //有股票：明天价格比今天低，就卖出去
                if (prices[i] > prices[i + 1]) {
                    sum += prices[i] - buyPrice;
                    has = false;
                    buyPrice = 0;
                }
            } else {
                //没有股票：今天价格比明天低，买进
                if (prices[i] < prices[i + 1]) {
                    buyPrice = prices[i];
                    has = true;
                }
            }
        }
        if (has) {
            sum += prices[prices.length - 1] - buyPrice;
        }
        return sum;
    }


    /**
     * 53. 最大子序和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        //当前的最大值
        int max = nums[0];
        //贪心的思路为局部最优：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”，
        // 因为负数加上下一个元素 “连续和”只会越来越小。从而推出全局最优：选取最大“连续和”
        //lastSum<0时，舍弃掉之前所有的连续和，重新从0开始
        int lastSum = nums[0] <= 0 ? 0 : nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (lastSum + num < 0) {
                //lastSum + num<0细分的话有两种情况：
                //lastSum=0，num<0  或者  lastSum>0，num<0
                //由于lastSum + num < 0，所以最大值取Math.max(max, num)
                max = Math.max(max, num);
                //由于“连续和”lastSum + num<0，舍弃掉所有的连续和，重新从0开始
                lastSum = 0;
            } else {
                //lastSum + num>=0  连续和继续
                max = Math.max(max, lastSum + num);
                lastSum += num;
            }
        }
        return max;
    }


    /**
     * 376. 摆动序列
     * [33,53,12,64,50,41,45,21,97,35,47,92,39,0,93,55,40,46,69,42,6,95,51,68,72,9,32,84,34,64,6,2,26,98,3,43,30,60,3,68,82,9,97,19,27,98,99,4,30,96,37,9,78,43,64,4,65,30,84,90,87,64,18,50,60,1,40,32,48,50,76,100,57,29,63,53,46,57,93,98,42,80,82,9,41,55,69,84,82,79,30,79,18,97,67,23,52,38,74,15]
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int length = 1;

        int lastDiffer = 0;
        for (int index = 1; index < nums.length; index++) {
            int curDiffer = nums[index] - nums[index - 1];

            if ((curDiffer > 0 && lastDiffer <= 0) || (curDiffer < 0 && lastDiffer >= 0)) {
                length++;
                lastDiffer = curDiffer;
            }
        }
        return length;

    }

    /**
     * 455. 分发饼干
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        int ans = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int gIndex = 0;
        for (int i = 0; i < s.length; i++) {
            if (gIndex >= g.length) {
                break;
            }
            if (s[i] >= g[gIndex]) {
                gIndex++;
                ans++;
            }
        }
        return ans;
    }
}
