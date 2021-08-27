package com.wjb.newmall.service.impl;

import com.wjb.newmall.NewmallApplicationTests;
import com.wjb.newmall.enums.RoleEnum;
import com.wjb.newmall.pojo.User;
import com.wjb.newmall.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserServiceImplTest extends NewmallApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void register() {
        User user = new User();
        user.setEmail("12331211");
        user.setPassword("1234516");
        user.setUsername("test11");
        user.setRole(RoleEnum.CUSTOMER.getCode());
        userService.register(user);
    }
}