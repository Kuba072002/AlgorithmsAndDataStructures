package pl.edu.pw.ee;

import java.io.*;

public class Utils {

    public void readFile(Graph g, String pathToFile) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(pathToFile));
            String line;
            int nline = 1;
            while ((line = bf.readLine()) != null) {
                String[] parts = line.split("\\s+");
                try {
                    g.add(parts);
                } catch (IllegalArgumentException e) {
                    bf.close();
                    throw new IllegalArgumentException(e.getMessage() + "\nLine: " + nline);
                }
                nline++;
            }
            bf.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
