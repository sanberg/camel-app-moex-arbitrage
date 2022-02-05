package io.sanberg;

import org.apache.camel.builder.RouteBuilder;

public class AlorQuoteDataSPBSubscribeByTickerRouteBuilder extends RouteBuilder {
    String[] tickers = {"BIDU", "AAPL"};

    @Override
    public void configure() {
        //    subscribe to specified ticker
        for (String ticker : tickers) {
            from("timer:subscribe_" + ticker + "?delay=5000&repeatCount=1")//for ping
                    .setBody(simple("{\n" +
                            "    \"opcode\": \"QuotesSubscribe\",\n" +
                            "    \"code\": \"" + ticker + "\",\n" +
                            "    \"exchange\": \"SPBX\",\n" +
                            "    \"format\": \"Simple\",\n" +
                            "    \"guid\":\"" + ticker + "\",\n" +
                            "    \"token\": \"${bean:alorToken?method=getAccessToken}\"\n" +
                            "}"))
                    .to("ahc-wss://api.alor.ru/ws");
        }
    }
}
