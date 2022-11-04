package pl.edu.pw.ee;

import org.junit.Test;
import org.junit.Assert;

public class RbtMapTest {

    @Test
    public void testOfSetAndGet() {
        RbtMap<Character, String> rbt = new RbtMap<>();
        char c = 'A';
        rbt.setValue((char) (c + 3), "1");
        rbt.setValue((char) (c + 7), "2");
        rbt.setValue((char) (c + 2), "3");
        rbt.setValue((char) (c + 4), "4");
        rbt.setValue((char) (c + 6), "5");

        Assert.assertEquals("3", rbt.getValue('C'));
        Assert.assertEquals("1", rbt.getValue('D'));
        Assert.assertEquals("4", rbt.getValue('E'));
        Assert.assertEquals("5", rbt.getValue('G'));
        Assert.assertEquals("2", rbt.getValue('H'));
    }

    @Test
    public void testOfSetNull() {
        RbtMap<Character, String> rbt = new RbtMap<>();
        try {
            rbt.setValue(null, null);
            Assert.fail("Nie zwrocono wyjatku");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Params (key, value) cannot be null.", e.getMessage());
        }
        try {
            rbt.setValue('A', null);
            Assert.fail("Nie zwrocono wyjatku");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Params (key, value) cannot be null.", e.getMessage());
        }
        try {
            rbt.setValue(null, "value");
            Assert.fail("Nie zwrocono wyjatku");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Params (key, value) cannot be null.", e.getMessage());
        }
    }

    @Test
    public void testOfGetSthWhatIsNotInRBT() {
        RbtMap<Character, String> rbt = new RbtMap<>();
        Assert.assertEquals(null, rbt.getValue('C'));
        char c = 'A';
        rbt.setValue((char) (c + 3), "1");
        rbt.setValue((char) (c + 7), "2");
        rbt.setValue((char) (c + 2), "3");
        Assert.assertEquals(null, rbt.getValue('z'));
    }
}
