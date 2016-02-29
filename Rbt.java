import java.awt.Color;

public class Rbt extends Bst {
    public class Node extends Bst.Node {
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
                return getParent().getParent();
            }
        }

        public Node sibling() {
            if (getParent() == null) {
                return null;
            } else if (getParent().getLeftChild == this) {
                return getParent().getRightChild();
            } else {
                return getParent().getLeftChild();
            }
        } 

    }

    public Rbt() {
        super();
    }
    
}
