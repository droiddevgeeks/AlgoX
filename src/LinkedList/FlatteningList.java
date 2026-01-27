package LinkedList;

public class FlatteningList {
    public static void main(String[] args) {
// Create a linked list with child pointers
        FlattenNode head = new FlattenNode(5);
        head.child = new FlattenNode(14);

        head.next = new FlattenNode(10);
        head.next.child = new FlattenNode(4);

        head.next.next = new FlattenNode(12);
        head.next.next.child = new FlattenNode(20);
        head.next.next.child.child = new FlattenNode(13);

        head.next.next.next = new FlattenNode(7);
        head.next.next.next.child = new FlattenNode(17);

        // Print original structure
        System.out.println("Original linked list:");
        printOriginalLinkedList(head, 0);

        FlattenNode list = flattenLinkedList(head);
        // Print flattened version
        System.out.print("\nFlattened linked list: ");
        printLinkedList(list);
    }

    private static FlattenNode flattenLinkedList(FlattenNode head) {
        if (head == null || head.next == null) return head;

        FlattenNode mergedHead = flattenLinkedList(head.next);
        return merge(head, mergedHead);
    }

    private static FlattenNode merge(FlattenNode l1, FlattenNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        FlattenNode temp = new FlattenNode(-1);
        FlattenNode curr = temp;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.child = l1;
                l1 = l1.child;
            } else {
                curr.child = l2;
                l2 = l2.child;
            }
            curr = curr.child;
            curr.next = null;
        }

        // append remaining nodes
        curr.child = (l1 != null) ? l1 : l2;

        // remove next of each node.
        FlattenNode t = curr.child;
        while (t != null) {
            t.next = null;
            t = t.child;
        }
        return temp.child;
    }

    // Print original linked list in grid format
    public static void printOriginalLinkedList(FlattenNode head, int depth) {
        while (head != null) {
            System.out.print(head.val);

            if (head.child != null) {
                System.out.print(" -> ");
                printOriginalLinkedList(head.child, depth + 1);
            }

            if (head.next != null) {
                System.out.println();
                for (int i = 0; i < depth; ++i) {
                    System.out.print("| ");
                }
            }
            head = head.next;
        }
    }

    // Print linked list in flattened form
    public static void printLinkedList(FlattenNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.child;
        }
        System.out.println();
    }
}
