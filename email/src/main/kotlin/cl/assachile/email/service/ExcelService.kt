package cl.assachile.email.service

import cl.assachile.email.parser.ExcelParser
import cl.assachile.email.parser.GuaraniExcelParser
import cl.assachile.email.parser.Supplier
import cl.assachile.email.repository.ProductRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class ExcelService(private var productRepository: ProductRepository): InitializingBean{
    fun parseExcel(file:InputStream, supplier: Supplier) {
        println("Parsing new excel...")
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