package com.pt.zh.yuanfang.modules.sys.controller;

import com.pt.zh.yuanfang.common.bean.ResponseCode;
import com.pt.zh.yuanfang.common.bean.ResponseResult;
import com.pt.zh.yuanfang.common.config.ConstantConfig;
import com.pt.zh.yuanfang.common.page.PageRequest;
import com.pt.zh.yuanfang.modules.sys.entity.SysRole;
import com.pt.zh.yuanfang.modules.sys.entity.SysRoleMenu;
import com.pt.zh.yuanfang.modules.sys.mapper.SysRoleMapper;
import com.pt.zh.yuanfang.modules.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制器
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("role")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@PostMapping(value="/save")
	public ResponseResult save(@RequestBody SysRole record) {
		SysRole role = sysRoleService.findById(record.getId());
		if(role != null) {
			if(ConstantConfig.ADMIN.equalsIgnoreCase(role.getName())) {
				return ResponseResult.error("超级管理员不允许修改!");
			}
		}
		// 新增角色
		if((record.getId() == null || record.getId() ==0) && !sysRoleService.findByName(record.getName()).isEmpty()) {
			return ResponseResult.error("角色名已存在!");
		}
		return ResponseResult.e(ResponseCode.OK,sysRoleService.save(record));
	}


	@PostMapping(value="/delete")
	public ResponseResult delete(@RequestBody  List<SysRole> records) {
		return ResponseResult.e(ResponseCode.OK,sysRoleService.delete(records));
	}


	@PostMapping(value="/findPage")
	public ResponseResult findPage(@RequestBody PageRequest pageRequest) {
		return ResponseResult.e(ResponseCode.OK,sysRoleService.findPage(pageRequest));
	}
	

	@GetMapping(value="/findAll")
	public ResponseResult findAll() {
		return ResponseResult.e(ResponseCode.OK,sysRoleService.findAll());
	}
	

	@GetMapping(value="/findRoleMenus")
	public ResponseResult findRoleMenus(@RequestParam  Integer roleId) {
		return ResponseResult.e(ResponseCode.OK,sysRoleService.findRoleMenus(roleId));
	}
	

	@PostMapping(value="/saveRoleMenus")
	public ResponseResult saveRoleMenus( @RequestBody List<SysRoleMenu> records) {
		for(SysRoleMenu record:records) {
			SysRole sysRole = sysRoleMapper.selectByPrimaryKey(record.getRoleId());
			if(ConstantConfig.ADMIN.equalsIgnoreCase(sysRole.getName())) {
				// 如果是超级管理员，不允许修改
				return ResponseResult.error("超级管理员拥有所有菜单权限，不允许修改！");
			}
		}
		return ResponseResult.e(ResponseCode.OK,sysRoleService.saveRoleMenus(records));
	}
}
