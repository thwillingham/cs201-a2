package helpers;

import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Importer {
    String fname = "";
    Scanner s = null;
    Boolean type = false;
    public Importer(String f, boolean t) {
        fname = f;
        type = t;
    }

    public void process() {
        String[] tokens = getTokens();
        if (type == false) { // tree contents
            // remove non alpha characters
            for (String s : tokens) {
                
                System.out.println("scan: " + s);
            }
        } else {
        }
    }

    public String[] getTokens() {
        String temp = "";
        List<String> temps = new ArrayList<String>();
        try {
            s = new Scanner(new BufferedReader(new FileReader(fname)));
            while (s.hasNext()) {
                temp = s.next().replaceAll("[^a-zA-Z]", "").toLowerCase();
                if (temp != null && !temp.isEmpty()) {
                    temps.add(temp);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error: file, " + fname + ", not found");
            System.exit(0);
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return temps.toArray(new String[0]);
    }
}
