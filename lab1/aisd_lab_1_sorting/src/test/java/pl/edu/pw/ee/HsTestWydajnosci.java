package pl.edu.pw.ee;

import java.util.Random;

import org.junit.Test;

public class HsTestWydajnosci {
    private double[] nums;
    private int n;
    Random r = new Random();

    @Test
    public void test() {
        HeapSort hs = new HeapSort();
        n = 1024;
        nums = new double[n];
        optymistyczny();
        losowy();
        long startTime = System.nanoTime();
        hs.sort(nums);
        long finishTime = System.nanoTime();
        long time = (finishTime - startTime) / 1000000;
        System.out.println(n + "  " + time);
        // QsTestWydajnosci.zapisz_do_pliku("qsOpt.txt",n,time);
    }

    private void losowy() {
        for (int i = 0; i < n; i++) {
            nums[i] = r.nextDouble();
        }
    }

    public void optymistyczny() {
        double d = r.nextDouble();
        for (int i = 0; i < n; i++)
            nums[i] = d;
    }
}
