package trees.hard;

import trees.Node;
import trees.Traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary Tree: 1 2 5 3 4 -1 6 -1 -1 -1 -1 7
 * 1 -1 2 -1 3 -1 4 -1 5 -1 6 -1 7
 */
public class FlattenBinaryTreeList {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);

        root.right = new Node(5);
        root.right.right = new Node(6);
        root.right.right.left = new Node(7);

        List<Node> nodes = new ArrayList<>();
        flattenBinaryTreeWithPreOrder(root, nodes);
        System.out.print("\nWith PreOrder Extra List\t");
        for (Node node : nodes) System.out.print(node.data + "\t");

        System.out.print("\nWith Rec\t");
        flattenBinaryTreeWithRecursion(root);
        Traversal.inOrderTraversal(root);

        System.out.print("\nWith Moris Traversal\t");
        flattenBinaryTreeWithMorisTraversal(root);
    }

    /**
     * Do preorder traversal
     * Store nodes in a list
     * Reconnect them like a linked list
     * Time  : O(N)
     * Space : O(N)
     */
    private static void flattenBinaryTreeWithPreOrder(Node root, List<Node> list) {
        preOrderTraversal(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            Node curr = list.get(i);
            curr.left = null;
            curr.right = list.get(i + 1);
        }
    }

    private static void preOrderTraversal(Node root, List<Node> list) {
        if (root == null) return;
        list.add(root);
        preOrderTraversal(root.left, list);
        preOrderTraversal(root.right, list);
    }

    /**
     * Time  : O(N^2)
     * Space : O(H)
     */
    private static void flattenBinaryTreeWithRecursion(Node root) {
        if (root == null) return;
        flattenBinaryTreeWithRecursion(root.left);
        flattenBinaryTreeWithRecursion(root.right);

        Node temp = root.right; // save right side
        root.right = root.left; // assign left side to right.
        root.left = null; // make left null

        Node curr = root;
        while (curr.right != null) curr = curr.right; // go extreme right

        curr.right = temp;
    }


    private static void flattenBinaryTreeWithMorisTraversal(Node root) {
        if (root == null) return;
        Node curr = root;
        while (curr != null) {
            if (curr.left != null) {
                Node predecessor = curr.left;
                while (predecessor.right != null) predecessor = predecessor.right;

                predecessor.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }
}
