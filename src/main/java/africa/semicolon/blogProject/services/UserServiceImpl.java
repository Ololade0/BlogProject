package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.model.Blog;
import africa.semicolon.blogProject.data.model.User;
import africa.semicolon.blogProject.data.repository.UserRepository;
import africa.semicolon.blogProject.dtos.requests.LoginUserRequest;
import africa.semicolon.blogProject.dtos.requests.RegisterUserRequest;
import africa.semicolon.blogProject.dtos.responses.LoginUserResponse;
import africa.semicolon.blogProject.dtos.responses.RegisterUserResponse;
import africa.semicolon.blogProject.exceptions.UserExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

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
        User savedUser = userRepository.findUserByEmail(email);
        if (savedUser != null) throw new UserExistsException(email + "Already exist");
    }


    @Override
    public long size() {
        return userRepository.count();

    }

    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        LoginUserResponse loginUserResponse = new LoginUserResponse();
        User savedUser = userRepository.findUserByEmail(request.getEmail());
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

}
