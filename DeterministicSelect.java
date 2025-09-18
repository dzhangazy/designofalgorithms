import java.util.Arrays;
public class DeterministicSelect {
    static int comparisons = 0;
    public static int select(int[] arr, int k) {
        if (arr.length == 1) return arr[0];
        int pivot = medianOfMedians(arr);
        int[] lows = Arrays.stream(arr).filter(x -> x < pivot).toArray();
        int[] highs = Arrays.stream(arr).filter(x -> x > pivot).toArray();
        int[] pivots = Arrays.stream(arr).filter(x -> x == pivot).toArray();
        comparisons += arr.length;
        if (k < lows.length) return select(lows, k);
        else if (k < lows.length + pivots.length) return pivot;
        else return select(highs, k - lows.length - pivots.length);
    }
    private static int medianOfMedians(int[] arr) {
        if (arr.length <= 5) {
            Arrays.sort(arr);
            return arr[arr.length / 2];
        }
        int numMedians = (int) Math.ceil(arr.length / 5.0);
        int[] medians = new int[numMedians];
        for (int i = 0; i < numMedians; i++) {
            int start = i * 5;
            int end = Math.min(start + 5, arr.length);
            int[] group = Arrays.copyOfRange(arr, start, end);
            Arrays.sort(group);
            medians[i] = group[group.length / 2];
        }
        return medianOfMedians(medians);
    }
    public static void main(String[] args) {
        int[] arr = {7, 10, 4, 3, 20, 15};
        int k = 3; // найти 3-й элемент (по возрастанию)
        long start = System.nanoTime();
        int result = select(arr, k - 1);
        long end = System.nanoTime();

        System.out.println(k + "-th smallest element: " + result);
        System.out.println("Time: " + (end - start) + " ns");
        System.out.println("Comparisons: " + comparisons);
    }
}
