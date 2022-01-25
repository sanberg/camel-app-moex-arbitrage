package io.sanberg;

import org.apache.camel.builder.RouteBuilder;


public class WssConsumerMOEXRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        from("ahc-wss://api.alor.ru/ws")
                .choice()
                    .when(body().startsWith("{\"data\":"))
                        //TODO unmarshalling and updating the hashmap
                        .log("received from socket: ${body}")
                    .otherwise()
                        .log("${body}");
    }
}
