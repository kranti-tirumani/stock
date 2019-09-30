package com.jpmc.stock.controllers

import com.jpmc.stock.Exception.StockException
import com.jpmc.stock.model.Stock
import com.jpmc.stock.services.TradeService
import com.jpmc.stock.services.TradeServiceImpl
import com.jpmc.stock.vo.TradeCommand
import spock.lang.Specification

class TradeControllerTests extends Specification {

    TradeController controller
    TradeService service

    def setup(){
        controller = new TradeController()
        service = Mock(TradeServiceImpl)
        controller.tradeService = service
    }

    void "test gcbe"() {
        given:

        when:
        Double gcbe = controller.getGCBE()

        then:
        service.getGBCEIndex() >>  new Double(100)
        gcbe == 100.0

    }

    void "test getVolumeWeightedStockPrice"() {
        given:
        String stock = 'QCOM'

        when:
        Double volume = controller.getVolumeWeightedStockPrice(stock)

        then:
        service.getVolumeWeightedStockPrice(_ as String) >>  new Double(100)
        volume == 100.0
    }

    void "test recordTrade "() {
        given:
        TradeCommand tradeCommand = [stockSymbol:'QCOM', type:'Test', lastDividend:0.2, fixedDividend:1.2, parValue:2.0] as TradeCommand

        when:
        controller.recordTrade(tradeCommand)

        then:
        service.saveTrade(_ as TradeCommand) >> {
            println "Save Successful"
        }
    }

    void "test recordTrade failed"() {
        given:
        TradeCommand tradeCommand = [stockSymbol:'QCOM', type:'Test', lastDividend:0.2, fixedDividend:1.2, parValue:2.0] as TradeCommand

        when:
        controller.recordTrade(tradeCommand)

        then:
        service.saveTrade(_ as TradeCommand) >> {
            throw new StockException("Save failed")
        }
        thrown StockException()
    }
}
