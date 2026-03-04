package trees.hard;

import trees.Node;

/**
 * 10
 * /  \
 * 8    2
 * / \
 * 3   5
 */
public class ChildrenSumProperty {
    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(8);
        root.left.left = new Node(3);
        root.left.right = new Node(5);

        root.right = new Node(2);
        boolean isValidChildSum = checkChildrenSumProperty(root);
        System.out.println("ChildrenSumProperty--->" + isValidChildSum);
    }

    private static boolean checkChildrenSumProperty(Node root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        int left = 0, right = 0;
        if (root.left != null) left = root.left.data;
        if (root.right != null) right = root.right.data;

        return (root.data == left + right)
                && checkChildrenSumProperty(root.left)
                && checkChildrenSumProperty(root.right);
    }
}
