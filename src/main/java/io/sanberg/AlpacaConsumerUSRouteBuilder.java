package io.sanberg;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class AlpacaConsumerUSRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        from("ahc-wss://stream.data.alpaca.markets/v2/sip")
                .setHeader("Apca-Api-Key-Id", simple("PKE5PQRGSKVQD6LQRJRK"))
                .setHeader("Apca-Api-Secret-Key", simple("yQ2lOJH8zFU1UVH4crFf0CMqe7Hmq7bTUTON4qX4"))
                .log("received from alpaca: + ${body}");
    }
}
