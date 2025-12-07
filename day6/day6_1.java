package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day6_1 {
    
    public static void main(String[] args) {
        long start = System.nanoTime();

        List<int[]> rows = new ArrayList<>();
        String[] ops = new String[0];

        try (BufferedReader br = new BufferedReader(new FileReader("day6/day6input.txt"))) {
            String line;
            String next;

            line = br.readLine();
            while (line != null) {
                String[] parts = line.trim().split("\\s+");
                
                next = br.readLine();
                if (next != null) {
                    rows.add(Arrays.stream(parts).mapToInt(Integer::parseInt).toArray());
                } else {
                    ops = parts;
                }
                line = next;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        long total = 0;
        for (int j = 0; j < ops.length; j++) {
            int[] nums = new int[rows.size()];
            for (int i = 0; i < rows.size(); i++) {
                nums[i] = rows.get(i)[j];
            }
            total += ops[j].equals("+") 
                            ? Arrays.stream(nums).asLongStream().sum() 
                            : Arrays.stream(nums).asLongStream().reduce(1, (a, b) -> a * b);
        }

        long end = System.nanoTime();

        System.out.println(total);
        System.out.printf("Time: %.3f ms%n", (end - start) / 1_000_000.0);
    }
}
