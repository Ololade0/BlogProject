package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.User;
import africa.semicolon.blogProject.dtos.requests.AddArticleRequest;
import africa.semicolon.blogProject.dtos.requests.CreateBlogRequest;
import africa.semicolon.blogProject.dtos.requests.LoginUserRequest;
import africa.semicolon.blogProject.dtos.requests.RegisterUserRequest;
import africa.semicolon.blogProject.dtos.responses.AddArticleResponse;
import africa.semicolon.blogProject.dtos.responses.CreateBlogResponse;
import africa.semicolon.blogProject.dtos.responses.LoginUserResponse;
import africa.semicolon.blogProject.dtos.responses.RegisterUserResponse;

import java.util.List;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest request);

    long size();

    LoginUserResponse login(LoginUserRequest request);

    void deleteAll();

    User getUserById(String userId);


    User getUserByEmail(String email);

    void reSave(User user);

    CreateBlogResponse createBlog(CreateBlogRequest createBlogRequest);
}

