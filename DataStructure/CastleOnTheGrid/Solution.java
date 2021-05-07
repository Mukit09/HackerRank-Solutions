package DataStructure.CastleOnTheGrid;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

    private static int[] dx = {0, -1, 0, 1};
    private static int[] dy = {-1, 0, 1, 0};
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private static boolean checkedPoint(String[] grid, int x, int y, int n) {
        if(x < 0 || x >= n || y < 0 || y >= n)
            return false;
        if(grid[x].charAt(y) == 'X')
            return false;
        return true;
    }

    private static int bfs(String[] grid, int startX, int startY, int goalX, int goalY) {
        int n = grid.length;
        boolean visit[][] = new boolean[n][n];
        int cost[][] = new int[n][n];
        Queue<Point> queue = new LinkedList<>();
        Point point = new Point(startX, startY);
        queue.offer(point);
        while(!queue.isEmpty()) {
            Point point1 = queue.poll();
            int x = point1.getX();
            int y = point1.getY();
            for(int i = 0; i<4; i++) {
                int tempX = x;
                int tempY = y;
                while(true) {
                    int newX = tempX + dx[i];
                    int newY = tempY + dy[i];
                    boolean isValid = checkedPoint(grid, newX, newY, n);
                    if(!isValid)
                        break;
                    if (!visit[newX][newY]) {
                        cost[newX][newY] = cost[x][y] + 1;
                        if (newX == goalX && newY == goalY)
                            return cost[newX][newY];
                        visit[newX][newY] = true;
                        queue.offer(new Point(newX, newY));
                    }
                    tempX = newX;
                    tempY = newY;
                }
            }
        }
        return -1;
    }
    static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {

        int minMoves = bfs(grid, startX, startY, goalX, goalY);
        return minMoves;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] startXStartY = scanner.nextLine().split(" ");

        int startX = Integer.parseInt(startXStartY[0]);
        int startY = Integer.parseInt(startXStartY[1]);
        int goalX = Integer.parseInt(startXStartY[2]);
        int goalY = Integer.parseInt(startXStartY[3]);
        int result = minimumMoves(grid, startX, startY, goalX, goalY);

        System.out.println(String.valueOf(result));


        scanner.close();
    }
}
