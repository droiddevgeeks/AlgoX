package trees.hard;

import trees.Node;
import trees.Pair;

import java.util.*;

/**
 * root = [3, 5, 1, 6, 2, 0, 8, N, N, 7, 4] , target = 5, k = 2
 * Output: [7,4,1]
 */
public class NodesKDistanceFar {
    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(5);
        root.left.left = new Node(6);
        root.left.right = new Node(2);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);

        root.right = new Node(1);
        root.right.left = new Node(0);
        root.right.right = new Node(8);

        Node target = root.left;
        int k = 2;

        List<Integer> res = findNodesKDistanceFarFromTarget(root, target, k);
        System.out.println(res);
    }

    private static List<Integer> findNodesKDistanceFarFromTarget(Node root, Node target, int k) {
        Map<Node, Node> parent = new HashMap<>();
        buildParentChildMap(root, null, parent);

        Queue<Pair> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        queue.add(new Pair(target, 0));
        visited.add(target);

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            if (curr.hd == k) {
                result.add(curr.node.data);
                continue;
            }

            if (curr.node.left != null && !visited.contains(curr.node.left)) {
                visited.add(curr.node.left);
                queue.add(new Pair(curr.node.left, curr.hd + 1));
            }

            if (curr.node.right != null && !visited.contains(curr.node.right)) {
                visited.add(curr.node.right);
                queue.add(new Pair(curr.node.right, curr.hd + 1));
            }

            Node parentNode = parent.get(curr.node);
            if (parentNode != null && !visited.contains(parentNode)) {
                visited.add(parentNode);
                queue.add(new Pair(parentNode, curr.hd + 1));
            }
        }
        return  result;
    }

    private static void buildParentChildMap(Node node, Node parent, Map<Node, Node> parentMap) {
        if (node == null) return;
        parentMap.put(node, parent);
        buildParentChildMap(node.left, node, parentMap);
        buildParentChildMap(node.right, node, parentMap);
    }
}
