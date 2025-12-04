package day4;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class day4_2 {
    
    public static void main(String[] args) {
        
        List<String> inp = new ArrayList<>();
        try {
            inp = Files.readAllLines(Paths.get("day4/day4input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        long start = System.nanoTime();
 
        // maps each String in inp into char[], then collects it into a char[][]
        // stream -> used to filter/process a collection
        // :: -> method reference, used to shorten to lambda, e.g. map(s -> s.toCharArray())
        // char[][]::new -> shortens (size -> new char[size][])
        //  -> in char[size][], second is empty as it is inferred from size of Strings we mapped
        char[][] grid = inp.stream()
                        .map(String::toCharArray)
                        .toArray(char[][]::new);

        int[][] dirs = {{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};

        Queue<int[]> toRemove = new ArrayDeque<>();
        boolean[][] removed = new boolean[grid.length][grid[0].length];
        int total = 0;

        int[][] adj = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '.') {
                    adj[i][j] = -1;
                    continue;
                }
                int adjRolls = 0;
                for (int[] dir : dirs) {
                    int rAdj = i + dir[0];
                    int cAdj = j + dir[1];
                    if (rAdj >= 0 && rAdj < grid.length && cAdj >= 0 && cAdj < grid[0].length) {
                        if (grid[rAdj][cAdj] == '@') {
                            adjRolls++;
                        }
                    }
                }
                adj[i][j] = adjRolls;
                if (adjRolls < 4) {
                    toRemove.offer(new int[]{i,j});
                    removed[i][j] = true;
                    total++;
                }
            }
        }

        while (!toRemove.isEmpty()) {            
            int[] pos = toRemove.poll();
            int row = pos[0];
            int col = pos[1];

            if (adj[row][col] == -1) continue;
            adj[row][col] = -1;
            for (int[] dir : dirs) {
                int rAdj = row + dir[0];
                int cAdj = col + dir[1];
                if (rAdj >= 0 && rAdj < adj.length && cAdj >= 0 && cAdj < adj[0].length) {
                    if (adj[rAdj][cAdj] < 0) continue;
                    
                    adj[rAdj][cAdj]--;
                    if (adj[rAdj][cAdj] < 4 && !removed[rAdj][cAdj]) {
                        toRemove.offer(new int[]{rAdj, cAdj});
                        removed[rAdj][cAdj] = true;
                        total++;
                    }
                }
            }
        }
        
        long end = System.nanoTime();

        System.out.println(total);
        System.out.printf("Time: %.3f ms%n", (end - start) / 1_000_000.0);
    }
}
