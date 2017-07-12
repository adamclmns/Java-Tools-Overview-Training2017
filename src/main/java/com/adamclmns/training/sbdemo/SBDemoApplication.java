package com.adamclmns.training.sbdemo;

import com.adamclmns.training.sbdemo.persistence.entities.Customer;
import com.adamclmns.training.sbdemo.persistence.entities.CustomerOrder;
import com.adamclmns.training.sbdemo.persistence.entities.Product;
import com.adamclmns.training.sbdemo.persistence.repo.CustomerOrderRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.adamclmns.training.sbdemo.persistence.repo.CustomerRepo;
import com.adamclmns.training.sbdemo.persistence.repo.ProductRepo;
import java.util.ArrayList;
import java.util.List;
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
    public CommandLineRunner loadData(CustomerRepo customeRepo, ProductRepo productRepo, CustomerOrderRepo customerOrderRepo) {
        return (args) -> {
            // save a few of customers
            Customer c1 = new Customer("Jack", "Bauer");
            Customer c2 = new Customer("Chloe","Bauer");
            Customer c3 = new Customer("Kim","Palmer");
            Customer c4 = new Customer("Michelle","Dessler");
            c1 = customeRepo.save(c1);
            c2 = customeRepo.save(c2);
            c3 = customeRepo.save(c3);
            c4 = customeRepo.save(c4);
            
            
            // save a few Products
            Product p1 = new Product("TEST Product");
            Product p2 = new Product("Another Product");
            Product p3 = new Product("Spidget Winners");
            //p1 = productRepo.save(p1);
            //p2 = productRepo.save(p2);
            //p3 = productRepo.save(p3);
            
            //save a few CustomerOrders
            //TODO: Customer Orders
            CustomerOrder co1 = new CustomerOrder();
            co1.setCustomer(c1);
            List<Product> prodList = new ArrayList<Product>();
            prodList.add(p1);
            prodList.add(p2);
            co1.setProducts(prodList);
            co1 = customerOrderRepo.save(co1);
            
            CustomerOrder co2 = new CustomerOrder();
            co2.setCustomer(c2);
            List<Product> prodList2 = new ArrayList<Product>();
            prodList2.add(p2);
            prodList2.add(p2);
            prodList2.add(p3);
            co2 = customerOrderRepo.save(co2);
            
            
        };
    }
}
