package trees.bst;

import trees.Node;
import trees.Traversal;

/**
 * 5
 * / \
 * 3   8
 * / \   \
 * 2   4   9
 */
public class InsertAndDeleteInBST {
    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(3);
        root.left.left = new Node(2);
        root.left.right = new Node(4);

        root.right = new Node(8);
        root.right.right = new Node(9);

        int val = 7;
        //root = insertInBSTRec(root, val);
        //Traversal.inOrderTraversal(root);

        root = insertInBSTIterative(root, val);
        System.out.print("After Insert\t");
        Traversal.inOrderTraversal(root);

        root = deleteInBST(root, 8);
        System.out.print("\nAfter Delete\t");
        Traversal.inOrderTraversal(root);
    }

    private static Node insertInBSTRec(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.data) root.left = insertInBSTRec(root.left, val);
        if (val > root.data) root.right = insertInBSTRec(root.right, val);
        return root;
    }

    private static Node insertInBSTIterative(Node root, int val) {
        Node newnode = new Node(val);
        if (root == null) return newnode;
        Node curr = root;

        while (true) {
            if (val < curr.data) {
                if (curr.left == null) {
                    curr.left = newnode;
                    break;
                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = newnode;
                    break;
                }
                curr = curr.right;
            }
        }
        return root;
    }

    private static Node deleteInBST(Node root, int val) {
        if (root == null) return null;
        if (val < root.data) root.left = deleteInBST(root.left, val);
        else if (val > root.data) root.right = deleteInBST(root.right, val);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            Node successor = findSuccessor(root.right);
            root.data = successor.data;
            root.right = deleteInBST(root.right, successor.data);
        }
        return root;
    }

    private static Node findSuccessor(Node root) {
        while (root.left != null) root = root.left;
        return root;
    }
}
