package cl.assachile.email.parser

import cl.assachile.email.repository.ProductRepository
import java.io.InputStream

interface ExcelParser {
    var productRepository: ProductRepository
    fun parse(file: InputStream)
}