package trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem Statement: Given the root of a binary tree, return the level order traversal of its nodes' values.
 * (i.e., from left to right, level by level).
 * root = [3, 9, 20, null, null, 15, 7]
 * [ [3], [9, 20], [15, 7] ]
 * root = [1, 4, null, 4, 2]
 * [ [1], [4], [4, 2] ]
 */
public class LevelOrderTraversal {
    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);

        printLevelOrderTraversal(root);
        System.out.println();
        printLevelOrderTraversalWithNewLine(root);
    }

    private static void printLevelOrderTraversal(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.print(curr.data + " ");
            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }
    }

    private static void printLevelOrderTraversalWithNewLine(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr == null) {
                System.out.println();
                if (!queue.isEmpty()) queue.add(null);
            }else {
                System.out.print(curr.data + " ");
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }
    }
}
