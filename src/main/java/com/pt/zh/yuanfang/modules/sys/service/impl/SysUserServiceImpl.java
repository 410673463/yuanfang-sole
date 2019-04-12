package com.pt.zh.yuanfang.modules.sys.service.impl;

import com.pt.zh.yuanfang.common.page.ColumnFilter;
import com.pt.zh.yuanfang.common.page.MybatisPageHelper;
import com.pt.zh.yuanfang.common.page.PageRequest;
import com.pt.zh.yuanfang.common.page.PageResult;
import com.pt.zh.yuanfang.modules.core.entity.SignIn;
import com.pt.zh.yuanfang.modules.sys.entity.*;
import com.pt.zh.yuanfang.modules.sys.mapper.SysDeptMapper;
import com.pt.zh.yuanfang.modules.sys.mapper.SysRoleMapper;
import com.pt.zh.yuanfang.modules.sys.mapper.SysUserMapper;
import com.pt.zh.yuanfang.modules.sys.mapper.SysUserRoleMapper;
import com.pt.zh.yuanfang.modules.sys.service.SysMenuService;
import com.pt.zh.yuanfang.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysDeptMapper sysdeptMapper;

    @Transactional
    @Override
    public int save(SysUser record) {
        Integer id = null;
        if(record.getId() == null || record.getId() == 0) {
            // 新增用户
            record.setId(null);
            sysUserMapper.insertSelective(record);
            id = record.getId();
        } else {
            // 更新用户信息
            sysUserMapper.updateByPrimaryKeySelective(record);
        }
        // 更新用户角色
        if(id != null) {
            for(SysUserRole sysUserRole:record.getUserRoles()) {
                sysUserRole.setUserId(id);
            }
        } else {
            sysUserRoleMapper.deleteByUserId(record.getId());
        }
        for(SysUserRole sysUserRole:record.getUserRoles()) {
            sysUserRoleMapper.insertSelective(sysUserRole);
        }
        if(id != null && id == 0) {
            return 1;
        }
        return 1;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public int delete(SysUser record) {
        return sysUserMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysUser> records) {
        for(SysUser record:records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public int update(SysUser record) {
        return 0;
    }

    @Override
    @Cacheable(value = "SYS:USER:ID",key = "#id")
    public SysUser findById(Integer id) {
        SysUser user =  sysUserMapper.getByIdWithoutPass(id);
        SysDept dept = sysdeptMapper.selectByPrimaryKey(user.getDeptId());
        user.setDept(dept);
        user.setDeptName(dept.getName());
        List<SysUserRole> userRoles = findUserRoles(user.getId());
        user.setUserRoles(userRoles);
        user.setRoleNames(getRoleNames(userRoles));
        return user;
    }

    @Override
    public SysUser findByName(String name) {
        return sysUserMapper.findByName(name);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult pageResult = null;
        String name = getColumnFilterValue(pageRequest, "name");
        String email = getColumnFilterValue(pageRequest, "email");
        if(name != null && !"".equals(name)) {
            if(email != null && !"".equals(email)) {
                pageResult = MybatisPageHelper.findPage(pageRequest, sysUserMapper, "findPageByNameAndEmail", name, email);
            } else {
                pageResult = MybatisPageHelper.findPage(pageRequest, sysUserMapper, "findPageByName", name);
            }
        } else {
            pageResult = MybatisPageHelper.findPage(pageRequest, sysUserMapper);
        }
        // 加载用户角色信息
        findUserRoles(pageResult);
        return pageResult;
    }

    /**
     * 获取过滤字段的值
     * @param filterName
     * @return
     */
    public String getColumnFilterValue(PageRequest pageRequest, String filterName) {
        String value = null;
        ColumnFilter columnFilter = pageRequest.getColumnFilter(filterName);
        if(columnFilter != null) {
            value = columnFilter.getValue();
        }
        return value;
    }

    /**
     * 加载用户角色
     * @param pageResult
     */
    private void findUserRoles(PageResult pageResult) {
        List<?> content = pageResult.getContent();
        for(Object object:content) {
            SysUser sysUser = (SysUser) object;
            List<SysUserRole> userRoles = findUserRoles(sysUser.getId());
            sysUser.setUserRoles(userRoles);
            sysUser.setRoleNames(getRoleNames(userRoles));
        }
    }

    private String getRoleNames(List<SysUserRole> userRoles) {
        StringBuilder sb = new StringBuilder();
        for(Iterator<SysUserRole> iter=userRoles.iterator(); iter.hasNext();) {
            SysUserRole userRole = iter.next();
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(userRole.getRoleId());
            if(sysRole == null) {
                continue ;
            }
            sb.append(sysRole.getRemark());
            if(iter.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    public Set<String> findPermissions(Integer userId) {
        Set<String> perms = new HashSet<>();
        List<SysMenu> sysMenus = sysMenuService.findByUser(userId);
        for(SysMenu sysMenu:sysMenus) {
            if(sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms())) {
                perms.add(sysMenu.getPerms());
            }
        }
        return perms;
    }

    @Override
    public List<SysUserRole> findUserRoles(Integer userId) {
        return sysUserRoleMapper.findUserRoles(userId);
    }

    @Override
    public SysUser singIn(SignIn signIn) {
        return sysUserMapper.singIn(signIn.getAccount(),signIn.getPassword());
    }
}
