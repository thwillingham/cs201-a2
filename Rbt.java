import java.awt.Color;

public class Rbt extends Bst {
    protected class Node extends Bst.Node {
        Color color = Color.red;

        public Node(String s) {
            super(s);
        }
        
        public Color getColor() {
            return this.color;
        }

        public boolean isRed() {
            return (this.color == Color.red);
        }

        public void setColorRed() {
            this.color = Color.red;
        }

        public boolean isBlack() {
            return (this.color == Color.black);
        }
        
        public void setColorBlack() {
            this.color = Color.black;
        }
        
        public Node getGrandparent() {
            if (getParent() == null) {
                return null;
            } else {
                return (Node)getParent().getParent();
            }
        }

        public Node getUncle() {
            if (getGrandparent() == null) {
                return null;
            } else if (getGrandparent().getLeftChild().getLeftChild() == this || getGrandparent().getLeftChild().getRightChild() == this) {
                return (Node)getGrandparent().getRightChild();
            } else {
                return (Node)getGrandparent().getLeftChild();
            }
        }

        public Node getSibling() {
            if (getParent() == null) {
                return null;
            } else if (getParent().getLeftChild() == this) {
                return (Node)getParent().getRightChild();
            } else {
                return (Node)getParent().getLeftChild();
            }
        } 

    }

    public Rbt() {
        super();
    }

    public void insert(String s) {
        insert(new Node(s));
        return;
    }

    public void insert(Node n) {
        super.insert(n);
        fixUp(n); 
    }    

    public void fixUp(Node n) {
    }
}
