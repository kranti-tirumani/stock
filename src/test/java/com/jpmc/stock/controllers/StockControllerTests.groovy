package com.jpmc.stock.controllers

import com.jpmc.stock.Exception.StockException
import com.jpmc.stock.services.StockServiceImpl
import spock.lang.Specification


//@CompileStatic
public class StockControllerTests extends Specification {
    String stock
    Double price
    Double dividend
    StockController controller
    StockServiceImpl service
    def setup(){
        controller = new StockController()
        service = Mock(StockServiceImpl)
        controller.stockService = service
    }

    def "dividend with valid stock and price"(){
        given:
        stock = "QCOM"
        price = new Double(10)

        when:
        dividend = controller.getDividend(stock, price)

        then:
        service.getDividend(_ as String, _ as Double) >>  new Double(2)
        dividend == 2.0
    }

    def "dividend with error case"(){
        given:
        stock = "QCOM"
        price = new Double(10)

        when:
        dividend = controller.getDividend(stock, price)

        then:
        service.getDividend(_ as String, _ as Double) >>  {String s, Double d ->
            throw new StockException("Error occured!")
        }
        dividend == 0.0
    }

    def "p/e ratio with valid stock and price"(){
        given:
        stock = "QCOM"
        price = new Double(10)

        when:
        dividend = controller.getPERatio(stock, price)

        then:
        service.getPERatio(_ as String, _ as Double) >>  new Double(2)
        dividend == 2.0
    }

    def "p/e ratio with error case"(){
        given:
        stock = "QCOM"
        price = new Double(10)

        when:
        dividend = controller.getPERatio(stock, price)

        then:
        service.getPERatio(_ as String, _ as Double) >>  {String s, Double d ->
            throw new StockException("Error occured!")
        }
        dividend == 0.0
    }

}
