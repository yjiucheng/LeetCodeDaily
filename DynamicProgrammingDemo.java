/**
 * 动态规划
 */
public class DynamicProgrammingDemo {
    public static void main(String[] args) {

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
