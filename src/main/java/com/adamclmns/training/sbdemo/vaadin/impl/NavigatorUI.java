/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.vaadin.impl;

import com.adamclmns.training.sbdemo.repo.CustomerRepo;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

/**
 *
 * @author Adam
 */
@SpringUI(path="/NavigatorUI")
public class NavigatorUI extends UI {
    Navigator navigator;
    protected static final String MAINVIEW = "main";
    
    @Override
    protected void init(VaadinRequest request){
        getPage().setTitle("Navigation Example");
        
        navigator = new Navigator(this, this);
        
    }
}
