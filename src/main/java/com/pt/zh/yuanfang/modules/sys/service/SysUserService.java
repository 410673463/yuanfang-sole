package com.pt.zh.yuanfang.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.pt.zh.yuanfang.common.service.CurdService;
import com.pt.zh.yuanfang.modules.core.entity.SignIn;
import com.pt.zh.yuanfang.modules.sys.entity.SysUser;
import com.pt.zh.yuanfang.modules.sys.entity.SysUserRole;

import java.util.List;
import java.util.Set;

/**
 * 用户管理
 * @author Louis
 * @date Oct 29, 2018
 */
public interface SysUserService extends CurdService<SysUser> {

	SysUser findByName(String username);

	/**
	 * 查找用户的菜单权限标识集合
	 * @param userName
	 * @return
	 */
	Set<String> findPermissions(String userName);

	/**
	 * 查找用户的角色集合
	 * @param userId
	 * @return
	 */
	List<SysUserRole> findUserRoles(Integer userId);


    SysUser singIn(SignIn signIn);

}
