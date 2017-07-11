/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author adamd
 */
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private float cost;
    private float salePrice;
    @ManyToMany(mappedBy = "products")
    private List<CustomerOrder> customerOrders;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
        this.description = "";
        this.cost = (float) 19.99;
        this.salePrice = (float) (this.cost * 1.5);
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

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public String toString() {
        return String.format("%s, %f]",
                name, salePrice);
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

}
