package io.sanberg;

import org.apache.camel.builder.RouteBuilder;

public class AlorQuoteDataMOEXSubscribeByTickerRouteBuilder extends RouteBuilder {
    String[] tickers = {"BIDU-RM", "AAPL-RM"};

    @Override
    public void configure() {
        //subscribe to usdrub quotes

        from("timer:subscribe_USDRUB_TMS" + "?delay=2000&repeatCount=1")//for ping
                .setBody(simple("{\n" +
                        "    \"opcode\": \"QuotesSubscribe\",\n" +
                        "    \"code\": \"" + "USDRUB_TMS" + "\",\n" +
                        "    \"exchange\": \"MOEX\",\n" +
                        "    \"format\": \"Simple\",\n" +
                        "    \"guid\":\"" + "USDRUB_TMS" + "\",\n" +
                        "    \"token\": \"${bean:alorToken?method=getAccessToken}\"\n" +
                        "}"))
                .to("ahc-wss://api.alor.ru/ws");

        //    subscribe to specified ticker
        for (String ticker : tickers) {
            from("timer:subscribe_" + ticker + "?delay=5000&repeatCount=1")//for ping
                    .setBody(simple("{\n" +
                            "    \"opcode\": \"QuotesSubscribe\",\n" +
                            "    \"code\": \"" + ticker + "\",\n" +
                            "    \"exchange\": \"MOEX\",\n" +
                            "    \"format\": \"Simple\",\n" +
                            "    \"guid\":\"" + ticker + "\",\n" +
                            "    \"token\": \"${bean:alorToken?method=getAccessToken}\"\n" +
                            "}"))
                    .to("ahc-wss://api.alor.ru/ws");
        }
    }
}
