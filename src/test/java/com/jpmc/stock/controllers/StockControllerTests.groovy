package com.jpmc.stock.controllers

import com.jpmc.stock.services.StockService
import com.jpmc.stock.services.StockServiceImpl
import groovy.transform.CompileStatic
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.codehaus.groovy.transform.sc.StaticCompileTransformation
import spock.lang.Specification

/*

    * some how I am seeing some issues with groovy and spock dependencies so not uploading failing test cases
    * Rather I verified the test cases with rest calls.

 */
@CompileStatic
public class StockControllerTest extends Specification {
    String stock
    Double price
    Double dividend
    StockController controller
    StockServiceImpl service
    def setup(){
        controller = new StockController()
        service = new StockServiceImpl()
        controller.stockService = service
    }

    def "dividend with valid stock and price"(){
        given:
        stock = "QCOM"
        price = new Double(10)

        when:
        dividend = controller.getDividend(stock, price)

        then:
        service.getDividend(stock,price) >>  new Double(10)
        dividend == 23.0
    }

}
