package com.example.hexagonal.domain.model;

import jakarta.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Embedded
    private Address address;

    public Customer() {}

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}
