import java.util.Arrays;

/**
 * 贪心算法
 */
public class GreedyAlgorithmDemo {
    public static void main(String[] args) {

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
