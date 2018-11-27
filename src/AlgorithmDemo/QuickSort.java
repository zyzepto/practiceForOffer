package AlgorithmDemo;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {2,6,5,7,6,8,4,2};
        quickSort(arr, 0, arr.length-1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    private static void quickSort(int[] arr, int low, int high){
        if(low >= high) return;
        int left = low, right = high;
        int key = arr[low];
        while (left < right){
            while (left < right && arr[right] >= key){
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= key){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] =  key;
        quickSort(arr, low, left-1);
        quickSort(arr, left+1, high);
    }
}
