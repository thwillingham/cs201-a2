package helpers;

public class Queue {
    int size = 0;
    Node head = null;
    Node tail = null;

    public class Node {
        Node previous = null;
        Node next = null;
        Object value = null;

        public Node(Object o) {
            this.value = o;
        }

        public void setPrevious(Node n) {
            this.previous = n;
        }

        public void setNext(Node n) {
            this.next = n;
        }

        public Node getPrevious() {
            return this.previous;
        }

        public Node getNext() {
            return this.next;
        }

        public Object getValue() {
            return this.value;
        }
    }

    public Queue() {
    }

    public void add(Object o) {
        Node n = new Node(o);
        if (size == 0) {
            this.head = n;
        } else {
            this.tail.setNext(n);
            n.setPrevious(this.tail);
        }
        this.tail = n;
        size++;
    }

    public Object peek() {
        if (size > 0) {
            return this.head.getValue();
        } else {
            return null;
        }
    }

    public Object remove() {
        if (size > 0) {
           size--;
           Object o = this.head.getValue();
           if (size > 0) {
               this.head.getNext().setPrevious(null); 
               this.head = this.head.getNext();
           } else {
               this.head = null;
               this.tail = null;
           }
           return o;
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return this.size;
    }

}
