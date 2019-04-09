package com.pt.zh.yuanfang.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.pt.zh.yuanfang.common.service.CurdService;
import com.pt.zh.yuanfang.modules.sys.entity.SysMenu;
import com.pt.zh.yuanfang.modules.sys.entity.SysRole;
import com.pt.zh.yuanfang.modules.sys.entity.SysRoleMenu;

import java.util.List;

/**
 * 角色管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysRoleService extends CurdService<SysRole> {

	/**
	 * 查询全部
	 * @return
	 */
	List<SysRole> findAll();

	/**
	 * 查询角色菜单集合
	 * @return
	 */
	List<SysMenu> findRoleMenus(Integer roleId);

	/**
	 * 保存角色菜单
	 * @param records
	 * @return
	 */
	int saveRoleMenus(List<SysRoleMenu> records);

	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	List<SysRole> findByName(String name);

}
