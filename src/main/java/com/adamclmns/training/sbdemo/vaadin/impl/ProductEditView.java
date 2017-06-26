/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.vaadin.impl;

import com.adamclmns.training.sbdemo.entities.Product;
import com.adamclmns.training.sbdemo.repo.ProductRepo;
import com.adamclmns.training.sbdemo.session.SBDemoSession;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
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
    private static final Logger log = Logger.getLogger(ProductEditView.class.getName());
    public static final String VIEW_NAME = "ProductEditor";
    Navigator navigator;

    @Autowired
    private ProductRepo repository;
    @Autowired
    private SBDemoSession session;
    
    private Product entity;

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
        addComponents(name, description, actions);
        binder.bindInstanceFields(this);
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        // wire action buttons to save, delete and reset
        save.addClickListener(e -> {
            repository.save(entity);
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
        setVisible(false);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        editEntity();
    }

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

    public void setChangeHandler(ProductEditView.ChangeHandler h) {
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
