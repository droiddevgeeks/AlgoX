package LinkedList;

public class SortList {
    public static void main(String[] args) {
        Node head = new Node(3);
        head.next = new Node(2);
        head.next.next = new Node(5);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(1);
        System.out.println("List Before Sort");
        Node.printLL(head);
        Node sortedList = sortList(head);
        System.out.println("\nList After Sort");
        Node.printLL(sortedList);
    }

    private static Node sortList(Node head) {
        if (head == null || head.next == null) return head;
        Node mid = findMid(head);
        Node left = sortList(head);
        Node right = sortList(mid);
        return mergeList(left, right);
    }

    private static Node findMid(Node head) {
        Node temp = null;
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        temp.next = null;
        return slow;
    }

    private static Node mergeList(Node l1, Node l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        Node temp = new Node(-1);
        Node curr = temp;

        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = (l1 != null) ? l1 : l2;
        return temp.next;
    }
}
