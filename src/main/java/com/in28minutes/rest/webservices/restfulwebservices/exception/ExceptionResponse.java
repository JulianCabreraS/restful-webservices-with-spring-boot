package com.in28minutes.rest.webservices.restfulwebservices.exception;


import java.util.Date;

public class ExceptionResponse {
    //timestamp
    private Date timestamp;
    //message
    private String mesage;
    //detail
    private String details;


    public ExceptionResponse(Date timestamp, String mesage, String details) {
        super();
        this.timestamp = timestamp;
        this.mesage = mesage;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMesage() {
        return mesage;
    }

    public String getDetails() {
        return details;
    }







}
