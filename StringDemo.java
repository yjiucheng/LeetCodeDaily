import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StringDemo {
    public static void main(String[] args) {
        System.err.println(5 / 2);
    }

    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * <p>
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * <p>
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     * <p>
     * 链接：https://leetcode-cn.com/problems/reverse-string
     */
    public void reverseString(char[] s) {
        for (int index = 0; index < s.length / 2; index++) {
            char temp = s[index];
            s[index] = s[s.length - 1 - index];
            s[s.length - 1 - index] = temp;
        }
    }


    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     */
    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() % 2 != 0) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            char current = chars[index];
            if (map.containsKey(current)) {
                stack.push(current);
            } else {
                if (stack.empty() || stack.peek() != current) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.empty();
    }
}
