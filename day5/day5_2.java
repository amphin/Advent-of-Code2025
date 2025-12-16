package day5;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class day5_2 {
    
    public static void main(String[] args) {

        List<String> inp = new ArrayList<>();
        try {
            inp = Files.readAllLines(Paths.get("day5/day5input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        List<Long[]> ranges = new ArrayList<>();
        for (String line : inp) {
            if (line.trim().isEmpty()) break;
            ranges.add(new Long[]{Long.parseLong(line.split("-")[0]), Long.parseLong(line.split("-")[1])});
        }

        long start = System.nanoTime();

        ranges.sort((a, b) -> Long.compare(a[0], b[0]));

        int i = 0;
        while (i < ranges.size() - 1) {
            long a = ranges.get(i)[0];
            long b = ranges.get(i)[1];
            long x = ranges.get(i+1)[0];
            long y = ranges.get(i+1)[1];

            if (b >= x) {
                ranges.set(i, new Long[]{a, Math.max(b, y)});
                ranges.remove(i+1);
                i--;
            }
            i++;
        }

        long freshCount = 0;
        for (Long[] range : ranges) {
            freshCount += range[1] - range[0] + 1;
        }

        long end = System.nanoTime();

        System.out.println(freshCount); // answer: 344260049617193
        System.out.printf("Time: %.3f ms%n", (end - start) / 1_000_000.0);
    }
}
