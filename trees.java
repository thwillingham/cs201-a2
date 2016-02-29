public class trees {
    public static void main(String[] args) {
        Bst tree = new Bst();
        tree.insert("the");
        tree.insert("quick");
        tree.insert("brown");
        tree.insert("fox");
        tree.insert("jumped");
        tree.insert("over");
        tree.insert("the");
        tree.insert("girl");
        tree.insert("and");
        tree.insert("her");
        tree.insert("lazy");
        tree.insert("lazy");
        tree.insert("dog");
        tree.print();

   
       tree.delete("the");
       tree.print(); 

       Rbt RbtTree = new Rbt();
    }
}
