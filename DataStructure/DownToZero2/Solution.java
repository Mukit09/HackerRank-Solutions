package DataStructure.DownToZero2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

    private static int bfs(int n) {
        int cost[] = new int [n+1];
        Queue<Integer> queue = new LinkedList<>();
        cost[n] = 0;
        queue.offer(n);
        while(!queue.isEmpty()) {
            int u = queue.poll();
            int sqrt = (int) Math.sqrt(u);
            for(int i = 2; i<=sqrt; i++) {
                int v = u/i;
                if(u%i==0 && cost[v] == 0) {
                    queue.offer(v);
                    cost[v] = cost[u] + 1;
                }
            }
            int v = u -1;
            if(cost[v] == 0) {
                cost[v] = cost[u] + 1;
                queue.offer(v);
            }
            if(v == 0)
                return cost[v];
        }
        return -1;
    }

    private static int downToZero(int n) {
        if(n == 0)
            return 0;
        int cost = bfs(n);
        return cost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int q = Integer.parseInt(scanner.nextLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            int n = Integer.parseInt(scanner.nextLine().trim());

            int result = downToZero(n);

            System.out.println(String.valueOf(result));
        }
    }
}
