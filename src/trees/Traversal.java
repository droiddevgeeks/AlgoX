package trees;

public class Traversal {
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
        preOrderTraversal(root);
        System.out.println();
        inOrderTraversal(root);
        System.out.println();
        postOrderTraversal(root);

    }

    private static void preOrderTraversal(Node root) {
        if (root == null) return;
        System.out.print(root.data + "\t");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    private static void inOrderTraversal(Node root) {
        if (root == null) return;
        inOrderTraversal(root.left);
        System.out.print(root.data + "\t");
        inOrderTraversal(root.right);
    }

    private static void postOrderTraversal(Node root) {
        if (root == null) return;
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.data + "\t");
    }
}
