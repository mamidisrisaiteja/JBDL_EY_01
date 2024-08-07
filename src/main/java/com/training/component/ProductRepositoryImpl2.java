package com.training.component;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component("repo2")
//@Primary
public class ProductRepositoryImpl2 implements ProductRepository{

    public ProductRepositoryImpl2(){
        System.out.println("ProductRepositoryImpl2()");
    }
    @Override
    public void doSomething(){
        System.out.println("In ProductRepositoryImpl2 do something");
    }
}
