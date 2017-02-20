package com.example.lib;

/**
 * Created by gschool on 2/20/17.
 */
public class Competition {
    private Integer id;
    private String name;
    private String result;

    public Competition() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return String.format("id: %s, name: %s, result: %s", getId(), getName(), getResult());
    }
}
