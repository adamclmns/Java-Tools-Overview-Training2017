package com.adamclmns.training.sbdemo.repo;

import com.adamclmns.training.sbdemo.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends AbstractRepo<Customer, Long> {

	List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}