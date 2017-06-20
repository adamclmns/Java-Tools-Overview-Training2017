package com.adamclmns.training.sbdemo;

import com.adamclmns.training.sbdemo.entities.Customer;
import com.adamclmns.training.sbdemo.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.adamclmns.training.sbdemo.repo.CustomerRepo;
import com.adamclmns.training.sbdemo.repo.ProductRepo;

@SpringBootApplication
public class SBDemoApplication {

    private static final Logger log = LoggerFactory.getLogger(SBDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SBDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(CustomerRepo customeRepo, ProductRepo productRepo) {
        return (args) -> {
            // save a couple of customers
            customeRepo.save(new Customer("Jack", "Bauer"));
            customeRepo.save(new Customer("Chloe", "O'Brian"));
            customeRepo.save(new Customer("Kim", "Bauer"));
            customeRepo.save(new Customer("David", "Palmer"));
            customeRepo.save(new Customer("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : customeRepo.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Customer customer = customeRepo.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
            log.info("--------------------------------------------");
            for (Customer bauer : customeRepo
                    .findByLastNameStartsWithIgnoreCase("Bauer")) {
                log.info(bauer.toString());
            }
            log.info("");
            
            
            productRepo.save(new Product("TEST Product"));
            productRepo.save(new Product("Another Product"));
        };
    }
}
