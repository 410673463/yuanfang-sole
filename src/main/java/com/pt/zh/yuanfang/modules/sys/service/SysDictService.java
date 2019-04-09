package com.pt.zh.yuanfang.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.pt.zh.yuanfang.common.service.CurdService;
import com.pt.zh.yuanfang.modules.sys.entity.SysDict;

import java.util.List;

/**
 * 字典管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysDictService extends CurdService<SysDict> {

	/**
	 * 根据名称查询
	 * @param lable
	 * @return
	 */
	List<SysDict> findByLable(String lable);
}
