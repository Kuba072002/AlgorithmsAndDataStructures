package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pl.edu.pw.ee.services.MinSpanningTree;

public class KruskalAlgorithmTest {

    @Test
    public void testOfworking() {
        KruskalAlgorithm k = new KruskalAlgorithm();
        assertEquals("H_4_D|C_6_H|A_7_E|E_7_B|A_8_C|A_8_F|E_9_G", k.findMST("plik.txt"));
    }

    @Test
    public void testOfGivingFileWithOneEdge() {
        MinSpanningTree m = new KruskalAlgorithm();
        assertEquals("A_7_E", m.findMST("oneEdge.txt"));
    }

    @Test
    public void testOfGivingFileWithTwoEdges() {
        MinSpanningTree m = new KruskalAlgorithm();
        assertEquals("A_7_E|E_8_B", m.findMST("twoEdges.txt"));
    }
}
