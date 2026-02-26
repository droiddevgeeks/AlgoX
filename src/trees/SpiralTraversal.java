package trees;

import java.util.*;

/**
 * Binary Tree: 1 2 3 4 5 -1 6
 * Output: [[1],[3, 2],[4, 5, 6]]
 */
public class SpiralTraversal {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.right = new Node(6);

        printSpiralOrder(root);
    }

    private static void printSpiralOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean directionLTF = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                level.add(curr.data);
                if (!directionLTF) Collections.reverse(level);

                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            result.add(level);
            directionLTF = !directionLTF;
        }

        for (List<Integer> temp : result) {
            System.out.println(Arrays.toString(temp.toArray()));
        }
    }
}
