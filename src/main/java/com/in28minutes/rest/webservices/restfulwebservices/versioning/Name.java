package com.in28minutes.rest.webservices.restfulwebservices.versioning;

public class Name {

    private String fistName;
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public Name(){

    }
    public Name(String fistName, String lastName) {
        super();
        this.fistName = fistName;
        this.lastName = lastName;
    }
}
