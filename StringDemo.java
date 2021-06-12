import java.util.*;

public class StringDemo {
    public static void main(String[] args) {
        System.err.println(strStr("mississippi", "issip"));
        String s = "123";
    }


    /**
     * 实现 strStr() 函数。
     * <p>
     * 给你两个字符串 haystack 和 needle ，
     * 请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
     * 说明：
     * <p>
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * <p>
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
     * <p>
     * <p>
     * 链接：https://leetcode-cn.com/problems/implement-strstr
     * 输入：haystack = "hello", needle = "ll"
     * 输出：2
     * 输入：haystack = "aaaaa", needle = "bba"
     * 输出：-1
     * 输入：haystack = "", needle = ""
     * 输出：0
     */
    public static int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        int ans = -1;
        char[] haysChars = haystack.toCharArray();
        char[] needleChars = needle.toCharArray();
        //KMP算法实现
        //1.先求前缀表
        int[] next = new int[needleChars.length];
        int j = 0;
        next[0] = j;
        for (int i = 1; i < needleChars.length; i++) {
            while (j > 0 && needleChars[i] != needleChars[j]) {
                j = next[j - 1];
            }

            if (needleChars[i] == needleChars[j]) {
                j++;
            }
            next[i] = j;
        }
        int targetIndex = 0;
        for (int index = 0; index < haysChars.length; index++) {
            if (haysChars[index] == needleChars[targetIndex]) {
                if (targetIndex == needleChars.length - 1) {
                    ans=index-targetIndex;
                    break;
                } else {
                    targetIndex++;
                }
            } else {
                if (targetIndex > 0) {
                    targetIndex = next[targetIndex - 1];
                    index--;
                }
            }
        }


        return ans;
    }


    /**
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
     * 请定义一个函数实现字符串左旋转操作的功能。
     * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     * <p>
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     * <p>
     * 输入: s = "lrloseumgh", k = 6
     * 输出: "umghlrlose"
     * <p>
     * 链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
     */
    public String reverseLeftWords(String s, int n) {
        StringBuilder builder = new StringBuilder();
        builder.append(s.substring(n, s.length()));
        builder.append(s.substring(0, n));

        return builder.toString();
    }

    /**
     * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
     * <p>
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * <p>
     * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
     * <p>
     * 说明：
     * <p>
     * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
     * 翻转后单词间应当仅用一个空格分隔。
     * 翻转后的字符串中不应包含额外的空格。
     * <p>
     * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
     * <p>
     * 输入：s = "the sky is blue"
     * 输出："blue is sky the"
     * <p>
     * 输入：s = "  Bob    Loves  Alice   "
     * 输出："Alice Loves Bob"
     */
    public static String reverseWords(String s) {
        StringBuilder ansBuild = new StringBuilder();
        char[] sNums = s.toCharArray();
        //上一个非空格的下标，用来确定起始位置是不是空格
        int lastWordIndex = -1;
        List<String> list = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int index = 0; index < sNums.length; index++) {
            char cur = sNums[index];
            //当前是空格：
            if (cur == ' ') {
                //首位空格
                if (lastWordIndex == -1) {
                    continue;
                }
                //前一位也是空格
                if (sNums[index - 1] == ' ') {
                    continue;
                }
                //处理空格:前面一个单词结束，开始记录下一个单词，同时吧上一个单词添加到list
                list.add(temp.toString());
                temp = new StringBuilder();
            } else {
                temp.append(cur);
                lastWordIndex = index;
                if (index == sNums.length - 1) {
                    list.add(temp.toString());
                }
            }
        }

        for (int index = list.size() - 1; index >= 0; index--) {
            if (list.get(index).length() == 0) {
                continue;
            }
            ansBuild.append(list.get(index));
            if (index != 0) {
                ansBuild.append(" ");
            }
        }

        return ansBuild.toString();
    }


    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     */
    public String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (char sChar : s.toCharArray()) {
            if (sChar == ' ') {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(sChar);
            }
        }
        return stringBuilder.toString();
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
