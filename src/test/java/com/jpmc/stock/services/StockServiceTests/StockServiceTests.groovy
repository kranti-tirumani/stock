package com.jpmc.stock.services.StockServiceTests

import com.jpmc.stock.dao.StockRepo
import com.jpmc.stock.model.Stock
import com.jpmc.stock.services.StockService
import com.jpmc.stock.services.StockServiceImpl
import spock.lang.Specification

class StockServiceTests extends Specification {
    StockService service
    StockRepo stockRepo

    def setup(){
        service = new StockServiceImpl()
        stockRepo = Mock(StockRepo)
        service.stockRepo = stockRepo

    }

    def "get dividend with stockname and price and common type"(){
        given:
        String stock = "QCOM"
        Double price = new Double(10)

        when:
        Double dividend = service.getDividend(stock, price)

        then:
        stockRepo.findByStockSymbol(_ as String) >>  {String s ->
            return [type:'Common', lastDividend:new Double(2.2), fixedDividend: new Double(2.0)] as Stock
        }
        dividend == 0.2

    }

    def "get dividend with stockname and price and non common type"(){
        given:
        String stock = "QCOM"
        Double price = new Double(10)

        when:
        Double dividend = service.getDividend(stock, price)

        then:
        stockRepo.findByStockSymbol(_ as String) >>  {String s ->
            return [type:'Test', lastDividend:new Double(2.2), fixedDividend: new Double(2.0)] as Stock
        }
        dividend == 0.2

    }

    def "get peratio with stock name and price"(){
        given:
        String stock = "QCOM"
        Double price = new Double(10)

        when:
        Double peRatio = service.getPERatio(stock, price)

        then:
        stockRepo.findByStockSymbol(_ as String) >>  {String s ->
            return [type:'Test', lastDividend:new Double(2.2), fixedDividend: new Double(2.0)] as Stock
        }
        peRatio == 50.0

    }
}
