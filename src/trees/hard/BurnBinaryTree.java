package trees.hard;

import trees.Node;

import java.util.*;

/**
 * Input : root = [1, 2, 3, 4, null, 5, 6, null, 7]. target = 1
 * Output : 3
 * Input : root = [1, 2, 3, null, 5, null, 4], target = 4
 * Output : 4
 */
public class BurnBinaryTree {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.left.right = new Node(7);

        root.right = new Node(3);
        root.right.left = new Node(5);
        root.right.right = new Node(6);

        int target = 1;

        int time = minTimeToBurnFromTarget(root, target);
        System.out.println("BurnBinaryTree" + time);
    }

    private static int minTimeToBurnFromTarget(Node root, int target) {
        Map<Node, Node> parentMap = new HashMap<>();
        Node targetNode = buildParentMap(root, parentMap, target);
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        queue.add(targetNode);
        visited.add(targetNode);
        int time = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isBurning = false;
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();

                if (curr.left != null && !visited.contains(curr.left)) {
                    isBurning = true;
                    queue.add(curr.left);
                    visited.add(curr.left);
                }

                if (curr.right != null && !visited.contains(curr.right)) {
                    isBurning = true;
                    queue.add(curr.right);
                    visited.add(curr.right);
                }

                Node parentNode = parentMap.get(curr);
                if (parentNode != null && !visited.contains(parentNode)) {
                    isBurning = true;
                    queue.add(parentNode);
                    visited.add(parentNode);
                }
            }
            if (isBurning) time++;
        }
        return time;
    }

    private static Node buildParentMap(Node root, Map<Node, Node> parentMap, int target) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node targetNode = null;
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.data == target) targetNode = curr;

            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                queue.add(curr.left);
            }

            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                queue.add(curr.right);
            }
        }
        return targetNode;
    }

}
