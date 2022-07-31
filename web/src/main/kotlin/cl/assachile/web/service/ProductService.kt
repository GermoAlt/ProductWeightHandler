package cl.assachile.web.service

import cl.assachile.web.entity.Product
import cl.assachile.web.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private var productRepository: ProductRepository) {
    fun listAll(): MutableIterable<Product> {
        return productRepository.findAll()
    }
}