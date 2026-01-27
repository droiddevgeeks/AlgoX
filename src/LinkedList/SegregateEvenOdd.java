package LinkedList;

/**
 * Input: 1→2→3→4→5→6→Null
 * Output: 2→4→6→1→3→5→Null
 * <p>
 * Input: 1→3→5→Null
 * Output: 1→3→5→Null
 */
public class SegregateEvenOdd {
    public static void main(String[] args) {
        Node head = new Node(17);
        head.next = new Node(15);
        head.next.next = new Node(8);
        head.next.next.next = new Node(12);
        head.next.next.next.next = new Node(10);
        head.next.next.next.next.next = new Node(5);
        head.next.next.next.next.next.next = new Node(4);

        Node temp;
        System.out.println("Before separation");
        Node.printLL(head);
        System.out.println("\nAfter separation");
        temp = segregateEvenOdd(head);
        Node.printLL(temp);
    }

    private static Node segregateEvenOdd(Node head) {
        if (head == null || head.next == null) return head;
        Node curr = head;

        Node evenHead = null, evenTail = null;
        Node oddHead = null, oddTail = null;

        while ((curr != null)) {
            if (curr.data % 2 == 0) {
                //even data
                if (evenHead == null) {
                    evenHead = curr;
                } else {
                    evenTail.next = curr;
                }
                evenTail = curr;
            } else {
                //odd data
                if (oddHead == null) {
                    oddHead = curr;
                } else {
                    oddTail.next = curr;
                }
                oddTail = curr;
            }
            curr = curr.next;
        }

        if (evenHead == null) return oddHead;
        else if (oddHead == null) return evenHead;

        evenTail.next = oddHead;
        oddTail.next = null;
        return evenHead;
    }
}