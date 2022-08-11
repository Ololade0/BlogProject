package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.Blog;
import africa.semicolon.blogProject.data.model.User;
import africa.semicolon.blogProject.data.repository.BlogRepository;
import africa.semicolon.blogProject.data.repository.UserRepository;
import africa.semicolon.blogProject.dtos.requests.CreateBlogRequest;
import africa.semicolon.blogProject.dtos.requests.LoginUserRequest;
import africa.semicolon.blogProject.dtos.requests.RegisterUserRequest;
import africa.semicolon.blogProject.dtos.responses.CreateBlogResponse;
import africa.semicolon.blogProject.dtos.responses.LoginUserResponse;
import africa.semicolon.blogProject.dtos.responses.RegisterUserResponse;
import africa.semicolon.blogProject.exceptions.BlogExistsException;
import africa.semicolon.blogProject.exceptions.UserExistsException;
import africa.semicolon.blogProject.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserService userService;


    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        isExist(request.getEmail());
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUserName(request.getUserName());
        userRepository.save(user);
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("Registration successful");
        return registerUserResponse;
    }

    private void isExist(String email) {
        Optional<User> savedUser = userRepository.findUserByEmail(email);
        if (savedUser.isPresent())
            throw new UserExistsException(email + "Already exist");
    }


    @Override
    public long size() {
        return userRepository.count();

    }

    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        LoginUserResponse loginUserResponse = new LoginUserResponse();
        User savedUser = getUserByEmail(request.getEmail());
        if (!savedUser.getPassword().equalsIgnoreCase(request.getPassword())) {
            throw new UserExistsException(request + "incorrect password");
        }
        loginUserResponse.setMessage("lOGIN SUCCESSFUL");
        return loginUserResponse;
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public User getUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserNotFoundException("user with id " + userId + " not found");
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> foundUser = userRepository.findUserByEmail(email);
        if(foundUser.isEmpty()){throw new UserNotFoundException("User not found!");}
        return foundUser.get();
    }

    @Override
    public void reSave(User user) {
        userRepository.save(user);
    }

    @Override
    public CreateBlogResponse createBlog(CreateBlogRequest createBlogRequest) {
       // isExist(createBlogRequest.getName());
        User user = userRepository.getUserByEmail(createBlogRequest.getUserEmail());
        Blog blog = new Blog();
        blog.setName(createBlogRequest.getName());
        var blog_ = blogRepository.save(blog);
        user.setBlog(blog_);
        userService.reSave(user);
        CreateBlogResponse createBlogResponse = new CreateBlogResponse();
        createBlogResponse.setMessage("Blog successfully created");
        return createBlogResponse;

    }

//    private void isExist(String name) {
//        Blog savedBlog = blogRepository.findBlogByName(name);
//        if (savedBlog != null) throw new BlogExistsException(name + "Already exist");
//    }
    }





