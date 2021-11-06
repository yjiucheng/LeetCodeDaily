import java.util.Arrays;

public class SortingAlgorithmDemo {

    public static void main(String[] args) {
        int[] nums = new int[]{7, 6, 5, 4, 3, 2, 1};
        SortingAlgorithmDemo demo = new SortingAlgorithmDemo();
        System.err.println(Arrays.toString(demo.sortArray3(nums)));

    }

    /**
     * 冒泡排序
     * 1, 5,  2, 4, 8, 7,1
     */
    public int[] sortArray1(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            System.err.println("i等于：" + i);
            for (int j = 1; j < length - i; j++) {
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                    System.err.println(Arrays.toString(nums));
                }
            }
        }
        return nums;
    }

    /**
     * 选择排序
     * 1, 5,  2, 4, 8, 7,1
     */
    public int[] sortArray2(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int minNumIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (nums[j] < nums[minNumIndex]) {
                    minNumIndex = j;
                }
            }
            if (minNumIndex != i) {
                int tem = nums[i];
                nums[i] = nums[minNumIndex];
                nums[minNumIndex] = tem;
            }
        }
        return nums;
    }

    /**
     * 插入排序
     * 1, 5,  2, 4, 8, 7,1
     */
    public int[] sortArray3(int[] nums) {
        int length = nums.length;
        for (int i = 1; i < length; i++) {
            int temp = nums[i];
            int index = i;
            while (index > 0 && nums[index - 1] > temp) {
                nums[index] = nums[index - 1];
                index--;
            }
            nums[index] = temp;
        }
        return nums;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
