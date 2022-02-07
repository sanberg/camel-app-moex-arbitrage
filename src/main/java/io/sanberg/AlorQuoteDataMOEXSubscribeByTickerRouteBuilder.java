package io.sanberg;

import org.apache.camel.builder.RouteBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class AlorQuoteDataMOEXSubscribeByTickerRouteBuilder extends RouteBuilder {

    List<String> tickers = Files.readAllLines(new File("src/main/resources/moex.tickers").toPath(),
            Charset.defaultCharset());

    public AlorQuoteDataMOEXSubscribeByTickerRouteBuilder() throws IOException {
    }

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

        //    subscribe to specified tickers
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
