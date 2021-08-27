package com.wjb.newmall;

import com.wjb.newmall.constant.Constant;
import com.wjb.newmall.exception.UserLoginException;
import com.wjb.newmall.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {

    //在请求之前拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //返回值要特别注意true表示继续，false表示中断
        log.info("preHandle...");
        HttpSession session = request.getSession();
        User current_user = (User)session.getAttribute(Constant.CURRENT_USER);
        //用户等于null
        if(current_user==null){
            log.info("user==null");
            throw new UserLoginException();
//            return false;
//            return ResponseVo.error(ResponseEnum.NEED_LOGIN);
        }
        return true;
    }

}
