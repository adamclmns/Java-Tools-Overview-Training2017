/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.repo;

import com.adamclmns.training.sbdemo.entities.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Service;

@Service
public interface ProductRepo extends AbstractRepo<Product, Long> {

    List<Product> findByNameStartsWithIgnoreCase(String name);
}
