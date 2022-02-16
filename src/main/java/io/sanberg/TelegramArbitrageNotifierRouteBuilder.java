package io.sanberg;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.telegram.TelegramParseMode;
import org.apache.camel.component.telegram.model.EditMessageTextMessage;
import org.apache.camel.component.telegram.model.OutgoingTextMessage;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TelegramArbitrageNotifierRouteBuilder extends RouteBuilder {
    LocalDateTime mskEnd = LocalDateTime.parse(LocalDate.now().minusDays(1) + "T23:45:00");
    LocalDateTime mskStart = LocalDateTime.parse(LocalDate.now() + "T07:00:00");

    @Override
    public void configure() {
        from("telegram:bots?authorizationToken=5000137095:AAFws5eJ7DKLSRDBqKAlLrfDPXz19sNBmGI")
                .log("${header.CamelTelegramChatId}");
        from("timer:arbitrageScanner?period=5000&delay=10000")
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
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    stringBuilder.append("Updated on: ").append(simpleDateFormat.format(new Date()));
                    stringBuilder.append("\n");
                    stringBuilder.append("\r\n");
                    DecimalFormat df = new DecimalFormat("#.##");
                    for (Map.Entry<String, StockData> entry : sorted.getStockDataHashMap().entrySet()
                    ) {
                        stringBuilder.append("*").append(entry.getKey()).append("*").append(":");
                        stringBuilder.append("\n");
                        stringBuilder.append("SPB: ")
                                .append(entry.getValue().spbBid)
                                .append("@")
                                .append(entry.getValue().getSpbBidVolume())
                                .append(" / ")
                                .append(entry.getValue().spbAsk)
                                .append("@").
                                append(entry.getValue().getSpbAskVolume());
                        stringBuilder.append("\n");
                        stringBuilder.append("MSK: ")
                                .append(df.format(entry.getValue().mskBid))
                                .append("@")
                                .append(entry.getValue().getMskBidVolume())
                                .append(" / ")
                                .append(df.format(entry.getValue().mskAsk))
                                .append("@")
                                .append(entry.getValue().getMskAskVolume());
                        stringBuilder.append("\n");
                        stringBuilder.append("US: ")
                                .append(entry.getValue().getUsBid())
                                .append("@")
                                .append(entry.getValue().getUsBidVolume())
                                .append(" / ")
                                .append(entry.getValue().getUsAsk())
                                .append("@")
                                .append(entry.getValue().getUsAskVolume());
                        stringBuilder.append("\r\n");
                        stringBuilder.append("\r\n");
                    }

                    if (sorted.getStockDataHashMap().size() == 0) {
                        if (LocalDateTime.now().compareTo(mskEnd) > 0 && LocalDateTime.now().compareTo(mskStart) < 0) {
                            stringBuilder.append("Ночь темна и полна ужасов");
                        } else {
                            stringBuilder.append("loading...");
                        }
                    }
                    //sending message via chat with bpt
                    //OutgoingTextMessage msg = new OutgoingTextMessage();
                    //msg.setText(stringBuilder.toString());
                    //msg.setParseMode("MARKDOWN");
                    EditMessageTextMessage edit = new EditMessageTextMessage("-1001556322892", 2,
                            "2", stringBuilder.toString(), "MARKDOWN", false, null);
                    exchange.getIn().setBody(edit);
                })
                //send message to bot
                //.to("telegram:bots?authorizationToken=5000137095:AAFws5eJ7DKLSRDBqKAlLrfDPXz19sNBmGI&chatId=47092572");

                //update message in channel
                .to("telegram:bots?authorizationToken=5000137095:AAFws5eJ7DKLSRDBqKAlLrfDPXz19sNBmGI&chatId=-1001556322892");
                //  Link for editing message
                // "https://api.telegram.org/bot5000137095:AAFws5eJ7DKLSRDBqKAlLrfDPXz19sNBmGI/editMessageText?chat_id=-1001556322892&message_id=2&text=\%27test\%27&parse_mode=MarkdownV2"

        //TODO
        //форматирование
        //изменение сообщения вместо повторной отправки
        //сохранить chatid, написавших команду subscribe боту
        //в цикле проходить по chatid и разослать всем
    }
}
