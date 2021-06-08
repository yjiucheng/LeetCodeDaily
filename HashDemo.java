import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * hash
 */
public class HashDemo {
    public static void main(String[] args) {

        String ss = "cbaebabacd";
        String s = "abc";
        System.err.println(findAnagrams(ss, s));
        String k = ss;
        char[] asd = k.toCharArray();
        Arrays.sort(asd);

    }

    /**
     * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     * <p>
     * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
     * <p>
     * 说明：
     * <p>
     * 字母异位词指字母相同，但排列不同的字符串。
     * 不考虑答案输出的顺序。
     * <p>
     * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
     * "cbaebabacd"
     * "abc"
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        int targetLength = p.length();
        char[] pChars = p.toCharArray();
        Arrays.sort(pChars);
        String sortTarget = String.valueOf(pChars);
        int length = s.length();
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < length; fastIndex++) {
            char curChar = s.charAt(fastIndex);
            if (p.contains(String.valueOf(curChar))) {
                if (fastIndex - slowIndex == targetLength - 1) {
                    String subStr = s.substring(slowIndex, fastIndex + 1);
                    char[] sub = subStr.toCharArray();
                    Arrays.sort(sub);
                    String subSort = String.valueOf(sub);
                    if (sortTarget.equals(subSort)) {
                        ans.add(slowIndex);
                    }
                    slowIndex++;
                }
            } else {
                slowIndex = fastIndex + 1;
            }
        }
        return ans;
    }


    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * <p>
     * 链接：https://leetcode-cn.com/problems/group-anagrams
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int index = 0; index < strs.length; index++) {
            String str = strs[index];
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortStr = String.valueOf(chars);
            if (map.containsKey(sortStr)) {
                int listIndex = map.get(sortStr);
                List<String> intList = ans.get(listIndex);
                intList.add(str);
            } else {
                List<String> inList = new ArrayList<>();
                inList.add(str);
                int size = ans.size();
                ans.add(inList);
                map.put(sortStr, size);
            }
        }
        return ans;
    }


    /**
     * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，
     * 判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
     * <p>
     * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
     * <p>
     * 链接：https://leetcode-cn.com/problems/ransom-note
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int index = 0; index < ransomNote.length(); index++) {
            map.put(ransomNote.charAt(index), map.getOrDefault(ransomNote, 0) + 1);
        }
        for (int index = 0; index < magazine.length(); index++) {
            if (map.containsKey(magazine.charAt(index))) {
                int cout = map.get(magazine.charAt(index));
                cout--;
                if (cout == 0) {
                    map.remove(magazine.charAt(index));
                } else {
                    map.put(magazine.charAt(index), cout);
                }

            }
        }
        if (map.size() > 0) {
            return false;
        }
        return true;
    }


    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * <p>
     * 输入: s = "rat", t = "car"`
     * 输出: false
     */
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int index = 0; index < s.length(); index++) {
            if (map.containsKey(s.charAt(index))) {
                int cout = map.get(s.charAt(index));
                map.put(s.charAt(index), cout + 1);
            } else {
                map.put(s.charAt(index), 1);
            }
        }

        for (int index = 0; index < t.length(); index++) {
            if (!map.containsKey(t.charAt(index))) {
                return false;
            } else {
                int cout = map.get(t.charAt(index));
                cout--;
                if (cout == 0) {
                    map.remove(t.charAt(index));
                } else {
                    map.put(t.charAt(index), cout);
                }
            }
        }
        if (map.size() != 0) {
            return false;
        }

        return true;
    }
}
