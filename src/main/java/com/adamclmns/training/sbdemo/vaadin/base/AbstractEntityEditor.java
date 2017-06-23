/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.vaadin.base;

import com.adamclmns.training.sbdemo.repo.AbstractRepo;
import com.adamclmns.training.sbdemo.repo.RepoFactory;
import com.adamclmns.training.sbdemo.session.SBDemoSession;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author adamd
 * @param <T>
 */
public abstract class AbstractEntityEditor<T> extends VerticalLayout {

    private Class<? extends AbstractRepo<T,Long>> repository;

    @Autowired
    public SBDemoSession session;

    public T entity;
    //Text Fields must Be Specified in Concrete Class
    public Button save = new Button("Save", FontAwesome.SAVE);
    public Button cancel = new Button("Cancel");
    public Button delete = new Button("Delete", FontAwesome.TRASH_O);
    public CssLayout actions = new CssLayout(save, cancel, delete);
    
    // Think about this: from SO - https://stackoverflow.com/a/28435335
    Binder<T> binder;// = new Binder<>(T.class);
    
    public AbstractEntityEditor(){
        repository = new RepoFactory().createRepo( entity.getClass());
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        
        save.addClickListener((Button.ClickEvent e) -> {
            repository.save((T)entity);
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
    
    public void editEntity(){
        //THIS METHOD MUST BE OVERRIDDENIN IMPL
        try {
            throw new Exception("Not yet implemented");
        } catch (Exception ex) {
            Logger.getLogger(AbstractEntityEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void goToListView(){
        //THIS METHOD MUST BE OVERRIDDENIN IMPL
        try {
            throw new Exception("Not yet implemented");
        } catch (Exception ex) {
            Logger.getLogger(AbstractEntityEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
