package com.pt.zh.yuanfang.common.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Licoy
 * @version 2018/4/18/10:54
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseCode {

    OK(200,"操作成功"),
    SIGN_IN_OK(200,"登录成功"),
    LOGOUT_OK(200,"注销登录成功"),
    SIGN_IN_INPUT_FAIL(-401,"账号或密码错误"),
    SIGN_IN_FAIL(-500,"登录失败"),
    FAIL(-1,"请求失败"),
    LOGOUT_FAIL(-2,"注销登录失败"),
    SING_IN_INPUT_EMPTY(-5,"账户和密码均不能为空"),
    NOT_SING_IN(-6,"用户未登录或身份异常");



    public Integer code;
    public String msg;

}
