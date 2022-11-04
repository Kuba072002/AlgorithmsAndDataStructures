package pl.edu.pw.ee;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GraphTest {
    @Test
    public void testOfAdding(){
        Graph g = new Graph();
        g.add(new String[]{"ab","ad","2"});
        g.add(new String[]{"ab","ac","2"});
        ArrayList<Vertex> arr = g.getVertices();
        assertEquals(3,arr.size());
        assertEquals(2,arr.get(0).getEdges().size());
        assertEquals(1,arr.get(1).getEdges().size());
        assertEquals(1,arr.get(2).getEdges().size());
        assertEquals("ab_2_ad",arr.get(0).getEdges().get(0).toString());
        assertEquals("ab_2_ac",arr.get(0).getEdges().get(1).toString());
        assertEquals("ad_2_ab",arr.get(1).getEdges().get(0).toString());
        assertEquals("ac_2_ab",arr.get(2).getEdges().get(0).toString());
    }

    @Test
    public void testOfAddingSameEdges(){
        Graph g = new Graph();
        g.add(new String[]{"ab","ad","2"});
        g.add(new String[]{"ab","ad","2"});
        g.add(new String[]{"ab","ad","3"});
        g.add(new String[]{"ad","ab","4"});
        ArrayList<Vertex> arr = g.getVertices();
        assertEquals(2,arr.size());
        assertEquals(1,arr.get(0).getEdges().size());
        assertEquals(1,arr.get(1).getEdges().size());
        assertEquals("ab_2_ad",arr.get(0).getEdges().get(0).toString());
        assertEquals("ad_2_ab",arr.get(1).getEdges().get(0).toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOfAddingLoopEdge() {
        // given
        Graph g = new Graph();

        // when
        g.add(new String[]{"ab","ab","2"});

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOfAddingWrongData1() {
        // given
        Graph g = new Graph();
        
        // when
        g.add(new String[]{"ab","ad_","2"});

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOfAddingWrongData2() {
        // given
        Graph g = new Graph();
        
        // when
        g.add(new String[]{"ab","ad","a"});

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOfAddingWrongData3() {
        // given
        Graph g = new Graph();
        
        // when
        g.add(new String[]{"2","ad","2"});

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOfAddingTooLongStringArray() {
        // given
        Graph g = new Graph();
        
        // when
        g.add(new String[]{"ab","ad","2","3"});

        // then
        assert false;
    }

    @Test
    public void testOfCheckingCohesivity(){
        Graph g = new Graph();
        g.add(new String[]{"a","b","2"});
        g.checkIfGraphIsCoherent();
        g.add(new String[]{"a","c","2"});
        g.checkIfGraphIsCoherent();
        g.add(new String[]{"b","c","2"});
        g.checkIfGraphIsCoherent();
    }

    @Test(expected = IllegalStateException.class)
    public void testOfGivingIncoherentGraph() {
        // given
        Graph g = new Graph();
        g.add(new String[]{"ab","ad","2"});
        g.add(new String[]{"a","b","2"});
        
        // when
        g.checkIfGraphIsCoherent();

        // then
        assert false;
    }

    @Test(expected = IllegalStateException.class)
    public void tetsCheckIfGraphIsCoherentForEmptyGraph() {
        // given
        Graph g = new Graph();
        
        // when
        g.checkIfGraphIsCoherent();

        // then
        assert false;
    }
}
