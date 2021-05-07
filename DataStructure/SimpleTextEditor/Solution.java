package DataStructure.SimpleTextEditor;

import java.util.Scanner;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCase = scanner.nextInt();
        StringBuilder builder = new StringBuilder();
        Stack<StringBuilder> stack = new Stack<>();
        stack.push(new StringBuilder(""));
        scanner.nextLine();
        while(testCase-->0) {
            String[] inputArray = scanner.nextLine().split(" ");
            int type = Integer.parseInt(inputArray[0]);
            if(type == 1) {
                builder.append(inputArray[1]);
                stack.push(new StringBuilder(builder));
            }
            else if(type == 2) {
                int k = Integer.parseInt(inputArray[1]);
                int end = builder.length();
                int start = end - k;
                builder.delete(start, end);
                stack.push(new StringBuilder(builder));
            }
            else if(type == 3) {
                int k = Integer.parseInt(inputArray[1]);
                System.out.println(builder.charAt(k-1));
            }
            else {
                stack.pop();
                StringBuilder changerStringBuilder = stack.peek();
                builder = new StringBuilder(changerStringBuilder);
            }
        }
    }
}
