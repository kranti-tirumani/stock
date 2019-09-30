package com.jpmc.stock.services.StockServiceTests

import com.jpmc.stock.dao.StockRepo
import com.jpmc.stock.dao.TradeRepo
import com.jpmc.stock.model.Stock
import com.jpmc.stock.model.Trade
import com.jpmc.stock.services.TradeService
import com.jpmc.stock.services.TradeServiceImpl
import spock.lang.Specification


class TradeServiceTests extends Specification {

    TradeService service
    TradeRepo tradeRepo
    StockRepo stockRepo

    def setup(){
        service = new TradeServiceImpl()
        stockRepo = Mock(StockRepo)
        service.stockRepo = stockRepo
        tradeRepo = Mock(TradeRepo)
        service.tradeRepo = tradeRepo
    }

    def "get getVolumeWeightedStockPrice with stockname"(){
        given:
        String stock = "QCOM"

        when:
        Double price = service.getVolumeWeightedStockPrice(stock)

        then:
        tradeRepo.findAllByStockSymbolAndTradeDateGreaterThan(_ as String, _ as Date) >>  {String s, Date date ->
            Trade trade = [quantity:2000, tradePrice:new Double(200.26)]
            return [trade]
        }
        price == 200.26

    }

    def "get getVolumeWeightedStockPrice with stockname with no value for given time"(){
        given:
        String stock = "QCOM"

        when:
        Double price = service.getVolumeWeightedStockPrice(stock)

        then:
        tradeRepo.findAllByStockSymbolAndTradeDateGreaterThan(_ as String, _ as Date) >>  {String s, Date date ->
            return null
        }
        price == 0.0

    }

    def "get getGCBEIndex with no stock value"(){
        given:

        when:
        Double index = service.getGBCEIndex()

        then:
        stockRepo.metaClass.static.findAll() >>  { ->
            return null
        }
        index == 0.0

    }


}
