package com.adamclmns.training.sbdemo.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Customer implements Serializable {

    @Transient
    private static final String[] fields = {"id", "firstName", "lastName"};

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    protected Customer() {
    }

    public Customer(String firstName) {
        this.firstName = firstName;
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String[] getFields() {
        return Customer.fields;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id,
                firstName, lastName);
    }

}
