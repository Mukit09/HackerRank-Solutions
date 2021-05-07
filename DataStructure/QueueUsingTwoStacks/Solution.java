package DataStructure.QueueUsingTwoStacks;

import java.util.Scanner;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        Stack<Integer> front = new Stack<>();
        Stack<Integer> rear = new Stack<>();
        while(q-- > 0) {
            int type = scanner.nextInt();
            if(type == 1) {
                int x = scanner.nextInt();
                rear.push(x);
            }
            else {
                if(front.empty()) {
                    while (!rear.empty()) {
                        front.push(rear.pop());
                    }
                }
                if(!front.empty()) {
                    if (type == 2)
                        front.pop();
                    else
                        System.out.println(front.peek());
                }
            }
        }
    }
}
