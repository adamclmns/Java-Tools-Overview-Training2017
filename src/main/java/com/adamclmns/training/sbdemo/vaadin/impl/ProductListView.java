/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.vaadin.impl;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author adamd
 */


@SpringView(name=ProductListView.VIEW_NAME)
public class ProductListView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "ProductList";
    
    @PostConstruct
    void init(){
        addComponent(new Label("This is the ProductListView"));
    }
    @Override
    public void enter(ViewChangeEvent event) {
        //View happens in init()
    }
    
    
}
