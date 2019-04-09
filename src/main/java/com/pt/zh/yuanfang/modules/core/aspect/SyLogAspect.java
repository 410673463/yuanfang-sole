package com.pt.zh.yuanfang.modules.core.aspect;


import com.alibaba.fastjson.JSON;
import com.pt.zh.yuanfang.common.utils.IPUtils;
import com.pt.zh.yuanfang.modules.sys.entity.SysLog;
import com.pt.zh.yuanfang.modules.sys.entity.SysUser;
import com.pt.zh.yuanfang.modules.sys.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Licoy
 * @version 2018/4/27/17:19
 */
@Aspect
@Component
public class SyLogAspect {
    @Autowired
    private SysLogService sysLogService;


    @Pointcut("@annotation( com.pt.zh.yuanfang.modules.core.annotations.SyLog)")
    public void log() {
    }

    @AfterReturning("log()")
    public void after(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        SysLog sysSyLog = new SysLog();
        //获取动作Action释义
        sysSyLog.setActionName(getMethodSysLogsAnnotationValue(joinPoint));
        //获取IP
        sysSyLog.setIp(IPUtils.getIpAddr(request));
        sysSyLog.setAjax(IPUtils.ajax(request) ? 1 : 0);
        sysSyLog.setUri(request.getRequestURI());
        String s = this.paramFilter(joinPoint.getArgs());
        //根据系统需求自定义
        sysSyLog.setParams(s.length() > 500 ? "数据过大，不给予记录" : s);
        sysSyLog.setHttpMethod(request.getMethod());
        sysSyLog.setClassMethod(joinPoint.getSignature().getDeclaringTypeName()
                + "." + joinPoint.getSignature().getName() + "()");
        //判断身份是否为空
        SysUser user = (SysUser) request.getAttribute("sysUser");
        if (user != null) {
            sysSyLog.setUsername(user.getName());
            sysSyLog.setUid(user.getId().toString());
        } else {
            sysSyLog.setUsername("游客");
            sysSyLog.setUid("0");
        }
        sysSyLog.setCreateDate(new Date());
        sysLogService.save(sysSyLog);
    }

    private String getMethodSysLogsAnnotationValue(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(com.pt.zh.yuanfang.modules.core.annotations.SyLog.class)) {
            //获取方法上注解中表明的权限
            com.pt.zh.yuanfang.modules.core.annotations.SyLog sysLogs = method.getAnnotation(com.pt.zh.yuanfang.modules.core.annotations.SyLog.class);
            return sysLogs.value();
        }
        return "未知";
    }

    private String paramFilter(Object[] params) {
        //判断是否含有密码敏感数据
        final String filterString = "******";
        if (params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof SysUser) {
                    SysUser user = (SysUser) params[i];
                    //SignInDTO sign = (SignInDTO) params[i];
                    user.setPassword(filterString);
                    params[i] = user;
                }
            }
        }
        return JSON.toJSONString(params);
    }


}
