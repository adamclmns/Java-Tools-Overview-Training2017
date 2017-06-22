package com.adamclmns.training.sbdemo.vaadin.impl;

import com.adamclmns.training.sbdemo.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.ui.VerticalLayout;

import com.adamclmns.training.sbdemo.repo.CustomerRepo;
import com.adamclmns.training.sbdemo.session.SBDemoSession;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import javax.annotation.PostConstruct;

@SpringView(name = CustomerEditView.VIEW_NAME)
public class CustomerEditView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "CustomerEdit";

    //Inject a Dependency... in this case a repository/service
    @Autowired
    private CustomerRepo repository;
    @Autowired
    private SBDemoSession session;

    private Customer entity;

    //If you name them correctly, Binder picks them up automtically for binding
    TextField firstName = new TextField("FirstName");
    TextField lastName = new TextField("LastName");

    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    Binder<Customer> binder = new Binder<>(Customer.class);

    @PostConstruct
    void init() {
        addComponents(firstName, lastName, actions);
        binder.bindInstanceFields(this);
        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        // wire action buttons to save, delete and reset
        save.addClickListener((Button.ClickEvent e) -> {
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
        Customer c = session.getCurrentCustomer();
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
        cancel.setVisible(true);

        // Bind customer properties to similarly named fields
        // Could also use annotation or "manual binding" or programmatically
        // moving values from fields to entities before saving
        binder.setBean(entity);

        setVisible(true);

        // A hack to ensure the whole form is visible
        save.focus();
    }

    public void setChangeHandler(CustomerEditView.ChangeHandler h) {
        // ChangeHandler is notified when either save or delete
        // is clicked
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }

    public interface ChangeHandler {

        void onChange();
    }

    private void goToListView() {
        session.setCurrentCustomer(null);
        getUI().getNavigator().navigateTo(CustomerListView.VIEW_NAME);
    }
}
