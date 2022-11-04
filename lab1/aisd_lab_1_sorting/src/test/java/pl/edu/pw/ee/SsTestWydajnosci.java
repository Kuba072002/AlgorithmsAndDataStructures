package pl.edu.pw.ee;

import java.util.Random;

import org.junit.Test;

public class SsTestWydajnosci {
    private double[] nums;
    private int n;

    @Test
    public void test() {
        SelectionSort ss = new SelectionSort();
        n = 1024;
        nums = new double[n];
        optymistyczny();
        losowy();
        long startTime = System.nanoTime();
        ss.sort(nums);
        long finishTime = System.nanoTime();
        long time = (finishTime - startTime) / 1000000;
        System.out.println(n + "  " + time);
        // QsTestWydajnosci.zapisz_do_pliku("ssLos.txt",n,time);
    }

    private void losowy() {
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            nums[i] = r.nextDouble();
        }
    }

    public void optymistyczny() {
        for (int i = 0; i < n; i++)
            nums[i] = i;
    }
}
