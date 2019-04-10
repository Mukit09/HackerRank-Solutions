package DataStructure.swapNodesAlgo;

import java.util.*;

public class Solution {

    static int index;

    static class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
        }
    }

    static void buildTree(Node root, int[][] indexes) {
        Node[] nodeArray = new Node[indexes.length];
        nodeArray[0] = root;
        for(int i = 0;i<indexes.length; i++) {
            int leftChild = indexes[i][0];
            int rightChild = indexes[i][1];
            if(leftChild>0) {
                nodeArray[leftChild - 1] = new Node(leftChild);
                nodeArray[i].left = nodeArray[leftChild - 1];
            }
            if(rightChild>0) {
                nodeArray[rightChild - 1] = new Node(rightChild);
                nodeArray[i].right = nodeArray[rightChild - 1];
            }
        }
    }

    static void inOrderTraverse(Node node, int rowIndex, int[][] result) {
        if(node == null)
            return;
        inOrderTraverse(node.left, rowIndex, result);
        result[rowIndex][index++] = node.data;
        inOrderTraverse(node.right, rowIndex, result);
    }

    static void swap(Node node, int k, int currentLevel) {
         if(node == null)
            return;
        if(currentLevel%k == 0) {
            Node temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        swap(node.left, k, currentLevel + 1);
        swap(node.right, k, currentLevel + 1);
    }

    static int[][] swapNodes(int[][] indexes, int[] queries) {
        int result[][] = new int [queries.length][indexes.length];
        Node root = new Node(1);
        buildTree(root, indexes);
        for(int i = 0; i<queries.length; i++) {
            swap(root, queries[i], 1);
            index = 0;
            inOrderTraverse(root, i, result);
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                System.out.print(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    System.out.print(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                System.out.print("\n");
            }
        }

        System.out.println();
    }
}
