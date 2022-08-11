package africa.semicolon.blogProject.services;

import africa.semicolon.blogProject.data.repository.UserRepository;
import africa.semicolon.blogProject.dtos.requests.LoginUserRequest;
import africa.semicolon.blogProject.dtos.requests.RegisterUserRequest;
import africa.semicolon.blogProject.exceptions.UserExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userService.deleteAll();
    }

    @Test
    void registerUser_repositorySizeIsOneTest() {
        //given that i have a register form
        //when i try to register
        //check that respository size can increase
        RegisterUserRequest registerUserform = new RegisterUserRequest();
        registerUserform.setUserName("Ololade");
        registerUserform.setPassword("1234");
        userService.registerUser(registerUserform);
        assertEquals(1, userRepository.count());

    }

    @Test
    void registerUser_UserCanLogin() {
        RegisterUserRequest registerUserform = new RegisterUserRequest();
        registerUserform.setUserName("Ololade");
        registerUserform.setPassword("1234");
        userService.registerUser(registerUserform);


        LoginUserRequest request = new LoginUserRequest();
        request.setUserName("Ololade");
        request.setPassword("1234");
        userService.login(request);
        assertEquals(1, userRepository.count());
    }


    @Test
    void duplicateUserUserNameThrowsExceptionTest() {
        RegisterUserRequest registerUserform = new RegisterUserRequest();
        registerUserform.setUserName("Ololade");
        registerUserform.setPassword("1234");
        registerUserform.setEmail("ola@gmail.com");
        userService.registerUser(registerUserform);
        assertThrows(UserExistsException.class, () -> userService.registerUser(registerUserform));
         assertEquals(1, userService.size());

    }





}