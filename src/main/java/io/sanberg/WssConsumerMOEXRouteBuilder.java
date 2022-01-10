package io.sanberg;

import org.apache.camel.builder.RouteBuilder;

public class WssConsumerMOEXRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {

        AlorTokenManager tokenManager = new AlorTokenManager();
        //subscribe
//        from("direct:start").log("connect & send message");
//        from("timer:foo?period=200000")//for ping
//                .setBody(simple("{\n" +
//                        "    \"opcode\": \"OrderBookGetAndSubscribe\",\n" +
//                        "    \"code\": \""+ ticker + "\",\n" +
//                        "    \"exchange\": \"MOEX\",\n" +
//                        "    \"depth\": 10,\n" +
//                        "    \"format\": \"Simple\",\n" +
//                        "    \"guid\": \"f35a2373-612c-4518-54af-72025384f59b\",\n" +
//                        "    \"token\": \"" + tokenManager.getToken() + "\n" +
//                        "}"))
//                .to("ahc-wss://api.alor.ru/ws");
        //
        //
        System.out.println("Connecting to wss");
        from("ahc-wss://api.alor.ru/ws")
                .log("log:${body}");
        //.to("log");
    }
}
