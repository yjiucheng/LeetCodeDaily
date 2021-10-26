import java.util.HashMap;
import java.util.Map;

public class DailyDemo {

    public static void main(String[] args) {

    }

    /**
     * 496. 下一个更大元素 I
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            int startIndex = i + 1;
            if (i == nums2.length - 1) {
                map.put(nums2[i], -1);
                break;
            }
            while (startIndex < nums2.length) {
                if (nums2[i] < nums2[startIndex]) {
                    map.put(nums2[i], nums2[startIndex]);
                    break;
                } else {
                    startIndex++;
                }
            }
            if (map.get(nums2[i]) == null) {
                map.put(nums2[i], -1);
            }
        }

        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }

}
