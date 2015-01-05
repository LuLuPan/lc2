/*
Design and implement a data structure for Least Recently Used (LRU) cache. 
It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists
in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. 
When the cache reached its capacity, it should invalidate the least recently 
used item before inserting a new item.

Solution: 1. Double linked list to store node which is easy for deletion
          2. HashMap stores Key-Value pair for fast search
          3. Head refers to the node lastest used.
          4. When no capacity, delete tail node

Notice: LinkedList is doubly linked list, but it doesn't provide prev and next
        So when remove a node from list using remove(), it may search the obejct
        sequentially which is slow.
        Other method: 
        1. Implement own doubly linked list and maintain node's
        prev, next. HashMap store key and node, when remove, just need to
        modify node's prev and next value
        2. Extends LinkedHashMap
        http://www.codewalk.com/2012/04/least-recently-used-lru-cache-implementation-java.html

*/

public class LRUCache {
    class Node {
        int key;
        int value;
        Node next;
        Node prev;
        public Node(int k, int v) {
            this.key = k;
            this.value = v;
            this.next = null;
            this.prev = null;
        }
    }
    
    private Node head;
    private Node tail;
    private HashMap<Integer, Node> map;
    private int size;
    private int capacity;
    
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = null;
        this.tail = null;
        this.map = new HashMap<Integer, Node>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        if (node != head) {
            removeNode(node);
            addHead(node);
        }
        
        return node.value;
     }
    
    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addHead(node);
        } else {
            Node node = new Node(key, value);
            addHead(node);
            map.put(key, node);
            if (size == capacity) {
                map.remove(tail.key);
                removeNode(tail);
            } else {
                size++;
            }
        }
    }
    
    private void addHead(Node node) {
        if (head == null) {
            tail = head = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }
    
    private void removeNode(Node node) {
        if (node == head) {
            if (node == tail)
                head = tail = null;
            else {
                head = node.next;
                node.next = null;
                head.prev = null;
            }
        } else if (node == tail) {
            tail = node.prev;
            node.prev = null;
            tail.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }
    }
}