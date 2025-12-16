package day8;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class day8_2 {
    
    public static void main(String[] args) {
        
        List<String> inp = new ArrayList<>();
        try {
            inp = Files.readAllLines(Paths.get("day8/day8input.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<int[]> junctions = new ArrayList<>();
        for (String line : inp) {
            String[] coords = line.split(",");
            int junctionX = Integer.parseInt(coords[0]);
            int junctionY = Integer.parseInt(coords[1]);
            int junctionZ = Integer.parseInt(coords[2]);

            junctions.add(new int[]{junctionX, junctionY, junctionZ});
        }
        long start = System.nanoTime();

        int numCoords = junctions.size();
        DSU dsu = new DSU(numCoords);

        record Edge(int a, int b, double w) {}
        int totalEdges = (numCoords * (numCoords - 1)) / 2;
        Edge[] edges = new Edge[totalEdges];

        int idx = 0;
        for (int i = 0; i < junctions.size() - 1; i++) {
            for (int j = i + 1; j < junctions.size(); j++) {
                edges[idx] = new Edge(i, j, euclideanDistance(junctions.get(i), junctions.get(j)));
                idx++;
            }
        }
        Arrays.sort(edges, Comparator.comparingDouble(e -> e.w()));

        int edgeIdx = -1;
        while (dsu.sets > 1) {
            edgeIdx++;
            dsu.union(edges[edgeIdx].a(), edges[edgeIdx].b());
        }
        long total = junctions.get(edges[edgeIdx].a())[0] * junctions.get(edges[edgeIdx].b())[0];
                     
        long end = System.nanoTime();

        System.out.println(total); // answer: 36045012
        System.out.printf("Time: %.3f ms%n", (end - start) / 1_000_000.0);
    }

    public static double euclideanDistance(int[] coords1, int[] coords2) {
        double deltaX = coords1[0] - coords2[0];
        double deltaY = coords1[1] - coords2[1];
        double deltaZ = coords1[2] - coords2[2];

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);   
    }

    static final class DSU {
        int[] parent;
        int[] size;
        int sets;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        boolean union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) return false; //in same set

            if (size[ra] < size[rb]) {
                int t = ra;
                ra = rb;
                rb = t;
            }

            parent[rb] = ra;
            size[ra] += size[rb];
            sets--;
            return true;
        }

    }
}