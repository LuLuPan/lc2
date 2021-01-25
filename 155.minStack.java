class MinStack {
    class Node {
        int value;
        int min;
        Node next;
        public Node(int v) {
            this.value = v;
            this.min = v;
            this.next = null;
        }
    }
    
    private Node top = null;
    public void push(int x) {
        Node node = new Node(x);
        if (top != null) {
            node.min = Math.min(x, top.min);
        }
        
        node.next = top;
        top = node;
    }

    public void pop() {
        if (top == null) return;
        Node node = top;
        top = top.next;
        node.next = null;
    }

    public int top() {
        return top == null ? 0 : top.value;
    }

    public int getMin() {
        return top == null ? 0 : top.min;
    }
}

/*** cpp ***/
class MinStack {
public:
    /** initialize your data structure here. */
    MinStack() {
        
    }
    
    void push(int x) {
        Node node(x);
        node.min_ = std::min(x, minStack_.empty() ? INT_MAX : minStack_.top().min_);
        minStack_.push(node);
    }
    
    void pop() {
        minStack_.pop();
    }
    
    int top() {
        return minStack_.top().value_;
    }
    
    int getMin() {
        const auto& node = minStack_.top();
        return node.min_;
    }
private:
    struct Node {
        int min_;
        int value_;
        Node(int val): value_(val) {}
    };
    
    stack<Node> minStack_;
};

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack* obj = new MinStack();
 * obj->push(x);
 * obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->getMin();
 */