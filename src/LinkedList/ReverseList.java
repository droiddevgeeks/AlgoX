package LinkedList;

public class ReverseList {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        System.out.println("Before Reverse");
        Node.printLL(head);
//        Node reverseHead = reverseListIterative(head);
//        System.out.println("\nAfter Reverse");
//        Node.printLL(reverseHead);

        System.out.println("\nAfter Reverse Using Recursion");
        Node reverseRecursion = reverseListRecursion(head);
        Node.printLL(reverseRecursion);
    }

    public static Node reverseListIterative(Node head) {
        Node prev = null;
        Node temp = head;
        while (temp != null) {
            Node curr = temp.next;
            temp.next = prev;
            prev = temp;
            temp = curr;
        }
        return prev;
    }

    private static Node reverseListRecursion(Node head) {
        if(head == null || head.next == null) return head;
        Node newHead = reverseListRecursion(head.next);
        Node curr = head.next;
        curr.next = head;
        head.next = null;
        return  newHead;
    }
}
