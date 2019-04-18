package com.pt.zh.yuanfang.modules.sys.service;

import com.pt.zh.yuanfang.common.service.CurdService;
import com.pt.zh.yuanfang.modules.sys.entity.SysDept;

import java.util.List;

/**
 * 机构管理
 *
 * @date Oct 29, 2018
 */
public interface SysDeptService extends CurdService<SysDept> {

	/**
	 * 查询机构树
	 * @param userId 
	 * @return
	 */
	List<SysDept> findTree();

}
