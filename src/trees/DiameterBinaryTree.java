package trees;

/**
 * Given the root of the Binary Tree, return the length of its diameter.
 * The Diameter of a Binary Tree is the longest distance between any two nodes of that tree.
 * This path may or may not pass through the root.
 */
public class DiameterBinaryTree {
    public static void main(String[] args) {
        // Creating a sample binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        int diameter = calculateDiameterRec(root);
        System.out.println("DiameterBinaryTree Rec---->" + diameter);

        int[] dm = new int[1];
        calculateDiameterOptimized(root, dm);
        System.out.println("DiameterBinaryTree optimized---->" + dm[0]);
    }

    private static int getHeight(Node root) {
        if (root == null) return 0;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return 1 + Math.max(left, right);
    }

    private static int calculateDiameterRec(Node root) {
        if (root == null) return 0;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        int diameterViaRoot = 1 + left + right;
        int leftDiameter = calculateDiameterRec(root.left);
        int rightDiameter = calculateDiameterRec(root.right);
        return Math.max(diameterViaRoot, Math.max(leftDiameter, rightDiameter));
    }

    // this is using height way
    private static int calculateDiameterOptimized(Node root, int[] dm) {
        if (root == null) return 0;
        int left = calculateDiameterOptimized(root.left, dm);
        int right = calculateDiameterOptimized(root.right, dm);
        dm[0] = Math.max(dm[0], 1 + left + right);
        return 1 + Math.max(left, right);
    }
}
