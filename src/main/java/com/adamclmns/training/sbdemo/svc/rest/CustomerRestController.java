/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.svc.rest;

import com.adamclmns.training.sbdemo.persistence.entities.Customer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.adamclmns.training.sbdemo.persistence.repo.CustomerRepo;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value="/svc/customersByLastName", method=GET)
    @ResponseBody
    public List<Customer> getAllCustomerByLastName(@RequestParam(value="lastName", defaultValue="Bauer") String lastName) {
        List<Customer> ret = repo.findByLastNameStartsWithIgnoreCase(lastName);
        return ret;
    }
    
    @RequestMapping(value="/svc/customer/new", method=PUT)
    @ResponseBody
    public void putNewCustomer(@RequestParam(value="firstName", defaultValue="James") String firstName,
            @RequestParam(value="lastName",defaultValue="Bond") String lastName){
        repo.save(new Customer(firstName,lastName));
        
    }
}
