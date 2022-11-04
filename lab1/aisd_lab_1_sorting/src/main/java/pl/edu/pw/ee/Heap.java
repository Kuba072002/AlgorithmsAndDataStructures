package pl.edu.pw.ee;

import java.util.ArrayList;

import pl.edu.pw.ee.services.HeapInterface;

public class Heap<T extends Comparable<T>> implements HeapInterface<T> {
    private final ArrayList<T> arr;
    private int n;

    public Heap() {
        arr = new ArrayList<>();
        n = 0;
    }

    @Override
    public void put(T item) {
        arr.add(item);
        n++;
        heapUp(n - 1);
    }

    private void heapUp(int i) {
        int p = (i - 1) / 2;
        if (i > 0 && arr.get(p).compareTo(arr.get(i)) < 0) {
            swap(p, i);
            heapUp(p);
        } else {
            return;
        }
    }

    @Override
    public T pop() {
        if (n == 0) {
            throw new ArrayIndexOutOfBoundsException("Heap is empty");
        }
        T item = arr.get(0);
        swap(0, n - 1);
        n--;
        heapDown(0);
        return item;
    }

    private void heapDown(int i) {
        int c = 2 * i + 1;
        if (c + 1 < n && arr.get(c + 1).compareTo(arr.get(c)) > 0) {
            c += 1;
        }
        if (c < n && arr.get(i).compareTo(arr.get(c)) < 0) {
            swap(i, c);
            heapDown(c);
        } else {
            return;
        }
    }

    public static void heapify(double[] nums, int n, int i) {
        int c = 2 * i + 1;
        if (c < n) {
            if (c + 1 < n && nums[c + 1] > nums[c])
                c += 1;
            if (c < n && nums[i] < nums[c]) {
                swap(nums, i, c);
            }
            heapify(nums, n, c);
        }
    }

    private void swap(int firstIndex, int secondIndex) {
        T item = arr.get(firstIndex);
        arr.set(firstIndex, arr.get(secondIndex));
        arr.set(secondIndex, item);
    }

    public static void swap(double[] nums, int firstIndex, int secondIndex) {
        double temp = nums[firstIndex];
        nums[firstIndex] = nums[secondIndex];
        nums[secondIndex] = temp;
    }

}
