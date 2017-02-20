package com.example.lib;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gschool on 2/18/17.
 */
public class Climber {
    private String firstName;
    private String lastName;
    @JsonFormat(pattern="MM-dd-yy")
    private Date startedClimbingOn;
    private Integer age;
    private Competition[] competitions;
    private Integer id;

    public Climber() {}

    public Climber(String firstName, String lastName, Date startedClimbingOn, Integer age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.startedClimbingOn = startedClimbingOn;
        this.age = age;
        this.competitions = new Competition[]{};
    }

    public Climber(Integer id, String firstName, String lastName, Date startedClimbingOn, Integer age){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startedClimbingOn = startedClimbingOn;
        this.age = age;
        this.competitions = new Competition[]{};
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

    public Date getStartedClimbingOn() {
        return startedClimbingOn;
    }

    public void setStartedClimbingOn(Date startedClimbingOn) {
        this.startedClimbingOn = startedClimbingOn;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Competition[] getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Competition[] competitions) {
        this.competitions = competitions;
    }

    @Override
    public String toString() {
        Competition[] comps = getCompetitions();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");

        String result = String.format("Name: %s %s\n" +
//                "startedClimbingOn: %s\n" +
                "age: %d", getFirstName(), getLastName(), getAge());

        if(comps != null){
            result += "\ncompetitions: ";
            for (int i = 0; i < comps.length; i++) {
                Competition comp = comps[i];
                String toAdd = "\n\t";
                if(i == 0) result += toAdd + comp.toString();
                else result += "," + toAdd + comp.toString();

            }
        }

        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
