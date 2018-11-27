package AlgorithmDemo;


public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {5,4,3,6,2,1,8,1};
        mergeSort(arr, 0, arr.length-1);
        for (int a : arr) {
            System.out.println(a);
        }
    }

    private static void mergeSort(int[] arr, int low, int high){
        if(low >= high){
            return;
        }
        int mid = (low + high)/2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }
    private static void merge(int[] arr, int low, int mid, int high){
        if(low >= high){
            return;
        }
        int[] tem = new int[high-low+1];
        int index = 0;
        int l = low, k = mid + 1;
        while(l <= mid && k <= high){
            if (arr[l] < arr[k]) {
                tem[index++] = arr[l++];
            }else{
                tem[index++] = arr[k++];
            }
        }
        while(l <= mid){
            tem[index++] = arr[l++];

        }
        while(k <= high){
            tem[index++] = arr[k++];
        }
        index = 0;
        for (int i = low; i <= high; i++) {
            arr[i] = tem[index++];
        }
    }
}
