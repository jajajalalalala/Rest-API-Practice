package com.in28minutes.rest.webservices.restfulwebservices.exception;

import javax.xml.crypto.Data;
import java.util.Date;



public class ExceptionResponse {

    //timestamp
    //messahe

    //detail


    private Date timestamp;
    private String messages;
    private String detail;

    public ExceptionResponse(Date timestamp, String messages, String detail) {
        super();
        this.timestamp = timestamp;
        this.messages = messages;
        this.detail = detail;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
