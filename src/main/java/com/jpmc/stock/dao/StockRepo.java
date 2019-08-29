package com.jpmc.stock.dao;

import com.jpmc.stock.model.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepo extends CrudRepository<Stock, Integer> {
    Stock findByStockSymbol(String stock);
}
