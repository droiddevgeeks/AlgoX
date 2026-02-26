package trees;

/**
 * Problem Statement: Given two Binary Trees, return if true if the two trees are identical, otherwise return false..
 * Two trees are said to be identical if these three conditions are met for every pair of nodes :
 * Value of a node in the first tree is equal to the value of the corresponding node in the second tree.
 * Left subtree of this node is identical to the left subtree of the corresponding node.
 * Right subtree of this node is identical to the right subtree of the corresponding node.
 */
public class IdenticalTree {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(5);

        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.right.left = new Node(4);
        root1.right.right = new Node(5);

        boolean isIdentical = checkIdentical(root, root1);
        System.out.print("IdenticalTree-->" + isIdentical);
    }

    /**
     * Time: O(n)
     * → Visit every node once
     * Space: O(h)
     * → Recursion stack (height of tree)
     * Worst case (skewed tree): O(n)
     * Balanced tree: O(log n)
     */
    private static boolean checkIdentical(Node root1, Node root2) {
        if (root1 == null || root2 == null) return root1 == root2;
        return root1.data == root2.data
                && checkIdentical(root1.left, root2.left)
                && checkIdentical(root1.right, root2.right);
    }
}
