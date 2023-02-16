package com.example.quotesgreatpeople.serviceTest;

import com.example.quotesgreatpeople.dto.UserDto;
import com.example.quotesgreatpeople.mapper.UserMapper;
import com.example.quotesgreatpeople.repository.UserRepository;
import com.example.quotesgreatpeople.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserServiceImpl userService;

    private static String userName = "UserUser";
    private static String userEmail = "userVasyaUser@gmail.com";

    @Test
    void createUserTest() {
        UserDto userIn = UserDto.builder()
                .name(userName)
                .email(userEmail)
                .password("1234")
                .build();

        final UserDto userAfter = userService.createUser(userIn);

        assertNotNull(userAfter);
        assertNotNull(userAfter.getCreationDate());
    }

}
