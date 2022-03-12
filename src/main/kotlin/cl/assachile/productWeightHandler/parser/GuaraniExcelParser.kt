package cl.assachile.productWeightHandler.parser

import cl.assachile.productWeightHandler.entity.Product
import cl.assachile.productWeightHandler.repository.ProductRepository
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GuaraniExcelParser(override var productRepository: ProductRepository) : ExcelParser {
    override fun parse(file: InputStream) {
        val workbook: Workbook = XSSFWorkbook(file)
        val sheet: Sheet = workbook.getSheetAt(0)
        val productList: ArrayList<Product> = arrayListOf()

        val nroEmbarque = sheet.getRow(1).getCell(3).numericCellValue

        for (row in sheet) {
            if(row.rowNum > 4){
                productList.add(
                    Product(
                        row.getCell(12).stringCellValue,
                        row.getCell(7).stringCellValue,
                        LocalDate.parse(row.getCell(8).stringCellValue, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        row.getCell(3).numericCellValue.toInt().toString(),
                        row.getCell(4).numericCellValue.toInt().toString(),
                        LocalDate.parse(row.getCell(10).stringCellValue, DateTimeFormatter.ofPattern("dd/MM/yy")),
                        row.getCell(5).numericCellValue,
                        nroEmbarque
                    )
                )
            }
        }
        productRepository.deleteProductsByDateAddedBefore(LocalDate.now())
        productRepository.saveAll(productList)
        println("Saved "+productList.size+" products")
    }
}