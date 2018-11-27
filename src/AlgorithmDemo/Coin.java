package AlgorithmDemo;

public class Coin {

    public static void main(String[] args) {
        int[] arr = new int[8];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 1;
        arr[3] = 2;
        arr[4] = 1;
        System.out.println(findMinNum(arr, 7));
    }
    
    public static int findMinNum(int num){
        int[] arr = new int[num];
        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 1;
        arr[3] = 2;
        arr[4] = 1;
        for (int i = 5; i < num; i++) {
            arr[i] = min(arr[i-1], arr[i-3], arr[i-5]) + 1;
        }
        return arr[num-1];
    }
    

    /** 
     * @author zepto
     * @Description
     * @param [arr, num]
     * @return int
     **/

    private static int findMinNum(int[] arr, int num){
        if (num < 5) return arr[num];
        arr[num] = min(findMinNum(arr, num - 1), findMinNum(arr, num - 3), findMinNum(arr, num - 3)) + 1;
        return arr[num];
    }
    

    /**
     * @author zepto
     * @Description
     * @date 2018/9/5
     * @param [i, j, k]
     * @return int
     **/

    private static int min(int i, int j, int k){
        if (i > j){
            if (j > k){
                return k;
            }else {
                return j;
            }
        }else if(i > k){
            return  k;
        }else return i;
    }
}
