package io.sanberg;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public StocksDataMap scanForArbitrage() {
        StocksDataMap resStocksDataMap = new StocksDataMap();
        //TODO check the time - after 23:45 enables mode of top loosers/gainers
        LocalDateTime mskEnd = LocalDateTime.parse(LocalDate.now().minusDays(1) + "T23:45:00");
        LocalDateTime mskStart = LocalDateTime.parse(LocalDate.now() + "T07:00:00");
        if (LocalDateTime.now().compareTo(mskEnd) > 0 && LocalDateTime.now().compareTo(mskStart) < 0) {
            return resStocksDataMap;
        }
        for (Map.Entry<String, StockData> entry : this.stockDataHashMap.entrySet()
        ) {
            if (entry.getValue().getMskAsk() < entry.getValue().getSpbBid()
                    && (entry.getValue().getSpbBid() - entry.getValue().getMskAsk())
                            / entry.getValue().getMskAsk() > 0.0003) {
                resStocksDataMap.put(entry.getKey(), entry.getValue());
            }
            //resStocksDataMap.put(entry.getKey(), entry.getValue()); //TODO Remove after testing
        }
        return resStocksDataMap;
    }
    //public int putUsQuoteData(String ticker, )


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
