package trees.bst;


import trees.Node;

/**
 *   10
 *  /  \
 * 5    15
 *     /
 *    6
 */
public class CheckBinaryTreeIsBST {
    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.right.left = new Node(6);

        boolean isBST = checkIsBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.print("CheckBinaryTreeIsBST-->" + isBST);
    }

    private static boolean checkIsBST(Node root, int min, int max) {
        if (root == null) return true;
        if (root.data < min || root.data > max) return false;
        return checkIsBST(root.left, min, root.data) // left subtree k liye max value root hoga
                && checkIsBST(root.right, root.data, max); // right subtree k liye min value root hoga
    }
}
