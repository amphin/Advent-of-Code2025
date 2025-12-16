package day3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class day3_1 {
    
    public static void main(String[] args) {
        
        List<String> inp = new ArrayList<>();
        try {
            inp = Files.readAllLines(Paths.get("day3/day3input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int times = 2;
        
        long total = 0;
        for (String digits : inp) {
            int start = 0;
            char[] num = new char[times];
            for (int i = times - 1; i >= 0; i--) {
                int index = findHighest(start, digits, i);
                start = index + 1;
                num[times - 1 - i] = digits.charAt(index);
            }
            total += Long.parseLong(new String(num));
        }
        System.out.println(total); // answer: 17311
    }

    public static int findHighest(int start, String digits, int offset) {
        int[] count = new int[10];
        int[] first = new int[10];
        Arrays.fill(first, -1);

        for (int i = start; i < digits.length() - offset; i++) {
            int digit = Integer.parseInt(digits.substring(i, i+1));
            count[digit]++;
            if (first[digit] == -1) first[digit] = i;            
        }
        
        int highest = 9;        
        while (count[highest] == 0) {
            highest--;
        }

        return first[highest];
    }
}
