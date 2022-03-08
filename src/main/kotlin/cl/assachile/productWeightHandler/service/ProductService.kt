package cl.assachile.productWeightHandler.service

import cl.assachile.productWeightHandler.entity.Product
import cl.assachile.productWeightHandler.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private var productRepository: ProductRepository) {
    fun listAll(): MutableIterable<Product> {
        return productRepository.findAll()
    }
}