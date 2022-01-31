package io.sanberg;

import org.apache.camel.builder.RouteBuilder;

public class AlpacaSubscriptionByTickerRouteBuilder extends RouteBuilder {
    String[] tickers = {"BIDU", "AAPL"};

    @Override
    public void configure() {
        //    subscribe to specified ticker
        for (String ticker : tickers) {
            from("timer:subscribe_" + ticker + "?delay=2000&repeatCount=1")//for ping
                    .setBody(simple("{\n" +
                            "    \"action\": \"subscribe\",\n" +
                            "    \"bars\": [],\n" +
                            "    \"quotes\": [\n" +
                            "        \"" + ticker + "\"\n" +
                            "    ],\n" +
                            "    \"trades\": [\n" +
                            "        \"" + ticker + "\"\n" +
                            "    ]\n" +
                            "}"))
                    .setHeader("Apca-Api-Key-Id", simple("PKE5PQRGSKVQD6LQRJRK"))
                    .setHeader("Apca-Api-Secret-Key", simple("yQ2lOJH8zFU1UVH4crFf0CMqe7Hmq7bTUTON4qX4"))
                    .to("ahc-wss://stream.data.alpaca.markets/v2/sip");
        }
    }
}
