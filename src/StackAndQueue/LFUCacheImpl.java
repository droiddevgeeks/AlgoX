package StackAndQueue;

import java.util.HashMap;

/**
 * Input:
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * Output:[null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 */
public class LFUCacheImpl {
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        // Queries
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.print(cache.get(1) + " ");
        cache.put(3, 3);
        System.out.print(cache.get(2) + " ");
        System.out.print(cache.get(3) + " ");
        cache.put(4, 4);
        System.out.print(cache.get(1) + " ");
        System.out.print(cache.get(3) + " ");
        System.out.print(cache.get(4) + " ");
    }
}

class LFUCache {

    int capacity;
    int minFreq;
    HashMap<Integer, LFUNode> keyToNode;
    HashMap<Integer, DDL> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        keyToNode = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToNode.containsKey(key)) return -1;
        LFUNode node = keyToNode.get(key);
        updateFreq(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        //if present ,update value & freq
        if (keyToNode.containsKey(key)) {
            LFUNode node = keyToNode.get(key);
            node.value = value;
            updateFreq(node);
            return;
        }

        // if full
        if (keyToNode.size() == capacity) {
            DDL minList = freqMap.get(minFreq);
            LFUNode node = minList.removeLast();
            keyToNode.remove(node.key);
        }

        LFUNode node = new LFUNode(key, value);
        minFreq = 1;
        DDL list = freqMap.get(1);
        if (list == null) {
            list = new DDL();
            freqMap.put(1, list);
        }
        list.addNode(node);
        keyToNode.put(key, node);
    }

    public void updateFreq(LFUNode node) {
        int oldFreq = node.freq;
        DDL oldList = freqMap.get(oldFreq);
        oldList.removeNode(node);

        if (oldFreq == minFreq && oldList.isEmpty()) minFreq++;

        int newFreq = ++node.freq;
        DDL newList = freqMap.get(newFreq);
        if (newList == null) {
            newList = new DDL();
            freqMap.put(newFreq, newList);
        }
        newList.addNode(node);
    }
}

class DDL {
    LFUNode head;
    LFUNode tail;
    int size;

    public DDL() {
        head = new LFUNode(0, 0);
        tail = new LFUNode(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addNode(LFUNode newNode) {
        newNode.next = head.next;
        newNode.prev = head;
        head.next.prev = newNode;
        head.next = newNode;
        size++;
    }

    public void removeNode(LFUNode delNode) {
        delNode.next.prev = delNode.prev;
        delNode.prev.next = delNode.next;
        size--;
    }

    public LFUNode removeLast() {
        if (size == 0) return null;
        LFUNode node = tail.prev;
        removeNode(node);
        return node;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

class LFUNode {
    int key;
    int value;
    int freq;
    LFUNode next;
    LFUNode prev;

    LFUNode(int key, int value) {
        this.key = key;
        this.value = value;
        freq = 1;
        next = null;
        prev = null;
    }
}
