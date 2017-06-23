/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.repo;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author adamd
 * @param <T>
 * @param <ID>
 */
public interface AbstractRepo<T, ID extends Serializable> extends JpaRepository<T, ID>{
    
}
