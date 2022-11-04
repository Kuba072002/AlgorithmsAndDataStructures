package pl.edu.pw.ee;

import java.io.*;
import java.net.URL;
import org.junit.Test;

public class TestLiczbyRekurencyjnyWywolanPuta {
    // Aby test dzialal nalezy dodac zmienna publiczna int "numberOfRecursiveCalls"
    // Na poczatku publicznej metody put dodac na poczatku linijke:
    // numberOfRecursiveCalls = 0;
    // Na poczatku prywatnej metody put dodac na poczatku linijke:
    // numberOfRecursiveCalls++;
    @Test
    public void testOfNamburRecursiveCallsOfPut() {
        RedBlackTree<String, Integer> root = new RedBlackTree<>();
        czytaj(root);
        System.out.println(root.numberOfRecursiveCalls);
    }

    private void czytaj(RedBlackTree<String, Integer> root) {
        URL strona;
        try {
            strona = new URL("https://www.mit.edu/~ecprice/wordlist.100000");
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(strona.openStream()));
            int i = 0;

            String inputLine;
            String suffix = "cats";
            while ((inputLine = in.readLine()) != null) {
                for (int j = 1; j <= 10; j++)
                    root.put(suffix + j + inputLine, i++);
                
                if(i<50000){
                    if(i==500)
                        zapisz_do_pliku("plik.txt",root.numberOfRecursiveCalls);
                    if(i<5000)
                        if(i%1000==0)
                            zapisz_do_pliku("plik.txt",root.numberOfRecursiveCalls);
                    if(i%5000==0)
                        zapisz_do_pliku("plik.txt",root.numberOfRecursiveCalls);
                }
                if(i%50000==0)
                    zapisz_do_pliku("plik.txt",root.numberOfRecursiveCalls);    
            }
            in.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void zapisz_do_pliku(String filename, int n) {
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter(filename, true));
            output.append("\n" + n);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
