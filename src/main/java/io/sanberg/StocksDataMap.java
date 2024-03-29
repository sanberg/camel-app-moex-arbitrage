package io.sanberg;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class StocksDataMap {
    private final HashMap<String, StockData> stockDataHashMap;
    private double usdRub;

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

    public double getUsdRub() {
        return usdRub;
    }

    public void setUsdRub(double usdRub) {
        this.usdRub = usdRub;
    }

    public void updateUsdRub(AlorQuoteData alorQuoteData) {
        setUsdRub(alorQuoteData.getData().getLast_price());
    }

    public int putMskQuoteData(AlorQuoteData alorQuoteData) {
        try {
            String ticker = alorQuoteData.getGuid().replace("-RM", "");
            StockData stockData = stockDataHashMap.get(ticker) == null ? new StockData() : stockDataHashMap.get(ticker);
            AlorQuoteData.Data data = alorQuoteData.getData();
            stockData.setMskBid(data.getBid() / usdRub);
            stockData.setMskBidVolume(data.getBid_vol());
            stockData.setMskAsk(data.getAsk() / usdRub);
            stockData.setMskAskVolume(data.getAsk_vol());
            stockData.setMskLastTrade(data.getLast_price() / usdRub);
            stockDataHashMap.put(ticker, stockData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 0;
    }

    public int putSpbQuoteData(AlorQuoteData alorQuoteData) {
        try {
            String ticker = alorQuoteData.getGuid();
            StockData stockData = stockDataHashMap.get(ticker) == null ? new StockData() : stockDataHashMap.get(ticker);
            AlorQuoteData.Data data = alorQuoteData.getData();
            stockData.setSpbBid(data.getBid());
            stockData.setSpbBidVolume(data.getBid_vol());
            stockData.setSpbAsk(data.getAsk());
            stockData.setSpbAskVolume(data.getAsk_vol());
            stockData.setSpbLastTrade(data.getLast_price());
            stockDataHashMap.put(ticker, stockData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 0;
    }

    public int putAlpacaStreamingData(List<AlpacaStreamingData> alpacaStreamingDataList) {
        try {
            String ticker = alpacaStreamingDataList.get(0).getSymbol();
            List<AlpacaStreamingData> trades = alpacaStreamingDataList.stream().filter(row -> Objects.equals(row.getMessageType(), "t"))
                    .collect(Collectors.toList());
            List<AlpacaStreamingData> quotes = alpacaStreamingDataList.stream().filter(row -> Objects.equals(row.getMessageType(), "q"))
                    .collect(Collectors.toList());
            AlpacaStreamingData lastTrade;
            AlpacaStreamingData lastQuote;
            StockData stockData = stockDataHashMap.get(ticker) == null ? new StockData() : stockDataHashMap.get(ticker);
            if (trades.size() > 0) {
                lastTrade = trades.get(trades.size() - 1);
                stockData.setUsLastTrade(lastTrade.getTradePrice());
            }
            if (quotes.size() > 0) {
                lastQuote = quotes.get(quotes.size() - 1);
                stockData.setUsAsk(lastQuote.getAskPrice());
                stockData.setUsAskVolume(lastQuote.getAskSize());
                stockData.setUsBid(lastQuote.getBidPrice());
                stockData.setUsBidVolume(lastQuote.getBidSize());
            }
            stockDataHashMap.put(ticker, stockData);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        return 0;
    }

    public StocksDataMap scanForArbitrage() {
        StocksDataMap resStocksDataMap = new StocksDataMap();
        LocalDateTime mskEnd = LocalDateTime.parse(LocalDate.now().minusDays(1) + "T23:45:00");
        LocalDateTime mskStart = LocalDateTime.parse(LocalDate.now() + "T07:00:00");
        if (LocalDateTime.now().compareTo(mskEnd) > 0 && LocalDateTime.now().compareTo(mskStart) < 0) {
            return resStocksDataMap;
        }
        for (Map.Entry<String, StockData> entry : this.stockDataHashMap.entrySet()
        ) {
            if (entry.getValue().getMskAsk() < entry.getValue().getSpbBid()
                    && (entry.getValue().getSpbBid() - entry.getValue().getMskAsk())
                    / entry.getValue().getMskAsk() > 0.0008) {
                resStocksDataMap.put(entry.getKey(), entry.getValue());
            }
        }
        return resStocksDataMap;
    }

    @Override
    public String toString() {
        return "StocksDataMap{" +
                "stockDataHashMap=" + stockDataHashMap +
                '}';
    }

    public HashMap<String, StockData> getStockDataHashMap() {
        return stockDataHashMap;
    }
}
