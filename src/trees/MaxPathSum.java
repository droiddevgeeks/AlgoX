package trees;

public class MaxPathSum {
    public static void main(String[] args) {
        //-10 9 20 -1 -1 15 7
        Node root = new Node(-10);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);

        int[] sum = new int[1];
        sum[0] = Integer.MIN_VALUE;
        calculateMaxSum(root, sum);
        System.out.println("MaxPathSum-->" + sum[0]);
    }

    private static int calculateMaxSum(Node root, int[] sum) {
        if (root == null) return 0;
        int left = calculateMaxSum(root.left, sum);
        if (left < 0) left = 0;

        int right = calculateMaxSum(root.right, sum);
        if (right < 0) right = 0;

        int curr = root.data + left + right;
        sum[0] = Math.max(sum[0], curr);
        // Return single path upward
        return root.data + Math.max(left, right);
    }
}
