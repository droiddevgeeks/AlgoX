package trees.hard;

import trees.Node;

/**
 * The lowest common ancestor is defined between two nodes p and q as
 * the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).
 * root = [3, 5, 1, 6, 2, 0, 8, null, null, 7, 4], p = 5, q = 1
 * Output:
 * 3
 */
public class LCABinaryTree {
    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(5);
        root.right = new Node(1);
        root.left.left = new Node(6);
        root.left.right = new Node(2);
        root.right.left = new Node(0);
        root.right.right = new Node(8);

        Node p = root.left;      // 5
        Node q =  root.right.right;    // 1

        Node lca = lowestCommonAncestor(root, p, q);
        System.out.println("LCA: " + lca.data);   // Output: 3
    }

    private static Node lowestCommonAncestor(Node root, Node p, Node q) {
        if (root == null || root == p || root == q) return root;
        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }
}
