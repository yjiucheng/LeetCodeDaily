/**
 * 动态规划
 */
public class DynamicProgrammingDemo {
    public static void main(String[] args) {
        DynamicProgrammingDemo demo = new DynamicProgrammingDemo();
        System.err.println(demo.integerBreak(13));
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
