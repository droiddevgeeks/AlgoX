package StackAndQueue;

import java.util.HashMap;

/**
 * Problem Statement: Design a data structure that follows the constraints of Least Recently Used (LRU) cache.
 * <p>
 * Implement the LRUCache class:
 * LRUCache(int capacity): We need to initialize the LRU cache with positive size capacity.
 * int get(int key): Returns the value of the key if the key exists, otherwise return -1.
 * void put(int key,int value): Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache.
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * <p>
 * The functions get and put must each run in O(1) average time complexity.
 */
public class LRUCacheImpl {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}

class LRUCache {
    int capacity;
    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);
    HashMap<Integer, Node> map = new HashMap<>();

    public LRUCache(int cap) {
        this.capacity = cap;
        head.next = tail;
        tail.prev = head;
    }

    public void addNode(Node newNode) {
        newNode.next = head.next;
        newNode.prev = head;
        tail.prev = newNode;
        head.next = newNode;
    }

    public void deleteNode(Node delNode) {
        Node delPrev = delNode.prev;
        Node delNext = delNode.next;
        delPrev.next = delNext;
        delNext.prev = delPrev;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node resNode = map.get(key);
            int resValue = resNode.value;
            map.remove(key);
            deleteNode(resNode);
            addNode(resNode);
            map.put(key, head.next);
            return resValue;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node existingNode = map.get(key);
            map.remove(key);
            deleteNode(existingNode);
        }
        if (map.size() == capacity) {
            map.remove(tail.prev.key);
            deleteNode(tail.prev);
        }
        addNode(new Node(key, value));
        map.put(key, head.next);
    }
}

class Node {
    int key, value;
    Node next;
    Node prev;

    Node(int _key, int _val) {
        key = _key;
        value = _val;
    }
}
