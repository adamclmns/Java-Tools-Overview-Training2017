/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.vaadin.impl;

import com.adamclmns.training.sbdemo.entities.Product;
import com.adamclmns.training.sbdemo.repo.ProductRepo;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author adamd
 */
@SpringView(name = ProductEditView.VIEW_NAME)
public class ProductEditView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "ProductEditor";
    Navigator navigator;
    
    @Autowired
    private ProductRepo repository;

    /**
     * The currently edited product
     */
    private Product product;

    /* Fields to edit properties in product entity */
    TextField name = new TextField("Name");
    TextField description = new TextField("Description");

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    Binder<Product> binder = new Binder<>(Product.class);

    @PostConstruct
    void init() {
        this.repository = repository;

        addComponents(name, description, actions);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> repository.save(product));
        delete.addClickListener(e -> repository.delete(product));
        cancel.addClickListener(e -> editProduct(product));
        setVisible(false);
    }

    public interface ChangeHandler {

        void onChange();
    }

    public final void editProduct(Product c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            product = repository.findOne(c.getId());
        } else {
            product = c;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(product);

        setVisible(true);

        // A hack to ensure the whole form is visible
        save.focus();
        // Select all text in firstName field automatically
        name.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to the Animal Farm");
    }
}
