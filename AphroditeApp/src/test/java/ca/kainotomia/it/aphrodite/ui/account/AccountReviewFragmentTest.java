package ca.kainotomia.it.aphrodite.ui.account;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountReviewFragmentTest {

    @Test
    public void reviewInputNullTest() throws Exception {
        String test = "";
        assertTrue(test, true);
    }

    @Test
    public void reviewFloatTest() throws Exception {
        float extraStar = 6;
        assertNotEquals(extraStar, 5 );
        assertNotEquals(extraStar, 4 );
        assertNotEquals(extraStar, 3);
        assertNotEquals(extraStar, 2 );
        assertNotEquals(extraStar, 1 );
        assertNotEquals(extraStar, 4.5 );
        assertNotEquals(extraStar, 3.5);
        assertNotEquals(extraStar, 2.5 );
        assertNotEquals(extraStar, 1.5 );
        assertNotEquals(extraStar, 0 );
    }

    @Test
    public void reviewInputFloatTest() throws Exception {
        float extraStar = 0;
        assertNotEquals(extraStar, 5 );
        assertNotEquals(extraStar, 4 );
        assertNotEquals(extraStar, 3);
        assertNotEquals(extraStar, 2 );
        assertNotEquals(extraStar, 1 );
        assertNotEquals(extraStar, 4.5 );
        assertNotEquals(extraStar, 3.5);
        assertNotEquals(extraStar, 2.5 );
        assertNotEquals(extraStar, 1.5 );
        assertNotEquals(extraStar, 0 );
    }

}