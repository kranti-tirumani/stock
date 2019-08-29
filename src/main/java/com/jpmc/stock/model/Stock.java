package com.jpmc.stock.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stock extends Auditable<String>{
    @Id
    private String stockSymbol;
    private String type;
    private Double lastDividend;
    private Double fixedDividend;
    private Double parValue;

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String symbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(Double lastDividend) {
        this.lastDividend = lastDividend;
    }

    public Double getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(Double fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public Double getParValue() {
        return parValue;
    }

    public void setParValue(Double parValue) {
        this.parValue = parValue;
    }


}
