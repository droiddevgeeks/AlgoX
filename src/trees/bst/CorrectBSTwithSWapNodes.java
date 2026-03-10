package trees.bst;

import trees.Node;
import trees.Traversal;

public class CorrectBSTwithSWapNodes {
    private static Node prev, first, middle, last;

    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(1);
        root.right = new Node(2);
        root.right.left = new Node(3);
        root.right.right = new Node(5);

        System.out.print("Inorder with swapped nodes\t");
        Traversal.inOrderTraversal(root);

        System.out.print("\nInorder after fixed nodes\t");
        fixSwapNodes(root);
        Traversal.inOrderTraversal(root);
    }

    private static void fixSwapNodes(Node root) {
        if (root == null) return;
        inOrderTraversal(root);

        if (first != null && last != null) {
            int temp = last.data;
            last.data = first.data;
            first.data = temp;
        } else if (first != null && middle != null) {
            int temp = middle.data;
            middle.data = first.data;
            first.data = temp;
        }
    }

    private static void inOrderTraversal(Node root) {
        if (root == null) return;
        inOrderTraversal(root.left);
        if (prev != null && prev.data > root.data) {
            if (first == null) {
                first = prev;
                middle = root;
            } else {
                last = root;
            }
        }
        prev = root;
        inOrderTraversal(root.right);
    }
}
