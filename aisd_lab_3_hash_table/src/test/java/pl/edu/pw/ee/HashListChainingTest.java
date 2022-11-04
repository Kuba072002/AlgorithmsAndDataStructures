package pl.edu.pw.ee;

import java.io.*;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

public class HashListChainingTest {

    @Test
    public void testAdd_method() {
        HashListChaining<String> h = new HashListChaining<>(3);
        h.add("value");
        h.add("Maks");
        h.add("Ara");
        h.add("Klara");
        h.add("Ania");
        h.add("mks");
        h.add("mka");
        Assert.assertEquals("value", h.get("value"));
        Assert.assertEquals("Maks", h.get("Maks"));
        Assert.assertEquals("Ara", h.get("Ara"));
        Assert.assertEquals("Klara", h.get("Klara"));
        Assert.assertEquals("Ania", h.get("Ania"));
        Assert.assertEquals("mks", h.get("mks"));
        Assert.assertEquals("mka", h.get("mka"));
    }

    @Test
    public void testDelete_method() {
        HashListChaining<String> h = new HashListChaining<>(3);
        h.add("value");
        h.add("Maks");
        h.add("Ara");
        h.add("Klara");
        h.delete("value");
        Assert.assertEquals(null, h.get("value"));
        h.delete("Maks");
        Assert.assertEquals(null, h.get("Maks"));
        h.delete("Ara");
        Assert.assertEquals(null, h.get("Ara"));
        h.delete("Klara");
        Assert.assertEquals(null, h.get("Klara"));
    }

    @Test
    public void testGet_method(){
        HashListChaining<String> h = new HashListChaining<>(3);
        h.add("value");
        h.add("Maks");
        h.add("Ara");
        Assert.assertEquals(null, h.get("Lara"));
    }

    @Test
    public void testAddDuplicate() {
        HashListChaining<String> h = new HashListChaining<>(1);
        h.add("value");
        h.add("Maks");
        h.add("value");
        h.delete("value");
        // Dodaje 2 elementy "value" ale w liscie pozosatje jeden czyli po usunieciu
        // powinismy dostac null-a
        // a jesli tworzylibysmy kolejny taki element w liscie(co jest zle) to po
        // usunieciu jednego ten drugi dalej by tam byl
        Assert.assertEquals(null, h.get("mka"));
    }

    @Test
    public void testNull() {
        HashListChaining<String> h = new HashListChaining<>(1);
        try {
            h.add(null);
            Assert.fail("Nie zwrocono wyjatku");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Value cannot be null", e.getMessage());
        }
        try {
            h.get(null);
            Assert.fail("Nie zwrocono wyjatku");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Value cannot be null", e.getMessage());
        }
        try {
            h.delete(null);
            Assert.fail("Nie zwrocono wyjatku");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Value cannot be null", e.getMessage());
        }
    }

    @Test
    public void test_MierzenieCzasu() {
        int n = 4096 * 8;
        HashListChaining<String> h = new HashListChaining<>(n);
        czytaj(h);
        long startTime = System.nanoTime();
        szukaj(h);
        long finishTime = System.nanoTime();
        long time = (finishTime - startTime) / 1000000;
        System.out.println(time);
        System.out.println(h.countLoadFactor());
        zapisz_do_pliku("plik.txt", n, time);
    }

    private void czytaj(HashListChaining<String> h) {
        URL strona;
        try {
            strona = new URL("https://www.mit.edu/~ecprice/wordlist.100000");
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(strona.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                h.add(inputLine);
            in.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void szukaj(HashListChaining<String> h) {
        URL strona;
        try {
            strona = new URL("https://www.mit.edu/~ecprice/wordlist.100000");
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(strona.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                h.get(inputLine);
            in.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void zapisz_do_pliku(String filename, int n, long time) {
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter(filename, true));
            output.append("\n" + n + "  " + time);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
