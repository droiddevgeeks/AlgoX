package trees.bst;

import trees.Node;

/**
 * Binary Search Tree: 5 3 7 2 4 6 9 1 -1 -1 -1 -1 -1 8 10, Key = 8
 * Output:
 * Inorder Predecessor: 7, Inorder Successor: 9
 */
public class SuccessorAndPredecessorBST {
    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(3);
        root.left.left = new Node(2);
        root.left.left.left = new Node(1);
        root.left.right = new Node(4);
        root.right = new Node(7);
        root.right.left = new Node(6);
        root.right.right = new Node(9);
        root.right.right.left = new Node(8);
        root.right.right.right = new Node(10);

        int key = 8;
        int predecessor = findPredecessorBST(root, key);
        System.out.print("predecessor-->" + predecessor);

        int successor = findSuccessorBST(root, key);
        System.out.print("\nsuccessor-->" + successor);
    }

    private static int findPredecessorBST(Node root, int key) {
        Node pred = null;
        Node curr = root;
        while (curr != null) {
            if (key <= curr.data) {
                curr = curr.left;
            } else {
                pred = curr;
                curr = curr.right;
            }
        }
        return pred.data;
    }

    private static int findSuccessorBST(Node root, int key) {
        Node succ = null;
        Node curr = root;
        while (curr != null) {
            if (key >= curr.data) {
                curr = curr.right;
            } else {
                succ = curr;
                curr = curr.left;
            }
        }
        return succ.data;
    }
}
