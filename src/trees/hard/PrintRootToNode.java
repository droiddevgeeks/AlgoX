package trees.hard;

import trees.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Input: Binary Tree: 1 2 3 4 5 -1 -1 -1 -1, Node: 7
 * Output: [1, 2, 5, 7]
 */
public class PrintRootToNode {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(6);
        root.left.right.right = new Node(7);
        root.right = new Node(3);

        int searchNode = 7;
        List<Integer> path = new ArrayList<>();
        boolean isFound = findPathFromRootToNode(root, searchNode, path);
        System.out.println("Path found\t" + isFound + " \nPath is" + path);
    }

    private static boolean findPathFromRootToNode(Node root, int searchKey, List<Integer> path) {
        if (root == null) return false;
        path.addLast(root.data);
        if (root.data == searchKey) return true;
        if (findPathFromRootToNode(root.left, searchKey, path) || findPathFromRootToNode(root.right, searchKey, path)) {
            return true;
        }
        path.removeLast();
        return false;
    }
}
