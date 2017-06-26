/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author adc1116
 */
@Entity
public class CustomerOrder implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    
    @OneToMany
    private List<Product> products;
    private BigInteger totalCost;
    
    public CustomerOrder(){
        
    }

    public Long getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigInteger getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigInteger totalCost) {
        this.totalCost = totalCost;
    }
    
    
}
