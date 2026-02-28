package trees;

import java.util.*;

/**
 * Input:Binary Tree: 1 2 7 3 -1 -1 8 -1 4 9 -1 5 6 10 11
 * Output: Boundary Traversal: [1, 2, 3, 4, 5, 6, 10, 11, 9, 8, 7]
 */
public class BoundaryTraversal {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.left.right = new Node(4);
        root.left.left.right.left = new Node(5);
        root.left.left.right.right = new Node(6);

        root.right = new Node(7);
        root.right.right = new Node(8);
        root.right.right.left = new Node(9);
        root.right.right.left.left = new Node(10);
        root.right.right.left.right = new Node(11);

        List<Integer> res = new ArrayList<>();
        printBoundaryNodes(root, res);
        System.out.println(Arrays.toString(res.toArray()));
    }

    private static void printBoundaryNodes(Node root, List<Integer> res) {
        if (root == null) return;
        if(!isLeafNode(root)) res.add(root.data);
        traverseLeftBoundary(root, res);
        traverseLeafNode(root, res);
        traverseRightBoundary(root, res);
    }

    private static void traverseLeftBoundary(Node root, List<Integer> res) {
        Node curr = root.left;
        while (curr != null) {
            if (!isLeafNode(curr)) {
                res.add(curr.data);
            }
            if (curr.left != null) curr = curr.left;
            else curr = curr.right;
        }
    }

    private static void traverseLeafNode(Node root, List<Integer> res) {
        if (root == null) return;
        if (isLeafNode(root)) {
            res.add(root.data);
            return;
        }
        traverseLeafNode(root.left, res);
        traverseLeafNode(root.right, res);
    }

    private static void traverseRightBoundary(Node root, List<Integer> res) {
        Stack<Integer> stack = new Stack<>();
        Node curr = root.right;
        while (curr != null) {
            if (!isLeafNode(curr)) {
                stack.push(curr.data);
            }
            if (curr.right != null) curr = curr.right;
            else curr = curr.left;
        }
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
    }

    private static boolean isLeafNode(Node root) {
        return root.left == null && root.right == null;
    }
}