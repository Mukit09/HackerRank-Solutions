package DataStructure.GameOfTwoStack;

import java.util.Scanner;

public class Solution {

    static int twoStacks(int x, int[] a, int[] b) {

        int count, i;
        long sum = 0;
        for(i = 0;i<a.length; i++) {
            if(sum+a[i]>x)
                break;
            sum+=a[i];
        }
        count = i;

        for(int j = 0;j<b.length;j++) {
            sum+=b[j];
            while(sum>x && i>0) {
                i--;
                sum-=a[i];
            }
            if(sum<=x && i+j+1>count)
                count = i+j+1;
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int g = Integer.parseInt(scanner.nextLine().trim());

        for (int gItr = 0; gItr < g; gItr++) {
            String[] nmx = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmx[0].trim());
            int m = Integer.parseInt(nmx[1].trim());
            int x = Integer.parseInt(nmx[2].trim());

            int[] a = new int[n];

            String[] aItems = scanner.nextLine().split(" ");

            for (int aItr = 0; aItr < n; aItr++) {
                int aItem = Integer.parseInt(aItems[aItr].trim());
                a[aItr] = aItem;
            }

            int[] b = new int[m];

            String[] bItems = scanner.nextLine().split(" ");

            for (int bItr = 0; bItr < m; bItr++) {
                int bItem = Integer.parseInt(bItems[bItr].trim());
                b[bItr] = bItem;
            }

            int result = twoStacks(x, a, b);
            System.out.println(String.valueOf(result));
        }
    }
}