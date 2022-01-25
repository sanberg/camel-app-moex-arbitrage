package io.sanberg;

import java.util.ArrayList;

public class AlorQuoteData {
    public Data data;
    public String guid;



    public class Ask {
        public int price;
        public int volume;
    }

    public class Bid {
        public int price;
        public int volume;
    }

    public class Data {
        public ArrayList<Ask> asks;
        public ArrayList<Bid> bids;
        public boolean existing;
        public long ms_timestamp;
        public boolean snapshot;
        public int timestamp;
    }
}
