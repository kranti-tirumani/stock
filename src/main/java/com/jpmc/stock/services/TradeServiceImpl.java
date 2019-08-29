package com.jpmc.stock.services;

import com.jpmc.stock.dao.StockRepo;
import com.jpmc.stock.dao.TradeRepo;
import com.jpmc.stock.model.Stock;
import com.jpmc.stock.model.Trade;
import com.jpmc.stock.vo.TradeCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TradeServiceImpl implements  TradeService{

    private static final Logger logger = LogManager.getLogger(TradeServiceImpl.class);

    @Autowired
    TradeRepo tradeRepo;
    @Autowired
    StockRepo stockRepo;

    /*
     *This method is used to record the Trade entry
     */
    @Override
    public void saveTrade(TradeCommand tradeCommand) throws Exception {
        logger.debug("saveTrade", "Trade values "+tradeCommand);
        Trade trade = new Trade();
        trade.setStockSymbol(tradeCommand.getSymbol());
        trade.setType(tradeCommand.getType());
        trade.setQuantity(tradeCommand.getQuantity());
        trade.setTradePrice(tradeCommand.getTradePrice());
        trade.setStockPrice(tradeCommand.getStockPrice());
        trade.setTradeDate(new Date());
        tradeRepo.save(trade);
    }

    /*
     *This method is  to get volume weighted stock price
     */
    @Override
    public Double getVolumeWeightedStockPrice(String stockName){
        logger.debug("getVolumeWeightedStockPrice", "Stock Name "+stockName);
        Double sumOfTradePrice = 0.0;
        Long sumOfQuantity = 0L;
        List<Trade> trades = tradeRepo.findAllByStockSymbolAndTradeDateGreaterThan(stockName, new Date(System.currentTimeMillis() - 900 * 1000));
        for(Trade trade: trades){
            sumOfTradePrice = sumOfTradePrice + (trade.getTradePrice()*trade.getQuantity());
            sumOfQuantity = sumOfQuantity + trade.getQuantity();
        }
        logger.debug("getVolumeWeightedStockPrice", "sumOfTradePrice "+sumOfTradePrice+" sumOfQuantity"+sumOfQuantity);
        return sumOfTradePrice/sumOfQuantity;
    }

    /*
     *This method is to get GCBE Index
     */
    @Override
    public Double getGBCEIndex(){
        Double gcbe = 0.0;
        List<Stock> stocks = (List<Stock>) stockRepo.findAll();
        for(Stock stock:stocks){
            gcbe = computeGCBE(stock.getStockSymbol()) + gcbe;
        }

        logger.debug("getGBCEIndex", "gcbe "+gcbe);
        return gcbe/stocks.size();
    }

    private Double computeGCBE(String stockSymbol){
        Double gcbe = 0.0;
        List<Trade> trades = tradeRepo.findAllByStockSymbol(stockSymbol);
        if(trades == null ){
            return gcbe;
        }
        for(Trade trade: trades){
            gcbe = gcbe + trade.getStockPrice();
        }
        return gcbe;
    }
}
