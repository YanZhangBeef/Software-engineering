package com.fit5136.entities;

// TradingWay entity class
public class TradingWay {

    // attribute
    private String tradingWayName;

    // constructon
    public TradingWay(String tradingWayName) {
        this.tradingWayName = tradingWayName;
    }

    // get or set methods
    public String getTradingWayName() {
        return tradingWayName;
    }

    public void setTradingWayName(String tradingWayName) {
        this.tradingWayName = tradingWayName;
    }

    // Override toString method
    @Override
    public String toString() {
        return "TradingWay{" +
                "tradingWayName='" + tradingWayName + '\'' +
                '}';
    }
}
