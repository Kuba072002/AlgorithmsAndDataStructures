package pl.edu.pw.ee;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class DeterministicFiniteAutomatonTextSearchTest {

    @Test
    public void test_for_MultipleNumberofPatternsInText() {
        DeterministicFiniteAutomatonTextSearch d = new DeterministicFiniteAutomatonTextSearch("aaa");
        assertArrayEquals(new int[] { 0, 1, 2, 3, 4 }, d.findAll("aaaaaaa"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_for_GivingNullPattern() {
        // given
        DeterministicFiniteAutomatonTextSearch d;

        // when
        d = new DeterministicFiniteAutomatonTextSearch(null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_for_GivingNullText_findFirst() {
        // given
        DeterministicFiniteAutomatonTextSearch d = new DeterministicFiniteAutomatonTextSearch("AV");

        // when
        d.findFirst(null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_for_GivingNullText_findAll() {
        // given
        DeterministicFiniteAutomatonTextSearch d = new DeterministicFiniteAutomatonTextSearch("AV");

        // when
        d.findAll(null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_for_PatternLengthEquals0() {
        // given
        DeterministicFiniteAutomatonTextSearch d;

        // when
        d = new DeterministicFiniteAutomatonTextSearch("");

        // then
        assert false;
    }

    @Test
    public void test_for_SpeciialCharacters() {
        DeterministicFiniteAutomatonTextSearch d = new DeterministicFiniteAutomatonTextSearch("\"Hello!\"");
        assertArrayEquals(new int[] { 0, 39 }, d.findAll("\"Hello!\" She said to me,so I answered  \"Hello!\""));
    }

    @Test
    public void test_for_LongerPatternThenText() {
        DeterministicFiniteAutomatonTextSearch d = new DeterministicFiniteAutomatonTextSearch("textText");
        assertEquals(-1, d.findFirst("text"));
        assertArrayEquals(new int[] {}, d.findAll("text"));
    }

    @Test
    public void test_for_FindFirst() {
        DeterministicFiniteAutomatonTextSearch d = new DeterministicFiniteAutomatonTextSearch("Town");
        assertEquals(62, d.findFirst("Georgetown-Guiana,Georgetown-Texas,Georgetown-Kentucky,George Town-caimans"));
    }

    @Test
    public void test_for_LackOfPatternsInText() {
        DeterministicFiniteAutomatonTextSearch d = new DeterministicFiniteAutomatonTextSearch("Hello!");
        assertArrayEquals(new int[] {}, d.findAll("Hello Tom,Hello Mr.Hanks"));
        d = new DeterministicFiniteAutomatonTextSearch("a");
        assertEquals(-1, d.findFirst(""));
    }

    @Test
    public void test_for_OneCharPattern() {
        DeterministicFiniteAutomatonTextSearch d = new DeterministicFiniteAutomatonTextSearch(" ");
        assertArrayEquals(new int[] { 5, 15 }, d.findAll("Hello Tom,Hello Mr.Hanks"));
    }
}
