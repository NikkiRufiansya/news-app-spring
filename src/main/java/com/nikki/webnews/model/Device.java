package com.nikki.webnews.model;


import jakarta.persistence.*;

@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nameDevice;

    // Constructors, getters, and setters

    public Device() {
        // Default constructor
    }

    public Device(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }
}

