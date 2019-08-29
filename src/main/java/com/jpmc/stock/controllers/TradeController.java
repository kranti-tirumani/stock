package com.jpmc.stock.controllers;

import com.jpmc.stock.services.TradeServiceImpl;
import com.jpmc.stock.vo.TradeCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeController {

    private static final Logger logger = LogManager.getLogger(TradeController.class);

    @Autowired
    TradeServiceImpl tradeService;

    /*
     * Sample rest call to test http://localhost:8080/recordTrade?symbol=QCOM&type=Common&quantity=100&tradePrice=12.35&stockPrice=12.3
     */
    @GetMapping("/recordTrade")
    public void recordTrade(TradeCommand tradeCommand){
        try {
            tradeService.saveTrade(tradeCommand);
        } catch (Exception exception){
            logger.error(exception);
        }
    }

    /*
     *Sample rest call to test http://localhost:8080/getVolumeWeightedStockPrice
     */
    @GetMapping("/getVolumeWeightedStockPrice")
    public Double getVolumeWeightedStockPrice(String stockName){
        Double volumeWeightedStockPrice = 0.0;
        try {
            volumeWeightedStockPrice = tradeService.getVolumeWeightedStockPrice(stockName);
        } catch (Exception exception){
            logger.error(exception);
        }
        return volumeWeightedStockPrice;
    }

    /*
     *Sample rest call to test http://localhost:8080/getGCBE
     *
     */

    @GetMapping("/getGCBE")
    public Double getGCBE(){
        Double gcbe = 0.0;
        try {
            tradeService.getGBCEIndex();
        } catch (Exception exception){
            logger.error(exception);
        }
        return gcbe;
    }
}
