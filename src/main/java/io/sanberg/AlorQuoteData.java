package io.sanberg;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlorQuoteData {
    public Data data;
    public String guid;

    static class Data {
        public String symbol;
        public String exchange;
        public String description;
        public int prev_close_price;
        public int last_price;
        public int last_price_timestamp;
        public int change;
        public double change_percent;
        public int high_price;
        public int low_price;
        public int accruedInt;
        public int accrued_interest;
        public int volume;
        public String open_interest;
        public int ask;
        public int bid;
        public int ask_vol;
        public int bid_vol;
        public Object ob_ms_timestamp;
        public int open_price;
        @JsonProperty("yield")
        public Object myyield;
        public int lotsize;
        public int lotvalue;
        public double facevalue;
        public String type;
        public int total_bid_vol;
        public int total_ask_vol;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getExchange() {
            return exchange;
        }

        public void setExchange(String exchange) {
            this.exchange = exchange;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getPrev_close_price() {
            return prev_close_price;
        }

        public void setPrev_close_price(int prev_close_price) {
            this.prev_close_price = prev_close_price;
        }

        public int getLast_price() {
            return last_price;
        }

        public void setLast_price(int last_price) {
            this.last_price = last_price;
        }

        public int getLast_price_timestamp() {
            return last_price_timestamp;
        }

        public void setLast_price_timestamp(int last_price_timestamp) {
            this.last_price_timestamp = last_price_timestamp;
        }

        public int getChange() {
            return change;
        }

        public void setChange(int change) {
            this.change = change;
        }

        public double getChange_percent() {
            return change_percent;
        }

        public void setChange_percent(double change_percent) {
            this.change_percent = change_percent;
        }

        public int getHigh_price() {
            return high_price;
        }

        public void setHigh_price(int high_price) {
            this.high_price = high_price;
        }

        public int getLow_price() {
            return low_price;
        }

        public void setLow_price(int low_price) {
            this.low_price = low_price;
        }

        public int getAccruedInt() {
            return accruedInt;
        }

        public void setAccruedInt(int accruedInt) {
            this.accruedInt = accruedInt;
        }

        public int getAccrued_interest() {
            return accrued_interest;
        }

        public void setAccrued_interest(int accrued_interest) {
            this.accrued_interest = accrued_interest;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public String getOpen_interest() {
            return open_interest;
        }

        public void setOpen_interest(String open_interest) {
            this.open_interest = open_interest;
        }

        public int getAsk() {
            return ask;
        }

        public void setAsk(int ask) {
            this.ask = ask;
        }

        public int getBid() {
            return bid;
        }

        public void setBid(int bid) {
            this.bid = bid;
        }

        public int getAsk_vol() {
            return ask_vol;
        }

        public void setAsk_vol(int ask_vol) {
            this.ask_vol = ask_vol;
        }

        public int getBid_vol() {
            return bid_vol;
        }

        public void setBid_vol(int bid_vol) {
            this.bid_vol = bid_vol;
        }

        public Object getOb_ms_timestamp() {
            return ob_ms_timestamp;
        }

        public void setOb_ms_timestamp(Object ob_ms_timestamp) {
            this.ob_ms_timestamp = ob_ms_timestamp;
        }

        public int getOpen_price() {
            return open_price;
        }

        public void setOpen_price(int open_price) {
            this.open_price = open_price;
        }

        public Object getMyyield() {
            return myyield;
        }

        public void setMyyield(Object myyield) {
            this.myyield = myyield;
        }

        public int getLotsize() {
            return lotsize;
        }

        public void setLotsize(int lotsize) {
            this.lotsize = lotsize;
        }

        public int getLotvalue() {
            return lotvalue;
        }

        public void setLotvalue(int lotvalue) {
            this.lotvalue = lotvalue;
        }

        public double getFacevalue() {
            return facevalue;
        }

        public void setFacevalue(double facevalue) {
            this.facevalue = facevalue;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getTotal_bid_vol() {
            return total_bid_vol;
        }

        public void setTotal_bid_vol(int total_bid_vol) {
            this.total_bid_vol = total_bid_vol;
        }

        public int getTotal_ask_vol() {
            return total_ask_vol;
        }

        public void setTotal_ask_vol(int total_ask_vol) {
            this.total_ask_vol = total_ask_vol;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }



    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
