package com.example.spring.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Forecast {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private Date date;
    private int temperature;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public Forecast() {
    }

    public Forecast(Date date, int temperature, Location location) {
        this.date = date;
        this.temperature = temperature;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
