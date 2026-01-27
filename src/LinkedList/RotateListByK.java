package LinkedList;

public class RotateListByK {
    public static void main(String[] args) {
        // Creating linked list: 1->2->3->4->5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        System.out.println("List Before Rotate");
        Node.printLL(head);
        int k = 2;
        Node newHead = rotateRight(head, k);
        System.out.println("\nList After Rotate");
        Node.printLL(newHead);
    }

    private static Node rotateRight(Node head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        int len = 1;
        Node temp = head;

        while (temp.next != null) {
            len++;
            temp = temp.next;
        }
        //make it circular
        temp.next = head;

        temp = head;
        k = len - k % len;
        for (int i = 1; i < k; i++) {
            temp = temp.next;
        }
        Node newHead = temp.next;
        temp.next = null;
        return newHead;
    }

}
