package cl.assachile.productWeightHandler.service

import cl.assachile.productWeightHandler.entity.Product
import cl.assachile.productWeightHandler.repository.ProductRepository
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream
import java.nio.file.Paths
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class ExcelService(private var productRepository: ProductRepository): InitializingBean{
    private val path = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\kotlin\\cl\\assachile\\productWeightHandler\\files\\product-info.xlsx"

    fun parseExcel() {
        val file = FileInputStream(File(path))
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
                        LocalDate.parse(row.getCell(10).stringCellValue, DateTimeFormatter.ofPattern("dd/MM/yy"))
                    )
                )
            }
        }
        productRepository.saveAll(productList)
    }

    override fun afterPropertiesSet() {
    }
}