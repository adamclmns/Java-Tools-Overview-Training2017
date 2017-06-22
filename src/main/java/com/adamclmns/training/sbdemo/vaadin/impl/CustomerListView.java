package com.adamclmns.training.sbdemo.vaadin.impl;

import com.adamclmns.training.sbdemo.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.adamclmns.training.sbdemo.repo.CustomerRepo;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import javax.annotation.PostConstruct;

@SpringView(name = CustomerListView.VIEW_NAME)
public class CustomerListView extends VerticalLayout implements View {
    public static final String VIEW_NAME="CustomerList";
    
    @Autowired
    private CustomerRepo repo;
    
    private Grid<Customer> grid;

    @PostConstruct
    protected void init() {
        // build layout
        grid = new Grid<>(Customer.class);
        TextField filter = new TextField();
        HorizontalLayout actions = new HorizontalLayout(filter);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid);
        addComponent(mainLayout);

        grid.setHeight(300, Unit.PIXELS);
        grid.setColumns("id", "firstName", "lastName");

        filter.setPlaceholder("Filter by last name");

        // Hook logic to components
        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));


        listCustomers(null);
    }

    // tag::listCustomers[]
    void listCustomers(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        } else {
            grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
        }
    }
    // end::listCustomers[]

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //View happens in init()
    }

}
