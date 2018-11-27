package AlgorithmDemo;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(topK(arr,2)));
    }

    public static void heapSort(int[] arr){
        for (int i = arr.length/2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, i);
        }
    }

    public static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i];
        for (int j = 2*i + 1; j < length; j = 2*j + 1){
            if (j + 1 < length && arr[j+1] > arr[j]){
                j++;
            }
            if (arr[j] > temp){
                arr[i] = arr[j];
                i = j;
            }else break;
        }
        arr[i] = temp;
    }

    public static int[] topK(int[] arr, int k){
        int[] res = new int[k];
        for (int i = arr.length/2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        int count = 0;
        while (count < k){
            res[count] = arr[0];
            int tem = arr[arr.length-1-count];
            arr[arr.length-1-count] = arr[0];
            arr[0] = tem;
            adjustHeap(arr, 0, arr.length-1-count);
            count++;
        }
        return res;
    }
}
