package DataStructure.Waiter;

import java.util.Scanner;
import java.util.Stack;

public class Solution {

    private static final int SIZE = 9800;
    private static int TOTAL_PRIME;
    static int pr[] = new int[1300];
    static boolean check[] = new boolean[SIZE];

    private static void sieve() {
        int sqrt = (int) Math.sqrt(SIZE);
        int cnt = 0;
        pr[cnt++] = 2;
        for(int i = 3; i<SIZE; i+=2) {
            if(!check[i]) {
                try {
                    pr[cnt++] = i;
                } catch(Exception ex) {
                    System.out.println("cnt exceeds for " + i);
                    break;
                }
                for (int j = i * i; j < SIZE; j += (2 * i)) {
                    check[j] = true;
                }
            }
        }
        TOTAL_PRIME = cnt;
    }

    static int[] waiter(int[] number, int q) {
        sieve();
        int n = number.length;
        Stack<Integer> stackArray[] = new Stack[q];
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stackA = new Stack<>();

        for (int i=0;i<n;i++) {
            stack.push(number[i]);
        }

        for(int i = 0; i< q; i++) {
            stackArray[i] = new Stack<>();
            while(!stack.empty()) {
                int prime = pr[i];
                int testNumber = stack.pop();
                if (testNumber % prime == 0) {
                    stackArray[i].push(testNumber);
                } else {
                    stackA.push(testNumber);
                }
            }
            stack = stackA;
            stackA = new Stack<>();
        }

        int index = 0;
        int[] outputArray = new int[n];
        for(int i = 0;i<q; i++) {
            while (!stackArray[i].empty()) {
                outputArray[index++] = stackArray[i].pop();
            }
        }

        while(!stack.empty()) {
            outputArray[index++] = stack.pop();
        }
        return outputArray;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] nq = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nq[0].trim());

        int q = Integer.parseInt(nq[1].trim());

        int[] number = new int[n];

        String[] numberItems = scanner.nextLine().split(" ");

        for (int numberItr = 0; numberItr < n; numberItr++) {
            int numberItem = Integer.parseInt(numberItems[numberItr].trim());
            number[numberItr] = numberItem;
        }

        int[] result = waiter(number, q);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            System.out.println(result[resultItr]);
        }
    }
}
