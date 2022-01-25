package io.sanberg;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.annotations.Component;


public class WssConsumerMOEXRouteBuilder extends RouteBuilder {
    String ticker = "BIDU-RM";
    String token;


    @Override
    public void configure() throws Exception {
        //subscribe
//        from("timer:foo?period=200000000")//for ping
//                .setBody(simple("{\n" +
//                        "    \"opcode\": \"OrderBookGetAndSubscribe\",\n" +
//                        "    \"code\": \""+ ticker + "\",\n" +
//                        "    \"exchange\": \"MOEX\",\n" +
//                        "    \"depth\": 10,\n" +
//                        "    \"format\": \"Simple\",\n" +
//                        "    \"guid\": \""+ ticker + "\",\n" +
//                        "    \"token\": \"eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJQMDczODAyIiwiZW50IjoiY2xpZW50IiwiZWluIjoiNTk0MDciLCJjbGllbnRpZCI6IjEzOTc3NyIsImF6cCI6IjkwZGVmNGM1MzBkNTRmNjI5ODFiIiwiYWdyZWVtZW50cyI6IjczODAyIDc1MjM1IiwicG9ydGZvbGlvcyI6Ijc1MDIwQUcgNzUwMjFVVSBENzM4MDIgRDc1MjM1IEc0MDQ1MSBHNDE4ODciLCJzY29wZSI6Ik9yZGVyc1JlYWQgT3JkZXJzQ3JlYXRlIFRyYWRlcyBQZXJzb25hbCBTdGF0cyIsImV4cCI6MTY0MzEwNjYwMiwiaWF0IjoxNjQzMTA0ODAyLCJpc3MiOiJBbG9yLklkZW50aXR5IiwiYXVkIjoiQ2xpZW50IFdBUlAgV2FycEFUQ29ubmVjdG9yIHN1YnNjcmlwdGlvbnNBcGkgQ29tbWFuZEFwaSJ9.EpBEoydDm65SiuyDTDILQ6T6vBEp-EjZeNgRGs5hr3Zs4LNbWIBYeqW8zpWHBMsT5kJ38ZV3TI7MSdg_l5TBlw\"\n" +
//                        "}"))
//                .to("ahc-wss://api.alor.ru/ws");
        //getMessages
        from("ahc-wss://api.alor.ru/ws")
                .log("log:${body}");
        //.to("log");
    }
}
