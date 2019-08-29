package com.jpmc.stock.services;

public interface StockService {

    public Double getDividend(String stockName, Double price);
    public Double getPERatio(String stockName, Double price);
}
