import java.util.Arrays;

/**
 * 贪心算法
 */
public class GreedyAlgorithmDemo {
    public static void main(String[] args) {
//        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int[] nums = new int[]{1, 2, 22, -23, -9, -30, -6, -9, 1, 8, 24, 2, 21, 29, 10, -25, 18, 30, 1, 9, -8, -11, -22, -23, -17, -12, 19, 28, 19, 28};
        GreedyAlgorithmDemo demo = new GreedyAlgorithmDemo();
        System.err.println(demo.largestSumAfterKNegations(nums, 24));
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
        int ans = -1;
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
        //如果没有股票：今天价格比明天低，就买进来
//          如果有股票：明天价格比今天低，就卖出去
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
