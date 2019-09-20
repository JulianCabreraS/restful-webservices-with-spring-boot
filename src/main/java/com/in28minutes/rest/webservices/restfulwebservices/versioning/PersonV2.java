package com.in28minutes.rest.webservices.restfulwebservices.versioning;

public class PersonV2 {
    public PersonV2(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    private Name name;
}
