import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

/**
 * 栈相关
 */
public class StackDemo {
    public static void main(String[] args) {
        System.err.println(removeDuplicates("abbaca"));
    }

    /**
     * 1047. 删除字符串中的所有相邻重复项
     */
    public static String removeDuplicates(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i =0; i <s.length() ;i++) {
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
