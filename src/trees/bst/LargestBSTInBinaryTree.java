package trees.bst;

import trees.Node;

import java.util.Map;

/**
 * 10
 * /  \
 * 5    15
 * / \     \
 * 1   8     7
 * o/p : 3
 */
public class LargestBSTInBinaryTree {
    public static int maxSize = 0;

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.left.left = new Node(1);
        root.left.right = new Node(8);

        root.right = new Node(15);
        root.right.right = new Node(7);

        int largestBSt = largestBSTBruteForce(root);
        System.out.print("LargestBSTInBinaryTree--->" + largestBSt);

        NodeInfo nodeInfo = findLargestBST(root);
        System.out.print("\nLargestBSTInBinaryTree--->" + nodeInfo.size);
    }

    /**
     * For each node we:
     * Check BST → O(N)
     * Calculate size → O(N)
     * And we do this for N nodes.
     * Total complexity = O(N²)
     * Worst case (skewed tree):
     * O(N²)
     */
    private static int largestBSTBruteForce(Node root) {
        if (root == null) return 0;

        if (isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            maxSize = Math.max(maxSize, size(root));
        }
        largestBSTBruteForce(root.left);
        largestBSTBruteForce(root.right);
        return maxSize;
    }

    private static boolean isBST(Node root, int min, int max) {
        if (root == null) return true;
        if (root.data <= min || root.data >= max) return false;

        return isBST(root.left, min, root.data)
                && isBST(root.right, root.data, max);
    }

    private static int size(Node root) {
        if (root == null) return 0;
        return 1 + size(root.left) + size(root.right);
    }


    private static NodeInfo findLargestBST(Node root) {
        if (root == null) return new NodeInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        NodeInfo left = findLargestBST(root.left);
        NodeInfo right = findLargestBST(root.right);

        if (left.max < root.data && right.min > root.data) {
            int min = Math.min(root.data, left.min);
            int max = Math.max(root.data, right.max);
            int size = left.size + right.size + 1;
            return new NodeInfo(min, max, size);
        }
        return new NodeInfo(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.size, right.size));

    }

    private static class NodeInfo {
        int min, max, size;

        public NodeInfo(int min, int max, int size) {
            this.min = min;
            this.max = max;
            this.size = size;
        }
    }
}
