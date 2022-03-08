package cl.assachile.productWeightHandler.service

import cl.assachile.productWeightHandler.parser.ExcelParser
import cl.assachile.productWeightHandler.parser.GuaraniExcelParser
import cl.assachile.productWeightHandler.parser.Supplier
import cl.assachile.productWeightHandler.repository.ProductRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class ExcelService(private var productRepository: ProductRepository): InitializingBean{
    fun parseExcel(file:InputStream, supplier: Supplier) {
        getParser(supplier).parse(file)
    }

    fun getParser(supplier: Supplier): ExcelParser {
        when(supplier) {
            Supplier.GUARANI -> return GuaraniExcelParser(productRepository)
        }
    }

    override fun afterPropertiesSet() {
    }
}