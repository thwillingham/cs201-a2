package trees;

import java.util.LinkedList;
import java.util.Queue;

public class Bst {
    protected Node rootNode;
    protected int size = 0;

    public void println(Object line) {
        System.out.println(line);
    }

    protected class Node {
        protected String value;
        protected int frequency;
        protected Node parent;
        protected Node leftChild;
        protected Node rightChild;

        public Node(String s) {
            parent = null;
            leftChild = null;
            rightChild = null;
            value = s;
            frequency = 1;
            return;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String v) {
            value = v;
            return;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int f) {
            frequency = f;
            return;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node p) {
            parent = p;
            return;
        }

        public Node getLeftChild() {
			return leftChild;
        }

        public void setLeftChild(Node l) {
            leftChild = l;
            if (l != null) {
            	l.setParent(this);
            }
            return;
        }

        public Node getRightChild() {
			return rightChild;
        }

        public void setRightChild(Node r) {
            rightChild = r;
            if (r != null) {
            	r.setParent(this);
            }
            return;
        }

        public void incrementFrequency() {
            frequency += 1;
            return;
        }

        public void decrementFrequency() {
            frequency -= 1;
            return;
        }

        public boolean isLeaf() {
            return (this.getLeftChild() == null && this.getRightChild() == null);
        }

        public Node getPredecessor() {
            Node curr = this.getLeftChild();
            if (curr != null) {
                while (curr.getRightChild() != null) {
                    curr = curr.getRightChild();
                }
            }
            return curr;
        }

        public void prune() {
            Node curr = this;
            if (curr.getParent().getLeftChild() == curr) {
                curr.getParent().setLeftChild(null);
            } else {
                curr.getParent().setRightChild(null);
            }
            curr.setParent(null);
        }

        public boolean hasBothChildren() {
            if (this.getLeftChild() != null && this.getLeftChild() != null) {
                return true;
            }
            return false;
        }

    }

    public Bst() {
        rootNode = null;
    }

    public int getSize() {
        return this.size;
    }

    public void report() {
        System.out.println("Number of nodes in tree: " + String.valueOf(this.size));
        System.out.println("Min Height: " + String.valueOf(minHeight(rootNode)));
        System.out.println("Max Height: " + String.valueOf(maxHeight(rootNode)));
    }

    public int minHeight(Node curr) {
        if (curr == null) {
            return 0;
        }
        if (curr.getLeftChild() == null && curr.getRightChild() == null) {
            return 1;
        }
        if (curr.getLeftChild() == null) {
            return minHeight(curr.getRightChild()) + 1;
        }
        if (curr.getRightChild() == null) {
            return minHeight(curr.getLeftChild()) + 1;
        }
        return Math.min(minHeight(curr.getLeftChild()), minHeight(curr.getRightChild())) + 1;
    }

    public int maxHeight(Node curr) {
        if (curr == null) {
            return 0;
        }
        if (curr.getLeftChild() == null && curr.getRightChild() == null) {
            return 1;
        }
        if (curr.getLeftChild() == null) {
            return maxHeight(curr.getRightChild()) + 1;
        }
        if (curr.getRightChild() == null) {
            return maxHeight(curr.getLeftChild()) + 1;
        }
        return Math.max(maxHeight(curr.getLeftChild()), maxHeight(curr.getRightChild())) + 1;
    }



    public String getRootValue() {
        return rootNode.getValue();
    }

    public void setRootNode(Node n) {
        if (n != null) {
            n.setParent(null);
        }
        this.rootNode = n;
    }

    public Node getNode(String s) {
        if (rootNode.getValue() == null) {
            return null;
        }
        Node curr = rootNode;
        while (curr != null) {
            int compare = s.compareTo(curr.getValue());
            if (compare < 0) {
                if (curr.getLeftChild() == null) {
                    return null;
                }
                curr = curr.getLeftChild();
            } else if (compare > 0) {
                if (curr.getRightChild() == null) {
                    return null;
                }
                curr = curr.getRightChild();
            } else if (compare == 0) { // strings are equal
                return curr;
            }
        }
        return null;

    }

    public void find(String s) {
        Node curr = getNode(s);
        if (curr != null) {
            System.out.println(String.valueOf(curr.getFrequency()));
        } else {
            System.out.println("0");
        }
    }

    public void insert(String[] c) {
        for (String s : c) {
            insert(s);
        }
    }

    public void insert(String s) {
        insert(new Node(s));
        return;
    }

    public Node insert(Node n) {
        size++;
        if (rootNode == null) {
            rootNode = n;
            //rootNode.setParent(rootNode);
            return n;
        }
        Node curr = rootNode;
        while (true) {
            int compare = n.getValue().compareTo(curr.getValue());
            if (compare < 0) {
                if (curr.getLeftChild() == null) {
                    curr.setLeftChild(n);
                    curr.getLeftChild().setParent(curr);
                    return n;
                }
                curr = curr.getLeftChild();
            } else if (compare > 0) {
                if (curr.getRightChild() == null) {
                    curr.setRightChild(n);
                    curr.getRightChild().setParent(curr);
                    return n;
                }
                curr = curr.getRightChild();
            } else { // strings are equal
                curr.incrementFrequency();
                size--;
                return curr;
            }
        }
    }

    public void delete(String s) {
        size--;
        Node curr = this.getNode(s);
        if (curr == null) {
            size++;
            return;
        } else if (!curr.isLeaf()) {
            if (curr.getFrequency() > 1) {
                curr.decrementFrequency();
                size++;
                return;
            }
            Node predecessor = curr.getPredecessor();
            curr.setValue(predecessor.getValue());
            curr.setFrequency(predecessor.getFrequency());
            curr = predecessor;
        }
        Node move = null;
        if (curr.getLeftChild() == null) {
            move = curr.getRightChild();
        } else {
            move = curr.getLeftChild();
        }
        if (curr == rootNode) {
            setRootNode(move);
        } else if (curr.getParent().getLeftChild() == curr) {
            curr.getParent().setLeftChild(move);
        } else {
            curr.getParent().setRightChild(move);
        }
    }

    public void print() {
        Queue<Node> q = new LinkedList<Node>();
        if (rootNode == null) { return; }
        q.add(rootNode);
        String stringToPrint = "";
        String parentValue = "";
        int lineNumber = 1;
        while (!q.isEmpty()) {
            int count = q.size();
            System.out.print(lineNumber + ": ");
            while (count > 0) {
                Node curr = q.remove();
                if (curr.getLeftChild() == null && curr.getRightChild() == null) {
                    stringToPrint = stringToPrint + "=";
                }
                if (rootNode == curr) {
                	parentValue = rootNode.getValue();
                } else {
                	parentValue = curr.getParent().getValue();
                }
                stringToPrint = stringToPrint + curr.getValue() + "(" + parentValue + ")" + curr.getFrequency();
                if (curr == rootNode) {
                    stringToPrint = stringToPrint + "X";
                } else if (curr.getParent().getLeftChild() == curr) {
                    stringToPrint = stringToPrint + "L ";
                } else if (curr.getParent().getRightChild() == curr) {
                    stringToPrint = stringToPrint + "R ";
                }
                System.out.print(stringToPrint);
                if (curr.getLeftChild() != null) {
                    q.add(curr.getLeftChild());
                }
                if (curr.getRightChild() != null) {
                    q.add(curr.getRightChild());
                }
                count--;
                stringToPrint = "";
            }
            System.out.println("");
            lineNumber += 1;
        }
        return;
    }
}
