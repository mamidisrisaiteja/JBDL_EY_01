package com.training.component;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component("repo1")
@Repository
//persistance logic

public class ProductRepositoryImpl implements ProductRepository{

    public ProductRepositoryImpl(){
        System.out.println("ProductRepositoryImpl()");
    }
    @Override
    public void doSomething(){
        System.out.println("In ProductRepositoryImpl do something");
    }
}
