import java.util.*;

public class ClosestPairOfPoints {
    static class Point {
        double x, y;
        Point(double x, double y) { this.x = x; this.y = y; }
    }

    static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));
    }

    public static double closestPair(Point[] points) {
        Arrays.sort(points, Comparator.comparingDouble(p -> p.x));
        return closest(points, 0, points.length - 1);
    }

    private static double closest(Point[] points, int l, int r) {
        if (r - l <= 3) {
            double min = Double.MAX_VALUE;
            for (int i = l; i <= r; i++) {
                for (int j = i + 1; j <= r; j++) {
                    min = Math.min(min, distance(points[i], points[j]));
                }
            }
            return min;
        }

        int mid = (l + r) / 2;
        double d1 = closest(points, l, mid);
        double d2 = closest(points, mid + 1, r);
        double d = Math.min(d1, d2);
        List<Point> strip = new ArrayList<>();
        double midX = points[mid].x;
        for (int i = l; i <= r; i++) {
            if (Math.abs(points[i].x - midX) < d) strip.add(points[i]);
        }
        strip.sort(Comparator.comparingDouble(p -> p.y));

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < d; j++) {
                d = Math.min(d, distance(strip.get(i), strip.get(j)));
            }
        }
        return d;
    }
    public static void main(String[] args) {
        Point[] points = {
            new Point(2, 3), new Point(12, 30), new Point(40, 50),
            new Point(5, 1), new Point(12, 10), new Point(3, 4)
        };
        long start = System.nanoTime();
        double minDist = closestPair(points);
        long end = System.nanoTime();

        System.out.println("Closest pair distance: " + minDist);
        System.out.println("Time: " + (end - start) + " ns");
    }
}
