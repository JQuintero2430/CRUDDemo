package com.jorge.preparation.cruddemo.services;

import com.jorge.preparation.cruddemo.models.Product;
import com.jorge.preparation.cruddemo.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    HashMap<String, Object> response;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    public ResponseEntity<Object> addProduct(Product product) {
        Optional<Product> body = this.productRepository.findProductByName(product.getName());
        response = new HashMap<>();
        if (body.isPresent() && product.getId() == null) {
            response.put("Error", true);
            response.put("message", "Product already exists");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.CONFLICT
            );
        }
        response.put("message", "Product added successfully");
        response.put("addedProduct", product);
        response.put("Error", false);
        productRepository.save(product);
        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateProduct(Product product) {
        Optional<Product> body = this.productRepository.findProductByName(product.getName());
        response = new HashMap<>();
        if (!body.isPresent()) {
            response.put("Error", true);
            response.put("message", "The product does not exist");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.CONFLICT
            );
        }
        response.put("Error", false);
        response.put("message", "The product was updated successfully");
        response.put("updatedProduct", product);
        this.productRepository.save(product);
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );
    }

    public ResponseEntity<Object> deleteProduct(Long id) {
        boolean exist = this.productRepository.existsById(id);
        response = new HashMap<>();
        if (!exist) {
            response.put("Error", true);
            response.put("message", "The product does not exist");
            return new ResponseEntity<>(
                    response,
                    HttpStatus.CONFLICT
            );
        }
        response.put("Error", false);
        response.put("message", "The product was deleted successfully");
        response.put("deletedProduct", this.productRepository.findById(id));
        this.productRepository.deleteById(id);
        return new ResponseEntity<>(
                response,
                HttpStatus.OK
        );

    }
}
