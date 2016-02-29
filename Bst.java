import java.util.LinkedList;
import java.util.Queue;

public class Bst {
    private Node rootNode;

    protected class Node {
        private String value;
        private int frequency;
        private Node parent;
        private Node leftChild;
        private Node rightChild;

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
            return;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node r) {
            rightChild = r;
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

    }

    public Bst() {
        rootNode = null;
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
        while (curr.getValue() != null) {
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
            } else if (curr.getValue() == s) { // strings are equal
                return curr;
            }
        }
        return null;
 
    }

    public void insert(String s) {
        insert(new Node(s));
        return;
    }

    public void insert(Node n) {
        if (rootNode == null) {
            rootNode = n;
            rootNode.setParent(rootNode);
            return;
        }
        Node curr = rootNode;
        while (true) {
            int compare = n.getValue().compareTo(curr.getValue());
            if (compare < 0) {
                if (curr.getLeftChild() == null) {
                    curr.setLeftChild(n);
                    curr.getLeftChild().setParent(curr);
                    return;
                }
                curr = curr.getLeftChild();
            } else if (compare > 0) {
                if (curr.getRightChild() == null) {
                    curr.setRightChild(n);
                    curr.getRightChild().setParent(curr);
                    return;
                }
                curr = curr.getRightChild();
            } else { // strings are equal
                curr.incrementFrequency();
                return;
            }
        }
    }
    
    public void delete(String s) {
        Node curr = this.getNode(s);
        if (curr == null) {
            return;
        } else if (!curr.isLeaf()) {
            if (curr.getFrequency() > 1) {
                curr.decrementFrequency();
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
        int lineNumber = 1;
        while (!q.isEmpty()) {
            int count = q.size();
            System.out.print(lineNumber + ": ");
            while (count > 0) {
                Node curr = q.remove();
                if (curr.getLeftChild() == null && curr.getRightChild() == null) {
                    stringToPrint = stringToPrint + "=";
                }
                stringToPrint = stringToPrint + curr.getValue() + "(" + curr.getParent().getValue() + ")" + curr.getFrequency();
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
