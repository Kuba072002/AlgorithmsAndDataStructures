package pl.edu.pw.ee;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HeapTest {
    @Test
    public void test(){
        Heap<Integer> heap = new Heap<>();
        heap.put(7);
        heap.put(4);
        heap.put(5);
        heap.put(3);
        
        assertEquals(Integer.valueOf(3),heap.pop());
        heap.put(6);
        assertEquals(Integer.valueOf(4),heap.pop());
        heap.put(2);
        assertEquals(Integer.valueOf(2),heap.pop());
        assertEquals(Integer.valueOf(5),heap.pop());
        assertEquals(Integer.valueOf(6),heap.pop());
        assertEquals(Integer.valueOf(7),heap.pop());
        assertEquals(null,heap.pop());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOfAddingLoopEdge() {
        // given
        Heap<Integer> heap = new Heap<>();

        // when
        heap.put(null);

        // then
        assert false;
    }
}
