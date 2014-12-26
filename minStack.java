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