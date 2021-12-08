/**
 * 动态规划
 */
public class DynamicProgrammingDemo {
    public static void main(String[] args) {
        DynamicProgrammingDemo demo = new DynamicProgrammingDemo();
//        System.err.println(demo.integerBreak(13));


        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        testWeightBagProblem(weight, value, bagSize);
        System.out.print("\n");
        System.out.print("\n");
        testWeightBagProblem2(weight, value, bagSize);
    }


    /**
     * 494. 目标和
     * 给你一个整数数组 nums 和一个整数 target 。
     * <p>
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * <p>
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     * <p>
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/target-sum
     * <p>
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int cur : nums) {
            sum += cur;
        }
        //nums[i]都是非负整数： 在不做任何操作的前提下，加法求和sum是确定的
        // 添加负号部分的和记作 neg
        // 所以：（sum-neg）-neg=target
        //neg=（sum-target）/2
        if ((sum - target) < 0 || (sum - target) % 2 != 0) {
            return 0;
        }
        int bigSize = (sum - target) / 2;
        //① 确定dp[j]的含义：装满容量为j的背包方法数
        int[] dp = new int[bigSize + 1];
        //② 确定递推公式：
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = bigSize; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[bigSize];
    }


    /**
     * 1049. 最后一块石头的重量 II
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int cur : stones) {
            sum += cur;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - 2 * dp[target];
    }


    //01背包：滚动数组
    public static void testWeightBagProblem2(int[] weight, int[] value, int bagSize) {
        /**
         * 1.确定dp数组以及下标的含义 dp[j] j:背包容量
         * dp[j] ：表示从物品0到i内，任取到到容量为j的背包中，能取到的最大物品价值总和
         */
        int[] dp = new int[bagSize + 1];
        dp[0] = 0;

