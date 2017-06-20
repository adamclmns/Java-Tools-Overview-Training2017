/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.entities;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author adamd
 */
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @Transient
    private static final String[] fields = {"id","name","description","cost","salePrice"};
    private String name;
    private String description;
    private BigDecimal cost;
    private BigDecimal salePrice;
    
    protected Product() {
    }
    
    public Product(String name){
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    public BigDecimal getSalePrice(){
        return salePrice;
    }
    public void setSalePrice(BigDecimal salePrice){
        this.salePrice = salePrice;
    }
    public String[] getFields(){
        return Product.fields;
    }
	@Override
	public String toString() {
		return String.format("Customer[id=%d, name='%s', description='%s', cost=%f, salePrice=%f]", id,
				name, description, cost, salePrice);
	}
     
}
