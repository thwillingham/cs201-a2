package main;

import trees.Bst;
import trees.Rbt;
import helpers.Importer;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

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
        String[] contents = i.getTokens();
        tree.insert(contents);


        Importer j = new Importer(commandsFile, true);
        String[] tok = j.getTokens();
        Queue<String> commands = new LinkedList<String>(Arrays.asList(tok));
        while (!commands.isEmpty()) {
            String temp = commands.remove();
            if (temp.equals("i")) {
                System.out.println("#inserting");
                tree.insert(commands.remove());
            } else if (temp.equals("d")) {
                System.out.println("#deleting");
                tree.delete(commands.remove());
            } else if (temp.equals("f")) {
                System.out.println("#finding");
                tree.find(commands.remove());
            } else if (temp.equals("s")) {
                System.out.println("#showing");
                tree.print();
            } else if (temp.equals("r")) {
                System.out.println("#reporting");
                tree.report();
            } else {
                System.out.println("Error: invalid command order");
                System.exit(0);
            }
        }


    }

}
