package africa.semicolon.blogProject.controller;

import africa.semicolon.blogProject.data.model.Comment;
import africa.semicolon.blogProject.dtos.requests.*;
import africa.semicolon.blogProject.dtos.responses.*;


import africa.semicolon.blogProject.exceptions.BlogExistsException;
import africa.semicolon.blogProject.exceptions.UserExistsException;

import africa.semicolon.blogProject.services.BlogService;
import africa.semicolon.blogProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private BlogService blogService;


    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request) {
        try {
            RegisterUserResponse service = userService.registerUser(request);
            return new ResponseEntity<>(service, HttpStatus.CREATED);
        } catch (UserExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/article")
    public ResponseEntity<?> addArticle(@RequestBody AddArticleRequest request) {
        try {
            var service = blogService.addArticle(request);
            return new ResponseEntity<>(service, HttpStatus.CREATED);
        } catch (UserExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request) {
        try {
            LoginUserResponse response = userService.login(request);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (UserExistsException message) {
            return new ResponseEntity<>(message.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/user/blog")
    public ResponseEntity<?> createBlog(@RequestBody CreateBlogRequest createBlogRequest) {
        try {
            CreateBlogResponse createBlogResponse = userService.createBlog(createBlogRequest);
            return new ResponseEntity<>(createBlogResponse, HttpStatus.ACCEPTED);
        } catch (BlogExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(),HttpStatus.ACCEPTED);
    }


    @PutMapping("/user/comment")
    public ResponseEntity<?> addComment(@RequestBody AddCommentRequest addCommentRequest) {
        try {
            Comment addCommentResponse = userService.addComment(addCommentRequest);
            return new ResponseEntity<>(addCommentResponse, HttpStatus.CREATED);
        } catch (UserExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping("/article")
    public ResponseEntity<?> deleteArticle(@RequestBody DeleteRequest deleteRequest){
        try {
            String message =  blogService.deleteArticle(deleteRequest);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (UserExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
