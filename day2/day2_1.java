package day2;

import java.nio.file.Files;
import java.nio.file.Paths;

public class day2_1 {
    
    public static void main(String[] args) {

        String inp = new String();
        try {
            inp = Files.readAllLines(Paths.get("day2/day2input.txt")).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] ranges = inp.split(",");
        long total = 0;

        for (String range : ranges) {

            String[] startEnd = range.split("-");
            long start = Long.parseLong(startEnd[0]);
            long end = Long.parseLong(startEnd[1]);

            // Pattern p = Pattern.compile("^(\\d+)\\1$");
            // for (long i = start; i <= end; i++) {
            //     String num = String.valueOf(i);
            //     if (p.matcher(num).matches()) {
            //         total += i;
            //     }
            // }

            int startLength = startEnd[0].length();
            int endLength = startEnd[1].length();

            //if range only contains odd length numbers, cannot have repeating digit sequences
            if (startLength % 2 == 1 && startLength == endLength) {
                continue;
            }

            if (startLength % 2 == 1) {
                start = (long) Math.pow(10, startLength);
            } else if (endLength % 2 == 1){
                end = (long) Math.pow(10, endLength - 1) - 1;
            }

            // since input range is always max 1 extra digit from start to end,
            // start and end now guaranteed to be same even number of digits

            int repeatLength = String.valueOf(start).length() / 2;
            long min = Long.parseLong(String.valueOf(start).substring(0, repeatLength));
            long max = Long.parseLong(String.valueOf(end).substring(0, repeatLength));

            for (long i = min; i <= max; i++) {
                long invalid = Long.parseLong(String.valueOf(i) + String.valueOf(i));
                if (invalid >= start && invalid <= end) {
                    total += invalid;
                }
            }
        }

        System.out.println(total);
    }
}
