package day9;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class day9_1 {
    
    public static void main(String[] args) {
        List<String> inp = new ArrayList<>();

        try {
            inp = Files.readAllLines(Paths.get("day9/day9input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        record Point(int x, int y) {}
        Point[] points = new Point[inp.size()];
        int maxX = 0;
        int maxY= 0;
        for (int i = 0; i < inp.size(); i++) {
            int x = Integer.parseInt(inp.get(i).split(",")[0]);
            int y = Integer.parseInt(inp.get(i).split(",")[1]);
            maxX = Math.max(x, maxX);
            maxY = Math.max(y, maxY);
            points[i] = new Point(Integer.parseInt(inp.get(i).split(",")[0]), Integer.parseInt(inp.get(i).split(",")[1]));

        }
        long start = System.nanoTime();

        Arrays.sort(points, Comparator.comparingInt((Point p) -> p.x()).thenComparingInt((Point p) -> p.y()));
        // for (Point p : points) {
        //     System.out.println(p);
        // }

        // Set<Point> pointSet = new HashSet<>(Arrays.asList(points));

        // for (int y = 0; y <= maxY + 1; y++) {
        //     for (int x = 0; x <= maxX + 1; x++) {
        //         if (pointSet.contains(new Point(x, y))) {
        //             System.out.print("#");
        //         } else {
        //             System.out.print(".");
        //         }
        //     }
        //     System.out.println();
        // }



        long maxArea = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                long deltaX = Math.abs(points[j].x() - points[i].x()) + 1;
                long deltaY = Math.abs(points[j].y() - points[i].y()) + 1;
                // System.out.println(deltaX * deltaY);
                maxArea = Math.max(deltaX * deltaY, maxArea);
            }
        }

        long end = System.nanoTime();

        System.out.println(maxArea);
        System.out.printf("Time: %.3f ms%n", (end - start) / 1_000_000.0);
    }
}
