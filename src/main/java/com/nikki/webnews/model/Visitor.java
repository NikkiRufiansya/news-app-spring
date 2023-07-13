package com.nikki.webnews.model;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "visitor")
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int count;

    // Constructors, getters, and setters

    public Visitor() {
        // Default constructor
    }

    public Visitor(LocalDate date, int count) {
        this.date = date;
        this.count = count;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
