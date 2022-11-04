package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LongestCommonSubsequenceTest {
    @Test
    public void testWithSpecialChar() {
        LongestCommonSubsequence l = new LongestCommonSubsequence("często_z_odkrywaniem",
                "rzeczy_nie_trzeba\n_się_spieszyć");
        assertEquals("cz__raie", l.findLCS());
        l.display();
    }

    @Test
    public void testOfWorking() {
        LongestCommonSubsequence l = new LongestCommonSubsequence("lokomotywa", "kompoty");
        assertEquals("komoty", l.findLCS());
        l.display();
        System.out.println();
        l = new LongestCommonSubsequence("kompoty", "lokomotywa");
        assertEquals("komoty", l.findLCS());
        l.display();

        l = new LongestCommonSubsequence("lokomotywa", "bialalokomotywa");
        assertEquals("lokomotywa", l.findLCS());
    }

    @Test
    public void testForOneCharArguments() {
        LongestCommonSubsequence l = new LongestCommonSubsequence("l", "k");
        assertEquals("", l.findLCS());
        l.display();
        System.out.println();
        l = new LongestCommonSubsequence("l", "l");
        assertEquals("l", l.findLCS());
        l.display();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_forArgumentNull() {
        // given
        LongestCommonSubsequence l;

        // when
        l = new LongestCommonSubsequence(null, null);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_forOnlyInitializedArgument() {
        // given
        LongestCommonSubsequence l;

        // when
        l = new LongestCommonSubsequence("a", "");

        // then
        assert false;
    }

    @Test(expected = IllegalStateException.class)
    public void should_ThrowException_forDisplayTooBigGrid() {
        // given
        LongestCommonSubsequence l = new LongestCommonSubsequence(
                "trzysta_czterdziesci_piec_plus_dwiescie_dwa_rowna_sie...", "547");
        l.findLCS();

        // when
        l.display();

        // then
        assert false;
    }

    @Test(expected = IllegalStateException.class)
    public void should_ThrowException_forDisplayBeforeFindLCS() {
        // given
        LongestCommonSubsequence l = new LongestCommonSubsequence("345+202=...", "547");

        // when
        l.display();

        // then
        assert false;
    }
}
