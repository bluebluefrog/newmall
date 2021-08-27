package com.wjb.newmall.controller;

import com.wjb.newmall.constant.Constant;
import com.wjb.newmall.enums.ResponseEnum;
import com.wjb.newmall.enums.RoleEnum;
import com.wjb.newmall.from.UserLoginForm;
import com.wjb.newmall.from.UserRegisterForm;
import com.wjb.newmall.pojo.User;
import com.wjb.newmall.service.UserService;
import com.wjb.newmall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseVo register(@Valid @RequestBody UserRegisterForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //拿到注解上的信息
            log.info("注册提交的参数有误,{}{}",bindingResult.getFieldError().getField(), bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(ResponseEnum.PARAM_ERROR,bindingResult);
        }

        User user = new User();
        //Spring中对象之间的拷贝方法
        BeanUtils.copyProperties(userForm, user);
        user.setRole(RoleEnum.CUSTOMER.getCode());
        return userService.register(user);
    }

    @PostMapping("/user/login")
    private ResponseVo login(@Valid @RequestBody UserLoginForm userLoginForm, BindingResult bindingResult,HttpSession session) {
        if (bindingResult.hasErrors()) {
            //拿到注解上的信息
            log.info("登录提交的参数有误,{}{}",bindingResult.getFieldError().getField(), bindingResult.getFieldError().getDefaultMessage());
            return ResponseVo.error(ResponseEnum.PARAM_ERROR,bindingResult);
        }
        ResponseVo userResponseVo = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword());

        //设置session
       session.setAttribute(Constant.CURRENT_USER,userResponseVo.getData());

        return userResponseVo;
    }

    @GetMapping("/user")
    public ResponseVo<User> userInfo(HttpSession session) {
        log.info("/user sessionId={}", session.getId());
        User current_user = (User)session.getAttribute(Constant.CURRENT_USER);
//        if(current_user==null){
//            return ResponseVo.error(ResponseEnum.NEED_LOGIN);
//        }

        return ResponseVo.success(current_user);
    }

    @GetMapping("/user/logout")
    public ResponseVo logout(HttpSession session) {
        log.info("/user/logout sessionId={}", session.getId());
//        User current_user = (User)session.getAttribute(Constant.CURRENT_USER);
//        if(current_user==null){
//            return ResponseVo.error(ResponseEnum.NEED_LOGIN);
//        }
        session.removeAttribute(Constant.CURRENT_USER);
        return ResponseVo.success();
    }

}
