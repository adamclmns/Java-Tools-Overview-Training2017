/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.vaadin.impl;

import com.adamclmns.training.sbdemo.vaadin.base.AbstractEntityEditor;
import com.adamclmns.training.sbdemo.entities.Product;
import com.adamclmns.training.sbdemo.repo.ProductRepo;
import com.adamclmns.training.sbdemo.session.SBDemoSession;
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
@SpringView(name = ProductEditViewImpl.VIEW_NAME)
public class ProductEditViewImpl extends AbstractEntityEditor<Product>(new ProductRepo()) implements View {

    public static final String VIEW_NAME = "ProductEditorInherited";
    Navigator navigator;
    
    @Autowired
    private ProductRepo repository;
    /* Fields to edit properties in product entity */
    TextField name = new TextField("Name");
    TextField description = new TextField("Description");

    Binder<Product> binder = new Binder<>(Product.class);

    @PostConstruct
    void init() {
        addComponents(name, description, actions);
        binder.bindInstanceFields(this);
        setSpacing(true);
        this.actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> {
            this.repository.save(entity);
            goToListView();
        });
        delete.addClickListener((Button.ClickEvent e) -> {
            repository.delete(entity);
            goToListView();
        });
        cancel.addClickListener((Button.ClickEvent e) -> {
            binder.removeBean();
            goToListView();
        });
    }

    @Override
    public void enter(ViewChangeEvent event) {
        editEntity();
    }

    @Override
    public final void editEntity() {
        Product c = session.getCurrentProduct();
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            entity = repository.findOne(c.getId());
        } else {
            entity = c;
        }
        cancel.setVisible(persisted);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(entity);

        setVisible(true);

        // A hack to ensure the whole form is visible
        save.focus();
        // Select all text in firstName field automatically
        name.selectAll();
    }

    public void setChangeHandler(ProductEditViewImpl.ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }

    public interface ChangeHandler {

        void onChange();
    }

    private void goToListView() {
        session.setCurrentProduct(null);
        getUI().getNavigator().navigateTo(ProductListView.VIEW_NAME);
    }
}
