package com.schibsted.engprod.stc;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;

/**
 * @see GreetingClientFake
 */
public class GreetingClientFakeTest {

    @Test
    public void shouldReturnGreetingWithCorrectName() throws IOException {
        final String name = "Dr.Falken";
        Greeting expected = new Greeting(1, name);
        GreetingClientFake fake = new GreetingClientFake();
        //assertTrue("Create a fake to be used in this test", false);
        assertEquals(expected.getContent(), fake.getGreeting(name).getContent());
    }
}
