package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

//Controller
@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource  messageSource;
    //    Get
    //    URI - /hello-world
    //    Method - "HelloWorld"
    @GetMapping( path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }


    // Use a class
    @GetMapping( path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }


    // Use a path variable
    @GetMapping( path = "/hello-world-bean/part-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }


    @GetMapping( path = "/hello-world-internalized")
    public String helloWorldInternalized(){
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }
}
