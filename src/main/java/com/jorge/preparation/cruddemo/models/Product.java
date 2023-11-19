package com.jorge.preparation.cruddemo.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (unique = true)
    private String name;
    private double price;
    private LocalDate date;

    private int createdAgo;

    public Product() {
    }

    public Product(String name, double price, LocalDate date) {
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public Product(Long id, String name, double price, LocalDate date) {
        this(name, price, date);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.createdAgo = Period.between(this.date, LocalDate.now()).getYears();
    }

    public int getCreatedAgo() {
        return createdAgo;
    }

}
