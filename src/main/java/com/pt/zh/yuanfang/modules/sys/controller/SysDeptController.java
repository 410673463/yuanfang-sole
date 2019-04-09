package com.pt.zh.yuanfang.modules.sys.controller;

import com.alibaba.fastjson.JSON;
import com.pt.zh.yuanfang.common.bean.ResponseCode;
import com.pt.zh.yuanfang.common.bean.ResponseResult;
import com.pt.zh.yuanfang.modules.sys.entity.SysDept;
import com.pt.zh.yuanfang.modules.sys.service.SysDeptService;
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
 * 机构控制器
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("/dept")
public class SysDeptController {

	@Autowired
	private SysDeptService sysDeptService;
	

	@PostMapping(value="/save")
	public ResponseResult save( @RequestBody SysDept record) {
		return ResponseResult.e(ResponseCode.OK,sysDeptService.save(record));
	}
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		//转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
	}


	@PostMapping(value="/delete")
	public ResponseResult delete(@RequestBody List<SysDept> records) {
		sysDeptService.delete(records);
		return ResponseResult.e(ResponseCode.OK);
	}


	@GetMapping(value="/findTree")
	public ResponseResult findTree() {
		return ResponseResult.e(ResponseCode.OK,sysDeptService.findTree());
	}

}
