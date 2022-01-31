package io.sanberg;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;


public class WssConsumerMOEXRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        from("ahc-wss://api.alor.ru/ws")
                .choice()
                    .when(body().startsWith("{\"data\":"))
                        //TODO unmarshalling and updating the hashmap
                        .unmarshal().json(AlorQuoteData.class)
                        .choice()
                            .when(exchange -> {
                                AlorQuoteData alorQuoteData = exchange.getIn().getBody(AlorQuoteData.class);
                                return alorQuoteData.getData().getBids().size() != 0
                                        && alorQuoteData.getData().getAsks().size() != 0;
                            })
                                .log("write to hashmap: ${body}")
                                .bean("stocksDataMap", "putSpbQouteData")
                                .log("status: ${body}")
                            .otherwise()
                                .log("received from socket: ${body}")
                        .endChoice()
//                        .process(exchange -> {
//                            AlorQuoteData alorQuoteData = exchange.getIn().getBody(AlorQuoteData.class);
//                            AlorQuoteData.Ask ask = alorQuoteData.getData().getAsks().get(0);
//                            AlorQuoteData.Bid bid = alorQuoteData.getData().getBids().get(0);
//                            System.out.println(alorQuoteData.getGuid() + ": " + bid.getPrice() + " | " + bid.getVolume());
//                            System.out.println(alorQuoteData.getGuid() + ": " + ask.getPrice() + " | " + ask.getVolume());
//                        })
                    .otherwise()
                        .log("${body}");
    }
}
