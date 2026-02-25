package trees;

public class BalancedTree {
    public static void main(String[] args) {
        // Creating a sample binary tree
        Node root = new Node(3);
        root.left = new Node(9);
        root.right = new Node(20);
        root.left.left = new Node(15);
        root.left.right = new Node(7);

        boolean isBalanced = checkBalancedTree(root);
        System.out.println("BalancedTree Rec-->" + isBalanced);

        isBalanced = checkBalancedTreeOptimized(root);
        System.out.println("BalancedTree Optimized-->" + isBalanced);

    }

    // O(n2)
    private static boolean checkBalancedTree(Node root) {
        if (root == null) return true;
        int l = getHeight(root.left);
        int r = getHeight(root.right);
        return Math.abs(l - r) <= 1 && checkBalancedTree(root.left) && checkBalancedTree(root.right);
    }

    private static int getHeight(Node root) {
        if (root == null) return 0;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return 1 + Math.max(left, right);
    }

    private static boolean checkBalancedTreeOptimized(Node root) {
        return isBalanced(root) != -1;
    }

    // O(n)
    private static int isBalanced(Node root) {
        if (root == null) return 0;
        int left = isBalanced(root.left);
        if (left == -1) return -1;

        int right = isBalanced(root.right);
        if (right == -1) return -1;

        if (Math.abs(right - left) > 1) return -1;
        return Math.max(left, right) + 1;
    }
}
