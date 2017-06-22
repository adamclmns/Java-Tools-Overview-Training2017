/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.vaadin.impl;

import com.adamclmns.training.sbdemo.repo.ProductRepo;
import com.adamclmns.training.sbdemo.session.SBDemoSession;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author adamd
 */


@SpringView(name=ProductListView.VIEW_NAME)
public class ProductListView extends VerticalLayout implements View {
    
    public static final String VIEW_NAME = "ProductList";
    
    @Autowired
    private ProductRepo repo;
    
    @Autowired
    SBDemoSession session;
    @PostConstruct
    void init(){
        addComponent(new Label("This is the ProductListView"));
    }
    @Override
    public void enter(ViewChangeEvent event) {
        //View happens in init()
    }
    
    
}
