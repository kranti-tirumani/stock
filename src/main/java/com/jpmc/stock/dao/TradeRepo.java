package com.jpmc.stock.dao;

import com.jpmc.stock.model.Trade;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TradeRepo extends CrudRepository<Trade, Integer> {
    List<Trade> findAllByStockSymbolAndTradeDateGreaterThan(String stock, Date tradeDate);
    List<Trade> findAllByStockSymbol(String stockSymbol);
}
