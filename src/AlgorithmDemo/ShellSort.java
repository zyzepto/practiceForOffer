package AlgorithmDemo;

import java.util.Arrays;

public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {2,3,1,7,4,6,4,1,8};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr){
        for (int gap = arr.length/2; gap > 0; gap /=2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while (j >= gap && arr[j] < arr[j-gap]){
                    swap(arr, j, j-gap);
                    j -= gap;
                }
            }
        }
    }
    public static void swap(int[] arr, int i, int j){
        int tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }
}
