package com.jorge.preparation.cruddemo.repositories;

import com.jorge.preparation.cruddemo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByName(String name);

}
