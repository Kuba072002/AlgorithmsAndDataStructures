package pl.edu.pw.ee;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.net.URL;
import org.junit.Test;

public class TestMierzenieczasu {
    // HashDoubleHashing HashQuadraticProbing HashLinearProbing
    // 512 1024 2048 4096 8192 16384 32768 65536 131072 262144
    private int liczba = 16384;
    private HashDoubleHashing<String> hlp;
    private ArrayList<String> tab = new ArrayList<>(100000);
    ArrayList<Long> wsta;
    ArrayList<Long> wysz;
    public int n;

    @Test
    public void test() {
        read();
        n = tab.size();
        wsta = new ArrayList<>(30);
        wysz = new ArrayList<>(30);
        for (int i = 0; i < 30; i++) {
            hlp = new HashDoubleHashing<>(liczba);
            long startTimeOfInserting = System.nanoTime();
            insertToHash();
            long finishTimeOfInserting = System.nanoTime();
            long timeOfInserting = (finishTimeOfInserting - startTimeOfInserting) / 1000;

            long startTimeOfGetting = System.nanoTime();
            getFromHash();
            long finishTimeOfGetting = System.nanoTime();
            long timeOfGetting = (finishTimeOfGetting - startTimeOfGetting) / 1000;
            wsta.add(timeOfInserting);
            wysz.add(timeOfGetting);
        }
        Collections.sort(wsta);
        Collections.sort(wysz);
        writoToFile("wstawianie.txt", obliczsrednia(wsta));
        writoToFile("wyszukiwanie.txt", obliczsrednia(wysz));
    }

    private void read() {
        try {
            URL url;
            url = new URL("https://www.mit.edu/~ecprice/wordlist.100000");
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                tab.add(inputLine);
            in.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void insertToHash() {
        for (int i = 0; i < n; i++) {
            hlp.put(tab.get(i));
        }
    }

    private void getFromHash() {
        for (int i = 0; i < n; i++) {
            hlp.get(tab.get(i));
        }
    }

    private long obliczsrednia(ArrayList<Long> arr) {
        long ave = 0;
        for (int i = 10; i < 20; i++)
            ave += arr.get(i);
        return ave / 10;
    }

    private void writoToFile(String filename, long time) {
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter(filename, true));
            output.append(time + "\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
