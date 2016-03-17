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

       System.out.println("############");
       System.out.println("############");
       System.out.println("############");
       System.out.println("############");



        String[] words = {"the", "quick", "brown", "fox", "jumped", "over", "the", "girl", "and", "her", "lazy", "lazy", "dog"};
       //tree.delete("the");
       //tree.print();
       //
        Rbt ree = new Rbt();

        for (String s: words) {
            ree.insert(s);
            ree.print();
            System.out.println("inserted: " + s + "\n");
        }


       
    }
}
