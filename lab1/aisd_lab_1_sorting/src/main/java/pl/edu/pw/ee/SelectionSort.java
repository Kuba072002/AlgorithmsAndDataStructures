package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        if (nums.length == 1) {
            return;
        }

        int n = nums.length;
        for (int j = 0; j < n; j++) {
            double min = nums[j];
            int place = j;
            for (int i = j; i < n; i++) {
                if (nums[i] < min) {
                    min = nums[i];
                    place = i;
                }
            }
            nums[place] = nums[j];
            nums[j] = min;
        }
    }

}
