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
import com.vaadin.data.HasValue;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Notification;
import javax.annotation.PostConstruct;

@SpringView(name = CustomerListView.VIEW_NAME)
public class CustomerListView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "CustomerList";

    @Autowired
    private CustomerRepo repo;

    @Autowired
    private SBDemoSession session;

    private Grid<Customer> grid = new Grid<>(Customer.class);

    @PostConstruct
    protected void init() {
        Notification.show("CustomerListView.init()");
        TextField filter = new TextField();
        Button addNewBtn = new Button("New Customer", FontAwesome.PLUS);
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid);
        addComponent(mainLayout);

        grid.setHeight(300, Unit.PIXELS);
        grid.setColumns("id", "firstName", "lastName");

        filter.setPlaceholder("Filter by last name");

        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        // Put selected Customer in Session and Navigate to Editor
        grid.asSingleSelect().addValueChangeListener((HasValue.ValueChangeEvent<Customer> e) -> {
            if (e.getValue() != null) {
                System.out.println("ListView - Clicking an Item.");
                session.setCurrentCustomer(e.getValue());
                goToEditor();

            }
        });
        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener((Button.ClickEvent e) -> {
            System.out.println("ListView - Clicking New Button");
            session.setCurrentCustomer(new Customer(""));
            goToEditor();
        });

        listCustomers(null);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.out.println("Entering View - CustomerListView");
        listCustomers(null); //Refresh grid view

    }

    private void goToEditor() {
        getUI().getNavigator().navigateTo(CustomerEditView.VIEW_NAME);
    }

    void listCustomers(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        } else {
            grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
        }
    }
}
