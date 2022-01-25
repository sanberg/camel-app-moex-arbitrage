package io.sanberg;

import org.apache.camel.builder.RouteBuilder;


public class WssConsumerMOEXRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        from("ahc-wss://api.alor.ru/ws")
                .log("received from socket: ${body}");
    }
}
