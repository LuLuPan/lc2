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

public class LRUCache < K, V > extends LinkedHashMap < K, V > {
 
    private int capacity; // Maximum number of items in the cache.
     
    public LRUCache(int capacity) { 
        super(capacity+1, 1.0f, true); // Pass 'true' for accessOrder.
        this.capacity = capacity;
    }
     
    protected boolean removeEldestEntry(Entry entry) {
        return (size() > this.capacity);
    } 
}

public class LRUCache {
    private class CacheNode {
    	private int key;
    	private int value;
    	public CacheNode(int k, int v) {
    		this.key = k;
    		this.value = v;
    	}

    	public int getKey() {
    		return this.key;
    	}
    	public int getValue() {
    		return this.value;
    	}
    	public void setValue(int v) {
    	    this.value = v;
    	}
    }
    private LinkedList<CacheNode> cacheList = new LinkedList<CacheNode>();
    private HashMap<Integer, CacheNode> cacheMap = new HashMap<Integer, CacheNode>();
    private int capacity = 0;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (!cacheMap.containsKey(key)) return -1;
        CacheNode node = cacheMap.get(key);
        if (cacheList.getFirst() != node) {
            cacheList.remove(node);
        	cacheList.addFirst(node);
        }

        return node.getValue();
    }
    
    public void set(int key, int value) {
    	// key already exists?
    	if (!cacheMap.containsKey(key)) {
            if (cacheList.size() == capacity) {
        	    // invalidate tail node if no space
        	    cacheMap.remove(cacheList.getLast().getKey());
        	    cacheList.removeLast();
            }
            // insert new node as head
            CacheNode newNode = new CacheNode(key, value);
            cacheList.addFirst(newNode);
            cacheMap.put(key, cacheList.getFirst());
        } else {
        	// put existed node as head
        	CacheNode node = cacheMap.get(key);
        	if (cacheList.getFirst() != node) {
        		cacheList.remove(node);
        		cacheList.addFirst(node);
        	}
        }
    }
}