package AlgorithmDemo;

public class DynamicProgramming {

    public static void main(String[] args) {
        int[][] item = {{4,6},{5,4},{6,5},{2,3},{2,6}};
        System.out.println(oneZeroPacakge(item, 10));
    }

    private static int oneZeroPacakge(int[][] item , int target){
        int num = item.length;
        int[][] arr = new int[num][target];
        for (int i = 0; i < target; i++) {
               if(i > item[0][0]) arr[0][i] = item[0][1];
        }
        for (int i = 1; i < num; i++) {
            for (int j = 1; j < target; j++) {
                if (j - item[i][0] < 0){
                    arr[i][j] = arr[i-1][j];
                }else arr[i][j] = Math.max(arr[i-1][j], arr[i-1][j-item[i][0]] + item[i][1]);
//                System.out.println(arr[i][j]);
            }
        }
        return arr[num-1][target-1];
    }
}
