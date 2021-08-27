package com.wjb.newmall.service.impl;

import com.wjb.newmall.dao.UserMapper;
import com.wjb.newmall.enums.ResponseEnum;
import com.wjb.newmall.pojo.User;
import com.wjb.newmall.service.UserService;
import com.wjb.newmall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public ResponseVo register(User user) {
        //username不能重复
        int countUsername = userMapper.countByUsername(user.getUsername());
        if (countUsername > 0) {
            return ResponseVo.error(ResponseEnum.USERNAME_EXIST);
        }

        //email不能重复
        int countByEamil = userMapper.countByEmail(user.getEmail());
        if (countByEamil > 0) {
            return ResponseVo.error(ResponseEnum.EMAIL_EXIST);
        }

        //MD5摘要算法(spring自带)
        //Charset.forName("utf-8")规定格式
        //DigestUtils.md5Digest(user.getPassword().getBytes(Charset.forName("utf-8")));
        //使用自带的规定格式，返回byts[]要使用md5DigestAsHex摘要算法
        String afterMd5 = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(afterMd5);

        //写入数据库
        int count = userMapper.insertSelective(user);
        if (count == 0) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success();
    }

    @Override
    public ResponseVo login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            //用户不存在
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        //用户名或密码错误
        //忽略大小写比较
        if(!user.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets
        .UTF_8)))){
            //密码错误
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        user.setPassword("");
        return ResponseVo.success(user);
    }
}
