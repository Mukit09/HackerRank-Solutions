package DataStructure.LeftRotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Mukit09
 * 07 May, 2021
 */

public class Solution {

    public static List<Integer> rotateLeft(int d, List<Integer> arr) {
        int n = arr.size();
        boolean b[] = new boolean[n];

        for (int i = 0;i<n; i++) {
            if (b[i] == false) {
                int j = i - d;
                int prevTemp = arr.get(i);
                while (true) {
                    if (j<0)
                        j+=n;
                    if (b[j])
                        break;
                    int tmp_j = arr.get(j);
                    arr.set(j, prevTemp);
                    prevTemp = tmp_j;
                    b[j] = true;
                    j-=d;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int d = scanner.nextInt();
        List<Integer> list = new ArrayList<>();

        for (int i = 0;i<n; i++) {
            list.add(scanner.nextInt());
        }

        List<Integer> result = rotateLeft(d, list);


        String collect = result.stream()
                .map(i -> Integer.toString(i))
                .collect(Collectors.joining(", "));
        System.out.println(collect);
    }
}
