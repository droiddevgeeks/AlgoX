package trees;

public class SymmetricalBinaryTree {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);

        root.right = new Node(2);
        root.right.left = new Node(4);
        root.right.right = new Node(3);

        boolean isTreeSymmetrical = checkSymmetrical(root);
        System.out.println("Symmetrical--" + isTreeSymmetrical);
    }

    private static boolean checkSymmetrical(Node root) {
        if (root == null) return true;
        return checkSymmetricalUtil(root.left, root.right);
    }

    private static boolean checkSymmetricalUtil(Node left, Node right) {
        if (left == null || right == null) return left == right;
        return left.data == right.data
                && checkSymmetricalUtil(left.left, right.right)
                && checkSymmetricalUtil(left.right, right.left);
    }
}
