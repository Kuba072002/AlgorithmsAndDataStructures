package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Random;

import org.junit.Test;

public class HashDoubleHashingTest {

    @Test
    public void shouldCorrectlyResizeArray() {
        // given
        HashDoubleHashing<Integer> emptyHash = new HashDoubleHashing<>(10);

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
        HashDoubleHashing<Double> hash = new HashDoubleHashing<>();

        // when
        hash.put(null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_forGetArgumentNull() {
        // given
        HashDoubleHashing<Double> hash = new HashDoubleHashing<>();

        // when
        hash.get(null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_forDeleteArgumentNull() {
        // given
        HashDoubleHashing<Double> hash = new HashDoubleHashing<>();

        // when
        hash.delete(null);

        // then
        assert false;
    }

    @Test
    public void testOfGettingAndDeleting() {
        HashDoubleHashing<String> hash = new HashDoubleHashing<>(30);
        String s = "string";
        for (int i = 0; i < 20; i++) {
            hash.put(s + i);
        }
        for (int i = 0; i < 20; i++) {
            assertEquals(s + i, hash.get(s + i));
        }
        assertEquals(20, HashLinearProbingTest.getNumOfElems(hash));
        hash.delete(s + 5);
        assertEquals(19, HashLinearProbingTest.getNumOfElems(hash));
        hash.put(s + 5);
        assertEquals(20, HashLinearProbingTest.getNumOfElems(hash));
    }

    @Test
    public void testOfworking() {
        HashDoubleHashing<Integer> hash = new HashDoubleHashing<>(750);
        Random r = new Random();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            Integer num = r.nextInt();
            set.add(num);
            hash.put(num);
        }
        assertEquals(set.size(), HashLinearProbingTest.getNumOfElems(hash));
        for (Integer i : set)
            hash.get(i);
        for (Integer i : set)
            hash.delete(i);
        assertEquals(0, HashLinearProbingTest.getNumOfElems(hash));
        for (Integer i : set)
            hash.put(i);
        assertEquals(set.size(), HashLinearProbingTest.getNumOfElems(hash));
        if (set.size() > 563)
            assertEquals(1500, hash.getSize());
        else {
            assertEquals(750, hash.getSize());
        }
    }
}
