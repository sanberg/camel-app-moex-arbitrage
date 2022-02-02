package io.sanberg;

import io.netty.handler.codec.http.HttpMethod;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class AlpacaConsumerUSRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        from("ahc-wss://stream.data.alpaca.markets/v2/sip?client=#myCustomAsyncHttpClientImpl")
                .setHeader("Apca-Api-Key-Id")
                .constant("PKE5PQRGSKVQD6LQRJRK")
                .setHeader("Apca-Api-Secret-Key")
                .constant("yQ2lOJH8zFU1UVH4crFf0CMqe7Hmq7bTUTON4qX4")
                .log("received from alpaca: + ${body}");
    }
}
