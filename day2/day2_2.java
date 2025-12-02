package day2;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class day2_2 {
    
    public static void main(String[] args) {

        String inp = new String();
        try {
            inp = Files.readAllLines(Paths.get("day2/day2input.txt")).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] ranges = inp.split(",");
        System.out.println(ranges.length);

        long total = 0;
        for (String range : ranges) {

            String[] startEnd = range.split("-");
            long start = Long.parseLong(startEnd[0]);
            long end = Long.parseLong(startEnd[1]);

            Pattern p = Pattern.compile("^(\\d+)\\1+$");
            for (long i = start; i <= end; i++) {
                String num = String.valueOf(i);
                if (p.matcher(num).matches()) {
                    total += i;
                }
            }
        }

        System.out.println(total);
    }
}
