package io.sanberg;

import java.util.HashMap;

public class StocksDataMap {
    private final HashMap<String, StockData> stockDataHashMap;

    public StocksDataMap() {
        this.stockDataHashMap = new HashMap<>();
    }

    public int put(String ticker, StockData stockData) {
        try {
            stockDataHashMap.put(ticker, stockData);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    public int putSpbQouteData(AlorQuoteData alorQuoteData) {
        try {
            AlorQuoteData.Bid bid =  alorQuoteData.getData().getBids().get(0);
            AlorQuoteData.Ask ask = alorQuoteData.getData().getAsks().get(0);
            String ticker = alorQuoteData.getGuid();
            StockData stockData = stockDataHashMap.get(ticker) == null ? new StockData() : stockDataHashMap.get(ticker);
            stockData.setSpbBid(bid.getPrice());
            stockData.setSpbBidVolume(bid.getVolume());
            stockData.setSpbAsk(ask.getPrice());
            stockData.setSpbAskVolume(bid.getVolume());
            stockDataHashMap.put(ticker, stockData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 0;
    }

    //public int putUsQuoteData(String ticker, )
}
