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

        AlorTokenManager tokenManager = new AlorTokenManager();
        //subscribe
        System.out.println("connect & send message");
        from("timer:foo?period=200000")//for ping
                .setBody(simple("{\n" +
                        "    \"opcode\": \"OrderBookGetAndSubscribe\",\n" +
                        "    \"code\": \""+ ticker + "\",\n" +
                        "    \"exchange\": \"MOEX\",\n" +
                        "    \"depth\": 10,\n" +
                        "    \"format\": \"Simple\",\n" +
                        "    \"guid\": \"f35a2373-612c-4518-54af-72025384f59b\",\n" +
                        "    \"token\": \"" + tokenManager.getToken() + "\n" +
                        "}"))
                .to("ahc-wss://api.alor.ru/ws");
        //getMessages
        System.out.println("listening");
        from("ahc-wss://api.alor.ru/ws")
                .log("log:${body}");
        //.to("log");
    }
}
