package main;

import helpers.Queue;
import trees.Bst;
import trees.Rbt;
import helpers.Importer;
//import java.util.Arrays;
//import java.util.Queue;
//import java.util.LinkedList;

public class Trees {
    public static void main(String[] args) {
        Bst tree = null;
        if (args.length != 3) {
            System.out.println("Error: invalid number of parameters");
            System.exit(0);
        }
        
        if (args[0].equals("-1")) {
            tree = new Bst();
        } else if (args[0].equals("-2")) {
            tree = new Rbt();
        } else {
            System.out.println("Error: invalid tree type");
            System.exit(0);
        }
        String contentsFile = args[1];
        String commandsFile = args[2];

        Importer i = new Importer(contentsFile, false);
        Queue contents = i.getTokens();
        //tree.insert(contents);
        while (!contents.isEmpty()) {
            tree.insert((String) contents.remove());
        }


        Importer j = new Importer(commandsFile, true);
        Queue commands = j.getTokens();
        while (!commands.isEmpty()) {
            String temp = (String) commands.remove();
            if (temp.equals("i")) {
                //System.out.println("#inserting");
                tree.insert((String) commands.remove());
            } else if (temp.equals("d")) {
                //System.out.println("#deleting");
                tree.delete((String) commands.remove());
            } else if (temp.equals("f")) {
                //System.out.println("#finding");
                tree.find((String) commands.remove());
            } else if (temp.equals("s")) {
                //System.out.println("#showing");
                tree.print();
            } else if (temp.equals("r")) {
                //System.out.println("#reporting");
                tree.report();
            } else {
                System.err.printf("Error: invalid command order");
                System.exit(0);
            }
        }


    }

}
