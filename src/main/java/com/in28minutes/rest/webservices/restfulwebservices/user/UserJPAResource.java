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

import javax.swing.plaf.ListUI;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;
    //GET /users
    //retrieveAllUsers
    @GetMapping( path = "/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }



    //Get /users/id
    //retrieveUser(int id)
    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> retrieveOneUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()){
            throw new UserNotFoundException("id-" + id);
        }

        //"all_users", SERVER_PATH + "/users"
        //retireveAllUsers

        EntityModel<User> resource = EntityModel.of(user.get());

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all_user"));

        //HATEOAS

        return resource;
    }



    //    Createed
//    input - details of user
//    output - Created & return the created URI
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){


        User savedUser = userRepository.save(user);

//        Created
//        /user/{id}    savedUser.getId()
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/jpa/{id}")
                .buildAndExpand(savedUser
                        .getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@Valid @RequestBody int id, @RequestBody Post post){
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()){
            throw new UserNotFoundException("id-" + id);
        }

        User user = userOptional.get();

        post.setUser(user);
        postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post
                        .getId()).toUri();
        return ResponseEntity.created(location).build();
    }



    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllUsers(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("id-" + id);
        }
        return user.get().getPosts();

    }


    //Get /users/id
    //retrieveUser(int id)
    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
         userRepository.deleteById(id);


    }
}
