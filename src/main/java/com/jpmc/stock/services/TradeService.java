package com.jpmc.stock.services;

import com.jpmc.stock.vo.TradeCommand;

public interface TradeService {

    public void saveTrade(TradeCommand tradeCommand) throws  Exception;
    public Double getVolumeWeightedStockPrice(String stockName);
    public Double getGBCEIndex();
}
