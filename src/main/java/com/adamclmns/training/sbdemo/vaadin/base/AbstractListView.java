/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.vaadin.base;

import com.adamclmns.training.sbdemo.entities.Customer;
import com.adamclmns.training.sbdemo.entities.Product;
import com.adamclmns.training.sbdemo.session.SBDemoSession;
import com.vaadin.data.HasValue;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author adc1116
 * @param <T>
 */
public abstract class AbstractListView<T extends Serializable> extends VerticalLayout implements View {

    private static final Logger log = LoggerFactory.getLogger(AbstractListView.class);

    @Autowired
    protected SBDemoSession session;

    protected HorizontalLayout actions;
    protected Grid<T> grid;
    protected String editorViewName;

    public AbstractListView(Class<T> klass) {
        // Doing reflection magic to Get the Column Names dynamically
        Field[] fields = klass.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
            log.info("FIELD NAME: " + field.getName());
        }
        String[] colNames = fieldNames.toArray(new String[fieldNames.size()]);
        //Setting up the grid, and the layout.
        this.grid = new Grid<>(klass);
        TextField filter = new TextField();
        Button addNewBtn = new Button("New", FontAwesome.PLUS);
        this.actions = new HorizontalLayout(filter, addNewBtn);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid);
        grid.setHeight(800, Unit.PIXELS);
        grid.setWidth(800, Unit.PIXELS);
        grid.setColumns(colNames);
        addComponent(mainLayout);

        filter.setPlaceholder("Filter by last name");

        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listEntities(e.getValue()));

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener((Button.ClickEvent e) -> {
            log.debug("ListView - Clicking New Button");
            try {
                saveSelectionToSession( klass.newInstance()); //Might see issues here... need to test
                goToEditor();
            } catch (InstantiationException | IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(AbstractListView.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        grid.asSingleSelect().addValueChangeListener((HasValue.ValueChangeEvent<T> e) -> {
            if (e.getValue() != null) {
                log.info("ListView - Clicking an Item.");
                saveSelectionToSession(e.getValue());
                log.info(e.getValue().getClass().getName());
                goToEditor();
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        listEntities(null);
    }

    protected void goToEditor() {
        getUI().getNavigator().navigateTo(this.editorViewName);
    }

    private void saveSelectionToSession(T selected) {
        // Any Entities added will need to be handled here. 
        if (selected.getClass().getName().equals(Product.class.getName())) {
            session.setCurrentProduct( (Product) selected);
        } else if (selected.getClass().getName().equals(Customer.class.getName())) {
            session.setCurrentCustomer((Customer) selected);
        }
    }

    protected abstract void listEntities(String filterText);
}
