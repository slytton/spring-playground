package com.example.lib;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by gschool on 2/18/17.
 */
public class Climber {
    public String firstName;
    public String lastName;
    @JsonFormat(pattern="MM-dd-yy")
    public Date startedClimbingOn;
    public Integer age;

    public Climber(String firstName, String lastName, Date startedClimbingOn, Integer age){
        this.firstName = firstName;

        this.lastName = lastName;
        this.startedClimbingOn = startedClimbingOn;
        this.age = age;
    }
}
