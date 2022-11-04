package pl.edu.pw.ee;

import java.util.Random;

import org.junit.Test;

public class IsTestWydajnosci {
    private double[] nums;
    private int n;

    @Test
    public void test() {
        InsertionSort is = new InsertionSort();
        n = 350000;
        nums = new double[n];
        optymistyczny();// posortowany
        pesymistyczny();
        losowy();
        long startTime = System.nanoTime();
        is.sort(nums);
        long finishTime = System.nanoTime();
        long time = (finishTime - startTime) / 1000000;
        System.out.println(n + "  " + time);
        // QsTestWydajnosci.zapisz_do_pliku("isOpt.txt",n,time);
    }

    private void optymistyczny() {
        for (int i = 0; i < n; i++)
            nums[i] = i + 1;
    }

    private void pesymistyczny() {
        int j = n;
        for (int i = 0; i < n; i++) {
            nums[i] = j--;
        }
    }

    private void losowy() {
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            nums[i] = r.nextDouble();
        }
    }
}
