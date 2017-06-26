/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.vaadin.impl;

import com.adamclmns.training.sbdemo.entities.Product;
import com.adamclmns.training.sbdemo.repo.ProductRepo;
import com.adamclmns.training.sbdemo.session.SBDemoSession;
import com.vaadin.data.HasValue;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 *
 * @author adamd
 */


@SpringView(name=ProductListView.VIEW_NAME)
public class ProductListView extends VerticalLayout implements View {
    static Logger log = Logger.getLogger(ProductListView.class.getName());
    public static final String VIEW_NAME = "ProductList";
    
    @Autowired
    private ProductRepo repo;
    
    @Autowired
    SBDemoSession session;
    
    private Grid<Product> grid = new Grid<>(Product.class);
    
    @PostConstruct
    void init(){
        Notification.show("ProductListView.init()");
        TextField filter = new TextField();
        Button addNewBtn = new Button("New Product", FontAwesome.PLUS);
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid);
        addComponent(mainLayout);
        
        grid.setHeight(300, Unit.PIXELS);
        grid.setColumns("id", "name", "description");
        
        filter.setPlaceholder("Filter by name");
        
        //Select one item
        grid.asSingleSelect().addValueChangeListener((HasValue.ValueChangeEvent<Product> e) -> {
            if(e.getValue() != null){
                session.setCurrentProduct(e.getValue());
                goToEditor();
            }
        });
        
        //Create New
        addNewBtn.addClickListener((Button.ClickEvent e) -> {
            session.setCurrentProduct(new Product(""));
            goToEditor();
        });
        
        //Put Stuff in the Grid
        listProducts(null);
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        log.debug("Entering View - PrdouctListView");
        listProducts(null); //Refresh grid view

    }
    
    private void goToEditor(){
        //getUI().getNavigator().navigateTo(ProductEditView.VIEW_NAME);
        getUI().getNavigator().navigateTo(ProductEditView.VIEW_NAME);
    }
    
    private void listProducts(String filterText){
        if(StringUtils.isEmpty(filterText)){
            grid.setItems(repo.findAll());
        } else {
            grid.setItems(repo.findByNameStartsWithIgnoreCase(filterText));
        }
    }
    
}
