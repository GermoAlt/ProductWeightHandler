package cl.assachile.web.entity

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
open class Product(
    @Id
    @Column(name = "barcode_number", nullable = false)
    open var barcodeNumber: String,
    open var description: String,
    open var productionDate: LocalDate,
    open var boxNumber: String,
    open var amount: String,
    open var bestBefore: LocalDate,
    open var weight: Double,
    open var transactionNumber: Double,
    open var dateAdded: LocalDate = LocalDate.now()
)