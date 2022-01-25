package io.sanberg;

import org.apache.camel.builder.RouteBuilder;

public class WssOrderBookSubscriptionByTicker extends RouteBuilder {
    String[] tickers = {"BIDU-RM", "AAPL-RM"};

    @Override
    public void configure() {
        //    subscribe to specified ticker
        for (String ticker : tickers) {
            from("timer:subscribe_" + ticker + "?delay=4000&repeatCount=1")//for ping
                    .setBody(simple("{\n" +
                            "    \"opcode\": \"OrderBookGetAndSubscribe\",\n" +
                            "    \"code\": \"" + ticker + "\",\n" +
                            "    \"exchange\": \"MOEX\",\n" +
                            "    \"depth\": 10,\n" +
                            "    \"format\": \"Simple\",\n" +
                            "    \"guid\":\"" + ticker + "\",\n" +
                            "    \"token\": \"${bean:alorToken?method=getAccessToken}\"\n" +
                            "}"))
                    .to("ahc-wss://api.alor.ru/ws");
        }


    }
}
