package com.pt.zh.yuanfang.modules.sys.controller;


import com.pt.zh.yuanfang.common.bean.ResponseCode;
import com.pt.zh.yuanfang.common.bean.ResponseResult;
import com.pt.zh.yuanfang.common.config.ConstantConfig;
import com.pt.zh.yuanfang.common.page.PageRequest;
import com.pt.zh.yuanfang.common.utils.PasswordUtils;
import com.pt.zh.yuanfang.common.utils.SecurityUtils;
import com.pt.zh.yuanfang.modules.sys.entity.SysUser;
import com.pt.zh.yuanfang.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @GetMapping("/info")
    public ResponseResult getUserInfo(){
        SysUser user = SecurityUtils.getUser();
        return ResponseResult.e(ResponseCode.OK,user);
    }


    @PostMapping(value="/save")
    public ResponseResult save(@RequestBody SysUser record) {
        SysUser user = sysUserService.findById(record.getId());
        if(user != null) {
            if(ConstantConfig.ADMIN.equalsIgnoreCase(user.getName())) {
                return ResponseResult.error("超级管理员不允许修改!");
            }
        }
        if(record.getPassword() != null) {
            String salt = PasswordUtils.getSalt();
            if(user == null) {
                // 新增用户
                if(sysUserService.findByName(record.getName()) != null) {
                    return ResponseResult.error("用户名已存在!");
                }
                //新用户密码默认123456
                record.setPassword("123456");
                String password = PasswordUtils.encode(record.getPassword(), salt);
                record.setSalt(salt);
                record.setPassword(password);
            }
        }
        return ResponseResult.e(ResponseCode.OK,sysUserService.save(record));
    }

    @PostMapping(value="/delete")
    public ResponseResult delete(@RequestBody List<SysUser> records) {
        for(SysUser record:records) {
            SysUser sysUser = sysUserService.findById(record.getId());
            if(sysUser != null && ConstantConfig.ADMIN.equalsIgnoreCase(sysUser.getName())) {
                return ResponseResult.error("超级管理员不允许删除!");
            }
        }
        return ResponseResult.e(ResponseCode.OK,sysUserService.delete(records));
    }


    @GetMapping(value="/findByName")
    public ResponseResult findByUserName(@RequestParam String name) {
        return ResponseResult.e(ResponseCode.OK,sysUserService.findByName(name));
    }


    @GetMapping(value="/findPermissions")
    public ResponseResult findPermissions() {
        SysUser user = SecurityUtils.getUser();
        return ResponseResult.e(ResponseCode.OK,sysUserService.findPermissions(user.getId()));
    }


    @GetMapping(value="/findUserRoles")
    public ResponseResult findUserRoles( Integer userId) {
        return ResponseResult.e(ResponseCode.OK,sysUserService.findUserRoles(userId));
    }


    @PostMapping(value="/findPage")
    public ResponseResult findPage(@RequestBody PageRequest pageRequest) {
        return ResponseResult.e(ResponseCode.OK,sysUserService.findPage(pageRequest));
    }

}
