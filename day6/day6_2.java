package day6;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class day6_2 {
    
    public static void main(String[] args) {
        long start = System.nanoTime();

        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("day6/day6input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] ops = lines.get(lines.size() - 1).split("\\s+");
        lines.remove(lines.size() - 1);
        
        List<String[]> columns = new ArrayList<>();
        
        int i = 0;
        int maxLength = 0;
        while (i < lines.get(0).length()) {
            for (String line : lines) {
                int firstSpace = line.indexOf(" ", i);
                if (firstSpace == -1) {
                    maxLength = lines.get(0).length();
                    break;
                }
                maxLength = Math.max(firstSpace, maxLength);
            }
            String[] column = new String[lines.size()];
            for (int j = 0; j < lines.size(); j++) {
                column[j] = lines.get(j).substring(i, maxLength); 
            }
            columns.add(column);
            i = maxLength + 1;
        }

        long total = 0;
        for (int k = 0; k < columns.size(); k++) {
            String[] col = columns.get(k);
            StringBuilder[] verticalNums = new StringBuilder[col[0].length()];
            for (int l = 0; l < verticalNums.length; l++) verticalNums[l] = new StringBuilder(); 
            for (int l = 0; l < col[0].length(); l++) {
                for (String num : col) {
                    verticalNums[l].append(num.charAt(l));
                }
            }

            long colTotal = 0;
            boolean sum = ops[k].equals("+");
            if (!sum) colTotal = 1;
            for (StringBuilder sb : verticalNums) {
                if (sum) {
                    colTotal += Long.parseLong(sb.toString().trim());
                } else {
                    colTotal *= Long.parseLong(sb.toString().trim());
                }
            }
            total += colTotal;
        }

        long end = System.nanoTime();

        System.out.println(total); // answer: 11299263623062
        System.out.printf("Time: %.3f ms%n", (end - start) / 1_000_000.0);
    }
}
