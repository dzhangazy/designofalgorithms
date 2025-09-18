import java.util.Arrays;
public class MergeSort {
    static int comparisons = 0;
    static int recursionDepth = 0;
    public static void mergeSort(int[] arr, int depth) {
        recursionDepth = Math.max(recursionDepth, depth);
        if (arr.length <= 1) return;

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left, depth + 1);
        mergeSort(right, depth + 1);

        merge(arr, left, right);
    }
    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            comparisons++;
            if (left[i] <= right[j]) arr[k++] = left[i++];
            else arr[k++] = right[j++];
        }
        while (i < left.length) arr[k++] = left[i++];
        while (j < right.length) arr[k++] = right[j++];
    }
    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        long start = System.nanoTime();
        mergeSort(arr, 1);
        long end = System.nanoTime();
        System.out.println("MergeSort result: " + Arrays.toString(arr));
        System.out.println("Time: " + (end - start) + " ns");
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Recursion depth: " + recursionDepth);
    }
}
