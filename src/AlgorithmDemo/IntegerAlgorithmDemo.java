package AlgorithmDemo;

import java.util.ArrayList;
import java.util.List;

public class IntegerAlgorithmDemo {

    public static void main(String[] args) {

    }

    /**
     * @author zepto
     * @Description
     * @date 2018/10/15 leetCode 89. 格雷编码
     * @return java.util.List<java.lang.Integer>
     **/

    public List<Integer> grayCode(int n) {
        if (n == 1) {
            List<Integer> res = new ArrayList<>();
            res.add(0);
            res.add(1);
            return res;
        }
        List<Integer> pre = grayCode(n - 1);
        List<Integer> res = new ArrayList<>(pre);
        int key = 1, tar = 0;
        for (int i = 0; i < pre.size(); i++) {
            if (pre.get(i) > tar) tar = pre.get(i);
        }
        while(key <= tar){
            key *= 2;
        }
        for (int i = pre.size() - 1; i >= 0; i--) {
            res.add(pre.get(i) + key);
        }
        return res;
    }
}
