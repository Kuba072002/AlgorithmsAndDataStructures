package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Random;

import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashLinearProbingTest {
    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        HashTable<Double> hash = new HashLinearProbing<>(initialSize);

        // then
        assert false;
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        HashTable<String> emptyHash = new HashLinearProbing<>();
        String newEleme = "nothing special";

        // when
        int nOfElemsBeforePut = getNumOfElems(emptyHash);
        emptyHash.put(newEleme);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    public static int getNumOfElems(HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        try {
            System.out.println(hash.getClass().getSuperclass().getName());
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldCorrectlyResizeArray() {
        // given
        HashLinearProbing<Integer> emptyHash = new HashLinearProbing<>(10);

        // when
        int nOfElemsBeforePut = emptyHash.getSize();
        ;
        for (int j = 0; j < 9; j++)
            emptyHash.put(j);
        int nOfElemsAfterPut = emptyHash.getSize();

        // then
        assertEquals(10, nOfElemsBeforePut);
        assertEquals(20, nOfElemsAfterPut);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_forPutArgumentNull() {
        // given
        HashLinearProbing<Double> hash = new HashLinearProbing<>();

        // when
        hash.put(null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_forGetArgumentNull() {
        // given
        HashLinearProbing<Double> hash = new HashLinearProbing<>();

        // when
        hash.get(null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_forDeleteArgumentNull() {
        // given
        HashLinearProbing<Double> hash = new HashLinearProbing<>();

        // when
        hash.delete(null);

        // then
        assert false;
    }

    @Test
    public void testOfGettingAndDeleting() {
        HashLinearProbing<String> hash = new HashLinearProbing<>(30);
        String s = "string";
        for (int i = 0; i < 20; i++) {
            hash.put(s + i);
        }
        for (int i = 0; i < 20; i++) {
            assertEquals(s + i, hash.get(s + i));
        }
        assertEquals(20, getNumOfElems(hash));
        hash.delete(s + 5);
        assertEquals(19, getNumOfElems(hash));
        hash.put(s + 5);
        assertEquals(20, getNumOfElems(hash));
    }

    @Test
    public void testOfworking() {
        HashLinearProbing<Integer> hash = new HashLinearProbing<>(750);
        Random r = new Random();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            Integer num = r.nextInt();
            set.add(num);
            hash.put(num);
        }
        assertEquals(set.size(), getNumOfElems(hash));
        for (Integer i : set)
            hash.get(i);
        for (Integer i : set)
            hash.delete(i);
        assertEquals(0, getNumOfElems(hash));
        for (Integer i : set)
            hash.put(i);
        assertEquals(set.size(), getNumOfElems(hash));
        if (set.size() > 563)
            assertEquals(1500, hash.getSize());
        else {
            assertEquals(750, hash.getSize());
        }
    }

}
