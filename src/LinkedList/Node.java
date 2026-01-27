package LinkedList;

public class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        next = null;
    }

    public static void printLL(Node head) {
        while (head != null) {
            System.out.print(head.data + "-->");
            head = head.next;
        }
    }
}

