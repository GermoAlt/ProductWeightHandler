package cl.assachile.productWeightHandler.parser

import cl.assachile.productWeightHandler.repository.ProductRepository
import java.io.InputStream

interface ExcelParser {
    var productRepository: ProductRepository
    fun parse(file: InputStream)
}