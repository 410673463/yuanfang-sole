package com.pt.zh.yuanfang.modules.core.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pt.zh.yuanfang.common.config.ConstantConfig;
import com.pt.zh.yuanfang.modules.core.service.ITokenUtil;
import com.pt.zh.yuanfang.modules.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;


@Component
public class TokenUntil implements ITokenUtil {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String getToken(SysUser user) {
        long loginTime = new Date().getTime();
        String token = "";
        /*token= JWT.create().withAudience(user.getId().toString())
                .sign(Algorithm.HMAC256(user.getCode()));*/
        token = JWT.create()
                // payload  载荷就是存放有效信息的地方

                .withIssuer("ADMIN-SOLE")
                //.withAudience()
                .withJWTId("jti")
                .withClaim("timestamp", new Date())
                // .withIssuedAt(iatDate) // sign time
                // .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(user.getId() + ConstantConfig.TOKEN_SECRET + loginTime)); // signature


        //reids保存token -->   id
        redisTemplate.opsForValue().set(ConstantConfig.SY_TOKEN_LOGIN_ID + token, user.getId(), ConstantConfig.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        //id --> time
        redisTemplate.opsForValue().set(ConstantConfig.SY_TOKEN__LOGIN_TIME + user.getId().toString(), loginTime, ConstantConfig.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);


        return token;
    }

    @Override
    public void updateToken(SysUser user, String oldToken, long tokeBirthTime) {
        Long diff = System.currentTimeMillis() - tokeBirthTime;
        if (diff > ConstantConfig.TOKEN_RESET_TIME) {
           //token续期
        }
    }
}
