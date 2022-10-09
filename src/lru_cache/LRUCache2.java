package lru_cache;

import java.util.HashMap;
import java.util.Map;

//Approach 2: Hashmap + DoubleLinkedList
public class LRUCache2 {

    class DoublyLinkedNode {
        private final int key;
        private int value;
        private DoublyLinkedNode prev;
        private DoublyLinkedNode next;

        DoublyLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private void addNode(DoublyLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DoublyLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DoublyLinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    private final Map<Integer, DoublyLinkedNode> cache = new HashMap<>();
    private final int capacity;
    private final DoublyLinkedNode head;
    private final DoublyLinkedNode tail;

    LRUCache2(int capacity) {
        this.capacity = capacity;
        head = new DoublyLinkedNode(0, 0);
        tail = new DoublyLinkedNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DoublyLinkedNode node = cache.get(key);
        if (node == null) return -1;
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DoublyLinkedNode node = cache.get(key);
        if (node == null) {
            DoublyLinkedNode newNode = new DoublyLinkedNode(key, value);
            addNode(newNode);
            cache.put(key, newNode);
            if (cache.size() > capacity) {
                cache.remove(tail.prev.key);
                removeNode(tail.prev);
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    public static void main(String[] args) {
        LRUCache2 lruCache2 = new LRUCache2(3);
        lruCache2.put(1, 11);
        printLRUCache(lruCache2.head);
        lruCache2.put(2, 12);
        printLRUCache(lruCache2.head);
        lruCache2.get(1);
        printLRUCache(lruCache2.head);
        lruCache2.put(3, 13);
        printLRUCache(lruCache2.head);
        lruCache2.put(4, 14);
        printLRUCache(lruCache2.head);
    }

    private static void printLRUCache(DoublyLinkedNode head) {
        DoublyLinkedNode currentNode = head.next;
        while (currentNode.next != null) {
            System.out.println("{" + currentNode.key + "," + currentNode.value + "}");
            currentNode = currentNode.next;
        }
        System.out.println("----------");
    }
}
