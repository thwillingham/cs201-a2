package helpers;

import helpers.Queue;
import java.io.*;
import java.util.Scanner;
//import java.util.List;
//import java.util.ArrayList;

public class Importer {
    String fname = "";
    Scanner s = null;
    Boolean type = false;
    public Importer(String f, boolean t) {
        fname = f;
        type = t;
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
