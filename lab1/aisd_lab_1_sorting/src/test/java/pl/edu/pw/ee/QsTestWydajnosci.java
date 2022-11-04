package pl.edu.pw.ee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;
import org.junit.Test;

public class QsTestWydajnosci {
    double[] nums;
    int n;

    @Test
    public void sort() {
        QuickSort qs = new QuickSort();
        n = 350000;
        nums = new double[n];
        // losowy
        losowy();
        // Przypadek pesymistyczne-elementy posortowane
        pesymistyczny();
        // Przypadek opymistyczny-rowne przedzialy
        optymistyczny();
        long startTime = System.nanoTime();
        qs.sort(nums);
        long finishTime = System.nanoTime();
        long time = (finishTime - startTime) / 1000000;
        System.out.println(n + "  " + time);
        // zapisz_do_pliku("qsOpt.txt",n,time);
    }

    public static void zapisz_do_pliku(String filename, int n, long time) {
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter(filename, true));
            output.append("\n" + n + "  " + time);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void losowy() {
        Random r = new Random();
        for (int i = 0; i < n; i++)
            nums[i] = r.nextDouble() * 1000;
    }

    private void pesymistyczny() {
        for (int i = 0; i < n; i++)
            nums[i] = i + 1;
    }

    private void optymistyczny() {
        for (int i = 0; i < n; i++)
            nums[i] = i + 1;
        generate(0, n);
    }

    private void generate(int begin, int end) {
        int count = end - begin;
        if (count < 3)
            return;
        int middle = begin + (count - 1) / 2;
        generate(begin, middle);
        swap(begin, middle);
        generate(++middle, end);
    }

    private void swap(int i, int j) {
        double t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
