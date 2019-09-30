package com.jpmc.stock.services;

import com.jpmc.stock.Exception.StockException;

public interface StockService {

    public Double getDividend(String stockName, Double price) throws StockException;
    public Double getPERatio(String stockName, Double price) throws StockException;
}
