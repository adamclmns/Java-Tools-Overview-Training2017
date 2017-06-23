/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.repo;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author adamd
 * @param <T>
 */
public class RepoFactory<T> {
    
    @Autowired
    private ProductRepo productRepo;
    
    @Autowired
    private CustomerRepo customerRepo;
    
    private Class<T> type;
    private T t;
    
    public RepoFactory(){
        
    }
    public AbstractRepo createRepo(Class clazz){
        if (clazz.getName().equalsIgnoreCase("Product")){
            return productRepo;
        }else if (clazz.getName().equalsIgnoreCase("Customer")){
            return customerRepo;
        }
        throw new IllegalArgumentException("No Factory Available for given Type");
    }
}
