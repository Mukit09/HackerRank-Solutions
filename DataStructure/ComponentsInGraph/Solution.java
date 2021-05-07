package DataStructure.ComponentsInGraph;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Solution {
    private static int rank[];
    private static int parent[];
    private static int result[];

    private static void link(int u, int v) {
        if(rank[u]>=rank[v]) {
            parent[v] = u;
            rank[u]++;
        }
        else {
            parent[u] = v;
            rank[v]++;
        }
    }

    private static int find(int u) {
        if(parent[u] == u)
            return u;
        return parent[u] = find(parent[u]);
    }

    static int[] componentsInGraph(int[][] gb) {
        int n = 2*gb.length + 1;
        Vector<Integer>[] graph = new Vector[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        rank = new int[n];
        parent = new int[n];
        result = new int[n];

        for(int i = 1; i<n; i++) {
            rank[i] = 1;
            parent[i] = i;
        }

        for(int i = 0;i<gb.length;i++) {
            int u = gb[i][0];
            int v = gb[i][1];
            int pu = find(u);
            int pv = find(v);
            if(pu != pv)
                link(pu,pv);
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i=1;i<n;i++) {
            int pu = find(i);
            result[pu]++;
        }

        for(int i=1;i<n;i++) {
            if(result[i]>1) {
                min = Math.min(min, result[i]);
                max = Math.max(max, result[i]);
            }
        }

        int[] response = new int[2];
        response[0] = min;
        response[1] = max;
        return response;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)   {

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] gb = new int[n][2];

        for (int gbRowItr = 0; gbRowItr < n; gbRowItr++) {
            String[] gbRowItems = scanner.nextLine().split(" ");

            for (int gbColumnItr = 0; gbColumnItr < 2; gbColumnItr++) {
                int gbItem = Integer.parseInt(gbRowItems[gbColumnItr].trim());
                gb[gbRowItr][gbColumnItr] = gbItem;
            }
        }

        int[] SPACE = componentsInGraph(gb);

        for (int SPACEItr = 0; SPACEItr < SPACE.length;  SPACEItr++) {
            System.out.print(String.valueOf(SPACE[SPACEItr]));

            if (SPACEItr != SPACE.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
