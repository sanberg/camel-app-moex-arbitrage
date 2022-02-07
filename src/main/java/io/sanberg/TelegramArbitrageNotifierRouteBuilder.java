package io.sanberg;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.telegram.TelegramParseMode;
import org.apache.camel.component.telegram.model.EditMessageTextMessage;
import org.apache.camel.component.telegram.model.OutgoingTextMessage;

import java.text.DecimalFormat;
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
                            .sorted((o1, o2) -> {
                                double spbBid1 = o1.getValue().getSpbBid();
                                double mskAsk1 = o1.getValue().getMskAsk();
                                double spbBid2 = o2.getValue().getSpbBid();
                                double mskAsk2 = o2.getValue().getMskAsk();
                                double arb1 = ((spbBid1 - mskAsk1) / mskAsk1);
                                double arb2 = ((spbBid2 - mskAsk2) / mskAsk2);
                                return Double.compare(arb1, arb2);
                            })
                            .forEach(x -> sorted.put(x.getKey(), x.getValue()));
                    StringBuilder stringBuilder = new StringBuilder();
                    DecimalFormat df = new DecimalFormat("#.##");
                    for (Map.Entry<String, StockData> entry : sorted.getStockDataHashMap().entrySet()
                    ) {
                        stringBuilder.append(entry.getKey()).append(":");
                        stringBuilder.append("\n");
                        stringBuilder.append("SPB:").append(entry.getValue().spbBid).append(" / ").append(entry.getValue().spbAsk);
                        stringBuilder.append("\n");
                        stringBuilder.append("MSK:").append(df.format(entry.getValue().mskBid)).append(" / ").append(df.format(entry.getValue().mskAsk));
                        stringBuilder.append("\n");

                    }
                    OutgoingTextMessage msg = new OutgoingTextMessage();
                    //EditMessageTextMessage edit = new EditMessageTextMessage();
                    if (sorted.getStockDataHashMap().size() == 0) {
                        stringBuilder.append("loading...");
                    }
                    msg.setText(stringBuilder.toString());
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
