package day1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class day1_1 {
    
    public static void main(String[] args) {
        
        List<String> inp = new ArrayList<String>();
        try {
            inp = Files.readAllLines(Paths.get("day1/day1input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        int point = 50;
        int password = 0;
        for (String line : inp) {
            if (line.charAt(0) == 'L') {
                point -= Integer.parseInt(line.substring(1));
            } else {
                point += Integer.parseInt(line.substring(1));
            }
            if (point % 100 == 0) password++;
        }
        
        System.out.println(password);
    }

}
