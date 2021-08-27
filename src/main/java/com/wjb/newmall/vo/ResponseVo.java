package com.wjb.newmall.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wjb.newmall.enums.ResponseEnum;
import lombok.Data;
import org.springframework.validation.BindingResult;

import java.util.Objects;

@Data
//@JsonSerialize(include = )
//上面是旧版本
@JsonInclude(value = JsonInclude.Include.NON_NULL)
//帮助data返回的数据序列化
//Json序列化返回的数据中数据不能为空,如果为空返回时不显示
public class ResponseVo<E> {

    private Integer status;

    private String msg;

    private E data;

    //一般构造器
    private ResponseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    //带数据的构造器
    private ResponseVo(Integer status, E data) {
        this.status = status;
        this.data = data;
    }

    //成功构造器方法
    public static <E>ResponseVo successByMsg(String msg) {
        return new ResponseVo<E>(ResponseEnum.SUCCESS.getCode(), msg);
    }

    //成功构造器方法
    public static <E>ResponseVo success(E data) {
        return new ResponseVo<E>(ResponseEnum.SUCCESS.getCode(), data);
    }

    //默认成功构造器方法
    public static <E>ResponseVo success() {
        return new ResponseVo<E>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMsg());
    }

    //失败构造器方法使用Enum
    public static <E>ResponseVo error(ResponseEnum responseEnum) {
        return new ResponseVo<E>(responseEnum.getCode(), responseEnum.getMsg());
    }

    //失败构造器方法使用enum和自定义msg
    public static <E>ResponseVo error(ResponseEnum responseEnum,String msg) {
        return new ResponseVo<E>(responseEnum.getCode(), msg);
    }

    //BindingResult失败构造器方法
    public static <E>ResponseVo error(ResponseEnum responseEnum, BindingResult bindingResult) {
        return new ResponseVo<E>(responseEnum.getCode(),
                //需要不为空
                Objects.requireNonNull(bindingResult.getFieldError().getField())
                        +""
                        +bindingResult.getFieldError().getDefaultMessage());
    }

}
