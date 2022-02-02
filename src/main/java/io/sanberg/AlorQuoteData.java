package io.sanberg;

import java.util.ArrayList;

public class AlorQuoteData {
    public Data data;
    public String guid;

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

    static class Ask {
        public int price;
        public int volume;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        @Override
        public String toString() {
            return "Ask{" +
                    "price=" + price +
                    ", volume=" + volume +
                    '}';
        }
    }

    static class Bid {
        public int price;
        public int volume;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        @Override
        public String toString() {
            return "Bid{" +
                    "price=" + price +
                    ", volume=" + volume +
                    '}';
        }
    }

    static class Data {
        public ArrayList<Ask> asks;
        public ArrayList<Bid> bids;
        public boolean existing;
        public long ms_timestamp;
        public boolean snapshot;
        public int timestamp;

        public ArrayList<Ask> getAsks() {
            return asks;
        }

        public void setAsks(ArrayList<Ask> asks) {
            this.asks = asks;
        }

        public ArrayList<Bid> getBids() {
            return bids;
        }

        public void setBids(ArrayList<Bid> bids) {
            this.bids = bids;
        }

        public boolean isExisting() {
            return existing;
        }

        public void setExisting(boolean existing) {
            this.existing = existing;
        }

        public long getMs_timestamp() {
            return ms_timestamp;
        }

        public void setMs_timestamp(long ms_timestamp) {
            this.ms_timestamp = ms_timestamp;
        }

        public boolean isSnapshot() {
            return snapshot;
        }

        public void setSnapshot(boolean snapshot) {
            this.snapshot = snapshot;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "asks=" + asks +
                    ", bids=" + bids +
                    ", existing=" + existing +
                    ", ms_timestamp=" + ms_timestamp +
                    ", snapshot=" + snapshot +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "AlorQuoteData{" +
                "data=" + data +
                ", guid='" + guid + '\'' +
                '}';
    }
}
