package com.ankita.firsttestdemo;

public class InvestedShare {
    private String share;
    private double buy;
    private double sell;
    private double profit;

    public InvestedShare(String share, double buy, double sell) {
        this.share = share;
        this.buy = buy;
        this.sell = sell;
    }


    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
