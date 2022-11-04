package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class HeapSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        if (nums.length == 1) {
            return;
        }

        int n = nums.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            Heap.heapify(nums, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            Heap.swap(nums, 0, i);
            Heap.heapify(nums, i, 0);
        }
    }
}
