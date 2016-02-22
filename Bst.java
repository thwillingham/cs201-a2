public class Bst {
    public BstNode rootNode;

    private class BstNode {
        private BstNode parent;
        private BstNode leftChild;
        private BstNode rightChild;
        private String datum;
        private int weight;

        public BstNode() {
            parent = null;
            leftChild = null;
            rightChild = null;
            datum = "";
            weight = 0;
        }
    }

    public Bst() {
        rootNode = null;
    }
}
