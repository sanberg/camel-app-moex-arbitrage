package io.sanberg;

import org.apache.camel.Route;
import org.apache.camel.builder.RouteBuilder;

public class WssOrderBookSubsctiptionByTicker extends RouteBuilder {
    String ticker;
    AlorTokenManager tokenManager = new AlorTokenManager();

    public WssOrderBookSubsctiptionByTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public void configure() {
        //    subscribe to specified ticker
        from("timer:foo?period=200000")//for ping
                .setBody(simple("{\n" +
                        "    \"opcode\": \"OrderBookGetAndSubscribe\",\n" +
                        "    \"code\": \""+ ticker + "\",\n" +
                        "    \"exchange\": \"MOEX\",\n" +
                        "    \"depth\": 10,\n" +
                        "    \"format\": \"Simple\",\n" +
                        "    \"guid\": \"f35a2373-612c-4518-54af-72025384f59b\",\n" +
                        "    \"token\": \"" + tokenManager.getToken() + "\n" +
                        "}"))
                .to("ahc-wss://api.alor.ru/ws");

    }
}
