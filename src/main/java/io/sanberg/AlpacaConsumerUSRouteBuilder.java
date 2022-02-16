package io.sanberg;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.ListJacksonDataFormat;

public class AlpacaConsumerUSRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        from("ahc-wss://stream.data.alpaca.markets/v2/sip?client=#myCustomAsyncHttpClientImpl")
                .setHeader("Apca-Api-Key-Id")
                .constant("PKE5PQRGSKVQD6LQRJRK")
                .setHeader("Apca-Api-Secret-Key")
                .constant("yQ2lOJH8zFU1UVH4crFf0CMqe7Hmq7bTUTON4qX4")
                //.log("received from alpaca: + ${body}")
                .choice()
                    .when(body().startsWith("[{\"T\":\"t\""))
                        .unmarshal((new ListJacksonDataFormat(AlpacaStreamingData.class)))
                        //.log("unmarshalled trade: ${body}")
                        .to("bean:stocksDataMap?method=putAlpacaStreamingData(${body})")
                    .when(body().startsWith("[{\"T\":\"q\""))
                        .unmarshal((new ListJacksonDataFormat(AlpacaStreamingData.class)))
                        //.log("unmarshalled quote: ${body}")
                        .to("bean:stocksDataMap?method=putAlpacaStreamingData(${body})")
                .otherwise()
                    .log("Received from alpaca: ${body}")
                .endChoice();
    }
}
