package day7;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class day7_1 {
   
    public static void main(String[] args) {
        
        List<String> grid = new ArrayList<>();
        try {
            grid = Files.readAllLines(Paths.get("day7/day7input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long start = System.nanoTime();

        boolean[][] explored = new boolean[grid.size()][grid.get(0).length()];
        int startPos = grid.get(0).indexOf('S');

        Queue<int[]> splitters = new ArrayDeque<>();
        explored[0][startPos] = true; // not needed for solution
        explored[1][startPos] = true; // not needed for solution
        explored[2][startPos] = true; // not needed for solution
        splitters.add(new int[]{2, startPos});

        int count = 1;
        while (!splitters.isEmpty()) {
            int[] splitterPos = splitters.poll();
            
            int[] leftRayPos = new int[]{splitterPos[0], splitterPos[1]-1};
            int[] rightRayPos = new int[]{splitterPos[0], splitterPos[1]+1};

            if (!explored[leftRayPos[0]][leftRayPos[1]]) {
                if (splitterExists(leftRayPos, grid, splitters, explored)) count++;
            }
            if (!explored[rightRayPos[0]][rightRayPos[1]]) {
                if (splitterExists(rightRayPos, grid, splitters, explored)) count++;
            }
        }

        long end = System.nanoTime();

        System.out.println(count); // answer: 1585
        System.out.printf("Time: %.3f ms%n", (end - start) / 1_000_000.0);
    }

    public static boolean splitterExists(int[] rayPos, List<String> grid, Queue<int[]> splitters, boolean[][] explored) {
        int[] splitterPos = exploreRay(new int[]{rayPos[0], rayPos[1]}, grid);

        if (splitterPos != null) {
            int sRow = splitterPos[0];
            int sCol = splitterPos[1];

            for (int i = rayPos[0]; i < sRow; i++) {
                explored[i][rayPos[1]] = true;
            }
            if (!explored[sRow][sCol]) {
                splitters.add(splitterPos);
                explored[sRow][sCol] = true;
                return true;
            }
        } else {
            for (int i = rayPos[0]; i < grid.size(); i++) {
                explored[i][rayPos[1]] = true;
            }
        }
        return false;
    }
    
    public static int[] exploreRay(int[] rayPos, List<String> grid) {
        while (rayPos[0] < grid.size() && grid.get(rayPos[0]).charAt(rayPos[1]) != '^') {
            rayPos[0]++;
        }
        if (rayPos[0] >= grid.size()) return null;
        return rayPos; // <- splitter position
    }    
}
