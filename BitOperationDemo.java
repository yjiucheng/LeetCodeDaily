public class BitOperationDemo {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 20, 21, 22, 23, 24, 24};
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            System.err.println("当前数字：" + nums[i]);
            ans^=nums[i];
            System.err.println("异或结果：" + ans);

        }
    }

    /**
     * 287. 寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
     * <p>
     * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
     * <p>
     * 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
     */
    public int findDuplicate(int[] nums) {
        //todo  算法有问题
        int ans = 0;
        int lastAns = -1;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
            if (ans == lastAns) {
                return nums[i];
            } else {
                lastAns = ans;
            }
        }
        return ans;
    }

    /**
     * 338. 比特位计数
     *
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {

            ans[i] = Integer.bitCount(i);
        }
        return ans;
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
