package com.adamclmns.training.sbdemo.vaadin.impl;

import com.adamclmns.training.sbdemo.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.adamclmns.training.sbdemo.repo.ProductRepo;

//@SpringUI(path="/ProductUI")
public class ProductUI extends UI {

    private final ProductRepo repo;

    private final ProductEditor editor;

    final Grid<Product> grid;

    final TextField filter;

    private final Button addNewBtn;

    @Autowired
    public ProductUI(ProductRepo repo, ProductEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(Product.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New Product", FontAwesome.PLUS);
    }

    @Override
    protected void init(VaadinRequest request) {
        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
        setContent(mainLayout);

        grid.setHeight(300, Unit.PIXELS);
        grid.setColumns("id", "name", "description", "cost", "salePrice");

        filter.setPlaceholder("Filter by name");

        // Hook logic to components
        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listProducts(e.getValue()));

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editProduct(e.getValue());
        });

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editProduct(new Product("")));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listProducts(filter.getValue());
        });

        // Initialize listing
        listProducts(null);
    }

    // tag::listCustomers[]
    void listProducts(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        } else {
            grid.setItems(repo.findByNameStartsWithIgnoreCase(filterText));
        }
    }
    // end::listCustomers[]

}
