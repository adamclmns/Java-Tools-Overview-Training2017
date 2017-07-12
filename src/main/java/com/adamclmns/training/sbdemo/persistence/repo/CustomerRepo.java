package com.adamclmns.training.sbdemo.persistence.repo;

import com.adamclmns.training.sbdemo.persistence.entities.Customer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}