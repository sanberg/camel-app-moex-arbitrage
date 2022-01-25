package io.sanberg;

import java.util.HashMap;

public class StocksDataMap {
    private HashMap<String, StockData> stockDataHashMap = new HashMap<String, StockData>();

    public int put(String ticker, StockData stockData) {
        try {
            stockDataHashMap.put(ticker, stockData);
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }
}
