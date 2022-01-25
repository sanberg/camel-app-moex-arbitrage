package io.sanberg;

public class StockData {
    public double spbLastTrade;
    public double spbBid;
    public int spbBidVolume;
    public double spbAsk;
    public int spbAskVolume;
    public double usLastTrade;
    public double usBid;
    public int usBidVolume;
    public double usAsk;
    public int usAskVolume;


    public double getSpbLastTrade() {
        return spbLastTrade;
    }

    public void setSpbLastTrade(double spbLastTrade) {
        this.spbLastTrade = spbLastTrade;
    }

    public double getSpbBid() {
        return spbBid;
    }

    public void setSpbBid(double spbBid) {
        this.spbBid = spbBid;
    }

    public double getSpbAsk() {
        return spbAsk;
    }

    public void setSpbAsk(double spbAsk) {
        this.spbAsk = spbAsk;
    }

    public double getUsLastTrade() {
        return usLastTrade;
    }

    public void setUsLastTrade(double usLastTrade) {
        this.usLastTrade = usLastTrade;
    }

    public double getUsBid() {
        return usBid;
    }

    public void setUsBid(double usBid) {
        this.usBid = usBid;
    }

    public double getUsAsk() {
        return usAsk;
    }

    public void setUsAsk(double usAsk) {
        this.usAsk = usAsk;
    }

    public int getSpbBidVolume() {
        return spbBidVolume;
    }

    public void setSpbBidVolume(int spbBidVolume) {
        this.spbBidVolume = spbBidVolume;
    }

    public int getSpbAskVolume() {
        return spbAskVolume;
    }

    public void setSpbAskVolume(int spbAskVolume) {
        this.spbAskVolume = spbAskVolume;
    }

    public int getUsBidVolume() {
        return usBidVolume;
    }

    public void setUsBidVolume(int usBidVolume) {
        this.usBidVolume = usBidVolume;
    }

    public int getUsAskVolume() {
        return usAskVolume;
    }

    public void setUsAskVolume(int usAskVolume) {
        this.usAskVolume = usAskVolume;
    }

    public void updateSpbQuotes(double spbBid, double spbAsk) {
        if (getSpbBid() != spbBid) {
            setSpbBid(spbBid);
        }
        if (getSpbAsk() != spbAsk) {
            setSpbAsk(spbAsk);
        }
    }

    public void updateUsQuotes(double usBid, double usAsk) {
        setUsBid(usBid);
        setUsAsk(usAsk);
    }
}
