package day7;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class day7_2 {
   
    public static void main(String[] args) {
        
        List<String> grid = new ArrayList<>();
        try {
            grid = Files.readAllLines(Paths.get("day7/day7input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        long start = System.nanoTime();
        
        // map of each splitter: {left, right}
        // left and right give how many timelines are created by choosing that direction

        // start from bottom row - 1
        // each splitter will have splitter: {1, 1}, as there are no other splitters below
        
        // bottom row - 2
        // for each splitter, fire ray off from left and from right
        // if no splitter hit, 1 (only 1 timeline, ray goes straight down off grid)
        // if splitter hit, add that splitter's left + right for total timelines off that left/right

        // continue until you reach start

        // combine left and right and plot it in integer grid at splitter location
        long totalTimelines = 0;
        long[][] timelines = new long[grid.size()][grid.get(0).length()];
        for (int i = grid.size() - 2; i >= 0; i--) {
            char[] row = grid.get(i).toCharArray();
            for (int j = 0; j < row.length; j++) {
                if (row[j] != '.') {
                    int[] leftSplitter = exploreRay(new int[]{i, j-1}, grid);
                    int[] rightSplitter = exploreRay(new int[]{i, j+1}, grid);

                    long leftTimelines = leftSplitter == null ? 1 : timelines[leftSplitter[0]][leftSplitter[1]];
                    long rightTimelines = rightSplitter == null ? 1 : timelines[rightSplitter[0]][rightSplitter[1]];

                    timelines[i][j] = leftTimelines + rightTimelines;
                    
                    if (row[j] == 'S') totalTimelines = leftTimelines + rightTimelines;
                }
            }
        }

        long end = System.nanoTime();

        System.out.println(totalTimelines); // answer: 16716444407407
        System.out.printf("Time: %.3f ms%n", (end - start) / 1_000_000.0);
    }
   
    public static int[] exploreRay(int[] rayPos, List<String> grid) {
        while (rayPos[0] < grid.size() && grid.get(rayPos[0]).charAt(rayPos[1]) != '^') {
            rayPos[0]++;
        }
        if (rayPos[0] >= grid.size()) return null;
        return rayPos; // <- splitter position
    }    
}
