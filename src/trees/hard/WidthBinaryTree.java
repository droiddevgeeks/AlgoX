package trees.hard;

import trees.Node;
import trees.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Input: Binary Tree: 1 2 3 5 6 -1 9
 * Output: Maximum Width: 4
 */
public class WidthBinaryTree {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(5);
        root.left.right = new Node(6);

        root.right = new Node(3);
        root.right.right = new Node(9);

        int maxWidth = calculateMaxWidth(root);
        System.out.println("WidthBinaryTree--->" + maxWidth);
    }

    private static int calculateMaxWidth(Node root) {
        if (root == null) return 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));
        int maxWidth = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            int minIndex = queue.peek().hd;
            int size = queue.size();
            int first = 0;
            int last = 0;
            for (int i = 0; i < size; i++) {
                Pair curr = queue.poll();
                Node currNode = curr.node;
                int index = curr.hd - minIndex; // to prevent integer overflow.

                if (i == 0) first = index;
                if (i == size - 1) last = index;
                if (currNode.left != null) queue.add(new Pair(currNode.left, 2 * index + 1));
                if (currNode.right != null) queue.add(new Pair(currNode.right, 2 * index + 2));
            }

            maxWidth = Math.max(maxWidth, (last - first) + 1);
        }
        return maxWidth;
    }
}
