package com.pt.zh.yuanfang.modules.sys.controller;

import com.pt.zh.yuanfang.common.bean.ResponseCode;
import com.pt.zh.yuanfang.common.bean.ResponseResult;
import com.pt.zh.yuanfang.common.utils.SecurityUtils;
import com.pt.zh.yuanfang.modules.core.annotations.AuthToken;
import com.pt.zh.yuanfang.modules.core.annotations.PassToken;
import com.pt.zh.yuanfang.modules.sys.entity.SysMenu;
import com.pt.zh.yuanfang.modules.sys.entity.SysUser;
import com.pt.zh.yuanfang.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 菜单控制器
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("/menu")
@AuthToken
public class SysMenuController {

	@Autowired
	private SysMenuService sysMenuService;
	

	@PostMapping(value="/save")
	@AuthToken
	public ResponseResult save(@RequestBody  SysMenu record) {
		return ResponseResult.e(ResponseCode.OK,sysMenuService.save(record));
	}
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		//转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
	}

	@PostMapping(value="/delete")
	public ResponseResult delete(@RequestBody List<SysMenu> records){
		sysMenuService.delete(records);
		return ResponseResult.e(ResponseCode.OK);
	}

	@GetMapping(value="/findNavTree")
	public ResponseResult findNavTree() {
		SysUser user = SecurityUtils.getUser();
		return ResponseResult.e(ResponseCode.OK,(sysMenuService.findTree(user.getId(), 1)));
	}
	

	@GetMapping(value="/findMenuTree")
	public ResponseResult findMenuTree() {
		return ResponseResult.e(ResponseCode.OK,sysMenuService.findTree(null, 0));
	}
}
