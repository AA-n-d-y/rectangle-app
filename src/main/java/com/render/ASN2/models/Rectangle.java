package com.render.ASN2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Rectangle")
public class Rectangle {
    // Setting up the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Other attributes
    String name;
    int width;
    int height;
    String color;

    // Default constructor
    public Rectangle() {
        
    }

    // Parameterized constructor
    public Rectangle(String name, int width, int height, String color) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // Getters and Setters
    public int getID() {
        return this.id;
    }
    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return this.width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return this.color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
