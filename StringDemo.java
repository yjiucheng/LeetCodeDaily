import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StringDemo {
    public static void main(String[] args) {

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
