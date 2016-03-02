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


       //tree.delete("the");
       //tree.print();
       System.out.println("############");
       System.out.println("############");
       System.out.println("############");
       System.out.println("############");

       Rbt ree = new Rbt();
       ree.insert("the");
       System.out.println("inserted: the");
       ree.insert("quick");
       System.out.println("inserted: quick");
       ree.insert("brown");
       System.out.println("inserted: brown");
       ree.insert("fox");
       System.out.println("inserted: fox");
       ree.insert("jumped");
       System.out.println("inserted: jumped");
       ree.insert("over");
       System.out.println("inserted: over");
       ree.insert("the");
       System.out.println("inserted: the");
       ree.insert("girl");
       System.out.println("inserted: girl");
       ree.insert("and");
       System.out.println("inserted: and");
       ree.insert("her");
       System.out.println("inserted: her");
       ree.insert("lazy");
       System.out.println("inserted: lazy");
       ree.insert("lazy");
       System.out.println("inserted: lazy");
       ree.insert("dog");
       System.out.println("inserted: dog");
       ree.print();
    }
}
