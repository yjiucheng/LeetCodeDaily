import java.util.*;

/**
 * 栈相关
 */
public class StackDemo {
    public static void main(String[] args) {

        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        System.err.println(maxSlidingWindow(nums, 3));
    }


    /**
     * 347. 前 K 个高频元素
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     */
    public int[] topKFrequent(int[] nums, int k) {
        //todo
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        return null;
    }


    /**
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
     * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * <p>
     * 返回滑动窗口中的最大值。
     * <p>
     * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        //todo
        int[] ans = new int[nums.length - k + 1];
        LinkedList<Integer> queue = new LinkedList();
        for (int i = 0; i < nums.length; i++) {
            //将num[i] 按从大到下插入Queue中，如果num[i+1]>num[i]，将num[i]出栈
            while (queue.size() > 0 && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.add(i);

            //判断当前qunue中所有数据是否满足条件
            if (queue.getFirst() <= i - k) {
                queue.pollFirst();
            }
            //在满足i+1-k>=0田间下，当前qunue最大的值加入数组
            if (i + 1 - k >= 0) {
                ans[i + 1 - k] = nums[queue.getFirst()];
            }
        }
        return ans;
    }


    /**
     * 1047. 删除字符串中的所有相邻重复项
     */
    public static String removeDuplicates(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (sb.length() > 0) {
                char stackChar = sb.charAt(sb.length() - 1);
                if (cur == stackChar) {
                    sb.deleteCharAt(sb.length() - 1);
                } else {
                    sb.append(cur);
                }
            } else {
                sb.append(cur);
            }
        }
        return sb.toString();
    }


    /**
     * 150. 逆波兰表达式求值
     * 说明：
     * <p>
     * 整数除法只保留整数部分。
     * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        HashSet<String> optor = new HashSet<>(Arrays.asList(new String[]{"+", "-", "*", "/"}));
        for (String str : tokens) {
            if (optor.contains(str)) {
                int value1 = stack.pop();
                int value2 = stack.pop();
                int ans = 0;
                switch (str) {
                    case "+":
                        ans = value1 + value2;
                        break;
                    case "-":
                        ans = value2 - value1;
                        break;
                    case "*":
                        ans = value1 * value2;
                        break;
                    case "/":
                        ans = value2 / value1;
                        break;
                }
                stack.push(ans);
            } else {
                stack.push(Integer.valueOf(str));
            }
        }
        return stack.pop();
    }

}
