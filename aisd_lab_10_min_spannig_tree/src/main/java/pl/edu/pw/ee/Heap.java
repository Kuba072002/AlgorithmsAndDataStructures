package pl.edu.pw.ee;

import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {
    private final ArrayList<T> arr;
    private int n;

    public Heap() {
        arr = new ArrayList<>();
        n = 0;
    }

    public void put(T item) {
        if (item == null)
            throw new IllegalArgumentException("Item cannot be null");
        arr.add(item);
        n++;
        heapUp(n - 1);
    }

    private void heapUp(int i) {
        int p = (i - 1) / 2;
        if (i > 0 && arr.get(p).compareTo(arr.get(i)) > 0) {
            swap(p, i);
            heapUp(p);
        } else {
            return;
        }
    }

    public T pop() {
        if (n == 0) {
            return null;
        }
        T item = arr.get(0);
        swap(0, n - 1);
        arr.remove(n - 1);
        --n;
        heapDown(0);
        return item;
    }

    private void heapDown(int i) {
        int c = 2 * i + 1;
        if (c + 1 < n && arr.get(c + 1).compareTo(arr.get(c)) < 0) {
            c += 1;
        }
        if (c < n && arr.get(i).compareTo(arr.get(c)) > 0) {
            swap(i, c);
            heapDown(c);
        } else {
            return;
        }
    }

    private void swap(int firstIndex, int secondIndex) {
        T item = arr.get(firstIndex);
        arr.set(firstIndex, arr.get(secondIndex));
        arr.set(secondIndex, item);
    }

    public void printHeap() {
        System.out.println("Heap:");
        for (int i = 0; i < n; i++)
            System.out.println(arr.get(i));
    }

}
