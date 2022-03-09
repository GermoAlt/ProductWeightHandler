package cl.assachile.productWeightHandler.repository

import cl.assachile.productWeightHandler.entity.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface ProductRepository: CrudRepository<Product, String> {
    fun deleteProductsByDateAddedBefore(date:LocalDate)
}