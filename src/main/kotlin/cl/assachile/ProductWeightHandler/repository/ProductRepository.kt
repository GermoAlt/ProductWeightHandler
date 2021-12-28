package cl.assachile.productWeightHandler.repository

import cl.assachile.productWeightHandler.entity.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: CrudRepository<Product, String> {
}