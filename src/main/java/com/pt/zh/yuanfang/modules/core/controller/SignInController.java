package com.pt.zh.yuanfang.modules.core.controller;

import com.pt.zh.yuanfang.common.bean.ResponseCode;
import com.pt.zh.yuanfang.common.bean.ResponseResult;
import com.pt.zh.yuanfang.modules.core.annotations.PassToken;
import com.pt.zh.yuanfang.modules.core.entity.SignIn;
import com.pt.zh.yuanfang.modules.core.service.ITokenUtil;
import com.pt.zh.yuanfang.modules.sys.entity.SysUser;
import com.pt.zh.yuanfang.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ITokenUtil tokenUtil;

    @PostMapping("/signIn")
    @PassToken
    public ResponseResult signIn(@RequestBody SignIn signIn) {
        SysUser user = sysUserService.singIn(signIn);
        if (null != user) {
            String token = tokenUtil.getToken(user);
            return ResponseResult.e(ResponseCode.SIGN_IN_OK, token);
        } else {
            return ResponseResult.e(ResponseCode.SIGN_IN_INPUT_FAIL);
        }
    }


}
