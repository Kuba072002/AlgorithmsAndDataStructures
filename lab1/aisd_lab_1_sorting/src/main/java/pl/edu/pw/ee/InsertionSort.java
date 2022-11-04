package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        if (nums.length == 1) {
            return;
        }

        int n = nums.length;
        int j;
        for (int i = 1; i < n; i++) {
            j = i - 1;
            while (j >= 0 && nums[j] > nums[j + 1]) {
                double tmp = nums[j];
                nums[j] = nums[j + 1];
                nums[j + 1] = tmp;
                j--;
            }
        }
    }

}
