package com.tommy.capstone_perscholas;

import com.tommy.capstone_perscholas.Dao.UserDao;
import com.tommy.capstone_perscholas.Dto.UserDTO;
import com.tommy.capstone_perscholas.Service.UserLoginService;
import com.tommy.capstone_perscholas.Service.UserLoginServiceImpl;
import com.tommy.capstone_perscholas.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class UserLoginServiceTest {

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserLoginServiceImpl userLoginServiceImpl;

    @Test
    void testUserCreate() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("kxsxs@gmail.com");
        userDTO.setPassword("password");
        userDTO.setFirstName("kdad");
        userDTO.setLastName("dad");
        userLoginService.creat(userDTO);
        assertNotNull(userLoginServiceImpl.findUserByEmail("kdad@gmail.com"));
    }

    @Test
    void testUserLogin() {
        User user = userLoginServiceImpl.findUserByEmail("kdad@gmail.com");
        assertNotNull(user);
    }
}