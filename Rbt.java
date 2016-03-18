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
        
        public void setColor(Color c) {
            this.color = c;
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
            insertionFixUp(curr);
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

    public void insertionFixUp(Node n) {
        if (n.getFrequency() > 1) {
            return;
        }
        
        Node curr = n;
        curr.setColorRed();
        if (rootNode != n && ((Node)curr.getParent()).isRed()) {
            if (curr.getUncle() != null && curr.getUncle().isRed()) {
                ((Node)curr.getParent()).setColorBlack();
                curr.getUncle().setColorBlack();
                curr.getGrandparent().setColorRed();
                insertionFixUp(curr.getGrandparent());
            } else if (curr.getParent() == curr.getGrandparent().getLeftChild()) {
                if (curr == curr.getParent().getRightChild()) {
                    curr = ((Node)curr.getParent());
                    curr.rotateLeft();
                }
                ((Node)curr.getParent()).setColorBlack();
                curr.getGrandparent().setColorRed();
                ((Node) curr.getGrandparent()).rotateRight();
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
    }
    
    @Override
    public void delete(String s) {
        Node curr = ((Node) getNode(s));
        if (curr == null) {
            return;
        } else if (curr.getLeftChild() != null && curr.getRightChild() != null) {
            Node predecessor = ((Node) curr.getPredecessor());
            curr.setValue(predecessor.getValue());
            curr.setFrequency(predecessor.getFrequency());
            curr = predecessor;
        }
        Node move = null;
        if (curr.getLeftChild() == null) {
            move = ((Node) curr.getRightChild());
        } else {
            move = ((Node) curr.getLeftChild());
        }
        if (move != null) {
            if (curr == rootNode) {
                setRootNode(move);
            } else if (curr.getParent().getLeftChild() == curr) {
                curr.getParent().setLeftChild(move);
            } else {
                curr.getParent().setRightChild(move);
            }
            if (curr.isBlack()) {
                deletionFixUp(curr);
            }
        } else if (curr == rootNode) {
            rootNode = null;
        } else {
            if (curr.isBlack()) {
                deletionFixUp(curr);
            }
            if (curr.getParent().getLeftChild() == curr) {
                curr.getParent().setLeftChild(null);
            } else {
                curr.getParent().setRightChild(null);
            }
            curr.setParent(null);
        }
    }

    public void deletionFixUp(Node x) {
        while (x != rootNode && !(x.isRed())) {
            if (x.getParent().getLeftChild() == x) {
                Node sibling = x.getSibling();
                if (sibling.isRed()) {
                    sibling.setColorBlack();
                    ((Node) x.getParent()).setColorRed();
                    ((Node) x.getParent()).rotateLeft();
                    sibling = ((Node) x.getParent()).getSibling();
                }
                if (((Node) sibling.getLeftChild()).isBlack() && ((Node) sibling.getRightChild()).isBlack()) {
                    sibling.setColorRed();
                    x = ((Node) x.getParent());
                } else {
                    if (((Node) sibling.getRightChild()).isBlack()) {
                        ((Node) sibling.getLeftChild()).setColorBlack();
                        sibling.setColorRed();
                        sibling.rotateRight();
                        sibling = x.getSibling();
                    }
                    sibling.setColor(((Node) x.getParent()).getColor());
                    ((Node)  x.getParent()).setColorBlack();
                    ((Node) sibling.getRightChild()).setColorBlack();
                    ((Node) x.getParent()).rotateLeft();
                    x = ((Node) rootNode);
                }
            } else {
                Node sibling = x.getSibling();
                if (sibling.isRed()) {
                    sibling.setColorBlack();
                    ((Node) x.getParent()).setColorRed();
                    ((Node) x.getParent()).rotateRight();
                    sibling = x.getSibling();
                }
                if (((Node) sibling.getLeftChild()).isBlack() && ((Node)sibling.getRightChild()).isBlack()) {
                    sibling.setColorRed();
                    x = ((Node) x.getParent());
                } else {
                    if (((Node) sibling.getLeftChild()).isBlack()) {
                        ((Node) sibling.getRightChild()).setColorBlack();
                        sibling.setColorRed();
                        sibling.rotateLeft();
                        sibling = x.getSibling();
                    }
                    sibling.setColor(((Node) x.getParent()).getColor());
                    ((Node) x.getParent()).setColorBlack();
                    ((Node) sibling.getLeftChild()).setColorBlack();
                    ((Node) x.getParent()).rotateRight();
                    x = ((Node) rootNode);
                }
            }
        } 
        x.setColorBlack();
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
