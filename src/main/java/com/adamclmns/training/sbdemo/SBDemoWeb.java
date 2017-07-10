/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author adamd
 */
@Configuration
public class SBDemoWeb {
    @Bean 
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet() {});
        registrationBean.addUrlMappings("/console/*");
        // This is for External Access to the H2 Console - like when this is deployed to Tomcat
        registrationBean.addInitParameter("webAllowOthers","true");
        return registrationBean;
    }
    
}
