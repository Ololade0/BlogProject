package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.Comment;
import africa.semicolon.blogProject.data.model.User;

import africa.semicolon.blogProject.dtos.requests.*;

import africa.semicolon.blogProject.dtos.responses.CreateBlogResponse;
import africa.semicolon.blogProject.dtos.responses.LoginUserResponse;
import africa.semicolon.blogProject.dtos.responses.RegisterUserResponse;
import jdk.dynalink.linker.LinkerServices;

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


    Comment addComment(AddCommentRequest addCommentRequest);

    List<User> findAllUsers();
}

