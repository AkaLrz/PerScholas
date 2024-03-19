package com.tommy.capstone_perscholas;

import com.tommy.capstone_perscholas.Service.UserLoginServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
class CapStonePerScholasApplicationTests {

    @Autowired
    private UserLoginServiceImpl userLoginService;
    @Test
    void contextLoads() {
    }

    @Test
    void testUserLogin(UserDetails userDetails) {
//        assertTrueValidate(userLoginService.findUserByName(userDetails.getUsername());
    }

}
