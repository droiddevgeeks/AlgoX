package trees;

import java.util.Stack;

/**
 * PreOrder : 4	2	3	9	1	5	7	6	8
 * InOrder : 3	1	9	2	4	7	5	8	6
 * PostOrder: 1	9	3	2	7	8	6	5	4
 */
public class IterativeTraversal {
    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.left.right = new Node(9);
        root.left.left.right.left = new Node(1);
        root.right = new Node(5);
        root.right.left = new Node(7);
        root.right.right = new Node(6);
        root.right.right.left = new Node(8);
        System.out.print("PreOrder--->");
        preOrderTraversalIterative(root);
        System.out.print("\nInOrder--->");
        inOrderTraversalIterative(root);
        System.out.print("\nPostOrder--->");
        postOrderTraversalIterative(root);
    }

    //Root-->Left--->right since left ko baad me process kanra hai..
    // it mean right ko pahle push karenge stack me.. so that pop me left pahle aaye
    private static void preOrderTraversalIterative(Node root) {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            //process node
            Node curr = stack.pop();
            System.out.print(curr.data + " \t");
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
    }


    //Push all left → Pop → Visit → Go right
    private static void inOrderTraversalIterative(Node root) {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while (!stack.isEmpty() || curr != null) {

            // Go to extreme left
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            //process node
            Node temp = stack.pop();
            System.out.print(temp.data + "\t");

            // Move to right subtree
            curr = temp.right;
        }
    }

    //Left--->right--Root ::: do like PreOrder.. but insert left first & in last reverse
    private static void postOrderTraversalIterative(Node root) {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        Stack<Node> result = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            //process node
            Node curr = stack.pop();
            result.push(curr);
            if (curr.left != null) stack.push(curr.left);
            if (curr.right != null) stack.push(curr.right);
        }

        while (!result.isEmpty()) {
            Node curr = result.pop();
            System.out.print(curr.data + "\t");
        }
    }
}
