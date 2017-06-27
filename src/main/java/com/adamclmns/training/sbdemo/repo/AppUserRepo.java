/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adamclmns.training.sbdemo.repo;

import com.adamclmns.training.sbdemo.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Adam
 */
public interface AppUserRepo extends JpaRepository<AppUser, Long>{
    
}
