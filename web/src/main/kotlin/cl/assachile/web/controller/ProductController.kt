package cl.assachile.web.controller

import cl.assachile.web.entity.Product
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import cl.assachile.web.service.ProductService
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/api")
internal class ProductController(private val productService: ProductService) {
    @GetMapping("/getProducts")
    fun listProducts(): List<*>? {
        return productService.listAll() as List<*>?
    }
}