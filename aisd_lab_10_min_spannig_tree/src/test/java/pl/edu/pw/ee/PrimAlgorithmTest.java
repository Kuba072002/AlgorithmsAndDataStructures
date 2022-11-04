package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithmTest {

    @Test
    public void testOfworking(){
        PrimAlgorithm p = new PrimAlgorithm();
        System.out.println(p.findMST("plik.txt"));
        assertEquals("A_7_E|E_7_B|A_8_C|C_6_H|H_4_D|A_8_F|E_9_G", p.findMST("plik.txt"));
    }

    @Test(expected = IllegalStateException.class)
    public void testOfGivingNotExistingFile(){
        // given
        MinSpanningTree m = new PrimAlgorithm();
        
        // when
        m.findMST("p.txt");

        // then
        assert false;
    }

    @Test
    public void testOfGivingFileWithOneEdge(){
        MinSpanningTree m = new PrimAlgorithm();
        assertEquals("A_7_E",m.findMST("oneEdge.txt"));
    }

    @Test
    public void testOfGivingFileWithTwoEdges(){
        MinSpanningTree m = new PrimAlgorithm();
        assertEquals("A_7_E|E_8_B",m.findMST("twoEdges.txt"));
    }

    @Test(expected = IllegalStateException.class)
    public void testOfGivingEmptyFile(){
        // given
        MinSpanningTree m = new KruskalAlgorithm();
        
        // when
        m.findMST("empty.txt");

        // then
        assert false;
    }
}
