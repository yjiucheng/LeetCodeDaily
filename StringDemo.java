import java.util.*;

public class StringDemo {
    public static void main(String[] args) {
//        System.err.println(strStr("mississippi", "issip"));
//        String[] nums = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
//        System.err.println(groupAnagrams(nums));
        String s = "leetcode";
        System.err.println(reverseVowels(s));
    }


    /**
     * 345. 反转字符串中的元音字母
     * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
     * 输入："hello"
     * 输出："holle"
     * 输入："leetcode"
     * 输出："leotcede"
     */
    public static String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('A');
        set.add('e');
        set.add('E');
        set.add('i');
        set.add('I');
        set.add('o');
        set.add('O');
        set.add('u');
        set.add('U');
        char[] sChars = s.toCharArray();
        List<Integer> indexList = new ArrayList<>();
        List<Character> cahrList = new ArrayList<>();
        for (int i = 0; i < sChars.length; i++) {
            char cur = sChars[i];
            if (set.contains(cur)) {
                indexList.add(i);
                cahrList.add(cur);
            }
        }
        for (int i = 0; i < cahrList.size(); i++) {
            char curChar = cahrList.get(i);
            int newIndex = indexList.get(cahrList.size() - 1 - i);
            sChars[newIndex] = curChar;
        }
        return new String(sChars);
    }


    /**
     * 551. 学生出勤记录 I
     */
    public static boolean checkRecord(String s) {
        char[] sChars = s.toCharArray();
        int countA = 0;
        int countL = 0;
        for (int i = 0; i < sChars.length; i++) {
            char cur = sChars[i];
            if (cur == 'A') {
                countA++;
                if (countA >= 2) {
                    return false;
                }
            }
            if (countL < 3) {
                if (cur == 'L') {
                    countL++;
                    if (countL >= 3) {
                        return false;
                    }
                } else {
                    countL = 0;
                }
            }
        }
        return true;
    }

    /**
     * 171. Excel 表列序号
     * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。
     */
    public int titleToNumber(String columnTitle) {
        int length = columnTitle.length();
        int multi = 1;
        int ans = 0;
        for (int i = length - 1; i >= 0; i--) {
            int num = columnTitle.charAt(i) - 'A' + 1;
            ans += num * multi;
            multi *= 26;
        }
        return ans;
    }


    /**
     * 1736. 替换隐藏数字得到的最晚时间
     * 给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。
     * <p>
     * 有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。
     * <p>
     * 替换 time 中隐藏的数字，返回你可以得到的最晚有效时间。
     * 输入：time = "2?:?0"
     * 输出："23:50"
     * 解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
     */
    public String maximumTime(String time) {
        char[] timeChar = time.toCharArray();
        for (int i = 0; i < timeChar.length; i++) {
            char cur = timeChar[i];
            if (cur == '?') {
                switch (i) {
                    case 0:
                        if ('?' == timeChar[1]) {
                            timeChar[0] = '2';
                        } else {
                            int index1 = Integer.valueOf(String.valueOf(timeChar[1]));
                            if (index1 <= 3) {
                                timeChar[0] = '2';
                            } else {
                                timeChar[0] = '1';
                            }
                        }
                        break;
                    case 1:
                        if (timeChar[0] == '2') {
                            timeChar[1] = '3';
                        } else {
                            timeChar[1] = '9';
                        }
                        break;
                    case 3:
                        timeChar[3] = '5';
                        break;
                    case 4:
                        timeChar[4] = '9';
                        break;
                }
            }
        }
        return new String(timeChar);
    }

    /**
     * 面试题 10.02. 变位词组
     * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return ans;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String string : strs) {
            char[] strChars = string.toCharArray();
            Arrays.sort(strChars);
            String key = new String(strChars);
            if (map.containsKey(key)) {
                ans.get(map.get(key)).add(string);
            } else {
                List<String> list = new ArrayList<>();
                list.add(string);
                map.put(key, ans.size());
                ans.add(list);
            }
        }

        return ans;
    }

    /**
     * 70. 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 注意：给定 n 是一个正整数。
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int p = 0, q = 1, r = 2;
        for (int i = 3; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }


    /**
     * 14. 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String first = strs[0];
        int i = 1;
        if (first == null || first.length() == 0) {
            return "";
        }
        for (int index = 0; index < first.length(); index++) {
            char cur = first.charAt(index);
            i = 1;
            while (i < strs.length) {
                String str = strs[i];
                if (index >= str.length()) {
                    return first.substring(0, index);
                }
                if (str.charAt(index) != cur) {
                    return first.substring(0, index);
                }
                i++;
            }
        }
        return first;
    }

    private static String listToString(List<Character> list) {
        if (list.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (char cur : list) {
            builder.append(cur);
        }
        return builder.toString();
    }


    /**
     * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
     *
     * @param s
     * @return
     */
    public static boolean repeatedSubstringPattern(String s) {
        if (s.length() == 1) {
            return true;
        }
        int[] next = new int[s.length()];
        int j = 0;
        next[0] = j;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        int repeatLength = s.length() - next[s.length() - 1];
        if (repeatLength == s.length()) {
            return false;
        }
        return (s.length() % repeatLength) == 0;
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
                    ans = index - targetIndex;
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
