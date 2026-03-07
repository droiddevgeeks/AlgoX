package trees.hard;

import trees.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Input:Binary Tree: 4 2 5 3 -1 7 6 -1 9 -1 -1 8 -1 1
 * Output: [4 2 3 9 1 5 7 6 8]
 */
public class MorisInOrderTraversal {
    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.left.right = new Node(9);
        root.left.left.right.left = new Node(1);

        root.right = new Node(5);
        root.right.left = new Node(7);
        root.right.right = new Node(6);
        root.right.right.left = new Node(8);

        System.out.println("\nMoris PreOrder");
        List<Integer> res = morisPreOrderTraversal(root);
        System.out.println(res);

        System.out.println("\nMoris Inorder");
        res = morisInOrderTraversal(root);
        System.out.println(res);
    }

    private static List<Integer> morisInOrderTraversal(Node root) {
        if (root == null) return null;
        List<Integer> inOrderList = new ArrayList<>();
        Node curr = root;
        while (curr != null) {
            if (curr.left == null) {
                inOrderList.add(curr.data);
                curr = curr.right;
            } else {
                Node predecessor = curr.left;
                while (predecessor.right != null && predecessor.right != curr) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    predecessor.right = curr;
                    curr = curr.left;
                } else {
                    inOrderList.add(curr.data);
                    predecessor.right = null;
                    curr = curr.right;
                }
            }
        }
        return inOrderList;
    }

    private static List<Integer> morisPreOrderTraversal(Node root) {
        if (root == null) return null;
        List<Integer> inOrderList = new ArrayList<>();
        Node curr = root;
        while (curr != null) {
            if (curr.left == null) {
                inOrderList.add(curr.data);
                curr = curr.right;
            } else {
                Node predecessor = curr.left;
                while (predecessor.right != null && predecessor.right != curr) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    inOrderList.add(curr.data);
                    predecessor.right = curr;
                    curr = curr.left;
                } else {
                    predecessor.right = null;
                    curr = curr.right;
                }
            }
        }
        return inOrderList;
    }
}
