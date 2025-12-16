package day4;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class day4_1 {
    
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

        int total = 0;
        int[][] dirs = {{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != '@') continue;

                int numAdj = 0;
                for (int[] dir : dirs) {
                    if (numAdj >= 4) break;

                    int xAdj = i + dir[0];
                    int yAdj = j + dir[1];
                    if (xAdj >= 0 && xAdj < grid.length && yAdj >= 0 && yAdj < grid[0].length) {
                        if (grid[xAdj][yAdj] == '@') {
                            numAdj++;
                        }
                    } 
                }
                if (numAdj < 4) {
                    total++;
                }
            }
        }
        long end = System.nanoTime();

        System.out.println(total); // answer: 1320
        System.out.printf("Time: %.3f ms%n", (end - start) / 1_000_000.0);
    }
}
