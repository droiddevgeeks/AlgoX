package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Assuming standing on the right side of a binary tree and given its root,
 * return the values of the nodes visible, arranged from top to bottom.
 * Input :root = [1, 2, 3, 6, 5, 8, 4]
 * Output : [1, 3, 4]
 */
public class RightAndLeftViewBinaryTree {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.right = new Node(5);
        root.left.left = new Node(6);

        root.right = new Node(3);
        root.right.left = new Node(8);
        root.right.right = new Node(4);

        printRightAndLeftViewBinaryTree(root);
    }

    private static void printRightAndLeftViewBinaryTree(Node root) {
        if (root == null) return;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if (i == 0) left.add(curr.data);
                if (i == size - 1) right.add(curr.data);
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }

        System.out.println(left);
        System.out.println(right);
    }
}
