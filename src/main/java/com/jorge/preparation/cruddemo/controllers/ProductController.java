package com.jorge.preparation.cruddemo.controllers;

import com.jorge.preparation.cruddemo.models.Product;
import com.jorge.preparation.cruddemo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/v1/products"})
public class ProductController {
    public final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"/", "", "index"})
    public List<Product> getProducts() {
        return this.productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {
        return this.productService.addProduct(product);
    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        return this.productService.updateProduct(product);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        return this.productService.deleteProduct(id);
    }

}
