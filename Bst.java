public class Bst {
    private BstNode rootNode;

    private class BstNode {
        private String value;
        private int weight;
        private BstNode parent;
        private BstNode leftChild;
        private BstNode rightChild;

        public BstNode() {
            parent = null;
            leftChild = null;
            rightChild = null;
            value = "";
            weight = 0;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String v) {
            value = v;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int w) {
            weight = w;
        }

        public BstNode getParent() {
            return parent;
        }

        public void setParent(BstNode p) {
            parent = p;
        }

        public BstNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(BstNode l) {
            leftChild = l;
        }

        public BstNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(BstNode r) {
            rightChild = r;
        }
    }

    public Bst() {
        rootNode = null;
    }

    public String getRootValue() {
        return rootNode.getValue();
    }
}
