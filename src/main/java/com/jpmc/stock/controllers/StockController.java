package com.jpmc.stock.controllers;

import com.jpmc.stock.Exception.StockException;
import com.jpmc.stock.services.StockServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    private static final Logger logger = LogManager.getLogger(StockController.class);

    @Autowired
    StockServiceImpl stockService;

    /*
     * Sample rest call to test http://localhost:8080/getDividend?stockSymbol=QCOM&price=10.2
     */
    @GetMapping("/getDividend")
    public Double getDividend(String stockSymbol, Double price) {
        Double dividend = 0.0;
        try{
            dividend = stockService.getDividend(stockSymbol, price);
        } catch (StockException exception){
           logger.error(exception);
        }
        return dividend;
    }

    /*
     * Sample rest call to test http://localhost:8080/getPERatio?stockSymbol=QCOM&price=10.2
     */
    @GetMapping("/getPERatio")
    public Double getPERatio(String stockSymbol, Double price) {
        Double peRatio = 0.0;
        try {
            peRatio = stockService.getPERatio(stockSymbol, price);
        } catch (StockException exception){
            logger.error(exception);
        }
        return peRatio;
    }

}
