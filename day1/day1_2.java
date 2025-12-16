package day1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class day1_2 {
    
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
            int rotation = Integer.parseInt(line.substring(1));
            password += rotation / 100;
            
            if (line.charAt(0) == 'L') {
                if (point == 0) point = 100;
                point -= rotation % 100;
                if (point <= 0) {
                    point += 100;
                    password++;
                }
            } else {
                if (point == 100) point = 0;
                point += rotation % 100;
                if (point >= 100) {
                    point -= 100;
                    password++;
                }
            }
        }
        
        System.out.println(password); // answer: 6133
    }

}
