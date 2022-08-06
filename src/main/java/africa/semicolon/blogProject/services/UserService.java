package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.User;
import africa.semicolon.blogProject.dtos.requests.LoginUserRequest;
import africa.semicolon.blogProject.dtos.requests.RegisterUserRequest;
import africa.semicolon.blogProject.dtos.responses.LoginUserResponse;
import africa.semicolon.blogProject.dtos.responses.RegisterUserResponse;

import java.util.List;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest request);

    long size();

    LoginUserResponse login(LoginUserRequest request);

    void deleteAll();

    ;
}

