package io.sanberg;

import org.apache.camel.builder.RouteBuilder;


public class AlorQuoteDataConsumerRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        from("ahc-wss://api.alor.ru/ws")
                .choice()
                    .when(body().startsWith("{\"data\":"))
                        .unmarshal().json(AlorQuoteData.class)
                        .choice()
                            .when(exchange -> {
                                AlorQuoteData alorQuoteData = exchange.getIn().getBody(AlorQuoteData.class);
                                return alorQuoteData.getGuid().endsWith("-RM") && alorQuoteData.getData().getExchange().equals("MOEX");
                            })
                                .to("bean:stocksDataMap?method=putMskQuoteData(${body})")
                            .when(exchange -> {
                                AlorQuoteData alorQuoteData = exchange.getIn().getBody(AlorQuoteData.class);
                                return alorQuoteData.getData().getExchange().equals("SPBX");
                            })
                                .to("bean:stocksDataMap?method=putSpbQuoteData(${body})")
                            .when(exchange -> {
                                AlorQuoteData alorQuoteData = exchange.getIn().getBody(AlorQuoteData.class);
                                return alorQuoteData.getData().getExchange().equals("MOEX") && alorQuoteData.getGuid().equals("USDRUB_TMS");
                            })
                                .to("bean:stocksDataMap?method=updateUsdRub(${body})")
                            .otherwise()
                                .log("received from socket: ${body}")
                        .endChoice()
                    .otherwise()
                        .log("${body}");
    }
}
