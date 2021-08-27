package com.wjb.newmall.from;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterForm {
    //判空
    //@NotEmpty//用于集合
    //@NotBlank//用于String判断空格
    //@NotNull//判断是否等于null
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "邮箱不能为空")
    private String email;

}
