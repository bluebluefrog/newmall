package com.wjb.newmall.service;

import com.wjb.newmall.pojo.User;
import com.wjb.newmall.vo.ResponseVo;

public interface UserService{
    /**
     * 注册
     * @return
     */
    ResponseVo register(User user);

    /**
     * 登录
     */
    ResponseVo login(String username, String password);
}
