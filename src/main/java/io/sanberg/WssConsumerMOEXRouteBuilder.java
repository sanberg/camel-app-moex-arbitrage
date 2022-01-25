package io.sanberg;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.annotations.Component;


public class WssConsumerMOEXRouteBuilder extends RouteBuilder {
    String ticker = "BIDU-RM";
    String token;


    @Override
    public void configure() {
        from("ahc-wss://api.alor.ru/ws")
                .log("received from socket: ${body}");
    }
}
