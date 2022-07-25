package io.sanberg;

import org.apache.camel.builder.RouteBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class AlpacaSubscriptionByTickerRouteBuilder extends RouteBuilder {
    List<String> tickers = Files.readAllLines(new File("src/main/resources/spbx.tickers").toPath(),
            Charset.defaultCharset());

    //    List<String> tickers = Arrays.asList("TSLA", "AAPL", "FB", "NVDA", "AMD");
    String tickersList = tickers.stream()
            .map(n -> String.valueOf(n))
            .collect(Collectors.joining("\",\""));


    public AlpacaSubscriptionByTickerRouteBuilder() throws IOException {
    }

    @Override
    public void configure() {
        //    subscribe to specified ticker
//        for (String ticker : tickers) {
//            from("timer:US_sub_" + ticker + "?delay=5000&repeatCount=1")//for ping
//                    .setBody(simple("{\n" +
//                            "    \"action\": \"subscribe\",\n" +
//                            "    \"bars\": [],\n" +
//                            "    \"quotes\": [\n" +
//                            "        \"" + ticker + "\"\n" +
//                            "    ],\n" +
//                            "    \"trades\": [\n" +
//                            "        \"" + ticker + "\"\n" +
//                            "    ]\n" +
//                            "}"))
//                    .to("ahc-wss://stream.data.alpaca.markets/v2/sip?client=#myCustomAsyncHttpClientImpl");
//        }
        from("timer:US_sub_" + "alpaca" + "?delay=5000&repeatCount=1")//for ping
                .setBody(simple("{\n" +
                        "    \"action\": \"subscribe\",\n" +
                        "    \"bars\": [],\n" +
                        "    \"quotes\": [\n" +
                        "        \"" + tickersList + "\"\n" +
                        "    ],\n" +
                        "    \"trades\": [\n" +
                        "        \"" + tickersList + "\"\n" +
                        "    ]\n" +
                        "}"))
                .to("ahc-wss://stream.data.alpaca.markets/v2/sip?client=#myCustomAsyncHttpClientImpl");
    }
}
