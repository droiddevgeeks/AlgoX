package LinkedList;

public class ReverseListGroups {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        int k = 2;
        System.out.println("Before Reverse");
        Node.printLL(head);
        System.out.println("\nAfter Reverse");
        Node reversed = reverseInGroupOfK(head, k);
        Node.printLL(reversed);
    }

    private static Node reverseInGroupOfK(Node head, int k) {
        Node temp = head;
        Node prev = null;

        while (temp != null) {
            Node kthNode = getKthNode(temp, k);
            if (kthNode == null) {
                // if given or remaining list is less than k
                if (prev != null) prev.next = temp;
                break;
            }
            Node nextNode = kthNode.next;
            kthNode.next = null;
            ReverseList.reverseListIterative(temp);
            if (head == temp) {
                // for first group, kth will become new head
                head = kthNode;
            } else {
                //It connects the previously reversed group to the current reversed group.
                prev.next = kthNode;
            }

            prev = temp;
            temp = nextNode;
        }
        return  head;
    }

    private static Node getKthNode(Node temp, int k) {
        while (temp != null && k > 1) {
            k--;
            temp = temp.next;
        }
        return temp;
    }
}
