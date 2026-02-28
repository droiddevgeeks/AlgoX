package trees;

import java.util.*;

/**
 * Input:Binary Tree: 1 2 3 4 10 9 11 -1 5 -1 -1 -1 -1 -1 -1 -1 6
 * Output: Vertical Order Traversal: [[4],[2, 5], [1, 10, 9, 6],[3],[11]]
 * <p>
 * For Top view, Add only once in tree map. if key don;t exist, insert hd. else do nothing.
 */

/**
 * For Top view, Add only once in tree map. if key don;t exist, insert hd. else do nothing.
 */

/**
 * For Bottom View, // 🔥 Always replace map data map.put(hd, node.data);
 */

public class VerticalOrderTraversal {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.right = new Node(10);
        root.left.left = new Node(4);
        root.left.left.right = new Node(5);
        root.left.left.right.right = new Node(6);

        root.right = new Node(3);
        root.right.left = new Node(9);
        root.right.right = new Node(11);

        printVerticalOrderTraversal(root);
    }

    //use concept of Level Order traversal
    private static void printVerticalOrderTraversal(Node root) {
        if (root == null) return;
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            Node currNode = pair.node;
            int hd = pair.hd;

            // here for vertical order, put every node in list...
            map.putIfAbsent(hd, new ArrayList<>());
            map.get(hd).add(currNode.data);


            if (currNode.left != null) queue.add(new Pair(currNode.left, hd - 1));
            if (currNode.right != null) queue.add(new Pair(currNode.right, hd + 1));
        }

        for (List<Integer> temp : map.values()) {
            System.out.println(temp.toString());
        }
    }

    private static class Pair {
        Node node;
        int hd;

        public Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }
}
