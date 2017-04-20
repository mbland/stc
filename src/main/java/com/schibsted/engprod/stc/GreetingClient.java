package com.schibsted.engprod.stc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Client provided to consumers of the greeting service.
 * @see GreetingApplication
 */

public class GreetingClient {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        GreetingClient client = new GreetingClient();
        String name = args.length == 0 ? "" : args[0];
        System.out.println(client.getGreeting(name).getContent());
    }

    public Greeting getGreeting(String name) throws IOException {
        HttpURLConnection conn = null;
        String response = "";
        try {
            URL url = new URL("http://localhost:8080/?name=" + name);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Request failed: HTTP " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                response += output;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return MAPPER.readValue(response, Greeting.class);
    }
}
