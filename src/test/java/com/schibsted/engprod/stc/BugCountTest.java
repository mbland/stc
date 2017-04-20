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
        bugCount.add(app1, 1); // Basic add
        bugCount.add(app2, 1); // Add app2 doesn't affect app1
        bugCount.add(app1, 0); // Add 0 OK
        bugCount.add(app1, 5); // Add to existing
    }

    /* What are we testing here? What are the causes and effects? How can this be made clearer? */
    @Test
    public void shouldWork() {
        assertEquals(6, bugCount.getCount(app1));
    }
}
