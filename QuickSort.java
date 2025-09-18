import java.util.Arrays;
import java.util.Random;
public class QuickSort {
    static int comparisons = 0;
    static int recursionDepth = 0;
    static Random rand = new Random();

    public static void quickSort(int[] arr, int low, int high, int depth) {
        recursionDepth = Math.max(recursionDepth, depth);
        if (low < high) {
            int pivotIndex = low + rand.nextInt(high - low + 1);
            int pivot = arr[pivotIndex];
            int i = low, j = high;
            while (i <= j) {
                while (arr[i] < pivot) { i++; comparisons++; }
                while (arr[j] > pivot) { j--; comparisons++; }
                if (i <= j) {
                    int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
                    i++; j--;
                }
            }
            if (low < j) quickSort(arr, low, j, depth + 1);
            if (i < high) quickSort(arr, i, high, depth + 1);
        }
    }
    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        long start = System.nanoTime();
        quickSort(arr, 0, arr.length - 1, 1);
        long end = System.nanoTime();

        System.out.println("QuickSort result: " + Arrays.toString(arr));
        System.out.println("Time: " + (end - start) + " ns");
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Recursion depth: " + recursionDepth);
    }
}
