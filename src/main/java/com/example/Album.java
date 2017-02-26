package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by gschool on 2/25/17.
 */
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;
    private String bandName;


    public String getName() {
        return name;
    }

    public Album setName(String name) {
        this.name = name;
        return this;
    }

    public String getBandName() {
        return bandName;
    }

    public Album setBandName(String bandName) {
        this.bandName = bandName;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Album setId(Long id) {
        this.id = id;
        return this;
    }
}
