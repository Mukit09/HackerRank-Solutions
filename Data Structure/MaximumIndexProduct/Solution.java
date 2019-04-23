package DataStructure.MaximumIndexProduct;

import java.util.Scanner;
import java.util.Stack;

public class Solution {

    static class ValueIndexPair {
        private int value;
        private long index;

        public ValueIndexPair(int value, long index) {
            this.value = value;
            this.index = index;
        }
    }

    private static long[] giveLeftArray(int[] arr) {
        long left[] = new long[arr.length];
        Stack<ValueIndexPair> stack = new Stack<>();
        for (int i=0;i<arr.length;i++) {
            left[i] = calculate(i, arr, stack);
        }
        return left;
    }

    private static long calculate(int index, int arr[], Stack<ValueIndexPair> stack) {
        int value = arr[index];
        long ret = 0;
        if(stack.empty()) {
            stack.push(new ValueIndexPair(value, index));
            return 0;
        }
        if(stack.peek().value > value) {
            long index1 = stack.peek().index;
            ret = index1 + 1;
            stack.push(new ValueIndexPair(value, index));
        }
        else {
            while (!stack.empty()) {
                if(stack.peek().value > value) {
                    long index1 = stack.peek().index;
                    ret = index1 + 1;
                    break;
                }
                stack.pop();
            }
            stack.push(new ValueIndexPair(value, index));
        }
        return ret;
    }

    private static long[] giveRightArray(int arr[]) {
        long right[] = new long[arr.length];
        Stack<ValueIndexPair> stack = new Stack<>();
        for (int i=arr.length - 1;i>=0;i--) {
            right[i] = calculate(i, arr, stack);
        }
        return right;
    }

    private static long giveMaxProduct(long[] left, long[] right) {
        long ret = Integer.MIN_VALUE;
        for(int i=0;i<left.length; i++) {
            ret = Math.max(ret, left[i] * right[i]);
        }
        return ret;
    }

    static long solve(int[] arr) {

        long left[] = giveLeftArray(arr);
        long right[] = giveRightArray(arr);
        long max = giveMaxProduct(left, right);
        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int arrCount = scanner.nextInt();
        scanner.nextLine();

        int[] arr1 = new int[arrCount];
        String[] arrItems1 = scanner.nextLine().split(" ");

        for (int i = 0; i < arrItems1.length; i++) {
            int arrItem1 = Integer.parseInt(arrItems1[i]);
            arr1[i] = arrItem1;
        }

        long result = solve(arr1);

        System.out.println(String.valueOf(result));
    }
}
