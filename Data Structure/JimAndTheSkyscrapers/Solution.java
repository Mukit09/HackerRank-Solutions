package DataStructure.JimAndTheSkyscrapers;

import java.util.Scanner;
import java.util.Stack;

public class Solution {

    private static long calculateRoutes(Stack<Integer> stack) {
        int tmp = stack.pop();
        long cnt = 1;
        while(!stack.empty()) {
            if(stack.peek() != tmp)
                break;
            cnt++;
            stack.pop();
        }
        long totalRoutes = (cnt * (cnt - 1));
        return totalRoutes;
    }

    static long solve(int[] arr) {
        long totalRoutes = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i<arr.length; i++) {
            int height = arr[i];
            if(stack.empty()) {
                stack.push(height);
                continue;
            }
            if(height <= stack.peek())
                stack.push(height);
            else {
                while (!stack.empty() && height > stack.peek()) {
                    totalRoutes += calculateRoutes(stack);
                }
                stack.push(height);
            }
        }
        while (!stack.empty()) {
            totalRoutes += calculateRoutes(stack);
        }
        return totalRoutes;
    }

    public static void main(String[] args) {
        Scanner scanner =  new Scanner(System.in);
        int arrCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[arrCount];

    //    scanner.nextLine();
        String[] arrItems = scanner.nextLine().split(" ");

        for (int arrItr = 0; arrItr < arrCount; arrItr++) {
            int arrItem = Integer.parseInt(arrItems[arrItr]);
            arr[arrItr] = arrItem;
        }
        long result = solve(arr);
        System.out.println(String.valueOf(result));
    }
}

/*
6
3 2 1 2 3 3
 */
