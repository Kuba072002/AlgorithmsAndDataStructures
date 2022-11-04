package pl.edu.pw.ee;

import org.junit.Test;

import java.util.Random;

import org.junit.Assert;

public class HeapTest {
    Random r = new Random();

    @Test
    public void test1() {
        Heap<Integer> heap = new Heap<>();
        try {
            heap.pop();
            Assert.fail("Nie zwrocono wyjatku");
        } catch (ArrayIndexOutOfBoundsException e) {
            Assert.assertEquals("Heap is empty", e.getMessage());
        }
    }

    @Test
    public void test2() {
        Heap<Integer> heap = new Heap<>();
        Integer[] tab = new Integer[100];
        for (int i = 0; i < tab.length; i++)
            heap.put(r.nextInt(200));
        for (int i = 0; i < tab.length; i++)
            tab[i] = heap.pop();
        Assert.assertTrue(isReversSorted(tab));
    }

    @Test
    public void test3() {
        Heap<Integer> heap = new Heap<>();
        Integer[] tab = new Integer[100];
        for (int i = 0; i < tab.length; i++)
            heap.put(i);
        for (int i = 0; i < tab.length; i++)
            tab[i] = heap.pop();
        Assert.assertTrue(isReversSorted(tab));
    }

    @Test
    public void test4() {
        Heap<Integer> heap = new Heap<>();
        Integer[] tab = new Integer[130];
        for (int i = tab.length; i > 0; i--)
            heap.put(i);
        for (int i = 0; i < tab.length; i++)
            tab[i] = heap.pop();
        Assert.assertTrue(isReversSorted(tab));
        try {
            heap.pop();
            Assert.fail("Nie zwrocono wyjatku");
        } catch (ArrayIndexOutOfBoundsException e) {
            Assert.assertEquals("Heap is empty", e.getMessage());
        }
    }

    private boolean isReversSorted(Integer[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            if (a[i] > a[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
