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
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SBDemoApplication extends SpringBootServletInitializer{

    private static final Logger log = LoggerFactory.getLogger(SBDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SBDemoApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(applicationClass);
    }
    
    private static Class<SBDemoApplication> applicationClass = SBDemoApplication.class;
    @Bean
    public CommandLineRunner loadData(CustomerRepo customeRepo, ProductRepo productRepo) {
        return (args) -> {
            // save a few of customers
            customeRepo.save(new Customer("Jack", "Bauer"));
            customeRepo.save(new Customer("Chloe", "O'Brian"));
            customeRepo.save(new Customer("Kim", "Bauer"));
            customeRepo.save(new Customer("David", "Palmer"));
            customeRepo.save(new Customer("Michelle", "Dessler"));
            
            // save a few Products
            productRepo.save(new Product("TEST Product"));
            productRepo.save(new Product("Another Product"));
            
            //save a few CustomerOrders
            //TODO: Customer Orders
        };
    }
}
