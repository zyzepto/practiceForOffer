package AlgorithmDemo;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

public class StringAlgorithmDemo {

    public static void main(String[] args) {
        String s1 = "1";
        String s2 = "2";
        String s3 = "123";
        System.out.println(isAdditiveNumber(s3));
    }

    public static String findLastWord(String s){
        String res = new String();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' && i+1 < s.length()){
                res = s.substring(i+1);
                findLastWord(res);
            }
        }
        return res;
    }

    public static int func(int a, int b){
        if((a + b)%2 == 1) return 0;
        int x = (a + b)/2 - 1;
        int y = (a - b)/2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x; i++) {
            sb.append("x");
        }
        for (int i = 0; i < y; i++) {
            sb.append("y");
        }
        Set<String> set = new HashSet<>();
        rangeOfString(sb.toString().toCharArray(), 0, new String(), set);
        return set.size();
    }

    /**
     * @author zepto
     * @Description 字符串全排列
     * @date 2018/9/19
     * @return void
     **/
    public static void rangeOfString(char[] chars, int index, String s, Set<String> set){
        if (index == chars.length - 1){
            set.add(s);
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            String tem = new String(chars);
            rangeOfString(chars, index+1, tem, set);
            swap(chars, index, i);
            while (i + 1 < chars.length && chars[i] == chars[i+1]) i++;
        }
    }

    public static void swap(char[] chars, int i ,int j){
        char tem = chars[i];
        chars[i] = chars[j];
        chars[j] = tem;
    }

    /**
     * @author zepto
     * @Description leetCode49. 字母异位词分组
     * @date 2018/9/16
     * @return
     **/
    public static List<List<String>> groupAnagrams(String[] strs){
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String tem = String.valueOf(chars);
            if (!map.containsKey(tem)){
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(tem,list);
            }else {
                map.get(tem).add(s);
            }
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()){
            res.add(entry.getValue());
        }
        return res;
    }

    /** 
     * @author zepto
     * @Description leetcode 91. 解码方法
     * @date 2018/10/16
     * @return int
     **/
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length() + 1];
        if (s.charAt(0) != '0') {
            dp[0] = 1;
            dp[1] = 1;
        }
        for (int i = 1; i < s.length(); i++) {
            String temp = s.substring(i, i + 1);
            int j = Integer.valueOf(temp);
            if (j != 0){
                dp[i+1] = dp[i];
            }
            temp = s.substring(i-1, i+1);
            j = Integer.valueOf(temp);
            if (j > 0 && j < 27 && s.charAt(i - 1) != '0'){
                dp[i+1] += dp[i-1];
            }
        }
        return dp[s.length()];
    }

    public static void numDecodingsHelper(String s, int index, List<List<String>> list, List<String> tempList){
        if (index >= s.length()){
            list.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = 0; index + i < s.length(); i++) {
            String temp = s.substring(index, index + i + 1);
            int j = Integer.valueOf(temp);
            if (j > 26 || j == 0){
                return;
            }
            tempList.add(temp);
            numDecodingsHelper(s, index + i + 1, list, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     * @author zepto
     * @Description leetcode 93. 复原IP地址
     * @date 2018/10/16
     * @return java.util.List<java.lang.String>
     **/
    public static List<String> restoreIpAddresses(String s) {
        List<String> list = new ArrayList<>();
        restoreIpAddressesHelper(s, 0, list, new String());
        return list;
    }

    public static void restoreIpAddressesHelper(String s, int n, List<String> list, String temp) {
        if (s.length() > 3 * (4 - n))
            return;
        if (n == 4){
            if (s.length() == 0){
                list.add(temp.substring(0, temp.length()-1));
            }
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if(s.length() < i) break;
            String str = s.substring(0,i);
            if (Integer.parseInt(str) > 255 ||(str.length() >1 && str.charAt(0) == '0')){
                return;
            }
            restoreIpAddressesHelper(s.substring(i), n + 1, list, temp + str + ".");
        }
    }

    /**
     * @author zepto
     * @Description 131. 分割回文串
     * @date 2018/10/22
     * @return java.util.List<java.util.List<java.lang.String>>
     **/
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        partitionHelper(s, new ArrayList<>(), res, 0);
        return res;
    }

    public static void partitionHelper(String s, List<String> list, List<List<String>> res, int index) {
        if (index >= s.length()){
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 1; i <= s.length()-index; i++) {
            String temp = s.substring(index, index + i);
            if (isValid(temp, 0, temp.length()-1)){
                list.add(temp);
                partitionHelper(s, list, res, index+i);
                list.remove(list.size()-1);
            }
        }
    }

    public static boolean isValid(String s, int i, int j){
        if (i > j) return true;
        if (s.charAt(i) != s.charAt(j)) return false;
        return isValid(s, i + 1, j - 1);
    }

    public static String reverseWords(String s) {
        String res = new String();
        int i = 0, j = 0, n = s.length();
        while (i < n){
            j = i;
            while (j < n && s.charAt(j) != ' ') j++;
            String tem = new StringBuilder(s.substring(i,j)).reverse().toString();
            if (i != 0) tem = " " + tem;
            res += tem;
            while (j < n && s.charAt(j) == ' ') j++;
            i = j;
        }
        return new StringBuilder(res).reverse().toString();
    }

    /** 
     * @author zepto
     * @Description 165. 比较版本号
     * @date 2018/10/29
     * @return int
     **/
    public static int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        int i = 0;
        while (i < s1.length && i < s2.length){
            if (Integer.parseInt(s1[i]) > Integer.parseInt(s2[i])){
                return 1;
            }else if (Integer.parseInt(s1[i]) < Integer.parseInt(s2[i])){
                return -1;
            }
            i++;
        }
        if (i == s1.length && i < s2.length) {
            for (int j = i; j < s2.length; j++) {
                if (Integer.parseInt(s2[j]) == 0) return -1;
            }
            return 0;
        }else if (i < s1.length && i == s2.length) {
            for (int j = i; j < s1.length; j++) {
                if (Integer.parseInt(s1[j]) == 0) return 1;
            }
            return 0;
        }
        return 0;
    }

    /**
     * @author zepto
     * @Description 205. 同构字符串 建立映射关系
     * @date 2018/10/30
     * @return boolean
     **/
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character a = s.charAt(i);
            Character b = t.charAt(i);
            if (map.containsKey(a)){
                if (map.get(a) == b){
                    continue;
                }else return false;
            }else {
                if (!map.containsValue(b)) {
                    map.put(a, b);
                }else return false;
            }
        }
        return true;
    }

    public static List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while(i < nums.length){
            StringBuilder sb = new StringBuilder();
            if (i + 1 != nums.length) {
                sb.append(nums[i] + "->");
            } else sb.append(nums[i]);
            int j = i + 1;
            while (j < nums.length){
                if (nums[j] - nums[j-1] == 1){
                    j++;
                }else {
                    if(j == i + 1){
                        sb.delete(sb.length()-2, sb.length());
                    }else {
                        sb.append(nums[j-1]);
                    }
                    break;
                }
            }
            res.add(sb.toString());
            i = j;
        }
        return res;
    }

    public static boolean isAdditiveNumber(String num) {
        for(int i = 1; i < num.length()-1; i++){
            String num1 = num.substring(0,i);
            for(int j = i + 1; j < num.length(); j++){
                String num2 = num.substring(i, j);
                String str = num.substring(j);
                if(num2.charAt(0) == '0') break;
//                if(str.charAt(0) == '0') break;
                if(DFS(num1, num2, str)) return true;
            }
        }
        return false;
    }

    public static boolean DFS(String num1, String num2, String str){
        if(str.length() == 0) return true;
        if(str.charAt(0) == '0') return false;
        for(int i = 1; i <= str.length(); i++){
            String num3 = str.substring(0,i);
            String temp = str.substring(i);
            if(num3.equals(getSum(num1, num2)) && DFS(num2, num3, temp)) return true;
        }
        return false;
    }

    public static String getSum(String s1, String s2){
        StringBuilder sb = new StringBuilder();
        int flag = 0, len1 = s1.length(), len2 = s2.length();
        while(len1 > 0 || len2 > 0){
            int val = 0;
            if(len1 >  0) {
                val += s1.charAt(len1-1) - '0';
                len1--;
            }
            if(len2 >  0) {
                val += s2.charAt(len2-1) - '0';
                len2--;
            }
            sb.append((val + flag) % 10);
            flag = (val + flag) / 10;
        }
        if(flag != 0) sb.append(1);
        return sb.reverse().toString();
    }
}
