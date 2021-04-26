package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.helloworld.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;
    //GET /users
    //retrieveAllUsers
    @GetMapping( path = "/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }



    //Get /users/id
    //retrieveUser(int id)
    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retrieveOneUser(@PathVariable int id){
        User user = service.findOne(id);

        if (user == null){
            throw new UserNotFoundException("id-" + id);
        }

        //"all_users", SERVER_PATH + "/users"
        //retireveAllUsers

        EntityModel<User> resource = EntityModel.of(user);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all_user"));

        //HATEOAS

        return resource;
    }



//    Createed
//    input - details of user
//    output - Created & return the created URI
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){


        User savedUser = service.save(user);

//        Created
//        /user/{id}    savedUser.getId()
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser
                        .getId()).toUri();
        return ResponseEntity.created(location).build();
    }



    //Get /users/id
    //retrieveUser(int id)
    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteBy(id);

        if (user == null)
            throw new UserNotFoundException("id-" + id);
    }
}
