package cl.assachile.web.repository

import cl.assachile.web.entity.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface ProductRepository: CrudRepository<Product, String> {
    fun deleteProductsByDateAddedBefore(date:LocalDate)
}