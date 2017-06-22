package com.adamclmns.training.sbdemo.vaadin.impl;

import com.adamclmns.training.sbdemo.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.adamclmns.training.sbdemo.repo.CustomerRepo;
import com.adamclmns.training.sbdemo.session.SBDemoSession;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Notification;
import javax.annotation.PostConstruct;

@UIScope
@SpringView(name = CustomerListView.VIEW_NAME)
public class CustomerListView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "CustomerList";
    @Autowired
    SpringViewProvider viewProvider;
    
    @Autowired
    private CustomerRepo repo;

    @Autowired
    private SBDemoSession session;

    private Grid<Customer> grid = new Grid<>(Customer.class);
        TextField filter = new TextField();
        Button addNewBtn = new Button("New Customer", FontAwesome.PLUS);
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid);
    @PostConstruct
    protected void init() {
        // build layout
        Notification.show("CustomerListView.init()");
        
        addComponent(mainLayout);

        grid.setHeight(300, Unit.PIXELS);
        grid.setColumns("id", "firstName", "lastName");

        filter.setPlaceholder("Filter by last name");

        // Hook logic to components
        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            session.setCurrentCustomer(e.getValue());
            getUI().getNavigator().navigateTo(CustomerEditView.VIEW_NAME);
            //editor.editEditCustomer(e.getValue());
        });
        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> {
            session.setCurrentCustomer(new Customer(""));
            getUI().getNavigator().navigateTo(CustomerEditView.VIEW_NAME);
        });
        
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
        listCustomers("");
        //Notification.show("Welcome to the Customer List View");
    }


}
