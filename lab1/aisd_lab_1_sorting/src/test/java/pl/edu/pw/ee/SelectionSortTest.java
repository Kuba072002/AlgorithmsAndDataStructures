package pl.edu.pw.ee;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

//Nie wyrozniamy przypadku optymistycznego i pesymistycznego
public class SelectionSortTest {
    SelectionSort ss = new SelectionSort();
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
            ss.sort(nums);
            Assert.fail("Nie zwrocono wyjatku");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Nums array cannot be null", e.getMessage());
        }
    }

    @Test
    public void test2() {
        nums = new double[12];
        for (int i = 0; i < 12; i++) {
            nums[i] = i;
        }
        ss.sort(nums);
        Assert.assertTrue(is.isSorted(nums));
    }

    @Test
    public void test3() {
        nums = new double[12];
        int j = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = j;
            j--;
        }
        ss.sort(nums);
        Assert.assertTrue(is.isSorted(nums));
    }

    @Test
    public void test4() {
        double nums2[] = { 11, 7, 14, 13, 3, 5, 4, 2, 9, 8, 10, 1, 6 };
        ss.sort(nums2);
        Assert.assertTrue(is.isSorted(nums2));
    }

    @Test
    public void test5() {
        nums = new double[40];
        Random r = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextDouble();
        }
        ss.sort(nums);

        Assert.assertTrue(is.isSorted(nums));
    }
}
