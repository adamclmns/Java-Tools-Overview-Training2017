/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.rest;

import com.adamclmns.training.sbdemo.entities.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.adamclmns.training.sbdemo.repo.CustomerRepo;

/**
 * https://spring.io/guides/gs/rest-service/
 *
 * @author adamd
 */
@RestController
public class CustomerRestController {
    
    @Autowired
    CustomerRepo repo;

    // http://localhost:8080/svc/customer?lastName=Palmer
    @RequestMapping("/svc/customer")
    public List<Customer> greeting(@RequestParam(value="lastName", defaultValue="Bauer") String lastName) {
        List<Customer> ret = repo.findByLastNameStartsWithIgnoreCase(lastName);
        return ret;
    }
}
