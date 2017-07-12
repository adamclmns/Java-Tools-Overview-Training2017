/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.vaadin.views;

import com.adamclmns.training.sbdemo.persistence.entities.Product;
import com.adamclmns.training.sbdemo.persistence.repo.ProductRepo;
import com.adamclmns.training.sbdemo.vaadin.views.base.AbstractListView;
import com.vaadin.spring.annotation.SpringView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 *
 * @author adamd
 */


@SpringView(name=ProductListView.VIEW_NAME)
public class ProductListView extends AbstractListView<Product> {
    private static final Logger log = LoggerFactory.getLogger(ProductListView.class.getName());
    public static final String VIEW_NAME = "ProductList";
    
    @Autowired
    protected ProductRepo repo;
    
    public ProductListView(){
        super(Product.class);
        // Lazy Instantiation and Lazy Fetching on the "product" side of the relationship causes a Hibernate Session Error. Removing this column, since it's not needed here. 
        grid.removeColumn("customerOrders");
        editorViewName = ProductEditView.VIEW_NAME;
    }
    
    @Override
    protected void listEntities(String filterText){
        if(StringUtils.isEmpty(filterText)){
            this.grid.setItems(repo.findAll());
        } else {
            this.grid.setItems(repo.findByNameStartsWithIgnoreCase(filterText));
        }
    }
    
}
