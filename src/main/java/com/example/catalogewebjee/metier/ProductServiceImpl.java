package com.example.catalogewebjee.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
   private long productindex=0;
   private final Map<Long,Product> productsMap=new HashMap<>();
    @Override
    public Product save(Product product) {
      if(product.getId()==null) product.setId(productindex++);

        return productsMap.put(product.getId(),product);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productsMap.values());
    }

    @Override
    public Product findById(Long id) {
        return productsMap.get(id);
    }

    @Override
    public List<Product> findByName(String kw) {
   List<Product> productList= productsMap.values().stream().filter(p->p.getName().contains(kw)).collect(Collectors.toList());

        return productList;
    }

    @Override
    public Product updateProduct(Product product) {
        return productsMap.put(product.getId(),product);
    }

    @Override
    public void delete(Long id) {
     productsMap.remove(id);
    }

    @Override
    public void initCataloge() {
        save(new Product(null,"ordinateur HP 234",5233));
        save(new Product(null,"Imprimante EPson 527",92783));
        save(new Product(null,"Smart phone Sumsung",4000));


    }
}
