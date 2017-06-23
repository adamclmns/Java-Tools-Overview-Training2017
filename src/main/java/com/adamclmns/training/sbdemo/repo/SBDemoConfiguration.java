/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.repo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

/**
 *
 * @author adamd
 */
@Configuration
@EnableSpringConfigured
@ComponentScan(basePackages = {"com.adamclmns.training"}, excludeFilters={
    @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, value=AbstractRepo.class)})
public class SBDemoConfiguration {
    
}
