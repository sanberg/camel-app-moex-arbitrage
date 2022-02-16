package io.sanberg;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class AlpacaStreamingData {
    @JsonProperty("T")
    public String messageType; //t for trades
    @JsonProperty("S")
    public String symbol;
    @JsonProperty("i")
    public BigInteger tradeId;
    @JsonProperty("x")
    public String exchangeCode;
    @JsonProperty("p")
    public double tradePrice;
    @JsonProperty("s")
    public int tradeSize;
    @JsonProperty("c")
    public ArrayList<String> tradeConditions;
    @JsonProperty("z")
    public String tape;
    @JsonProperty("t")
    public Date date;
    @JsonProperty("bx")
    public String bidExchange;
    @JsonProperty("bp")
    public double bidPrice;
    @JsonProperty("bs")
    public int bidSize;
    @JsonProperty("ax")
    public String askExchange;
    @JsonProperty("ap")
    public double askPrice;
    @JsonProperty("as")
    public int askSize;

    @Override
    public String toString() {
        return "AlpacaStreamingData{" +
                "messageType='" + messageType + '\'' +
                ", symbol='" + symbol + '\'' +
                ", tradeId=" + tradeId +
                ", exchangeCode='" + exchangeCode + '\'' +
                ", tradePrice=" + tradePrice +
                ", tradeSize=" + tradeSize +
                ", tradeConditions=" + tradeConditions +
                ", tape='" + tape + '\'' +
                ", date=" + date +
                ", bidExchange='" + bidExchange + '\'' +
                ", bidPrice=" + bidPrice +
                ", bidSize=" + bidSize +
                ", askExchange='" + askExchange + '\'' +
                ", askPrice=" + askPrice +
                ", askSize=" + askSize +
                '}';
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigInteger getTradeId() {
        return tradeId;
    }

    public void setTradeId(BigInteger tradeId) {
        this.tradeId = tradeId;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public int getTradeSize() {
        return tradeSize;
    }

    public void setTradeSize(int tradeSize) {
        this.tradeSize = tradeSize;
    }

    public ArrayList<String> getTradeConditions() {
        return tradeConditions;
    }

    public void setTradeConditions(ArrayList<String> tradeConditions) {
        this.tradeConditions = tradeConditions;
    }

    public String getTape() {
        return tape;
    }

    public void setTape(String tape) {
        this.tape = tape;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBidExchange() {
        return bidExchange;
    }

    public void setBidExchange(String bidExchange) {
        this.bidExchange = bidExchange;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public int getBidSize() {
        return bidSize;
    }

    public void setBidSize(int bidSize) {
        this.bidSize = bidSize;
    }

    public String getAskExchange() {
        return askExchange;
    }

    public void setAskExchange(String askExchange) {
        this.askExchange = askExchange;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(double askPrice) {
        this.askPrice = askPrice;
    }

    public int getAskSize() {
        return askSize;
    }

    public void setAskSize(int askSize) {
        this.askSize = askSize;
    }
}
