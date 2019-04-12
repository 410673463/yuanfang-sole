package com.pt.zh.yuanfang.common.config;


/**
 * 系统常量
 */
public interface ConstantConfig {
    /**
     * 系统管理员用户名
     */
    String ADMIN = "SUPERADMIN";
    /**
     * token SECRET
     */
   String TOKEN_SECRET = "不能说的秘密";
    /**
     * redis存储token设置的过期时间
     * 单位秒 2小时
     */
   int TOKEN_EXPIRE_TIME = 60 * 60 * 2;

    /**
     * 设置可以重置token过期时间的时间界限
     * TOKEN 使用超过1小时，更新token过期时间 2小时
     */
   int TOKEN_RESET_TIME =60*60*1*1000;

    /**
     * token对应的userID
     */
   String SY_TOKEN_LOGIN_ID = "SY:TOKEN:TOKEN-ID:";

    /**
     * userId对应的登录时间戳
     */
   String SY_TOKEN__LOGIN_TIME = "SY:TOKEN:TOKEN-TIME:";


    String SY_USER = "SY:USER:";
}
