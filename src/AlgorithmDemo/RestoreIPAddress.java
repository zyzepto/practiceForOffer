package AlgorithmDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class RestoreIPAddress {

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        List<List<Integer>> res = new ArrayList<>();
        combinationSum2(arr, 3, 0, res, new ArrayList<Integer>());
        System.out.println(res);
    }

    /**
     * @author zepto
     * @Description
     * @date 2018/9/14
     * @return void
     **/
    public static void restoreIPAddress(String s, int mark, String item, List<String> list){
        if (mark > 3) return;
        if (mark == 3 && isValid(s)){
            list.add(item + s);
            return;
        }
        for (int i = 1; i < 4 && i < s.length(); i++) {
            String sub = s.substring(0, i);
            if (isValid(sub)) {
                int length = s.length();
                restoreIPAddress(s.substring(i, length), mark + 1, item+ sub + ".",list);
            }
        }
    }

    public static boolean isValid(String s){
        if (s.charAt(0) == '0'){
            return s.equals("0");
        }
        int i = Integer.parseInt(s);
        if (i > 255 || i < 0){
            return false;
        }
        return true;
    }

    /**
     * @author zepto
     * @Description
     * @date 2018/9/14
     * @return
     **/
    public static void combinationSum1(int[] candidates, int target, int start, List<List<Integer>> result, List<Integer> list){
        if (target < 0) return;
        if (target == 0){
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            //不能改变target值
            list.add(candidates[i]);
            combinationSum1(candidates, target-candidates[i], i, result, list);
            list.remove(list.size()-1);
        }
    }

    /**
     * @author zepto
     * @Description
     * @date 2018/9/14
     * @return void
     **/
    public static void combinationSum2(int[] candidates, int target, int start, List<List<Integer>> result, List<Integer> list){
        if (target < 0) return;
        if (target == 0){
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            //不能改变target值
            list.add(candidates[i]);
            combinationSum2(candidates, target-candidates[i], i+1, result, list);
            list.remove(list.size()-1);
        }
    }
}
