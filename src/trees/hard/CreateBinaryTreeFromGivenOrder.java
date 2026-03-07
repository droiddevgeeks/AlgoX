package trees.hard;


import trees.Node;
import trees.Traversal;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Input : preorder = [3, 9, 20, 15, 7] , inorder = [9, 3, 15, 20, 7]
 * Output : [3, 9, 20, null, null, 15, 7]
 * Input : preorder = [3, 4, 5, 6, 2, 9] , inorder = [5, 4, 6, 3, 2, 9]
 * Output : [3, 4, 2, 5, 6, null, 9]
 */
public class CreateBinaryTreeFromGivenOrder {
    private static int preOrderIndex = 0;
    private static int postOrderIndex = 0;

    public static void main(String[] args) {
        int[] pre = new int[]{3, 9, 20, 15, 7};
        int[] in = new int[]{9, 3, 15, 20, 7};
        int[] post = new int[]{9, 15, 7, 20, 3};

        Node root = createBinaryTreeFromGivenTraversalsRec(pre, in);
        System.out.println("\nRec");
        Traversal.inOrderTraversal(root);
        System.out.println();
        Traversal.preOrderTraversal(root);
        System.out.println();
        Traversal.postOrderTraversal(root);

        System.out.println("\nOptimized Pre & Inorder");
        Node root1 = createBinaryTreeFromGivenPreAndInTraversalsOptimized(pre, in);

        Traversal.inOrderTraversal(root1);
        System.out.println();
        Traversal.preOrderTraversal(root1);
        System.out.println();
        Traversal.postOrderTraversal(root1);


        System.out.println("\nOptimized Post & Inorder");
        Node root2 = createBinaryTreeFromGivenPostAndInTraversalsOptimized(post, in);

        Traversal.inOrderTraversal(root2);
        System.out.println();
        Traversal.preOrderTraversal(root2);
        System.out.println();
        Traversal.postOrderTraversal(root2);
    }

    /**
     * Time  = O(N²)
     * Space = O(N²)
     */
    private static Node createBinaryTreeFromGivenTraversalsRec(int[] pre, int[] in) {
        if (pre.length == 0) return null;

        int root = pre[0];
        Node rootNode = new Node(root);
        int rootIndex = findIndexOfRootInInorder(in, root); // O(n)

        int[] leftIn = Arrays.copyOfRange(in, 0, rootIndex);
        int[] rightIn = Arrays.copyOfRange(in, rootIndex + 1, in.length);

        int[] leftPre = Arrays.copyOfRange(pre, 1, leftIn.length + 1);
        int[] rightPre = Arrays.copyOfRange(pre, leftIn.length + 1, pre.length);

        rootNode.left = createBinaryTreeFromGivenTraversalsRec(leftPre, leftIn);
        rootNode.right = createBinaryTreeFromGivenTraversalsRec(rightPre, rightIn);
        return rootNode;
    }

    private static int findIndexOfRootInInorder(int[] in, int root) {
        int index = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == root) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Time  = O(N)
     * Space = O(N)
     */
    private static Node createBinaryTreeFromGivenPreAndInTraversalsOptimized(int[] pre, int[] in) {
        preOrderIndex = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return buildTreePreAndIn(pre, 0, in.length - 1, map);
    }

    private static Node buildTreePreAndIn(int[] pre, int startIn, int endIn, HashMap<Integer, Integer> map) {
        if (startIn > endIn) return null;
        int root = pre[preOrderIndex++];
        Node rootNode = new Node(root);
        int rootIndex = map.get(root);
        rootNode.left = buildTreePreAndIn(pre, startIn, rootIndex - 1, map);
        rootNode.right = buildTreePreAndIn(pre, rootIndex + 1, endIn, map);
        return rootNode;
    }

    private static Node createBinaryTreeFromGivenPostAndInTraversalsOptimized(int[] post, int[] in) {
        postOrderIndex = post.length - 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return buildTreePostAndIn(post, 0, in.length - 1, map);
    }

    private static Node buildTreePostAndIn(int[] post, int startIn, int endIn, HashMap<Integer, Integer> map) {
        if (startIn > endIn) return null;
        int root = post[postOrderIndex--];
        Node rootNode = new Node(root);
        int rootIndex = map.get(root);
        rootNode.right = buildTreePostAndIn(post, rootIndex + 1, endIn, map);
        rootNode.left = buildTreePostAndIn(post, startIn, rootIndex - 1, map);
        return rootNode;
    }
}
