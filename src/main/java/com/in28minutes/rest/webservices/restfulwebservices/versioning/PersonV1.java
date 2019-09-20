package com.in28minutes.rest.webservices.restfulwebservices.versioning;

public class PersonV1 {
    public String getName() {
        return name;
    }

    public PersonV1(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
