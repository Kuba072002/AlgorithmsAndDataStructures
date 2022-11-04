package pl.edu.pw.ee;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class InsertionSortTest {
    InsertionSort ins = new InsertionSort();
    private double[] nums;
    IsSorted is = new IsSorted();

    private class IsSorted {
        public boolean isSorted(double[] a) {
            for (int i = a.length - 1; i > 0; i--) {
                if (a[i] < a[i - 1]) {
                    return false;
                }
            }
            return true;
        }
    }

    @Test
    public void test1() {
        try {
            ins.sort(nums);
            Assert.fail("Nie zwrocono wyjatku");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Nums array cannot be null", e.getMessage());
        }
    }

    @Test
    public void test2() {// Optimistic
        nums = new double[13];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        ins.sort(nums);
        Assert.assertArrayEquals(new double[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }, nums, 0);
    }

    @Test
    public void test3() {// Pessimistic
        nums = new double[13];
        int j = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = j;
            j--;
        }
        ins.sort(nums);
        Assert.assertArrayEquals(new double[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }, nums, 0);
    }

    @Test
    public void test4() {
        double nums2[] = { 11, 7, 14, 13, 3, 5, 4, 2, 9, 8, 10, 1, 6, 12 };
        ins.sort(nums2);
        Assert.assertArrayEquals(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 }, nums2, 0);
    }

    @Test
    public void test5() {
        nums = new double[42];
        Random r = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextDouble();
        }
        ins.sort(nums);

        Assert.assertTrue(is.isSorted(nums));
    }
}