package io.sanberg;

import org.apache.camel.builder.RouteBuilder;

public class WssConsumerMOEXRouteBuilder extends RouteBuilder {
    String ticker;
    String token;
    public WssConsumerMOEXRouteBuilder(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public void configure() throws Exception {

        TokenManager tokenManager = new TokenManager();
        //subscribe
        from("timer:foo?period=200000000")//for ping
                .setBody(simple("{\n" +
                        "    \"opcode\": \"OrderBookGetAndSubscribe\",\n" +
                        "    \"code\": \""+ ticker + "\",\n" +
                        "    \"exchange\": \"MOEX\",\n" +
                        "    \"depth\": 10,\n" +
                        "    \"format\": \"Simple\",\n" +
                        "    \"guid\": \"f35a2373-612c-4518-54af-72025384f59b\",\n" +
                        "    \"token\": \"eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJQMDczODAyIiwiZW50IjoiY2xpZW50IiwiZWluIjoiNTk0MDciLCJjbGllbnRpZCI6IjEzOTc3NyIsImF6cCI6IjkwZGVmNGM1MzBkNTRmNjI5ODFiIiwiYWdyZWVtZW50cyI6IjczODAyIDc1MjM1IiwicG9ydGZvbGlvcyI6Ijc1MDIwQUcgNzUwMjFVVSBENzM4MDIgRDc1MjM1IEc0MDQ1MSBHNDE4ODciLCJzY29wZSI6Ik9yZGVyc1JlYWQgT3JkZXJzQ3JlYXRlIFRyYWRlcyBQZXJzb25hbCBTdGF0cyIsImV4cCI6MTYzNzMwNzI5NiwiaWF0IjoxNjM3MzA1NDk2LCJpc3MiOiJBbG9yLklkZW50aXR5IiwiYXVkIjoiQ2xpZW50IFdBUlAgV2FycEFUQ29ubmVjdG9yIHN1YnNjcmlwdGlvbnNBcGkgQ29tbWFuZEFwaSJ9.a2f7l3BZ4t5817LK1OAOo5kjvpI-zVbJbQRYm2lNkn3vTVMA_2BW6NxDogYt9L9RMmabQFOnuxSecHgg0nrGkg\"\n" +
                        "}"))
                .to("ahc-wss://api.alor.ru/ws")
                .choice()
                .when().jsonpath("$.httpCode");
        //getMessages
        from("ahc-wss://api.alor.ru/ws")
                .log("log:${body}");
        //.to("log");
    }
}
