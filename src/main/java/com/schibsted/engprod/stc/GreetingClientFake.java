package com.schibsted.engprod.stc;

import java.io.IOException;

/**
 * Fake class for:
 * @see GreetingClient
 */
public class GreetingClientFake {
    // Add your magic.
    public Greeting getGreeting(String name) throws IOException {
      return new Greeting(1, "Dr.Falken");
    }
}
