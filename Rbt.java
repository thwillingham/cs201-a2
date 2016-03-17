import java.util.LinkedList;
import java.util.Queue;
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
            System.out.println("parent: " + String.valueOf(curr.getParent().getValue()));
            if (curr.getParent() == null || curr.getParent() == curr) {
                setRootNode(prev);
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
              setRootNode(prev);
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
        
        if (this.rootNode == null) {
            this.rootNode = n;
            ((Node) this.rootNode).setColorBlack();
        } else {
            Node curr = ((Node)super.insert(n));
            fixUp(curr);
        }
            //print();
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

    public void fixUp(Node n) {
        if (n.getFrequency() > 1) {
            return;
        }
        
        Node curr = n;
        curr.setColorRed();
        System.out.println("root: " + rootNode.getValue());
        System.out.println("curr: " + curr.getValue());
        System.out.println("curr is root: " + Boolean.toString(curr == rootNode));
        if (rootNode != n && ((Node)curr.getParent()).isRed()) {
            if (curr.getUncle() != null && curr.getUncle().isRed()) {
                System.out.println("%%% 1");
                ((Node)curr.getParent()).setColorBlack();
                curr.getUncle().setColorBlack();
                curr.getGrandparent().setColorRed();
                fixUp(curr.getGrandparent());
            } else if (curr.getParent() == curr.getGrandparent().getLeftChild()) {
                System.out.println("here 1");
                if (curr == curr.getParent().getRightChild()) {
                    curr = ((Node)curr.getParent());
                    curr.rotateLeft();
                }
                System.out.println("%%% 2");
                ((Node)curr.getParent()).setColorBlack();
                curr.getGrandparent().setColorRed();
                System.out.println("before");
                this.print();
                ((Node) curr.getGrandparent()).rotateRight();
                System.out.println("after");
                this.print();
            } else if (curr.getParent() == curr.getGrandparent().getRightChild()) {
                if (curr == curr.getParent().getLeftChild()) {
                    ((Node)curr.getParent()).rotateRight();
                }
                System.out.println("%%% 3");
                ((Node)curr.getParent()).setColorBlack();
                curr.getGrandparent().setColorRed();
                curr.getGrandparent().rotateLeft();
            }
        }
        ((Node)rootNode).setColorBlack();
    }

    @Override
    public void print() {
        Queue<Node> q = new LinkedList<Node>();
        if (rootNode == null) { return; }
        q.add((Node)rootNode);
        String stringToPrint = "";
        Node parent = null;
        int lineNumber = 1;
        while (!q.isEmpty()) {
            //if (lineNumber > 10) {return;}
            int count = q.size();
            System.out.print(lineNumber + ": ");
            while (count > 0) {
                Node curr = q.remove();
                if (curr.getLeftChild() == null && curr.getRightChild() == null) {
                    stringToPrint = stringToPrint + "=";
                }
                if (rootNode == curr) {
                	parent = ((Node) rootNode);
                } else {
                	parent = ((Node) curr.getParent());
                }
                stringToPrint = stringToPrint + curr.getValue();
                //System.out.println(curr.isRed());
                if (curr.isRed()) {
                    stringToPrint.concat("*");
                    stringToPrint = stringToPrint + "*";
                }
                stringToPrint = stringToPrint + "(" + parent.getValue();
                if (parent.isRed()) {
                    stringToPrint = stringToPrint + "*";
                }
                stringToPrint = stringToPrint + ")" + curr.getFrequency();
                if (curr == rootNode) {
                    stringToPrint = stringToPrint + "X";
                } else if (curr.getParent().getLeftChild() == curr) {
                    stringToPrint = stringToPrint + "L ";
                } else if (curr.getParent().getRightChild() == curr) {
                    stringToPrint = stringToPrint + "R ";
                }
                System.out.print(stringToPrint);
                if (curr.getLeftChild() != null) {
                    q.add((Node)curr.getLeftChild());
                }
                if (curr.getRightChild() != null) {
                    q.add((Node)curr.getRightChild());
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
