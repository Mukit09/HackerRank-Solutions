package DataStructure.SelfBalancingTree;

class Node {
    Node left, right;
    int val, ht;
}

public class Solution {

    static private int getHeight(Node node) {
        if(node == null)
            return -1;
        return node.ht;
    }

    static private int getBalance(Node node) {
        if(node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    static private void setHeight(Node node) {
        int leftSubTreeHeight = node.left == null ? -1 : getHeight(node.left);
        int rightSubTreeHeight = node.right == null ? -1 : getHeight(node.right);
        node.ht = 1 + Math.max(leftSubTreeHeight, rightSubTreeHeight);
    }

    static private Node doLLRotation(Node node) {
        Node tempNode1 = node.left.right;
        Node tempNode2 = node.left;
        node.left = tempNode1;
        tempNode2.right = node;
        setHeight(node);
        setHeight(tempNode2);
        return tempNode2;
    }

    static private Node doRRRotation(Node node) {
        Node tempNode1 = node.right.left;
        Node tempNode2 = node.right;
        node.right = tempNode1;
        tempNode2.left = node;
        setHeight(node);
        setHeight(tempNode2);
        return tempNode2;
    }

    static public Node insert(Node node, int key) {
        if(node == null) {
            Node tempNode = new Node();
            tempNode.val = key;
            tempNode.ht = 0;
            return tempNode;
        }
        if(key < node.val )
            node.left = insert(node.left, key);
        else if(key > node.val)
            node.right = insert(node.right, key);
        else
            return node;

        setHeight(node);
        int balance = getBalance(node);

        // balancing if not balanced
        // Inserted in Left Left
        if(balance > 1 && key < node.left.val)
            node = doLLRotation(node);
        else if(balance > 1 && key > node.left.val) {// Inserted in right child of left child of the node
            node.left = doRRRotation(node.left);
            node = doLLRotation(node);
        }
        else if(balance < -1 && key >  node.right.val)
            node = doRRRotation(node);
        else if(balance < -1 && key < node.right.val) {
            node.right = doLLRotation(node.right);
            node = doRRRotation(node);
        }
        return node;
    }

    static private void preOrderPrint(Node node) {
        if(node == null)
            return;
        System.out.print(" " + node.val);
        preOrderPrint(node.left);
        preOrderPrint(node.right);
    }

    public static void main(String[] args) {
        Node root = new Node();
        root.val = 10;
        root.ht = 0;
        root = insert(root, 20);
        root = insert(root, 30);
        root = insert(root, 40);
        root = insert(root, 50);
        //    root = demo.insert(root, 25);
        root = insert(root, 60);
        root = insert(root, 70);
        root = insert(root, 80);
        root = insert(root, 90);

        preOrderPrint(root);
        System.out.println();
    }
}