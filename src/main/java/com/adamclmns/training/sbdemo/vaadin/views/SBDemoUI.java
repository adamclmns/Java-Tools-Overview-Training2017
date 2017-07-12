/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.vaadin.views;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Adam
 */
@Theme("valo")
@SpringUI
@SpringViewDisplay
public class SBDemoUI extends UI implements ViewDisplay{
    private static final Logger log = LoggerFactory.getLogger(SBDemoUI.class);
    private Panel springViewDisplay;
    
    @Autowired
    private SpringViewProvider viewProvider;
    

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Vaadin Spring UI Example With Navigation");
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);
        setContent(root);
        
        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        navigationBar.addComponent(createNavigationButton("Home", IndexView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("ProductListView",   ProductListView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("CustomerListView", CustomerListView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("CustomerOrderListView", CustomerOrderListView.VIEW_NAME));
        root.addComponent(navigationBar);

        springViewDisplay = new Panel();
        springViewDisplay.setSizeFull();
        root.addComponent(springViewDisplay);
        root.setExpandRatio(springViewDisplay, 1.0f);
        
        // Creating the Navigator, May need to update this with newer Vaadin versions. Good Demo for swappable components. 
        Navigator navigator = new Navigator(this, springViewDisplay);
        navigator.addProvider(viewProvider);
        
    }
    
    @Override
    public void showView(View view){
        springViewDisplay.setContent((Component) view);
    }

    private Button createNavigationButton(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        // If you didn't choose Java 8 when creating the project, convert this to an anonymous listener class
        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }
}
