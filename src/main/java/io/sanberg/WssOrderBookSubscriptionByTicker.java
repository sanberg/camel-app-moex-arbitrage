package io.sanberg;

import org.apache.camel.builder.RouteBuilder;

public class WssOrderBookSubscriptionByTicker extends RouteBuilder {
    String[] tickers = {"BIDU-RM", "AAPL-RM"};

    @Override
    public void configure() {
        //    subscribe to specified ticker
        for (String ticker : tickers) {
            from("timer:subscribe_" + ticker + "?period=200000000")//for ping
                    .setBody(simple("{\n" +
                            "    \"opcode\": \"OrderBookGetAndSubscribe\",\n" +
                            "    \"code\": \"" + ticker + "\",\n" +
                            "    \"exchange\": \"MOEX\",\n" +
                            "    \"depth\": 10,\n" +
                            "    \"format\": \"Simple\",\n" +
                            "    \"guid\":\"" + ticker + "\",\n" +
                            "    \"token\": \"eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJQMDczODAyIiwiZW50IjoiY2xpZW50IiwiZWluIjoiNTk0MDciLCJjbGllbnRpZCI6IjEzOTc3NyIsImF6cCI6IjkwZGVmNGM1MzBkNTRmNjI5ODFiIiwiYWdyZWVtZW50cyI6IjczODAyIDc1MjM1IiwicG9ydGZvbGlvcyI6Ijc1MDIwQUcgNzUwMjFVVSBENzM4MDIgRDc1MjM1IEc0MDQ1MSBHNDE4ODciLCJzY29wZSI6Ik9yZGVyc1JlYWQgT3JkZXJzQ3JlYXRlIFRyYWRlcyBQZXJzb25hbCBTdGF0cyIsImV4cCI6MTY0MzExNjI5NSwiaWF0IjoxNjQzMTE0NDk1LCJpc3MiOiJBbG9yLklkZW50aXR5IiwiYXVkIjoiQ2xpZW50IFdBUlAgV2FycEFUQ29ubmVjdG9yIHN1YnNjcmlwdGlvbnNBcGkgQ29tbWFuZEFwaSJ9.SVfK7mZbR7ZlUImjnoL6VGkuGxuTwWNKIY-PpHZz2OtHCoVyCfZXc9k_-ctOsYvZohR7oX5O2ROFhGR0Jb7HmQ\"\n" +
                            "}"))
                    .to("ahc-wss://api.alor.ru/ws");
        }


    }
}
