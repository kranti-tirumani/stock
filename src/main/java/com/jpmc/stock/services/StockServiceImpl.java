package com.jpmc.stock.services;

import com.jpmc.stock.Exception.StockException;
import com.jpmc.stock.dao.StockRepo;
import com.jpmc.stock.model.Stock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    private static final Logger logger = LogManager.getLogger(StockServiceImpl.class);
    private static final String STOCK_TYPE_COMMON = "Common";

    @Autowired
    StockRepo stockRepo;

    /*
     * This method is used to get the divident for a given share and price
     */
    @Override
    public Double getDividend(String stockName, Double price)  throws StockException {
        logger.debug("getDividend", "stockName "+stockName + " price"+price);
        Double dividend = 0.0;
        Stock stock = stockRepo.findByStockSymbol(stockName);
        if(null == stock){
            return dividend;
        }
        if(stock.getType().equals(STOCK_TYPE_COMMON)){
            dividend = stock.getLastDividend()/price;
        }
        dividend = stock.getFixedDividend()/price;
        logger.debug("getDividend", dividend);
        return dividend;
    }

    /*
     * This method is to get PERatio based on stock name and price
     */

    @Override
    public Double getPERatio(String stockName, Double price) throws StockException {
        logger.debug("getPERatio", "stockName "+stockName + " price"+price);
        Double dividend = getDividend(stockName, price);
        if(dividend == 0){
            return dividend;
        }
        return price/dividend;
    }


}
