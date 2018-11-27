package AlgorithmDemo;


import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(), x = scanner.nextInt();
        int[] nums = new int[n];
        int index = 0;
        while (n > 0){
            nums[index++] = scanner.nextInt();
            n--;
        }
        List<Integer> res = new ArrayList<>();
        subsetsHelper(nums, 0, x, new ArrayList<>(), res);
        int min = 2147483647;
        for (Integer i : res) {
            if (i < min) min = i;
        }
        System.out.println(min);
        System.out.println(res);
    }

    public static void subsetsHelper(int[] nums, int start, int key, List<Integer> list, List<Integer> res){
        int sum = 0;
        for (Integer i: list) {
            sum += i;
        }
        if (sum >= key) {
            res.add(sum);
            return;
        }
        if (start == nums.length){
            return;
        }
        subsetsHelper(nums, start + 1, key, list, res);
        list.add(nums[start]);
        subsetsHelper(nums, start + 1, key, list, res);
        list.remove((Integer) nums[start]);
    }

    public static int func(){
        return 0;
    }

    public static long getBonus(long[] arr){
        long first = 0, third = arr[arr.length-1];
        for (int i = 0; i < arr.length-1; i++) {
            first += arr[i];
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            if (first == third){
                return first;
            }else if (first < third){
                for (int j = i+1; j < arr.length; j++) {
                    if (third - first == arr[j]){
                        return first;
                    }else third -= arr[j];
                }
            }
            first -= arr[i];
            third += arr[i];
        }
        return 0;
    }

    public static double findProbability(double m, double a, int r){
        double res = 0;
        if (m >= a){
            res += (m-a+1)/r;
        }
        for (double i = 1; i < a; i++) {
            double p = (a-1)/r;
            res += p*(1/(a-1))*findProbability(m, a-i, r);
        }
        return res;
    }

    public static List<String> findMostWords(String s){
        String[] strings = s.split(" ");
        for (int i = 0; i < strings.length; i++) {
            char c = strings[i].charAt(strings[i].length()-1);
            if ((c >= 65  && c <= 90) || (c >= 97 && c <= 122)){
                strings[i] = strings[i].toLowerCase();
            }else strings[i] = strings[i].substring(0, strings[i].length()-1);
        }
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String tem : strings) {
            if (!map.containsKey(tem)){
                map.put(tem, 1);
            }else {
                map.put(tem, map.get(tem)+1);
            }
        }
        int max = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            if (entry.getValue() > max){
                max = entry.getValue();
            }
        }
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()){
            if (entry.getValue() == max){
                res.add(entry.getKey());
            }
        }
        return res;
    }

    public static void func(List<String> strings, int target, Map<String, String> map){
        Map<String, List<String>> col = new HashMap<>();
        for (int i = 0; i < strings.size(); i++) {
            if (target < strings.get(i).length()) {
                String test = strings.get(i).substring(0,target + 1);
                if (!col.containsKey(strings.get(i).substring(0,target + 1))){
                    List<String> list = new ArrayList<>();
                    list.add(strings.get(i));
                    col.put(strings.get(i).substring(0,target + 1), list);
                }else {
                    List<String> list = col.get(strings.get(i).substring(0,target + 1));
                    list.add(strings.get(i));
                    col.put(strings.get(i).substring(0,target + 1), list);
                }
            }else map.put(strings.get(i), strings.get(i));
        }
        for (Map.Entry<String, List<String>> entry : col.entrySet()){
            if (entry.getValue().size() == 1){
                map.put(entry.getValue().get(0), entry.getKey());
            }else {
                func(entry.getValue(), target+1, map);
            }
        }
    }

    public static int func(int a, int b){
        int res = b - a + 1;
        for (int i = a; i < b; i++) {
            List<Integer> list = new ArrayList<>();
            int tem = i;
            while (tem > 0){
                list.add(tem%10);
                tem /= 10;
            }
            int size = list.size();
            for (int j = 0; j < size/2; j++) {
                if (list.get(j) == list.get(size-1-j)){
                    res--;
                    break;
                }
            }
        }
        return res;
    }
    public static String func(String str) {
        if(str==null || str.length()==0)
            return str;
        Stack<String> stack = new Stack<String>();
        String[] list = str.split("/");
        for(int i=0;i<list.length;i++){
            if(list[i].equals(".")||list[i].length()==0)
                continue;
            else if(!list[i].equals(".."))
                stack.push(list[i]);
            else{
                if(!stack.isEmpty())
                    stack.pop();
            }
        }
        StringBuilder res = new StringBuilder();
        Stack<String> temp = new Stack<String>();
        while(!stack.isEmpty())
            temp.push(stack.pop());
        while(!temp.isEmpty())
            res.append("/"+temp.pop());
        if(res.length()==0){
            res.append("/");
        }
        return res.toString();
    }


    public static void findRange(String s, List<Integer> integers, StringBuilder sb, List<String> strings){
        if (sb.length() == s.length()) {
            StringBuilder tem = new StringBuilder(sb);
            strings.add(tem.toString());
        }
        for (int i = 0; i < s.length(); i++) {
            if (!integers.contains(i)){
                if (sb.length() == 0 || s.charAt(i) != sb.charAt(sb.length() - 1)) {
                    sb.append(s.charAt(i));
                    integers.add(i);
                    findRange(s, integers, sb, strings);
                    integers.remove(integers.size()-1);
                    sb.deleteCharAt(sb.length() - 1);
                }
                while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) i++;
            }
        }
    }

    public static void recursionArrange(char[] arrayA, int start, int end, List<String> strings){
        if(end <= 1)
            return;
        if(start == end){
            strings.add(new String(arrayA));
        }
        else{
            for(int i = start;i <= end;i++){
                if (!(i-1 >= 0 && arrayA[i] == arrayA[i-1])) {
                    swap(arrayA,i,start);
                    if (!(i-1 >= 0 && arrayA[i] == arrayA[i-1])) {
                        recursionArrange(arrayA,start+1,end, strings);
                    }
                    swap(arrayA,i,start);
                }
                while (i + 1 < arrayA.length && arrayA[i] == arrayA[i+1]) i++;
            }
        }

    }
    //交换数组m位置和n位置上的值
    public static void swap(char[] arrayA,int m,int n){
        char temp = arrayA[m];
        arrayA[m] = arrayA[n];
        arrayA[n] = temp;
    }

    public static int findAddMinAttempts(List<Integer> arr, int target) {
        int res = 0;
        for (int i = 0; i < arr.size(); i++) {
            res++;
            if (9 - arr.get(i) >= target) {
                return res;
            } else target -= 9 - arr.get(i);
        }
        return 0;
    }

    public static int findDelMinAttempts(List<Integer> arr, int target){
        int res = 0;
        for (int i = 0; i < arr.size(); i++) {
            res++;
            if (arr.get(i) >= target){
                return res;
            }else target -= arr.get(i);
        }
        return 0;
    }



    public static int longestSubstring(String s) {

        Map<Character, Integer> charPosition = new HashMap<Character, Integer>();
        int[] preArr = new int[s.length()];
        char[] str2charArr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            Integer lastPosOfChar = charPosition.get(str2charArr[i]);
            if (lastPosOfChar == null) {
                preArr[i] = i == 0 ? 1 : preArr[i - 1] + 1;
                charPosition.put(str2charArr[i], i);
            } else {
                int aPos = lastPosOfChar + 1;
                int unRepeatLen = preArr[i - 1];
                int bPos = i - unRepeatLen;
                if (aPos >= bPos) {
                    preArr[i] = i - aPos + 1;
                } else {
                    preArr[i] = i - bPos + 1;
                }
                charPosition.put(str2charArr[i], i);
            }
        }
        int max = preArr[0];
        for (int i : preArr) if (i > max) max = i;
        return max;
    }

    public static void deleteAroundOne(int[][] arr, int m, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= m) {
            return;
        }
        if (arr[x][y] == 0) {
            return;
        } else {
            arr[x][y] = 0;
        }
        deleteAroundOne(arr, m, x - 1, y);
        deleteAroundOne(arr, m, x + 1, y);
        deleteAroundOne(arr, m, x, y - 1);
        deleteAroundOne(arr, m, x, y + 1);
    }

    public static void restoreIpAddresses(String s, String tmp,
                                          List<String> res, int n) {
        if (s.length() > 3 * (4 - n))
            return;
        if (n == 4) {
            if (s.length() == 0)
                res.add(tmp.substring(0, tmp.length() - 1));
            return;
        }
        for (int k = 1; k <= 3; k++) {
            if (s.length() < k)
                break;
            int val = Integer.parseInt(s.substring(0, k));
            if (val > 255 || k != String.valueOf(val).length())
                continue;
            restoreIpAddresses(s.substring(k), tmp + s.substring(0, k)
                    + ".", res, n + 1);
        }
    }


    public static int judge(int n) {
        if ((128 & n) == 0)
            return -1;
        if ((192 & n) == 128)//10
            return 0;
        if ((224 & n) == 192)
            return 1;
        if ((240 & n) == 224)
            return 2;
        if ((248 & n) == 240)
            return 3;
        return -2;
    }

    public static int validUtf8(int[] data) {
        boolean isUtf8 = false;// 当前num是否在一个验证的字符中
        int times = 0;
        for (int i = 0; i < data.length; i++) {
            if (isUtf8 == false) {//当前数不在某字符编码内
                times = judge(data[i]);//当前编码需要多少个10
                if (times == -2 || times == 0)//非编码
                    return 0;
                if (times > 0)//后面需要跟多少个10编码
                    isUtf8 = true;
            } else {
                if (times > 0 && judge(data[i]) == 0)//是10编码
                    times--;
                else
                    return 0;
                if (times == 0)
                    isUtf8 = false;
            }
        }
        return times > 0 ? 0 : 1;
    }

    public static int sumOfStar(int[][] arr, int n, int m){
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(arr[i]);
        }
        for (int i = 1; i <= n; i++) {
            int[] tem = new int[2];
            tem[0] = i;
            tem[1] = i;
            list.add(tem);
        }
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                if (arr[i][1] == arr[j][0]){
                    int[] tem = new int[2];
                    tem[0] = arr[i][0];
                    tem[1] = arr[j][1];
                    list.add(tem);
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int[] pair : list) {
                if (pair[0] == i){
                    set.add(pair[1]);
                }
            }
            if (set.size() == n){
                res++;
            }
        }
        return res;
    }

    static List<String> evaluateActions(List<String> actions) {
        Map<String, List<String>> cities = new HashMap<>();
        Map<String, Integer> soldiers = new HashMap<>();
        Map<String, String> survive = new HashMap<>();
        for (String action : actions) {
            String[] tem = action.split(" ");
            if (tem[tem.length-1] == "Hold"){
                List<String> list =new ArrayList<>();
                list.add(tem[0]);
                cities.put(tem[1], list);
            }else if (tem.length == 4 && !cities.containsKey(tem[2])){

            }
        }
        return null;
    }

}
class Pair{
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}

