package DataStructure.Contacts;

import java.util.Scanner;

public class Solution {

    static class Node {
        Node next[] = new Node[30];
        int count;
    }
    private static int find(Node node, String st) {
        for(int i = 0; i<st.length(); i++) {
            int index = st.charAt(i) - 97;
            if(node.next[index] == null)
                return 0;
            node = node.next[index];
        }
        return node.count;
    }

    private static void insert(Node node, String st) {
        for(int i = 0; i<st.length(); i++) {
            int index = st.charAt(i) - 97;
            if(node.next[index] == null) {
                node.next[index] = new Node();
            }
            node = node.next[index];
            node.count++;
        }
    }

    private static int[] contacts(String[][] queries) {
        int size = 0;
        for ( String[] query : queries) {
            String command = query[0];
            if(command.equals("find"))
                size++;
        }

        int result[] = new int[size];

        int i = 0;
        Node root = new Node();
        for ( String[] query : queries) {
            String command = query[0];
            String contact = query[1];

            if(command.equals("add")) {
                root.count++;
                insert(root, contact);
            }
            else {
                result[i] = find(root, contact);
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int queriesRows = Integer.parseInt(scanner.nextLine().trim());

        String[][] queries = new String[queriesRows][2];

        for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                String queriesItem = queriesRowItems[queriesColumnItr];
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        int[] result = contacts(queries);
        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            System.out.print(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
