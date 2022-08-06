package africa.semicolon.blogProject.controller;

import africa.semicolon.blogProject.dtos.requests.LoginUserRequest;
import africa.semicolon.blogProject.dtos.requests.RegisterUserRequest;
import africa.semicolon.blogProject.dtos.responses.LoginUserResponse;
import africa.semicolon.blogProject.dtos.responses.RegisterUserResponse;
import africa.semicolon.blogProject.exceptions.UserExistsException;
import africa.semicolon.blogProject.services.UserService;
import africa.semicolon.blogProject.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService = new UserServiceImpl();

    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest request) {
        try {
            RegisterUserResponse service = userService.registerUser(request);
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


}
