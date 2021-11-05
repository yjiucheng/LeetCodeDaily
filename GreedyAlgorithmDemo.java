import java.util.Arrays;

/**
 * 贪心算法
 */
public class GreedyAlgorithmDemo {
    public static void main(String[] args) {
//        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int[] nums = new int[]{-11,-14,-2};
        GreedyAlgorithmDemo demo = new GreedyAlgorithmDemo();
        System.err.println(demo.maxSubArray(nums));
    }

    /**
     * 53. 最大子序和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int lastSum = nums[0] <= 0 ? 0 : nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (lastSum + num < 0) {
                lastSum = 0;
                max = Math.max(max, num);
            } else {
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
