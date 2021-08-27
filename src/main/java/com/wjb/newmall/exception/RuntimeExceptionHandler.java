package com.wjb.newmall.exception;

import com.wjb.newmall.enums.ResponseEnum;
import com.wjb.newmall.vo.ResponseVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
//该注解用来处理异常
public class RuntimeExceptionHandler {

    @ExceptionHandler(RuntimeException.class)//碰到该异常进行拦截
    @ResponseBody//变成Json格式
//    @ResponseStatus(HttpStatus.ACCEPTED)默认的错误返回状态
    public ResponseVo handle(RuntimeException e) {
        return ResponseVo.error(ResponseEnum.ERROR, e.getMessage());
    }

    @ExceptionHandler(UserLoginException.class)//碰到UserLogin异常进行拦截
    @ResponseBody
    public ResponseVo userLoginHandle() {
        return ResponseVo.error(ResponseEnum.NEED_LOGIN);
    }
}
