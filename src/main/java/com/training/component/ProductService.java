package com.training.component;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service

public class ProductService {
  @Autowired
 // @Qualifier("repo1")
  private ProductRepository productRepository;

    //private ProductRepository productRepository=new ProductRepositoryImpl();
    // this is thight coupline
    public ProductService(){
        System.out.println("ProductService()");
    }

    @PostConstruct
    public void init(){
        productRepository.doSomething();
    }
}
