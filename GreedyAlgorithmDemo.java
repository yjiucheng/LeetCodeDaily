import java.util.Arrays;

/**
 * 贪心算法
 */
public class GreedyAlgorithmDemo {
    public static void main(String[] args) {
//        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int[] nums = new int[]{7,6,4,3,1};
        GreedyAlgorithmDemo demo = new GreedyAlgorithmDemo();
        System.err.println(demo.maxProfit(nums));
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
