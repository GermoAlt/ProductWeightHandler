package cl.assachile.productWeightHandler

import cl.assachile.productWeightHandler.service.ExcelService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductWeightHandlerApplication

fun main(args: Array<String>) {
	runApplication<ProductWeightHandlerApplication>(*args)
}
