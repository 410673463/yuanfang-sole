package com.pt.zh.yuanfang.modules.core.entity;


import lombok.Data;

@Data
public class SignIn {
    private String account;
    private String password;
    private String captcha;
}
