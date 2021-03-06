/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.persistence.repo;

import com.adamclmns.training.sbdemo.persistence.entities.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Service;
//Spring & JPA Magic -https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details
@Service
public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findByNameStartsWithIgnoreCase(String name);
}
