package DataStructure.CheckBST;

public class Solution {

    class Node {
        int data;
        Node left;
        Node right;
    }

    boolean check(Node node, int min, int max) {
        if(node == null)
            return true;
        if(node.data>=max || node.data<=min)
            return false;
        return check(node.left, min, node.data) & check(node.right, node.data, max);
    }

    boolean checkBST(Node root) {
        return check(root, 0, Integer.MAX_VALUE);
    }
}
