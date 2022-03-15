package com.example.catalogewebjee.metier;

import java.util.List;

public interface ProductService {

    Product save(Product product);
    List<Product> findAll();
    Product findById(Long id);
    List<Product> findByName(String kw);
    Product updateProduct(Product product);
    void delete(Long id);
    void initCataloge();
}
