package pl.edu.pw.ee;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class HeapSortTest {
    HeapSort hs = new HeapSort();
    Random r = new Random();

    @Test
    public void test1() {
        double[] nums = null;
        try {
            hs.sort(nums);
            Assert.fail("Nie zwrocono wyjatku");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Nums array cannot be null", e.getMessage());
        }
    }

    @Test
    public void test2() {
        double[] nums = new double[512];
        for (int i = 0; i < 512; i++) {
            nums[i] = i;
        }
        hs.sort(nums);
        Assert.assertTrue(isSorted(nums));
    }

    @Test
    public void test3() {// test optymistyczny
        double[] nums = new double[512];
        double d = r.nextDouble() * 10000;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = d;
        }
        hs.sort(nums);
        Assert.assertTrue(isSorted(nums));
    }

    @Test
    public void test5() {// test losowy
        double[] nums = new double[512];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextDouble();
        }
        hs.sort(nums);
        Assert.assertTrue(isSorted(nums));
    }

    public boolean isSorted(double[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
