package com.pt.zh.yuanfang.modules.sys.service.impl;


import com.pt.zh.yuanfang.common.config.ConstantConfig;
import com.pt.zh.yuanfang.common.page.MybatisPageHelper;
import com.pt.zh.yuanfang.common.page.PageRequest;
import com.pt.zh.yuanfang.common.page.PageResult;
import com.pt.zh.yuanfang.modules.sys.entity.SysMenu;
import com.pt.zh.yuanfang.modules.sys.mapper.SysMenuMapper;
import com.pt.zh.yuanfang.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public int save(SysMenu record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysMenuMapper.insertSelective(record);
		}
		if(record.getParentId() == null) {
			record.setParentId(0);
		}
		return sysMenuMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(Integer id) {
		return sysMenuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int delete(SysMenu record) {
		return sysMenuMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysMenu> records) {
		for(SysMenu record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public int update(SysMenu record) {
		return sysMenuMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysMenu findById(Integer id) {
		return sysMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, sysMenuMapper);
	}

	@Override
	public List<SysMenu> findTree(Integer userId, int menuType) {
		List<SysMenu> sysMenus = new ArrayList<>();
		List<SysMenu> menus = findByUser(userId);
		for (SysMenu menu : menus) {
			if (menu.getParentId() == null || menu.getParentId() == 0) {
				menu.setLevel(0);
				if(!exists(sysMenus, menu)) {
					sysMenus.add(menu);
				}
			}
		}
		sysMenus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
		findChildren(sysMenus, menus, menuType);
		return sysMenus;
	}

	@Override
	public List<SysMenu> findByUser(Integer userId) {
		if(userId == null || "".equals(userId)) {
			return sysMenuMapper.findAll();
		}
		return sysMenuMapper.findByUserId(userId);
	}

	private void findChildren(List<SysMenu> SysMenus, List<SysMenu> menus, int menuType) {
		for (SysMenu SysMenu : SysMenus) {
			List<SysMenu> children = new ArrayList<>();
			for (SysMenu menu : menus) {
				if(menuType == 1 && menu.getType() == 2) {
					// 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
					continue ;
				}
				if (SysMenu.getId() != null && SysMenu.getId().equals(menu.getParentId())) {
					menu.setParentName(SysMenu.getName());
					menu.setLevel(SysMenu.getLevel() + 1);
					if(!exists(children, menu)) {
						children.add(menu);
					}
				}
			}
			SysMenu.setChildren(children);
			children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
			findChildren(children, menus, menuType);
		}
	}

	private boolean exists(List<SysMenu> sysMenus, SysMenu sysMenu) {
		boolean exist = false;
		for(SysMenu menu:sysMenus) {
			if(menu.getId().equals(sysMenu.getId())) {
				exist = true;
			}
		}
		return exist;
	}

}
