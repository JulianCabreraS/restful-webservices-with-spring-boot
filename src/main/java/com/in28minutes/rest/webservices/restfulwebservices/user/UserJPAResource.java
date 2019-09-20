package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    //retrieveAllUser
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }
    //retrieveUser
    @GetMapping("/jpa/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id){
        Optional user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("id-"+id);

        //all users
        Resource resource = new Resource(user.get());
        ControllerLinkBuilder linkto = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkto.withRel("all-users"));

        return resource;
    }
    //retrieveUser
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
       userRepository.deleteById(id);
    }

    //Input details of the user
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = (User) userRepository.save(user);

        //CREATED
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //Retrieve post from a user
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllPost(@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id+"+id);
        }

        return userOptional.get().getPosts();
    }

    //Post posts for a user
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id+"+id);
        }

        User user = userOptional.get();
        post.setUser(user);
        postRepository.save(post);

        //CREATED
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }




}
