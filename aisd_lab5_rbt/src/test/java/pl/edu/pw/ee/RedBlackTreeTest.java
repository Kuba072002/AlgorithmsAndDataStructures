package pl.edu.pw.ee;

import org.junit.Test;
import org.junit.Assert;

public class RedBlackTreeTest {

    @Test
    public void testOfPutAndGet() {
        RedBlackTree<Double, String> root = new RedBlackTree<>();
        root.put(10., "value1");
        root.put(20., "value2");
        Assert.assertEquals("value1", root.get(10.));
        Assert.assertEquals("value2", root.get(20.));
    }

    @Test
    public void testOfGetSthWhatIsNotInRBT() {
        RedBlackTree<Character, String> rbt = new RedBlackTree<>();
        Assert.assertEquals(null, rbt.get('C'));
        char c = 'A';
        rbt.put((char) (c + 3), "1");
        rbt.put((char) (c + 7), "2");
        rbt.put((char) (c + 2), "3");
        Assert.assertEquals(null, rbt.get('z'));
    }

    @Test
    public void testOfGeters() {
        RedBlackTree<Character, Integer> rbt = new RedBlackTree<>();
        Assert.assertEquals("Rbt is empty", rbt.getPreOrder());
        Assert.assertEquals("Rbt is empty", rbt.getPostOrder());
        Assert.assertEquals("Rbt is empty", rbt.getInOrder());
        char c = 'A';
        rbt.put((char) (c + 3), 1);
        rbt.put((char) (c + 7), 2);
        rbt.put((char) (c + 2), 3);
        rbt.put((char) (c + 4), 4);
        rbt.put((char) (c + 6), 5);
        Assert.assertEquals(" G:5 C:3 E:4 D:1 H:2", rbt.getPreOrder());
        Assert.assertEquals(" C:3 E:4 D:1 H:2 G:5", rbt.getPostOrder());
        Assert.assertEquals(" C:3 D:1 E:4 G:5 H:2", rbt.getInOrder());
    }

    @Test
    public void testOfDeleteMax() {
        RedBlackTree<Double, Integer> rbt = new RedBlackTree<>();
        rbt.put(5., 3);
        rbt.put(8., 5);
        rbt.put(10., 6);
        rbt.put(12., 7);
        rbt.put(15., 8);
        rbt.put(1., 1);
        rbt.put(7., 4);
        rbt.put(4., 2);
        Assert.assertEquals(" 1.0:1 4.0:2 5.0:3 7.0:4 8.0:5 10.0:6 12.0:7 15.0:8", rbt.getInOrder());
        rbt.deleteMax();
        Assert.assertEquals(" 1.0:1 4.0:2 5.0:3 7.0:4 8.0:5 10.0:6 12.0:7", rbt.getInOrder());
        rbt.deleteMax();
        Assert.assertEquals(" 1.0:1 4.0:2 5.0:3 7.0:4 8.0:5 10.0:6", rbt.getInOrder());
        rbt.deleteMax();
        Assert.assertEquals(" 1.0:1 4.0:2 5.0:3 7.0:4 8.0:5", rbt.getInOrder());
        rbt.deleteMax();
        Assert.assertEquals(" 1.0:1 4.0:2 5.0:3 7.0:4", rbt.getInOrder());
        rbt.deleteMax();
        Assert.assertEquals(" 1.0:1 4.0:2 5.0:3", rbt.getInOrder());
        rbt.deleteMax();
        Assert.assertEquals(" 1.0:1 4.0:2", rbt.getInOrder());
        rbt.deleteMax();
        Assert.assertEquals(" 1.0:1", rbt.getInOrder());
        rbt.deleteMax();
        Assert.assertEquals("Rbt is empty", rbt.getInOrder());
        try {
            rbt.deleteMax();
            Assert.fail("Nie zwrocono wyjatku");
        } catch (ArrayIndexOutOfBoundsException e) {
            Assert.assertEquals("Rbt is empty", e.getMessage());
        }
    }

    @Test
    public void testOfSetNull() {
        RedBlackTree<Character, String> rbt = new RedBlackTree<>();
        try {
            rbt.put(null, null);
            Assert.fail("Nie zwrocono wyjatku");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Input params (key, value) cannot be null.", e.getMessage());
        }
        try {
            rbt.put('A', null);
            Assert.fail("Nie zwrocono wyjatku");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Input params (key, value) cannot be null.", e.getMessage());
        }
        try {
            rbt.put(null, "value");
            Assert.fail("Nie zwrocono wyjatku");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Input params (key, value) cannot be null.", e.getMessage());
        }
    }
}
