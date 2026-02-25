package trees;

import java.util.LinkedList;
import java.util.Queue;

public class DepthBinaryTree {
    public static void main(String[] args) {
        //1 2 5 -1 -1 4 6 5

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(5);
        root.right.left = new Node(4);
        root.right.right = new Node(6);
        root.right.left.left = new Node(8);

        int depth = depth(root);
        System.out.println("Depth Rec-->" + depth);

        depth = depthLevelOrder(root);
        System.out.println("Depth LOT-->" + depth);
    }

    private static int depth(Node root) {
        if (root == null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        return 1 + Math.max(left, right);
    }

    private static int depthLevelOrder(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        int depth = 0;
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr == null) {
                depth++;
                if (!queue.isEmpty()) queue.add(null);
            } else {
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }
        return depth;
    }
}
