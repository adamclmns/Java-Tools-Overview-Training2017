/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author adc1116
 */
@Entity
public class CustomerOrder implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private Customer customer;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "customerOrder_products", joinColumns = @JoinColumn(name = "customerOrder_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<Product> products;
    
    
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public float getTotal(){
        //Adding a total field for the CustomerOrderListView. 
        // Using lambda's because the IDE said I could :)
        float total = 0;
        total = products.stream().map((p) -> p.getSalePrice()).reduce(total, (accumulator, _item) -> accumulator + _item);
        return total;
    }
    
    public int countItems(){
        return products.size();
    }
    
}
