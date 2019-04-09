package com.pt.zh.yuanfang.modules.sys.controller;


import com.github.pagehelper.PageInfo;
import com.pt.zh.yuanfang.common.bean.ResponseCode;
import com.pt.zh.yuanfang.common.bean.ResponseResult;
import com.pt.zh.yuanfang.common.config.ConstantConfig;
import com.pt.zh.yuanfang.common.page.PageRequest;
import com.pt.zh.yuanfang.common.utils.PasswordUtils;
import com.pt.zh.yuanfang.modules.sys.entity.SysUser;
import com.pt.zh.yuanfang.modules.sys.entity.SysUserRole;
import com.pt.zh.yuanfang.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @GetMapping("/info")
    public ResponseResult getUserInfo(){
        Map<String,String> map = new HashMap<>();
        map.put("roles","admin");
        map.put("introduction","我是超级管理员");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin");
        return ResponseResult.e(ResponseCode.OK,map);
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
            } /*else {
                // 修改用户, 且修改了密码
                if(!record.getPassword().equals(user.getPassword())) {
                    String password = PasswordUtils.encode(record.getPassword(), salt);
                    record.setSalt(salt);
                    record.setPassword(password);
                }
            }*/
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
    public ResponseResult findPermissions(@RequestParam String name) {
        return ResponseResult.e(ResponseCode.OK,sysUserService.findPermissions(name));
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
