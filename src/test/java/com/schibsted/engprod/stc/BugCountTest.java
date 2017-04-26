package com.schibsted.engprod.stc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unclear cause and effect test
 */
public class BugCountTest {

    private final BugCount bugCount = new BugCount();
    private final String app1 = "TypicalApp";
    private final String app2 = "AnotherApp";

    @Before
    public void setUp() {
    }

    @Test
    public void initializedToZero() {
        assertEquals(0, bugCount.getCount(app1));
    }

    @Test
    public void addZero() {
        bugCount.add(app1, 0);
        assertEquals(0, bugCount.getCount(app1));
    }

    @Test
    public void addOneValue() {
        bugCount.add(app1, 1);
        assertEquals(1, bugCount.getCount(app1));
    }

    @Test
    public void addMultipleValues() {
        bugCount.add(app1, 1);
        bugCount.add(app1, 0);
        bugCount.add(app1, 5);
        assertEquals(6, bugCount.getCount(app1));
    }

    @Test
    public void countsBetweenAppsAreIndependent() {
        bugCount.add(app1, 1);
        bugCount.add(app2, 1);
        bugCount.add(app1, 0);
        bugCount.add(app1, 5);
        assertEquals(6, bugCount.getCount(app1));
        assertEquals(1, bugCount.getCount(app2));
    }
}
