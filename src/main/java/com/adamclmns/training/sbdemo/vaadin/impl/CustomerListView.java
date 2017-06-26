package com.adamclmns.training.sbdemo.vaadin.impl;

import com.adamclmns.training.sbdemo.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.adamclmns.training.sbdemo.repo.CustomerRepo;
import com.adamclmns.training.sbdemo.vaadin.base.AbstractListView;
import com.vaadin.spring.annotation.SpringView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringView(name = CustomerListView.VIEW_NAME)
public class CustomerListView extends AbstractListView<Customer> {
    private static final Logger log = LoggerFactory.getLogger(CustomerListView.class.getName());
    public static final String VIEW_NAME = "CustomerList";

    
    public CustomerListView(){
        super(Customer.class);
        editorViewName = CustomerEditView.VIEW_NAME;
    }

    @Autowired
    protected CustomerRepo repo;

    @Override
    protected void listEntities(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            this.grid.setItems(repo.findAll());
        } else {
            this.grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
        }
    }
}
