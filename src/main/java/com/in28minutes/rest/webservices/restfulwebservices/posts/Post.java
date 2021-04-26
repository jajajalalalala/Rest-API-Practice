package com.in28minutes.rest.webservices.restfulwebservices.posts;

import java.util.Date;

public class Post {
    private int id;
    private Date date;
    private String Content;

    protected Post(){

    }
    public Post(int id, Date date, String content) {
        super();
        this.id = id;
        this.date = date;
        Content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", date=" + date +
                ", Content='" + Content + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
