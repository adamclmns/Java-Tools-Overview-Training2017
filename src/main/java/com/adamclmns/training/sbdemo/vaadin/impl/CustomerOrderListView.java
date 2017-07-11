/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.vaadin.impl;

import com.adamclmns.training.sbdemo.entities.CustomerOrder;
import com.adamclmns.training.sbdemo.repo.CustomerOrderRepo;
import com.adamclmns.training.sbdemo.repo.ProductRepo;
import com.adamclmns.training.sbdemo.vaadin.base.AbstractListView;
import com.vaadin.spring.annotation.SpringView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Adam
 */
@SpringView(name=CustomerOrderListView.VIEW_NAME)
public class CustomerOrderListView extends AbstractListView<CustomerOrder> {
    public static final String VIEW_NAME="CustomerOrderList";
    @Autowired
    protected CustomerOrderRepo repo;
    @Autowired
    protected ProductRepo productRepo;
    
    public CustomerOrderListView() {
        super(CustomerOrder.class);
        grid.removeColumn("products");
        grid.addColumn(CustomerOrder::getTotal).setCaption("Total Sale");
        grid.addColumn(CustomerOrder::countItems).setCaption("Number of Items");
        editorViewName = null;
    }

    @Override
    protected void listEntities(String filterText) {
        this.grid.setItems(repo.findAll());
    }

}
