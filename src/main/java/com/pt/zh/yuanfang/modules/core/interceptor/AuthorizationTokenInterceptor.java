package com.pt.zh.yuanfang.modules.core.interceptor;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pt.zh.yuanfang.common.config.ConstantConfig;
import com.pt.zh.yuanfang.modules.core.annotations.AuthToken;
import com.pt.zh.yuanfang.modules.core.annotations.PassToken;
import com.pt.zh.yuanfang.modules.core.service.ITokenUtil;
import com.pt.zh.yuanfang.modules.sys.entity.SysUser;
import com.pt.zh.yuanfang.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * token认证 拦截器
 */
public class AuthorizationTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ITokenUtil tokenService;
    @Autowired
    private SysUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("authorizationtoken");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class) || method.getDeclaringClass().isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        //if (method.isAnnotationPresent(AuthToken.class) || method.getDeclaringClass().isAnnotationPresent(AuthToken.class)) {
        // 执行认证
        if (token == null) {
            sendError(httpServletResponse);
        }
        //检查服务端 redis保存的 token --> id
        Integer userId = (Integer) redisTemplate.opsForValue().get(ConstantConfig.SY_TOKEN_LOGIN_ID + token);
        if (userId == null) {
            sendError(httpServletResponse);
        }
        //redis id获取user
        SysUser user = userService.findById(userId);
        if (null == user) {
            sendError(httpServletResponse);
        }
        //获取redis保存的时间戳
        Long timestamp = (Long) redisTemplate.opsForValue().get(ConstantConfig.SY_TOKEN__LOGIN_TIME + userId);
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getId() + ConstantConfig.TOKEN_SECRET + timestamp)).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            sendError(httpServletResponse);
        }
        //token续费
        tokenService.updateToken(user, token, timestamp);
        //user放入到httprequest中
        httpServletRequest.setAttribute("sysUser", user);
        return true;

        //}
        // return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
    private static void sendError(HttpServletResponse response) throws IOException {
//        response.addHeader("Access-Control-Allow-Origin","http://loaclhost:8081");
//        response.addHeader("Access-Control-Methods","*");
//        response.addHeader("Access-Control-Max-Age","1000");
//        response.addHeader("Access-Control-Allow-Headers","Content-Type");
//        response.addHeader("Access-Control-Allow-Credentials","true");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"请重新登录!");
    }
}
