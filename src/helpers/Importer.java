package helpers;

import helpers.Queue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;
import trees.Bst;
import trees.Rbt;

public class Importer {
    String fname = "";
    Scanner s = null;
    Boolean type = false;
    Bst tree = null;
    public Importer(String f, boolean t, boolean treeType) {
        fname = f;
        type = t;
        if (treeType == false) {
            tree = new Bst();
        } else {
            tree = new Rbt();
        }
    }

    public Bst importToTree() {
        String temp = "";
        try {
            s = new Scanner(new BufferedReader(new FileReader(fname)));
            while (s.hasNext()) {
                temp = cleanForTree(s.next());
                if (temp != null && !temp.isEmpty()) {
                    tree.insert(temp);
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.printf("Error: file, " + fname + ", not found");
            System.exit(0);
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return tree;
    }

       

    public Queue getTokens() {
        String temp = "";
        Queue temps = new Queue();
        try {
            s = new Scanner(new BufferedReader(new FileReader(fname)));
            while (s.hasNext()) {
                temp = cleanForTree(s.next());
                if (temp != null && !temp.isEmpty()) {
                    temps.add(temp);
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.printf("Error: file, " + fname + ", not found");
            System.exit(0);
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return temps;
    }

    public String cleanForTree(String s) {
        return s.replaceAll("[^a-zA-Z]", "").toLowerCase();
    }
}
