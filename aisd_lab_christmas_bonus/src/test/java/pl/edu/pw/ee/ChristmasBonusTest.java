package pl.edu.pw.ee;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ChristmasBonusTest {
    @Test
    public void testOfWorking() {
        ChristmasBonus cb = new ChristmasBonus();
        int[] arr1 = { 2, 2, 4, 5, 4, 4, 5 };
        int c1 = 2;
        int[] arr2 = { 5, 1, 1, 5, 3, 3, 5 };
        int c2 = 0;
        int[] arr3 = { 3, 5, 5, 5, 2 };
        int c3 = 2;
        int[] arr4 = { 4, 2, 2, 2, 0, 2, 1, 1, 2, 5, 2, 0, 3, 1, 5, 0, 0, 1, 6, 2 };
        int c4 = 4;
        int[] arr5 = { 3, 0, 2, 6, 5, 5, 1, 1, 4, 4, 4, 2, 2, 4, 0, 3, 4, 3, 0, 4, 2, 2, 3, 0, 0, 4, 7, 4, 1, 2, 2, 6,
                0, 5, 5, 7, 4, 7, 0, 2 };
        int c5 = 7;
        int[] arr6 = { 2, 4, 2, 6, 2, 3, 1, 3, 1, 3, 3, 3, 1, 3, 3 };
        int c6 = 3;
        int[] arr7 = { 2, 3, 2, 3, 2 };
        int c7 = 1;

        assertEquals(5, cb.findMaxLength(arr1, c1));
        assertEquals(2, cb.findMaxLength(arr2, c2));
        assertEquals(5, cb.findMaxLength(arr3, c3));
        assertEquals(10, cb.findMaxLength(arr4, c4));
        assertEquals(13, cb.findMaxLength(arr5, c5));
        assertEquals(10, cb.findMaxLength(arr6, c6));
        assertEquals(3, cb.findMaxLength(arr7, c7));
    }

    @Test
    public void testForBiggerNumberOfChangesThanArrayLength() {
        ChristmasBonus cb = new ChristmasBonus();
        int[] arr = { 3, 2, 4, 5, 6, 7, 3, 2, 1, 5, 6, 2 };
        int c = arr.length + 2;
        assertEquals(arr.length, cb.findMaxLength(arr, c));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_forTooBigArray() {
        // given
        ChristmasBonus cb = new ChristmasBonus();

        // when
        cb.findMaxLength(new int[100000], 2);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_forTooBigNumberOfChanges() {
        // given
        ChristmasBonus cb = new ChristmasBonus();

        // when
        cb.findMaxLength(new int[10], 100000);

        // then
        assert false;
    }
}
