package io.sanberg;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.telegram.TelegramParseMode;
import org.apache.camel.component.telegram.model.OutgoingTextMessage;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TelegramArbitrageNotifierRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("telegram:bots?authorizationToken=5000137095:AAFws5eJ7DKLSRDBqKAlLrfDPXz19sNBmGI")
                .log("${header.CamelTelegramChatId}");
        from("timer:arbitrageScanner?period=10000")
                .bean("stocksDataMap", "scanForArbitrage")
                .log("${body}")
                .process(exchange -> {
                    StocksDataMap stocksDataHashMap = exchange.getIn().getBody(StocksDataMap.class);
                    String resString = "";
                    HashMap<String, StockData> unsorted = stocksDataHashMap.getStockDataHashMap();
                    StocksDataMap sorted = new StocksDataMap();
                    unsorted.entrySet()
                            .stream()
                            .sorted(new Comparator<Map.Entry<String, StockData>>() {
                                @Override
                                public int compare(Map.Entry<String, StockData> o1, Map.Entry<String, StockData> o2) {
                                    return (o1.getValue().getSpbBid() - o1.getValue().getMskAsk())
                            / o2.getValue().getMskAsk()) >  ;
                                }
                            })
                    for (Map.Entry<String, StockData> entry : stocksDataHashMap.getStockDataHashMap().entrySet()
                    ) {

                    }
                    OutgoingTextMessage msg = new OutgoingTextMessage();
                    msg.setText("*Dollar*");
                    msg.setParseMode("MARKDOWN");

                    exchange.getIn().setBody(msg);
                })
                .to("telegram:bots?authorizationToken=5000137095:AAFws5eJ7DKLSRDBqKAlLrfDPXz19sNBmGI&chatId=47092572");
        //TODO
        //форматирование
        //изменение сообщения вместо повторной отправки
        //сохранить chatid, написавших команду subscribe боту
        //в цикле проходить по chatid и разослать всем
    }
}