        for (int i = 0; i < weight.length; i++) {
            for (int j = bagSize; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], value[i] + dp[j - weight[i]]);
            }
            for (int cur : dp) {
                System.out.print(cur + " ");
            }
            System.out.print("\n");
        }

    }

    //01背包：二维数组
    public static void testWeightBagProblem(int[] weight, int[] value, int bagSize) {
        /**
         * 1.确定dp数组以及下标的含义 dp[i][j] i:物品下标  j:背包容量
         * dp[i][j] ：表示从物品0到i内，任取到到容量为j的背包中，能取到的最大物品价值总和
         */
        int[][] dp = new int[value.length][bagSize + 1];
        /**
         * 2.确定递推公式
         *
         *  dp[i][j]中对于当前物品i，有以下两种状态：
         *  【不取物品i】：不物品i，那就和当前物品i没啥关系，前一个物取品i-1的最大即为当前最大，即dp[i-1][j]的值为当前最大
         *  即：
         *  dp[i][j]=dp[i-1][j]
         *
         *  【取物品i】：（注意：取物品i默认是背包容量j能装下物品i，即j>=weight[i]）
         *    物品i的价值 value[i]
         *    物品i的重量weight[i]
         *    取物品i之后的背包总价值=value[i]+背包装了物品i之后的剩余空间能装的最大价值
         *    即：
         *    dp[i][j]=value[i]+dp[i-1][j-weight[i]]
         *    对dp[i-1][j-weight[i]]说明：
         *    ①.为啥是i-1：因为是装了物品之后的剩余空间的最大价值，物品i已经在背包里了，不能再次使用，所以只能从物品0到物品i-1中选
         *    ②.j-weight[i]:表示装了物品i之后背包的剩余空间（不用担心j-weight[i]<0,在取物品i的时候就默认j>=weight[i]）
         *
         *
         * 【综上】:取两种情况中的大值
         * dp[i][j]=Math.max(dp[i-1][j],value[i]+dp[i-1][j-weight[i]]）
         *
         */

        /**
         * 3.dp数组如何初始化
         * 分析：dp[i][j]=Math.max(dp[i-1][j],value[i]+dp[i-1][j-weight[i]]）
         * 和i-1关联，所以i=0一定要初始化
         * 另外：考虑j的实际含义：背包的容量，如果j=0，则最大价值一定未0
         *
         * 所以，需要初始化：dp[0][j]
         * dp[i][0]=0
         */
        for (int j = 0; j <= bagSize; j++) {
            if (weight[0] > j) {
                dp[0][j] = 0;
            } else {
                dp[0][j] = value[0];
            }
        }

        /**
         * 4.确定遍历顺序
         * 二维数组先遍历背包，还是先遍历物品都一样
         */
        for (int i = 1; i < weight.length; i++) {
            for (int j = 0; j <= bagSize; j++) {
                if (j < weight[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], value[i] + dp[i - 1][j - weight[i]]);
                }
            }
        }


        //5.举例推导dp数组
        //打印dp数组
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.print("\n");
        }

    }

    /**
     * 416. 分割等和子集
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     */
    public boolean canPartition(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }
        boolean ans = false;
        int sum = 0;
        for (int cur : nums) {
            sum += cur;
        }
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        int[] dp = new int[target + 1];
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        return target == dp[target];
    }

    /**
     * 96. 不同的二叉搜索树
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     */
    public int numTrees(int n) {
        //初始化 dp 数组
        int[] dp = new int[n + 1];
        //初始化0个节点和1个节点的情况
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                //对于第i个节点，需要考虑1作为根节点直到i作为根节点的情况，所以需要累加
                //一共i个节点，对于根节点j时,左子树的节点个数为j-1，右子树的节点个数为i-j
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }


    /**
     * 343. 整数拆分
     * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
     */
    public int integerBreak(int n) {
        if (n <= 1) {
            return n;
        }
        int max = 1;
        if (n < 7) {
            return getSum(n);
        }
        int n4 = 4;
        int n5 = 6;
        int n6 = 9;
        for (int i = 7; i <= n; i++) {
            if (i % 3 == 2) {
                n5 *= 3;
                max = n5;
            } else if (i % 3 == 1) {
                n4 *= 3;
                max = n4;
            } else {
                n6 *= 3;
                max = n6;
            }
        }
        return max;
    }

    private int getSum(int n) {
        if (n <= 1) {
            return 1;
        }
        int max = 1;
        if (n % 2 == 0) {
            max = (n / 2) * (n / 2);
        } else {
            max = (n / 2) * (n / 2 + 1);
        }
        return max;
    }


    /**
     * 63. 不同路径 II
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * <p>
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-paths-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] ans = new int[m][n];
        ans[0][0] = 1 - obstacleGrid[0][0];

        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 0 && ans[0][i - 1] == 1) {
                ans[0][i] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 0 && ans[i - 1][0] == 1) {
                ans[i][0] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    ans[i][j] = 0;
                } else {
                    ans[i][j] = ans[i - 1][j] + ans[i][j - 1];
                }
            }
        }
        return ans[m - 1][n - 1];
    }

    /**
     * 62. 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * <p>
     * 问总共有多少条不同的路径？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-paths
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        int[][] ans = new int[m][n];
        ans[0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0) {
                    ans[0][j] = 1;
                }
                if (j == 0) {
                    ans[i][0] = 1;
                }
                if (i >= 1 && j >= 1) {
                    ans[i][j] = ans[i - 1][j] + ans[i][j - 1];
                }
            }
        }

        return ans[m - 1][n - 1];
    }

    /**
     * 746. 使用最小花费爬楼梯x
     * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
     * <p>
     * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
     * <p>
     * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 输入：cost = [10, 15, 20]
     * 输出：15
     * 解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶 ，一共花费 15 。
     */
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length <= 1) {
            return 0;
        }
        int[] minPay = new int[cost.length];
        minPay[0] = cost[0];
        minPay[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            minPay[i] = Math.min(minPay[i - 1], minPay[i - 2]) + cost[i];
        }


        return Math.min(minPay[minPay.length - 1], minPay[minPay.length - 2]);

    }

    /**
     * 70. 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 注意：给定 n 是一个正整数。
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int s1 = 1, s2 = 2;
        int sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = s1 + s2;
            s1 = s2;
            s2 = sum;
        }
        return sum;
    }


    /**
     * 509. 斐波那契数
     */
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }

        int a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
