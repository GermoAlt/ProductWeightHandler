package cl.assachile.productWeightHandler.controller

import cl.assachile.productWeightHandler.entity.Product
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import cl.assachile.productWeightHandler.service.ProductService
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/api")
internal class ProductController(private val productService: ProductService) {
    @get:GetMapping("/getProducts")
    val products: List<Product>
        get() = productService.listAll() as List<Product>
}