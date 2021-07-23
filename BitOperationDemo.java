public class BitOperationDemo {

    public static void main(String[] args) {

    }

    /**
     * 461. 汉明距离
     * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
     * <p>
     * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
     * 输入：x = 1, y = 4
     * 输出：2
     * 解释：
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     * ↑   ↑
     * 上面的箭头指出了对应二进制位不同的位置。
     */
    public int hammingDistance(int x, int y) {
        int ans = 0;
        int result = x ^ y;
        while (result != 0) {
            if (1 == (result & 1)) {
                ans++;
            }
            result = result >> 1;
        }
        return ans;
    }
}
