package io.sanberg;

import org.apache.camel.builder.RouteBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class AlorQuoteDataSPBSubscribeByTickerRouteBuilder extends RouteBuilder {
    List<String> tickers = Files.readAllLines(new File("src/main/resources/spbx.tickers").toPath(),
            Charset.defaultCharset());

    public AlorQuoteDataSPBSubscribeByTickerRouteBuilder() throws IOException {
    }

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
