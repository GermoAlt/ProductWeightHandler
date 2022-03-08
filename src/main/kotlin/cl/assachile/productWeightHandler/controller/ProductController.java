package cl.assachile.productWeightHandler.controller;

import cl.assachile.productWeightHandler.entity.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import cl.assachile.productWeightHandler.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
class ProductController{
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProducts")
    public List<Product> getProducts(){
        return (List<Product>) productService.listAll();
    }
}