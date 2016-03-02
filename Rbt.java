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
            if (this == null || getParent() == null || getParent().getParent() == getParent()) {
                return null;
            } else {
                return (Node)getParent().getParent();
            }
        }

        public Node getUncle() {
            if (this == null || getGrandparent() == null) {
                return null;
            } else if (getGrandparent().getLeftChild() == getParent()) {
                return (Node)getGrandparent().getRightChild();
            } else {
                return (Node)getGrandparent().getLeftChild();
            }
        }

        public Node getSibling() {
            if (this == null || getParent() == null) {
                return null;
            } else if (getParent().getLeftChild() == this) {
                return (Node)getParent().getRightChild();
            } else {
                return (Node)getParent().getLeftChild();
            }
        }

        public void rotateLeft() {
            if (this.getRightChild() == null) {
                return;
            }
            Node curr = this;
            Node prev = ((Node)this.getRightChild());
            curr.setRightChild(prev.getLeftChild());
            if (curr.getParent() == null) {
                rootNode = prev;
            } else if (curr.getParent().getLeftChild() == curr) {
                curr.getParent().setLeftChild(prev);
            } else {
                curr.getParent().setRightChild(prev);
            }
            prev.setLeftChild(curr);
        }

        public void rotateRight() {
          if (this.getLeftChild() == null) {
              return;
          }
          Node curr = this;
          Node prev = ((Node)this.getLeftChild());
          curr.setLeftChild(prev.getRightChild());
          if (curr.getParent() == null) {
              rootNode = prev;
          } else if (curr.getParent().getLeftChild() == curr) {
              curr.getParent().setLeftChild(prev);
          } else {
              curr.getParent().setRightChild(prev);
          }
          prev.setRightChild(curr);
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
        Node curr = ((Node)super.insert(n));
        fixUp(curr);
        print();
    }
    
    public boolean isNotLinear(Node curr) {
    	if (curr.getParent() == null || curr.getGrandparent() == null) {
    		return false;
    	}
    	boolean lin = true;
    	if (curr.getParent().getLeftChild() == curr && curr.getGrandparent().getLeftChild() == curr.getParent()) {
    		lin = false;
    	} else if (curr.getParent().getRightChild() == curr && curr.getGrandparent().getRightChild() == curr.getParent()) {
    		lin = false;
    	}
    	return lin;
    }
/*
    public void fixUp(Node n) {
        //if (n == rootNode || n.getParent() == rootNode) {
          //((Node)rootNode).setColorBlack();
          //return;
        //}
        Node curr = n;
        if (rootNode != n && ((Node)curr.getParent()).isRed()) {
            if (curr.getUncle() != null && curr.getUncle().isRed()) {
                ((Node)curr.getParent()).setColorBlack();
                curr.getUncle().setColorBlack();
                curr.getGrandparent().setColorRed();
                fixUp(curr.getGrandparent());
            } else if (curr.getParent() == curr.getGrandparent().getLeftChild()) {
                if (curr == curr.getParent().getRightChild()) {
                    ((Node)curr.getParent()).rotateLeft();
                }
                ((Node)curr.getParent()).setColorBlack();
                curr.getGrandparent().setColorRed();
                curr.getGrandparent().rotateRight();
            } else if (curr.getParent() == curr.getGrandparent().getRightChild()) {
                if (curr == curr.getParent().getLeftChild()) {
                    ((Node)curr.getParent()).rotateRight();
                }
                ((Node)curr.getParent()).setColorBlack();
                curr.getGrandparent().setColorRed();
                curr.getGrandparent().rotateLeft();
            }
        }
        ((Node)rootNode).setColorBlack();
    }*/

    public void fixUp(Node n) {
    	Node curr = n;
    	while(true) {
	    	if (rootNode == curr) { return; }
	    	
	    	if (((Node)curr.getParent()).isBlack()) { return; }
	    	
	    	if (curr.getUncle() != null && curr.getUncle().isRed()) {
	    		((Node)curr.getParent()).setColorBlack();
	    		curr.getUncle().setColorBlack();
	    		curr.getGrandparent().setColorRed();
	    		curr = curr.getGrandparent();
	    	} else {
	    		if (isNotLinear(curr)) {
	    			if (curr.getParent().getLeftChild() == curr) {
	    				curr.rotateRight();
	    			} else {
	    				curr.rotateLeft();
	    			}
	    		}
    			((Node)curr.getParent()).setColorBlack();
    			curr.getGrandparent().setColorRed();
    			if (curr.getGrandparent().getLeftChild() == curr.getParent()) {
    				((Node)curr.getParent()).rotateRight();
    			} else {
    				((Node)curr.getParent()).rotateLeft();
	    		}	    			
	    	}
	    	((Node)rootNode).setColorBlack();
    	}
    	
    }

}
