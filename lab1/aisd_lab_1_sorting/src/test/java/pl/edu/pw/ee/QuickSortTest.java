package pl.edu.pw.ee;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class QuickSortTest {
    QuickSort qs = new QuickSort();
    private double[] nums;

    @Test
    public void test1() {
        try {
            qs.sort(nums);
            Assert.fail("Nie zwrocono wyjatku");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Nums array cannot be null", e.getMessage());
        }
    }

    @Test
    public void test2() {// pesymistyczny
        nums = new double[128];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        qs.sort(nums);
        Assert.assertTrue(isSorted(nums));
    }

    @Test
    public void test3() {
        nums = new double[128];
        int j = 127;
        for (int i = 0; i < 128; i++) {
            nums[i] = j;
            j--;
        }
        qs.sort(nums);
        Assert.assertTrue(isSorted(nums));
    }

    @Test
    public void test4() {
        double nums2[] = { 11, 7, 14, 13, 3, 5, 4, 2, 9, 8, 10, 1, 6 };
        qs.sort(nums2);
        Assert.assertTrue(isSorted(nums2));
    }

    @Test
    public void test5() {// losowe
        nums = new double[128];
        Random r = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextDouble();
        }
        qs.sort(nums);

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
