package LinkedList;

import java.util.HashMap;

public class CopyListRandomAndNext {
    public static void main(String[] args) {
        // Example linked list: 7 -> 14 -> 21 -> 28
        RNNode head = new RNNode(7);
        head.next = new RNNode(14);
        head.next.next = new RNNode(21);
        head.next.next.next = new RNNode(28);

        // Assigning random pointers
        head.random = head.next.next;
        head.next.random = head;
        head.next.next.random = head.next.next.next;
        head.next.next.next.random = head.next;

        System.out.println("Before clone");
        printClonedLinkedList(head);

        System.out.println("After clone with Hashmap");
        RNNode newClonedHead = clonedList(head);
        printClonedLinkedList(newClonedHead);

        System.out.println("After clone without Hashmap");
        newClonedHead = clonedListWithoutHashMap(head);
        printClonedLinkedList(newClonedHead);
    }

    private static RNNode clonedList(RNNode head) {
        HashMap<RNNode, RNNode> map = new HashMap<>();
        RNNode temp = head;
        while (temp != null) {
            RNNode newNode = new RNNode(temp.data);
            map.put(temp, newNode);
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            RNNode newNode = map.get(temp);
            newNode.next = map.get(temp.next);
            newNode.random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(head);
    }

    private static RNNode clonedListWithoutHashMap(RNNode head) {
        RNNode temp = head;

        while (temp != null) {
            RNNode newNode = new RNNode(temp.data);
            newNode.next = temp.next;
            temp.next = newNode;
            temp = temp.next.next;
        }
        temp = head;
        while (temp != null) {
            RNNode copyNode = temp.next;
            if (temp.random != null) copyNode.random = temp.random.next; //
            temp = temp.next.next;
        }

        RNNode dummy = new RNNode(-1);
        RNNode res = dummy;
        temp = head;

        while (temp != null) {
            res.next = temp.next;
            res = res.next;

            temp.next = temp.next.next;
            temp = temp.next;
        }
        return dummy.next;
    }

    // Function to print the cloned linked list
    private static void printClonedLinkedList(RNNode head) {
        while (head != null) {
            System.out.print("Data: " + head.data);
            if (head.random != null) {
                System.out.print(", Random: " + head.random.data);
            } else {
                System.out.print(", Random: null");
            }
            System.out.println();
            // Move to the next node
            head = head.next;
        }
    }
}

class RNNode {
    int data;
    RNNode next;
    RNNode random;

    RNNode(int data) {
        this.data = data;
        this.next = null;
        this.random = null;
    }
}
